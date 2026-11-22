PACKAGES =+ " nvidia-wayland"

SUMMARY:nvidia-wayland = "NVIDIA Wayland and EGL Platform Integration"
DESCRIPTION:nvidia-wayland = "NVIDIA proprietary userspace libraries and EGL external-platform configuration providing Wayland, GBM, XCB, and Xlib integration for hardware-accelerated graphics."
SECTION:nvidia-wayland = "libs"

# License files from NVIDIA's extracted driver:
# LICENSE;md5=92aa2e2af6aa0bcba1c3fe49da021937
# supported-gpus/LICENSE;md5=3b4ecd9f96b20c7cdbabe7686cf9a35d
# LICENSE:nvidia-wayland = "UPDATE_ME"

FILES:nvidia-wayland = " \
    /usr/lib/libnvidia-egl-gbm.so.1 \
    /usr/lib/libnvidia-egl-gbm.so.1.1.3 \
    /usr/lib/libnvidia-egl-wayland.so.1 \
    /usr/lib/libnvidia-egl-wayland.so.1.1.20 \
    /usr/lib/libnvidia-egl-wayland2.so.1 \
    /usr/lib/libnvidia-egl-wayland2.so.1.0.1 \
    /usr/lib/libnvidia-egl-xcb.so.1 \
    /usr/lib/libnvidia-egl-xcb.so.1.0.5 \
    /usr/lib/libnvidia-egl-xlib.so.1 \
    /usr/lib/libnvidia-egl-xlib.so.1.0.5 \
    /usr/lib/libnvidia-wayland-client.so.610.43.02 \
    /usr/share/egl/egl_external_platform.d/09_nvidia_wayland2.json \
    /usr/share/egl/egl_external_platform.d/10_nvidia_wayland.json \
    /usr/share/egl/egl_external_platform.d/15_nvidia_gbm.json \
    /usr/share/egl/egl_external_platform.d/20_nvidia_xcb.json \
    /usr/share/egl/egl_external_platform.d/20_nvidia_xlib.json \
"

RDEPENDS:nvidia-wayland += " \
    libdrm \
    libgbm \
    libx11 libx11-xcb \
    libxext \
"
