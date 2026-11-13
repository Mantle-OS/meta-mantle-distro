PACKAGES =+ " nvidia-opencl"

SUMMARY:nvidia-opencl = "NVIDIA OpenCL GPU Driver Libraries"
DESCRIPTION:nvidia-opencl = "NVIDIA proprietary OpenCL userspace driver libraries and ICD configuration providing GPU-accelerated parallel computing through the OpenCL API."
SECTION:nvidia-opencl = "libs"

# License files from NVIDIA's extracted driver:
# LICENSE;md5=92aa2e2af6aa0bcba1c3fe49da021937
# supported-gpus/LICENSE;md5=3b4ecd9f96b20c7cdbabe7686cf9a35d
# LICENSE:nvidia-opencl = "UPDATE_ME"

FILES:nvidia-opencl = " \
    /etc/OpenCL/vendors/nvidia.icd \
    /usr/lib/libnvidia-opencl.so.1 \
    /usr/lib/libnvidia-opencl.so.610.43.02 \
"

RDEPENDS:nvidia-opencl = ""
