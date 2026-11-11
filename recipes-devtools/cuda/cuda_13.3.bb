LICENSE = "LicenseRef-CUDA-EULA"
LICENSE_FLAGS = "nvidia_cuda"
LICENSE_FLAGS_DETAILS[nvidia_cuda] = "NVIDIA CUDA Toolkit requires acceptance of the NVIDIA SDK license agreement. Review https://docs.nvidia.com/cuda/eula/ and add nvidia_cuda to LICENSE_FLAGS_ACCEPTED to enable this recipe."
# How to accept
LICENSE_FLAGS_ACCEPTED:append = " nvidia_cuda"
LIC_FILES_CHKSUM = "file://cuda-${PV}/EULA.txt;md5=a3c52c1f3e8953cbfbc50639ad4b63e1"

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

inherit update-alternatives
ALTERNATIVE:${PN} = "cuda"
ALTERNATIVE_LINK_NAME[cuda] = "/usr/local/cuda"
ALTERNATIVE_TARGET[cuda] = "/usr/local/cuda-13.3"
ALTERNATIVE_PRIORITY[cuda] = "133"
