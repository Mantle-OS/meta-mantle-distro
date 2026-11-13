PACKAGES =+ " nvidia-compute"

SUMMARY:nvidia-compute = "NVIDIA CUDA Driver API and GPU Compute Libraries"
DESCRIPTION:nvidia-compute = "NVIDIA userspace GPU compute libraries providing the CUDA Driver API, NVIDIA Management Library (NVML), JIT compilation, memory management, and supporting GPU compute functionality."
SECTION:nvidia-compute = "libs"

# License files from NVIDIA's extracted driver:
# LICENSE;md5=92aa2e2af6aa0bcba1c3fe49da021937
# supported-gpus/LICENSE;md5=3b4ecd9f96b20c7cdbabe7686cf9a35d
# LICENSE:nvidia-compute = "UPDATE_ME"

FILES:nvidia-compute = " \
    /usr/bin/nvidia-cuda-mps-control \
    /usr/bin/nvidia-cuda-mps-server \
    /usr/lib/libcuda.so.1 \
    /usr/lib/libcuda.so.610.43.02 \
    /usr/lib/libcudadebugger.so.1 \
    /usr/lib/libcudadebugger.so.610.43.02 \
    /usr/lib/libnvidia-allocator.so.1 \
    /usr/lib/libnvidia-allocator.so.610.43.02 \
    /usr/lib/libnvidia-api.so.1 \
    /usr/lib/libnvidia-cfg.so.1 \
    /usr/lib/libnvidia-cfg.so.610.43.02 \
    /usr/lib/libnvidia-ml.so.1 \
    /usr/lib/libnvidia-ml.so.610.43.02 \
    /usr/lib/libnvidia-ngx.so.1 \
    /usr/lib/libnvidia-ngx.so.610.43.02 \
    /usr/lib/libnvidia-nvvm.so.4 \
    /usr/lib/libnvidia-nvvm.so.610.43.02 \
    /usr/lib/libnvidia-nvvm70.so.4 \
    /usr/lib/libnvidia-pkcs11-openssl3.so.610.43.02 \
    /usr/lib/libnvidia-ptxjitcompiler.so.1 \
    /usr/lib/libnvidia-ptxjitcompiler.so.610.43.02 \
    /usr/lib/libnvidia-rtcore.so.610.43.02 \
    /usr/lib/libnvidia-sandboxutils.so.1 \
    /usr/lib/libnvidia-sandboxutils.so.610.43.02 \
    /usr/lib/libnvidia-tileiras.so.610.43.02 \
    /usr/lib/libnvoptix.so.1 \
    /usr/lib/libnvoptix.so.610.43.02 \
    /usr/share/man/man1/nvidia-cuda-mps-control.1.gz \
    /usr/share/nvidia/files.d/sandboxutils-filelist.json \
"
#     /usr/lib/libnvidia-pkcs11.so.610.43.02

RDEPENDS:nvidia-compute = " \
    libcrypto \
"
