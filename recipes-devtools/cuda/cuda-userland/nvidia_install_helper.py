#!/usr/bin/env python3

import argparse
import filecmp
import hashlib
import json
import os
import shutil
import subprocess
from pathlib import Path


FEATURES = (
    "compute", "systemd", "x11", "wayland", "vulkan", "opengl",
    "glesv1", "glesv2", "opencl", "video", "firmware", "tools", "html-docs",
)

# These are generic dispatch/loader libraries managed by the distribution.
GLVND_LOADERS = (
    "libGL.so", "libEGL.so", "libGLESv1_CM.so", "libGLESv2.so",
    "libGLX.so", "libGLdispatch.so", "libOpenGL.so", "libOpenCL.so",
)

# Driver private libraries are assigned to one feature, even when used by several.
LIBRARIES = {
    "compute": (
        "libcuda.so", "libcudadebugger.so", "libnvidia-ml.so",
        "libnvidia-cfg.so", "libnvidia-ptxjitcompiler.so",
        "libnvidia-nvvm", "libnvidia-allocator.so",
        "libnvidia-sandboxutils.so", "libnvidia-tileiras.so",
        "libnvidia-api.so", "libnvidia-pkcs11", "libnvidia-ngx.so",
        "libnvoptix.so", "libnvidia-rtcore.so",
    ),
    "opencl": ("libnvidia-opencl.so",),
    "video": (
        "libnvcuvid.so", "libnvidia-encode.so", "libnvidia-fbc.so",
        "libnvidia-opticalflow.so", "libvdpau_nvidia.so",
    ),
    "wayland": (
        "libnvidia-egl-wayland", "libnvidia-egl-gbm", "libnvidia-egl-xcb",
        "libnvidia-egl-xlib", "libnvidia-wayland-client",
    ),
    "opengl": (
        "libEGL_nvidia.so", "libGLX_nvidia.so", "libnvidia-eglcore.so",
        "libnvidia-glcore.so", "libnvidia-glsi.so", "libnvidia-tls.so",
        "libnvidia-present.so",
    ),
    "glesv1": ("libGLESv1_CM_nvidia.so",),
    "glesv2": ("libGLESv2_nvidia.so",),
    "vulkan": (
        "libnvidia-glvkspirv.so", "libnvidia-gpucomp.so",
        "libnvidia-vksc-core.so",
    ),
    "x11": ("libglxserver_nvidia.so",),
    "tools": ("libnvidia-gtk2.so", "libnvidia-gtk3.so"),
}

APPLICATIONS = {
    "compute": ("nvidia-cuda-mps-control", "nvidia-cuda-mps-server"),
    "tools": (
        "nvidia-smi", "nvidia-settings", "nvidia-xconfig", "nvidia-modprobe",
        "nvidia-persistenced", "nvidia-bug-report.sh", "nvidia-debugdump",
        "nvidia-ngx-updater",
    ),
    "systemd": ("nvidia-powerd", "nvidia-sleep.sh"),
    "vulkan": ("nvidia-pcc",),
}

# These belong to an installer/build environment or a Windows runtime, not Linux.
EXCLUDED_FILES = {
    ".manifest", "makeself-help-script.sh", "makeself.sh", "mkprecompiled",
    "nvidia-installer", "nvidia-installer.1.gz",
    "nvidia-persistenced-init.tar.bz2", "_nvngx.dll", "nvngx.dll",
    "nvngx_dlssg.dll", "dlsnetparams.csv",
}

DOCUMENTS = (
    "LICENSE", "LICENSE.txt", "EULA.txt",
    "NVIDIA_Changelog", "README.txt", "pkg-history.txt",
)


def file_md5(path):
    digest = hashlib.md5()
    with path.open("rb") as stream:
        for chunk in iter(lambda: stream.read(1024 * 1024), b""):
            digest.update(chunk)
    return digest.hexdigest()


def selected_components():
    selected = list(dict.fromkeys(os.environ.get("CUDA_PACKAGES", "").split()))
    unknown = sorted(set(selected) - set(FEATURES))
    if unknown:
        raise RuntimeError(f"Unknown driver components: {', '.join(unknown)}")
    return selected


