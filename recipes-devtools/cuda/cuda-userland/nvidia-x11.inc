PACKAGES =+ " nvidia-x11"

SUMMARY:nvidia-x11 = "NVIDIA X.Org Display Driver and GLX Integration"
DESCRIPTION:nvidia-x11 = "NVIDIA proprietary X.Org display driver modules, server-side GLX integration, and X11 configuration for hardware-accelerated graphics and display management."
SECTION:nvidia-x11 = "x11"

# License files from NVIDIA's extracted driver:
# LICENSE;md5=92aa2e2af6aa0bcba1c3fe49da021937
# supported-gpus/LICENSE;md5=3b4ecd9f96b20c7cdbabe7686cf9a35d
# LICENSE:nvidia-x11 = "UPDATE_ME"

FILES:nvidia-x11 = " \
    /usr/lib/xorg/modules/drivers/nvidia_drv.so \
    /usr/lib/xorg/modules/extensions/libglxserver_nvidia.so.610.43.02 \
    /usr/share/X11/xorg.conf.d/nvidia-drm-outputclass.conf \
"

RPROVIDES:nvidia-x11 += " xorg-video-driver"
RDEPENDS:nvidia-x11 = " \
    libx11 \
    libx11-xcb \
    xserver-xorg \
"
python nvidia_x11_abi_depends() {
    d.appendVar("RDEPENDS:nvidia-x11", " xorg-abi-video-25")
}

PACKAGEFUNCS:append = " nvidia_x11_abi_depends"
