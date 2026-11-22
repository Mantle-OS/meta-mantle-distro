PACKAGES =+ " nvidia-video"

SUMMARY:nvidia-video = "NVIDIA GPU Video Acceleration Libraries"
DESCRIPTION:nvidia-video = "NVIDIA proprietary userspace libraries providing hardware-accelerated video encoding and decoding through NVENC and NVDEC, VDPAU video acceleration, framebuffer capture, and optical flow processing."
SECTION:nvidia-video = "libs"

# License files from NVIDIA's extracted driver:
# LICENSE;md5=92aa2e2af6aa0bcba1c3fe49da021937
# supported-gpus/LICENSE;md5=3b4ecd9f96b20c7cdbabe7686cf9a35d
# LICENSE:nvidia-video = "UPDATE_ME"

FILES:nvidia-video = " \
    /usr/lib/libnvcuvid.so.1 \
    /usr/lib/libnvcuvid.so.610.43.02 \
    /usr/lib/libnvidia-encode.so.1 \
    /usr/lib/libnvidia-encode.so.610.43.02 \
    /usr/lib/libnvidia-fbc.so.1 \
    /usr/lib/libnvidia-fbc.so.610.43.02 \
    /usr/lib/libnvidia-opticalflow.so.1 \
    /usr/lib/libnvidia-opticalflow.so.610.43.02 \
    /usr/lib/vdpau/libvdpau_nvidia.so.1 \
    /usr/lib/vdpau/libvdpau_nvidia.so.610.43.02 \
"


RDEPENDS:nvidia-video += " \
    libx11 \
    libxext \
"
