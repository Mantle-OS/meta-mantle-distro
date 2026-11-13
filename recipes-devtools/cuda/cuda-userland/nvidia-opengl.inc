PACKAGES =+ " nvidia-opengl"

SUMMARY:nvidia-opengl = "NVIDIA OpenGL and EGL Driver Libraries"
DESCRIPTION:nvidia-opengl = "NVIDIA proprietary OpenGL, EGL, and GLX vendor libraries providing hardware-accelerated graphics and integration with the GLVND dispatch framework."
SECTION:nvidia-opengl = "libs"

# License files from NVIDIA's extracted driver:
# LICENSE;md5=92aa2e2af6aa0bcba1c3fe49da021937
# supported-gpus/LICENSE;md5=3b4ecd9f96b20c7cdbabe7686cf9a35d
# LICENSE:nvidia-opengl = "UPDATE_ME"

FILES:nvidia-opengl = " \
    /usr/lib/libEGL_nvidia.so.0 \
    /usr/lib/libEGL_nvidia.so.610.43.02 \
    /usr/lib/libGLX_nvidia.so.0 \
    /usr/lib/libGLX_nvidia.so.610.43.02 \
    /usr/lib/libnvidia-eglcore.so.610.43.02 \
    /usr/lib/libnvidia-glcore.so.610.43.02 \
    /usr/lib/libnvidia-glsi.so.610.43.02 \
    /usr/lib/libnvidia-present.so.610.43.02 \
    /usr/lib/libnvidia-tls.so.610.43.02 \
    /usr/share/glvnd/egl_vendor.d/10_nvidia.json \
"

RDEPENDS:nvidia-opengl += " \
    libx11 \
    libxext \
"