def copy_file(source, target, installed, stage_root):
    target.parent.mkdir(parents=True, exist_ok=True)

    if source.is_symlink():
        link = os.readlink(source)
        if target.is_symlink() and os.readlink(target) == link:
            pass
        elif target.exists() or target.is_symlink():
            raise RuntimeError(f"File collision: {target}")
        else:
            target.symlink_to(link)

    elif source.is_file():
        if target.exists() or target.is_symlink():
            if not target.is_file() or target.is_symlink() or not filecmp.cmp(source, target, shallow=False):
                raise RuntimeError(f"File collision: {target}")
        else:
            shutil.copy2(source, target)

    else:
        raise RuntimeError(f"Unsupported file type: {source}")

    installed.append(str(target.relative_to(stage_root)))


def copy_tree(source, target, installed, stage_root):
    if not source.is_dir():
        return

    for path in sorted(source.rglob("*")):
        relative = path.relative_to(source)
        output = target / relative

        if path.is_dir() and not path.is_symlink():
            output.mkdir(parents=True, exist_ok=True)
        else:
            copy_file(path, output, installed, stage_root)


def create_link(destination, target, installed, stage_root):
    destination.parent.mkdir(parents=True, exist_ok=True)

    if destination.is_symlink():
        if os.readlink(destination) != target:
            raise RuntimeError(f"Conflicting symlink: {destination}")
    elif destination.exists():
        raise RuntimeError(f"Destination already exists: {destination}")
    else:
        destination.symlink_to(target)

    installed.append(str(destination.relative_to(stage_root)))


def soname(path, readelf):
    result = subprocess.run([readelf, "-d", str(path)], capture_output=True, text=True)

    if result.returncode:
        return None

    for line in result.stdout.splitlines():
        if "(SONAME)" in line and "[" in line and "]" in line:
            return line.split("[", 1)[1].split("]", 1)[0]

    return None


def library_feature(filename):
    if any(filename.startswith(name) for name in GLVND_LOADERS):
        return None

    for feature, names in LIBRARIES.items():
        if any(filename.startswith(name) for name in names):
            return feature

    return None


