LICENSE_FLAGS = "nvidia_cuda"
LICENSE_FLAGS_DETAILS[nvidia_cuda] = "NVIDIA CUDA Toolkit requires acceptance of the NVIDIA SDK license agreement. Review https://docs.nvidia.com/cuda/eula/ and add nvidia_cuda to LICENSE_FLAGS_ACCEPTED to enable this recipe."

PACKAGES =+ " ${PN}-cuda"
SUMMARY:${PN}-cuda = "GGML CUDA computation backend"
DESCRIPTION:${PN}-cuda = "NVIDIA CUDA backend for GGML tensor operations, providing GPU-accelerated computation using CUDA kernels, cuBLAS, and optional FlashAttention optimizations."
SECTION:${PN}-cuda = "libs"

GGML_CUDA_DEFAULT_PKGCONF ?= " \
    ${@bb.utils.contains('LICENSE_FLAGS_ACCEPTED', 'nvidia_cuda', 'cuda cuda-fa cuda-nccl', '', d)} \
"

#######
# CUDA OPTIONS
#######
PACKAGECONFIG[cuda] = " -DGGML_CUDA=ON, -DGGML_CUDA=OFF, cuda-toolkit cuda-toolkit-native cuda-userland"

# option(GGML_CUDA_FORCE_MMQ                  "ggml: use mmq kernels instead of cuBLAS"         OFF)
PACKAGECONFIG[cuda-force-mmq] = " -DGGML_CUDA_FORCE_MMQ=ON, -DGGML_CUDA_FORCE_MMQ=OFF,"

# option(GGML_CUDA_FORCE_CUBLAS               "ggml: always use cuBLAS instead of mmq kernels"  OFF)
PACKAGECONFIG[cuda-force-cublas] = " -DGGML_CUDA_FORCE_CUBLAS=ON, -DGGML_CUDA_FORCE_CUBLAS=OFF,"

# option(GGML_CUDA_NO_PEER_COPY               "ggml: do not use peer to peer copies"            OFF)
PACKAGECONFIG[cuda-no-peer-copy] = " -DGGML_CUDA_NO_PEER_COPY=ON, -DGGML_CUDA_NO_PEER_COPY=OFF,"

# option(GGML_CUDA_NO_VMM                     "ggml: do not try to use CUDA VMM"                OFF)
PACKAGECONFIG[cuda-no-vmm] = " -DGGML_CUDA_NO_VMM=ON, -DGGML_CUDA_NO_VMM=OFF,"

# option(GGML_CUDA_FA                         "ggml: compile ggml FlashAttention CUDA kernels"  ON)
PACKAGECONFIG[cuda-fa] = " -DGGML_CUDA_FA=ON, -DGGML_CUDA_FA=OFF,"

# option(GGML_CUDA_FA_ALL_QUANTS              "ggml: compile all quants for FlashAttention"     OFF)
PACKAGECONFIG[cuda-fa-all-quants] = " -DGGML_CUDA_FA_ALL_QUANTS=ON, -DGGML_CUDA_FA_ALL_QUANTS=OFF,"

# set  (GGML_CUDA_FA_QUANTS  CACHE STRING "ggml: FlashAttention K-V type combinations to compile, \"all\" or a list such as \"q8_0-q8_0;q8_0-q4_0\"")
CUDA_FA_QUANTS ?= "q4_0-q4_0;q8_0-q8_0;f16-f16;bf16-bf16"
PACKAGECONFIG[override-cuda-fa-quants] = " -DGGML_CUDA_FA_QUANTS=${CUDA_FA_QUANTS}, ,"

# option(GGML_CUDA_GRAPHS                     "ggml: use CUDA graphs (llama.cpp only)"          ${GGML_CUDA_GRAPHS_DEFAULT})
PACKAGECONFIG[cuda-graphs] = " -DGGML_CUDA_GRAPHS=ON, -DGGML_CUDA_GRAPHS=OFF,"

# TODO NEEDS TESTING WE NEED TO MAKE SURE THAT WE HAVE THIS IN SYSROOT & NATIVE AS NEEDED RIGHT NOW CMAKE AILS HERE
# option(GGML_CUDA_NCCL                       "ggml: use NVIDIA Collective Comm. Library"       ON)
PACKAGECONFIG[cuda-nccl] = " -DGGML_CUDA_NCCL=ON, -DGGML_CUDA_NCCL=OFF,"

FILES:${PN}-cuda = " ${libdir}/libggml-cuda.so.* "
RDEPENDS:${PN}-cuda:class-target = " \
    lib${PN}-base \
    cuda-cudart \
    libcublas \
    nvidia-compute \
"

######################
# UNSUPPORTED
######################
# set   (GGML_CUDA_CCCL_VERSION "" CACHE STRING "ggml: CCCL git tag to fetch, empty to use the version bundled with the installed CUDA Toolkit")
## TODO
# set(GGML_CUDA_COMPRESSION_MODE "size" CACHE STRING "ggml: cuda link binary compression mode; requires cuda 12.8+")
# set_property(CACHE GGML_CUDA_COMPRESSION_MODE PROPERTY STRINGS "none;speed;balance;size")
