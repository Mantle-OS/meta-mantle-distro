# DESCRIPTION =- ""
PV = "13.3.1"
PR = "610.43.02"

CUDA_SUPPORT_LIBS ??= "libcublas libcufft libcufile libcuobjclient libcurand libcusolver libcusparse libnpp libnvfatbin libnvjitlink libnvjpeg libnvptxcompiler libnvvm"
PACKAGECONFIG = "\
    cccl \
    cuda_crt \
    cuda_ctadvisor \
    cuda_cudart \
    cuda_culibos \
    cuda_cuobjdump \
    cuda_cupti \
    cuda_cuxxfilt \
    cuda_documentation \
    cuda_gdb \
    cuda_nvcc \
    cuda_nvdisasm \
    cuda_nvml_dev \
    cuda_nvprune \
    cuda_nvrtc \
    cuda_nvtx  \
    cuda_opencl  \
    cuda_profiler_api \
    cuda_sandbox_dev \
    cuda_sanitizer_api \
    cuda_tileiras \
"

require cuda_installer.inc