def location(path, version):
    name = path.name

    # Preserve nested systemd destinations, including sleep hooks and drop-ins.
    if path.parts[0] == "systemd" and len(path.parts) > 1:
        if path.parts[1] in ("system", "system-sleep"):
            return "systemd", Path("usr/lib/systemd") / Path(*path.parts[1:])

    if name.startswith("lib") and ".so" in name:
        feature = library_feature(name)
        if feature is None:
            return None

        if name.startswith("libglxserver_nvidia.so"):
            return feature, Path("usr/lib/xorg/modules/extensions") / name

        if name.startswith("libvdpau_nvidia.so"):
            return feature, Path("usr/lib/vdpau") / name

        return feature, Path("usr/lib") / name

    if name == "nvidia_drv.so":
        return "x11", Path("usr/lib/xorg/modules/drivers") / name

    if name.endswith(".bin") and path.parts[0] == "firmware":
        return "firmware", Path("usr/lib/firmware/nvidia") / version / Path(*path.parts[1:])

    for feature, names in APPLICATIONS.items():
        if name in names:
            return feature, Path("usr/bin") / name

    if name.endswith(".1.gz") and name.startswith("nvidia-"):
        feature = "compute" if name == "nvidia-cuda-mps-control.1.gz" else "tools"
        return feature, Path("usr/share/man/man1") / name

    if name == "nvidia-settings.desktop":
        return "tools", Path("usr/share/applications") / name

    if name == "nvidia-settings.png":
        return "tools", Path("usr/share/pixmaps") / name

    if name == "nvidia-dbus.conf":
        return "systemd", Path("usr/share/dbus-1/system.d") / name

    if name.endswith((".service", ".socket")):
        return "systemd", Path("usr/lib/systemd/system") / name

    if name.endswith(".rules"):
        return "systemd", Path("usr/lib/udev/rules.d") / name

    if name == "nvidia_icd.json":
        return "vulkan", Path("etc/vulkan/icd.d") / name

    if name == "nvidia_layers.json":
        return "vulkan", Path("etc/vulkan/implicit_layer.d") / name

    if name in ("nvidia_icd_vksc.json", "nvidia_vksc_icd.json"):
        return "vulkan", Path("etc/vulkansc/icd.d") / name

    if name == "nvidia.icd":
        return "opencl", Path("etc/OpenCL/vendors") / name

    if name == "10_nvidia.json":
        return "opengl", Path("usr/share/glvnd/egl_vendor.d") / name

    if name.endswith(".json") and "nvidia" in name and any(
        key in name for key in ("wayland", "gbm", "xcb", "xlib")
    ):
        return "wayland", Path("usr/share/egl/egl_external_platform.d") / name

    if name == "nvidia-drm-outputclass.conf":
        return "x11", Path("usr/share/X11/xorg.conf.d") / name

    if name.startswith("nvidia-application-profiles") or name == "nvoptix.bin":
        return "tools", Path("usr/share/nvidia") / name

    if name == "sandboxutils-filelist.json":
        return "compute", Path("usr/share/nvidia/files.d") / name


    if path.parts[0] == "supported-gpus":
        return "html-docs", Path("usr/share/doc/cuda-userland") / path

    if name in DOCUMENTS:
        return "html-docs", Path("usr/share/doc/cuda-userland") / name

    return None


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("--source", type=Path, required=True)
    parser.add_argument("--dest", type=Path, required=True)
    parser.add_argument("--arch", default=os.environ.get("CUDA_TARGET_ARCH", "x86_64-linux"))
    parser.add_argument("--mode", choices=("native", "target"), default="target")
    parser.add_argument("--readelf", default=os.environ.get("READELF", "readelf"))
    args = parser.parse_args()

    source = args.source.resolve()
    stage_root = args.dest.resolve()

    if not source.is_dir():
        parser.error(f"NVIDIA driver source directory does not exist: {source}")

    version = source.name.removeprefix("NVIDIA-Linux-x86_64-")
    if version == source.name:
        parser.error("--source must be an extracted NVIDIA-Linux-x86_64-VERSION directory")

    selected = selected_components()
    if not selected:
        parser.error("CUDA_PACKAGES is empty")

    manifest = {
        name: {"component": name, "files": [], "licenses": [], "unmapped": []}
        for name in selected
    }

    ignored = []
    unmapped = []
    excluded_files = []
    stage_root.mkdir(parents=True, exist_ok=True)

    # Kernel trees and 32-bit binaries are not part of this userspace payload.
    excluded_dirs = {"kernel", "kernel-open", "32", "libglvnd_install_checker"}

    for path in sorted(source.rglob("*")):
        relative = path.relative_to(source)

        if relative.parts[0] in excluded_dirs or (path.is_dir() and not path.is_symlink()):
            continue

        # Documentation is staged separately under the html-docs feature.
        if relative.parts[0] == "html":
            continue

        # Record intentionally excluded installer, Windows and generic loader files.
        if relative.name in EXCLUDED_FILES or any(
            relative.name.startswith(name) for name in GLVND_LOADERS
        ):
            excluded_files.append(str(relative))
            continue

        mapping = location(relative, version)
        if mapping is None:
            unmapped.append(str(relative))
            continue

        feature, destination = mapping
        if feature not in manifest:
            ignored.append(str(relative))
            continue

        output = stage_root / destination
        files = manifest[feature]["files"]

        copy_file(path, output, files, stage_root)

        if path.is_file() and not path.is_symlink() and ".so" in path.name:
            name = soname(output, args.readelf)
            if name and name != output.name:
                create_link(output.with_name(name), output.name, files, stage_root)

    # Record license evidence independently of documentation selection.
    for filename in ("LICENSE", "LICENSE.txt", "EULA.txt", "supported-gpus/LICENSE"):
        path = source / filename
        if path.is_file():
            checksum = {
                "file": filename,
                "md5": file_md5(path),
            }
            for component in manifest.values():
                component["licenses"].append(checksum)

    # NVIDIA HTML documentation is optional.
    if "html-docs" in manifest:
        copy_tree(
            source / "html",
            stage_root / "usr/share/doc/cuda-userland/html",
            manifest["html-docs"]["files"],
            stage_root,
        )

    for name, component in manifest.items():
        component["files"] = list(dict.fromkeys(component["files"]))
        print(f"{name}: {len(component['files'])} staged entries")

    manifest_path = stage_root / "usr/share/doc/cuda-userland/nvidia-manifest.json"
    manifest_path.parent.mkdir(parents=True, exist_ok=True)
    manifest_path.write_text(json.dumps({
        "mode": args.mode,
        "architecture": args.arch,
        "prefix": "/",
        "components": manifest,
        "unmapped": unmapped,
        "unselected": ignored,
        "excluded": excluded_files,
    }, indent=2) + "\n")

    print(f"Unmapped driver files: {len(unmapped)}")
    for name in unmapped:
        print(f"  Unmapped: {name}")

    print(f"Intentionally excluded files: {len(excluded_files)}")
    print(f"Manifest: {manifest_path}")


if __name__ == "__main__":
    main()
