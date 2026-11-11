
#!/usr/bin/env python3

import argparse
import filecmp
import json
import os
import shutil
from pathlib import Path


def selected_components():
    packages = os.environ.get("CUDA_PACKAGES", "").split()
    libraries = os.environ.get("CUDA_SUPPORT_LIBS", "").split()
    return list(dict.fromkeys(packages + libraries))


def copy_payload(source, destination, installed, stage_root):
    if not source.is_dir():
        return

    for path in sorted(source.rglob("*")):
        relative = path.relative_to(source)
        target = destination / relative

        # These component-local aliases become invalid when the
        # architecture-specific payloads are merged into one tree.
        # The correct aliases are created at the CUDA installation root.
        if path.is_symlink() and (
            (source.name == "include" and relative == Path("include")) or
            (source.name == "lib" and relative == Path("lib64"))
        ):
            continue

        if path.is_symlink():
            link = os.readlink(path)
            target.parent.mkdir(parents=True, exist_ok=True)

            if target.is_symlink() and os.readlink(target) == link:
                pass
            elif target.exists() or target.is_symlink():
                raise RuntimeError(f"File collision: {target}")
            else:
                target.symlink_to(link)

        elif path.is_dir():
            target.mkdir(parents=True, exist_ok=True)
            continue

        elif path.is_file():
            target.parent.mkdir(parents=True, exist_ok=True)

            if target.exists() or target.is_symlink():
                if not target.is_file() or target.is_symlink() or not filecmp.cmp(path, target, shallow=False):
                    raise RuntimeError(f"File collision: {target}")
            else:
                shutil.copy2(path, target)

        else:
            raise RuntimeError(f"Unsupported file type: {path}")

        installed.append(str(target.relative_to(stage_root)))


def copy_file(source, destination, installed, stage_root):
    if not source.is_file():
        return

    destination.parent.mkdir(parents=True, exist_ok=True)

    if destination.exists() or destination.is_symlink():
        if destination.is_symlink() or not destination.is_file() or not filecmp.cmp(source, destination, shallow=False):
            raise RuntimeError(f"File collision: {destination}")
    else:
        shutil.copy2(source, destination)

    installed.append(str(destination.relative_to(stage_root)))


def create_link(destination, target):
    destination.parent.mkdir(parents=True, exist_ok=True)

    if destination.is_symlink():
        if os.readlink(destination) == target:
            return
        raise RuntimeError(f"Conflicting symlink: {destination}")

    if destination.exists():
        raise RuntimeError(f"Destination already exists: {destination}")

    destination.symlink_to(target)


def stage_component(component, source_root, destination, arch, mode, stage_root):
    source = source_root / component

    if not source.is_dir():
        raise RuntimeError(f"Missing CUDA component: {component}")

    installed = []
    target_root = source / "targets" / arch

    # Common CUDA installation layout.
    copy_payload(source / "bin", destination / "bin", installed, stage_root)
    copy_payload(source / "sbin", destination / "sbin", installed, stage_root)

    # Architecture-specific CUDA payload.
    copy_payload(target_root / "include", destination / "targets" / arch / "include", installed, stage_root)
    copy_payload(target_root / "lib", destination / "targets" / arch / "lib", installed, stage_root)

    # NVIDIA compiler and runtime tool structures.
    extra_directories = {
        "nvvm": "nvvm",
        "compute-sanitizer": "compute-sanitizer",
        "extras": "extras",
        "share": "share",
        "nvml": "nvml",
        "gds": "gds",
        "src": "src",
        "tools": "tools",
        "nsightee_plugins": "nsightee_plugins",
        "DOCS": "DOCS",
    }

    for name, target in extra_directories.items():
        copy_payload(source / name, destination / target, installed, stage_root)

    # Component-specific pkg-config metadata.
    copy_payload(
        source / "pkg-config",
        destination / "targets" / arch / "lib" / "pkgconfig",
        installed,
        stage_root,
    )

    # Component documentation.
    for name in ("doc", "docs"):
        copy_payload(
            source / name,
            destination / "doc" / component,
            installed,
            stage_root,
        )

    # Component-specific release metadata and licenses.
    for filename in (
        "LICENSE",
        "LICENSE.txt",
        "EULA.txt",
        "README",
        "CUDA_Toolkit_Release_Notes.txt",
        "cublas_version.txt",
    ):
        copy_file(
            source / filename,
            destination / "doc" / component / filename,
            installed,
            stage_root,
        )

    recognized = {
        "bin", "sbin", "targets", "include", "lib", "lib64",
        "pkg-config", "doc", "docs",
        *extra_directories.keys(),
        "LICENSE", "LICENSE.txt", "EULA.txt", "README",
        "CUDA_Toolkit_Release_Notes.txt", "cublas_version.txt",
    }

    extras = sorted(
        entry.name for entry in source.iterdir()
        if entry.name not in recognized
    )

    print(f"{component}: {len(installed)} staged entries")

    if extras:
        print(f"  Unmapped: {', '.join(extras)}")

    return {
        "component": component,
        "files": list(dict.fromkeys(installed)),
        "unmapped": extras,
    }


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("--source", type=Path, default=Path("cuda-13.3.1"))
    parser.add_argument("--dest", type=Path, required=True)
    parser.add_argument("--arch", default=os.environ.get("CUDA_TARGET_ARCH", "x86_64-linux"))
    parser.add_argument("--mode", choices=("native", "target"), required=True)
    parser.add_argument("--prefix", default="/usr/local/cuda-13.3")
    args = parser.parse_args()

    source = args.source.resolve()
    stage_root = args.dest.resolve()

    if not source.is_dir():
        parser.error(f"CUDA source directory does not exist: {source}")

    prefix = Path(args.prefix)

    if not prefix.is_absolute() or ".." in prefix.parts:
        parser.error("--prefix must be an absolute path without '..'")

    destination = stage_root.joinpath(*prefix.parts[1:])

    components = selected_components()

    if not components:
        parser.error("CUDA_PACKAGES and CUDA_SUPPORT_LIBS are empty")

    destination.mkdir(parents=True, exist_ok=True)

    manifest = {}

    for component in components:
        manifest[component] = stage_component(
            component, source, destination, args.arch,
            args.mode, stage_root,
        )

    # NVIDIA's top-level compatibility symlinks.
    create_link(
        destination / "include",
        f"targets/{args.arch}/include",
    )

    create_link(
        destination / "lib64",
        f"targets/{args.arch}/lib",
    )

    # Preserve release metadata.
    for filename in ("version.json", "EULA.txt"):
        original = source / filename
        if original.is_file():
            shutil.copy2(original, destination / filename)

    manifest_path = stage_root / "cuda-manifest.json"
    manifest_path.write_text(json.dumps({
        "mode": args.mode,
        "prefix": args.prefix,
        "architecture": args.arch,
        "components": manifest,
    }, indent=2) + "\n")

    print(f"\nCUDA installation: {destination}")
    print(f"Manifest: {manifest_path}")


if __name__ == "__main__":
    main()
