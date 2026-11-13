PACKAGES =+ " nvidia-vulkan"

SUMMARY:nvidia-vulkan = "NVIDIA Vulkan GPU Driver Libraries"
DESCRIPTION:nvidia-vulkan = "NVIDIA proprietary Vulkan userspace driver libraries, shader compilation components, and ICD configuration providing hardware-accelerated graphics and compute through the Vulkan API, including Vulkan SC support."
SECTION:nvidia-vulkan = "libs"

# License files from NVIDIA's extracted driver:
# LICENSE;md5=92aa2e2af6aa0bcba1c3fe49da021937
# supported-gpus/LICENSE;md5=3b4ecd9f96b20c7cdbabe7686cf9a35d
# LICENSE:nvidia-vulkan = "UPDATE_ME"

FILES:nvidia-vulkan = " \
    /etc/vulkan/icd.d/nvidia_icd.json \
    /etc/vulkan/implicit_layer.d/nvidia_layers.json \
    /etc/vulkansc/icd.d/nvidia_icd_vksc.json \
    /usr/bin/nvidia-pcc \
    /usr/lib/libnvidia-glvkspirv.so.610.43.02 \
    /usr/lib/libnvidia-gpucomp.so.610.43.02 \
    /usr/lib/libnvidia-vksc-core.so.1 \
    /usr/lib/libnvidia-vksc-core.so.610.43.02 \
"

RDEPENDS:nvidia-vulkan = ""
