PACKAGES =+ " nvidia-glesv1"

SUMMARY:nvidia-glesv1 = "NVIDIA OpenGL ES 1.x Driver Libraries"
DESCRIPTION:nvidia-glesv1 = "NVIDIA proprietary OpenGL ES 1.x userspace driver libraries providing hardware-accelerated graphics through the OpenGL ES 1.x API."
SECTION:nvidia-glesv1 = "libs"

# License files from NVIDIA's extracted driver:
# LICENSE;md5=92aa2e2af6aa0bcba1c3fe49da021937
# supported-gpus/LICENSE;md5=3b4ecd9f96b20c7cdbabe7686cf9a35d
# LICENSE:nvidia-glesv1 = "UPDATE_ME"

FILES:nvidia-glesv1 = " \
    /usr/lib/libGLESv1_CM_nvidia.so.1 \
    /usr/lib/libGLESv1_CM_nvidia.so.610.43.02 \
"

RDEPENDS:nvidia-glesv1 = ""
