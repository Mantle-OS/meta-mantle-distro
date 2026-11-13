PACKAGES =+ " nvidia-glesv2"

SUMMARY:nvidia-glesv2 = "NVIDIA OpenGL ES 2.x Driver Libraries"
DESCRIPTION:nvidia-glesv2 = "NVIDIA proprietary OpenGL ES 2.x userspace driver libraries providing hardware-accelerated graphics through the OpenGL ES 2.x API."
SECTION:nvidia-glesv2 = "libs"

# License files from NVIDIA's extracted driver:
# LICENSE;md5=92aa2e2af6aa0bcba1c3fe49da021937
# supported-gpus/LICENSE;md5=3b4ecd9f96b20c7cdbabe7686cf9a35d
# LICENSE:nvidia-glesv2 = "UPDATE_ME"

FILES:nvidia-glesv2 = " \
    /usr/lib/libGLESv2_nvidia.so.2 \
    /usr/lib/libGLESv2_nvidia.so.610.43.02 \
"

RDEPENDS:nvidia-glesv2 = ""
