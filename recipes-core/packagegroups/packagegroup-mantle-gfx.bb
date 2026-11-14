SUMMARY = "Mantle self-hosted graphics stack"
DESCRIPTION = "Mantle build time graphics stack"
LICENSE = "MIT"
PR = "r1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGECONFIG[opengl] = ""
PACKAGECONFIG[opencl] = ""
PACKAGECONFIG[directfb] = ""
PACKAGECONFIG[kms] = ""
PACKAGECONFIG[wayland] = ""
PACKAGECONFIG[vulkan] = ""
PACKAGECONFIG[x11] = ""
PACKAGECONFIG[eglfs] = ""
PACKAGECONFIG[wm] = ""


PACKAGECONFIG ?= " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'opengl', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opencl', 'opencl', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'directfb', 'directfb', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'kms', 'kms', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'vulkan', 'vulkan', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'eglfs', 'eglfs', '', d)} \
    ${@bb.utils.contains('VIRTUAL-RUNTIME_wm', 'none', '', 'wm', d)} \
"

PACKAGES = "\
    ${PN} \
    ${PN}-opencl \
    ${PN}-opengl \
    ${PN}-directfb \
    ${PN}-kms \
    ${PN}-wayland \
    ${PN}-vulkan \
    ${PN}-x11 \
    ${PN}-eglfs \
"


SUMMARY:${PN}-opencl = "opengl user time"
DESCRIPTION:${PN}-opencl = "tools that play well with toolchains"
LICENSE:${PN}-opencl = "MIT"
RDEPENDS:${PN}-opencl = " \
    ${@bb.utils.contains('PREFERRED_PROVIDER_virtual/libopencl1', 'opencl-icd-loader', '\
                                                                    opencl-clhpp \
                                                                    opencl-cts \
                                                                    opencl-headers \
                                                                    opencl-icd-loader opencl-icd-loader-dev libicdlog ' , '', d)} \
"

SUMMARY:${PN}-opengl = "opengl user time"
DESCRIPTION:${PN}-opengl = "tools that play well with toolchains"
LICENSE:${PN}-opengl = "MIT"
RDEPENDS:${PN}-opengl = " \
    ${VIRTUAL-RUNTIME_gl} \
    ${VIRTUAL-RUNTIME_egl} \
    ${VIRTUAL-RUNTIME_gles} \
    ${VIRTUAL-RUNTIME_glu} \
    ${VIRTUAL-RUNTIME_drm} \
    ${VIRTUAL-RUNTIME_gbm} \
    ${@bb.utils.contains('PREFERRED_PROVIDER_virtual/gl', 'mesa', 'libopencl-mesa libteflon', '', d)} \
"

SUMMARY:${PN}-kms = "DRM pipeline"
DESCRIPTION:${PN}-kms = "kms -> DRI -> DRM runtime"
LICENSE:${PN}-kms = "MIT"
RDEPENDS:${PN}-kms = " \
    ${VIRTUAL-RUNTIME_drm} \
    ${VIRTUAL-RUNTIME_glu} \
    ${VIRTUAL-RUNTIME_drm} \
    ${VIRTUAL-RUNTIME_gbm} \
"

SUMMARY:${PN}-wayland = "wayland pipeline"
DESCRIPTION:${PN}-wayland = "lms -> DRI -> DRM runtime"
LICENSE:${PN}-wayland = "MIT"
RDEPENDS:${PN}-wayland = " \
    wayland \
    wayland-protocols \
    wayland-tools \
"

SUMMARY:${PN}-vulkan = "vulkan pipeline"
DESCRIPTION:${PN}-vulkan = ""
LICENSE:${PN}-vulkan = "MIT"
RDEPENDS:${PN}-vulkan = " \
    vulkan-loader \
    vulkan-tools \
    vulkan-validation-layers \
    ${VIRTUAL-RUNTIME_vulkan} \
"


SUMMARY:${PN}-x11 = "x11 user time"
DESCRIPTION:${PN}-x11 = "x11 runtime"
LICENSE:${PN}-x11 = "MIT"
RDEPENDS:${PN}-x11 = " \
    libxrandr \
    libxinerama \
    libxi \
    libxcursor \
"

#     libxcb-common
RDEPENDS:${PN} = " \
    ${VIRTUAL-RUNTIME_font} \
    ${VIRTUAL-RUNTIME_input} \
    ${@bb.utils.contains('PACKAGECONFIG', 'opencl', '${PN}-opencl', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'opengl', '${PN}-opengl', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'directfb', 'directfb directfb-examples', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'kms', '${PN}-kms', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'wayland', '${PN}-wayland', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'vulkan', '${PN}-vulkan', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'x11', '${PN}-x11', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'wm', '${VIRTUAL-RUNTIME_input}', '', d)} \
"
