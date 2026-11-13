SUMMARY = "NVIDIA CUDA Userland Driver Components"
DESCRIPTION = "NVIDIA userland driver libraries, GPU firmware, graphics integration, and supporting utilities."
SECTION = "libs"

require cuda.inc
# later
# require cuda-drivers-package.inc
SRC_URI:append = " \
    file://nvidia_install_helper.py \
"

# CUDA driver API, NVML, JIT and compute libraries
PACKAGECONFIG[compute] = ",,"

# NVIDIA systemd service integration
PACKAGECONFIG[systemd]  = " , , systemd"

# Xorg driver and GLX integration
PACKAGECONFIG[x11]  = ",,"

# NVIDIA Wayland and EGL external-platform components
PACKAGECONFIG[wayland]  = ",,"

# Vulkan ICD and NVIDIA Vulkan integration
PACKAGECONFIG[vulkan]  = ",,"

# NVIDIA vendor OpenGL/EGL/GLX libraries
PACKAGECONFIG[opengl]  = ",,"

# NVIDIA vendor OpenGLESv1,v2 libraries
PACKAGECONFIG[glesv1]  = ",,"
PACKAGECONFIG[glesv2]  = ",,"

# NVIDIA vendor OpenCL libraries
PACKAGECONFIG[opencl]  = ",,"

# NVENC, NVDEC, VDPAU and related libraries
PACKAGECONFIG[video]  = ",,"

# GSP firmware
PACKAGECONFIG[firmware] = ",,"

# nvidia-smi, configuration and support utilities
PACKAGECONFIG[tools] = ",,"
PACKAGECONFIG[html-docs] = ",,"

PACKAGECONFIG ?= "\
    compute \
    systemd \
    x11 \
    wayland \
    vulkan \
    opengl glesv1 glesv2 \
    opencl \
    video \
    firmware \
    tools \
    html-docs \
"

# for the python installer script
export CUDA_PACKAGES = "${PACKAGECONFIG}"
export CUDA_SUPPORT_LIBS
export CUDA_TARGET_ARCH



do_configure:append(){
    install -m 0644 ${THISDIR}/cuda-userland/nvidia_install_helper.py ${S}/nvidia_install_helper.py
}

do_install:append() {
    python3 ${S}/nvidia_install_helper.py \
        --source ${S}/NVIDIA-Linux-x86_64-${PR} \
        --mode target \
        --dest ${D} \
        --arch ${CUDA_TARGET_ARCH} \
        --readelf ${READELF}


    rm ${D}/${libdir}/libnvidia-pkcs11.so.610.43.02
}

RRECOMMENDS:${PN} += "nvidia-open-gpu-kernel-modules"


## Packaging
##
require ${PN}/nvidia-compute.inc
require ${PN}/nvidia-firmware.inc
require ${PN}/nvidia-glesv1.inc
require ${PN}/nvidia-glesv2.inc
require ${PN}/nvidia-html-docs.inc
require ${PN}/nvidia-opencl.inc
require ${PN}/nvidia-opengl.inc
require ${PN}/nvidia-systemd.inc
require ${PN}/nvidia-tools.inc
require ${PN}/nvidia-video.inc
require ${PN}/nvidia-vulkan.inc
require ${PN}/nvidia-wayland.inc
require ${PN}/nvidia-x11.inc


python __anonymous() {
    packages = d.getVar("PACKAGES").split()
    skips = d.getVar("CUDA_INSANE_SKIP")

    for package in packages:
        d.appendVar("INSANE_SKIP:" + package, " " + skips)
}


