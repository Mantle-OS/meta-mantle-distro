
CUDA_INSTALL_PREFIX ?= "/usr/local/cuda-13.3"
CUDA_TARGET_PREFIX = "${CUDA_INSTALL_PREFIX}/targets/${CUDA_TARGET_ARCH}"

PACKAGES =+ " \
    cuda-cudart-staticdev \
    cuda-culibos-staticdev \
    cuda-cupti-staticdev \
    cuda-nvcc-staticdev \
    cuda-nvrtc-staticdev \
    cuda_stubs-staticdev \
    libcublas-staticdev \
    libcufft-staticdev \
    libcufile-staticdev \
    libcurand-staticdev \
    libcusolver-staticdev \
    libcusparse-staticdev \
    libnpp-staticdev \
    libnvfatbin-staticdev \
    libnvjitlink-staticdev \
    libnvjpeg-staticdev \
    libnvptxcompiler-staticdev \
    \
    cuda_stubs \
    \
    cccl \
    cuda-crt \
    cuda-ctadvisor \
    cuda-cudart \
    cuda-culibos \
    cuda-cuobjdump \
    cuda-cupti \
    cuda-cuxxfilt \
    cuda-docs \
    cuda-gdb \
    cuda-nvcc \
    cuda-nvdisasm \
    cuda-nvml-dev \
    cuda-nvprune \
    cuda-nvrtc \
    cuda-nvtx \
    cuda-opencl \
    cuda-profiler-api \
    cuda-sandbox-dev \
    cuda-sanitizer-api \
    cuda_tileiras \
    \
    libcublas \
    libcufft \
    libcufile \
    libcuobjclient \
    libcurand \
    libcusolver \
    libcusparse \
    libnpp \
    libnvfatbin \
    libnvjitlink \
    libnvjpeg \
    libnvptxcompiler \
    libnvvm \
"

SUMMARY:cccl = "NVIDIA CUDA C++ Core Compute Libraries"
FILES:cccl += " \
    ${CUDA_TARGET_PREFIX}/include/cccl \
    ${CUDA_TARGET_PREFIX}/include/nv \
    ${CUDA_TARGET_PREFIX}/lib/cmake/cccl \
    ${CUDA_TARGET_PREFIX}/lib/cmake/thrust \
    ${CUDA_TARGET_PREFIX}/lib/cmake/cub \
    ${CUDA_TARGET_PREFIX}/lib/cmake/libcudacxx \
"
# RDEPENDS:cccl += ""

SUMMARY:cuda-crt = "NVIDIA CUDA Compiler Runtime"
FILES:cuda-crt += " \
    ${CUDA_TARGET_PREFIX}/include/crt \
    ${CUDA_INSTALL_PREFIX}/bin/crt \
"
# RDEPENDS:cuda-crt += ""

SUMMARY:cuda-ctadvisor = "NVIDIA CUDA Compilation Advisor"
FILES:cuda-ctadvisor += " \
    ${CUDA_INSTALL_PREFIX}/bin/ctadvisor \
"
RDEPENDS:cuda-ctadvisor += " glibc "

SUMMARY:cuda-cudart = "NVIDIA CUDA Runtime Library"
FILES:cuda-cudart += " \
    ${CUDA_TARGET_PREFIX}/lib/libcudart.so* \
    ${CUDA_TARGET_PREFIX}/lib/libcudart_static.a \
    ${CUDA_TARGET_PREFIX}/lib/libcudadevrt.a \
    ${CUDA_TARGET_PREFIX}/include/cuda_runtime.h \
    ${CUDA_TARGET_PREFIX}/include/cuda_runtime_api.h \
    ${CUDA_TARGET_PREFIX}/include/driver_types.h \
    ${CUDA_TARGET_PREFIX}/include/vector_types.h \
    ${CUDA_TARGET_PREFIX}/include/vector_functions.h \
    ${CUDA_TARGET_PREFIX}/include/cuda*.h \
    ${CUDA_TARGET_PREFIX}/include/cuda*.hpp \
    ${CUDA_TARGET_PREFIX}/include/device*.h \
    ${CUDA_TARGET_PREFIX}/include/device*.hpp \
    ${CUDA_TARGET_PREFIX}/include/driver*.h \
    ${CUDA_TARGET_PREFIX}/include/vector*.h \
    ${CUDA_TARGET_PREFIX}/include/vector*.hpp \
    ${CUDA_TARGET_PREFIX}/include/sm_*.h \
    ${CUDA_TARGET_PREFIX}/include/sm_*.hpp \
    ${CUDA_TARGET_PREFIX}/include/texture*.h \
    ${CUDA_TARGET_PREFIX}/include/surface*.h \
    ${CUDA_TARGET_PREFIX}/include/host*.h \
    ${CUDA_TARGET_PREFIX}/include/cooperative_groups \
    ${CUDA_TARGET_PREFIX}/include/cooperative_groups.h \
    ${CUDA_TARGET_PREFIX}/include/mma.h \
    ${CUDA_TARGET_PREFIX}/include/math*.h \
    ${CUDA_TARGET_PREFIX}/include/builtin_types.h \
    ${CUDA_TARGET_PREFIX}/include/channel_descriptor.h \
    ${CUDA_TARGET_PREFIX}/include/common_functions.h \
    ${CUDA_TARGET_PREFIX}/include/fatbinary_section.h \
    ${CUDA_TARGET_PREFIX}/include/library_types.h \
"
RDEPENDS:cuda-cudart += " glibc libgcc libstdc++ "

SUMMARY:cuda-culibos = "NVIDIA CUDA Operating System Support Library"
FILES:cuda-culibos += " \
    ${CUDA_TARGET_PREFIX}/lib/libculibos.a \
"
# RDEPENDS:cuda-culibos += ""

SUMMARY:cuda-cuobjdump = "NVIDIA CUDA Object Dump Utility"
FILES:cuda-cuobjdump += " \
    ${CUDA_INSTALL_PREFIX}/bin/cuobjdump \
"
RDEPENDS:cuda-cuobjdump += " glibc "

SUMMARY:cuda-cupti = "NVIDIA CUDA Profiling Tools Interface"
FILES:cuda-cupti += " \
    ${CUDA_INSTALL_PREFIX}/extras/CUPTI \
"
RDEPENDS:cuda-cupti += " glibc libgcc perl "

SUMMARY:cuda-cuxxfilt = "NVIDIA CUDA C++ Symbol Demangler"
FILES:cuda-cuxxfilt += " \
    ${CUDA_INSTALL_PREFIX}/bin/cu++filt \
"
RDEPENDS:cuda-cuxxfilt += " glibc "

SUMMARY:cuda-docs = "NVIDIA CUDA Toolkit Documentation"
FILES:cuda-docs += " \
    ${CUDA_INSTALL_PREFIX}/doc \
    ${CUDA_INSTALL_PREFIX}/share/doc \
    ${CUDA_INSTALL_PREFIX}/tools \
"
# RDEPENDS:cuda-docs += ""

SUMMARY:cuda-gdb = "NVIDIA CUDA Debugger"
FILES:cuda-gdb += " \
    ${CUDA_INSTALL_PREFIX}/bin/cuda-gdb \
    ${CUDA_INSTALL_PREFIX}/bin/cuda-gdbserver \
    ${CUDA_INSTALL_PREFIX}/bin/cuda-gdb-* \
    ${CUDA_INSTALL_PREFIX}/share/gdb \
    ${CUDA_INSTALL_PREFIX}/share/cuda-gdb \
    ${CUDA_INSTALL_PREFIX}/extras/Debugger \
    ${CUDA_INSTALL_PREFIX}/extras/cuda-gdb-*.src.tar.gz \
"
RDEPENDS:cuda-gdb += " \
    glibc \
    libgcc \
    libstdc++ \
    ncurses-libncursesw \
    ncurses-libtinfo \
    python3 \
"
# TODO: Resolve GDB's libgmp, ncursesw/tinfo, and multiple bundled
# libpython3.x ABI dependencies against available runtime package providers.

SUMMARY:cuda-nvcc = "NVIDIA CUDA Compiler"
FILES:cuda-nvcc += " \
    ${CUDA_INSTALL_PREFIX}/bin/nvcc \
    ${CUDA_INSTALL_PREFIX}/bin/nvcc.profile \
    ${CUDA_INSTALL_PREFIX}/bin/ptxas \
    ${CUDA_INSTALL_PREFIX}/bin/fatbinary \
    ${CUDA_INSTALL_PREFIX}/bin/bin2c \
    ${CUDA_INSTALL_PREFIX}/bin/cudafe++ \
    ${CUDA_INSTALL_PREFIX}/bin/nvlink \
    ${CUDA_INSTALL_PREFIX}/bin/__nvcc_device_query \
    ${CUDA_INSTALL_PREFIX}/nvvm \
    ${CUDA_TARGET_PREFIX}/lib/libcufilt.a \
    ${CUDA_TARGET_PREFIX}/include/cuComplex.h \
    ${CUDA_TARGET_PREFIX}/include/nvfunctional \
    ${CUDA_TARGET_PREFIX}/include/nv_decode.h \
    ${CUDA_TARGET_PREFIX}/lib/stubs/libcuda.so \
"
RDEPENDS:cuda-nvcc += " glibc libgcc libstdc++ cuda-crt cuda-cudart "

SUMMARY:cuda-nvdisasm = "NVIDIA CUDA Disassembler"
FILES:cuda-nvdisasm += " \
    ${CUDA_INSTALL_PREFIX}/bin/nvdisasm \
"
RDEPENDS:cuda-nvdisasm += " glibc "

SUMMARY:cuda-nvml-dev = "NVIDIA Management Library Development Files"
FILES:cuda-nvml-dev += " \
    ${CUDA_TARGET_PREFIX}/include/nvml.h \
    ${CUDA_TARGET_PREFIX}/lib/stubs/libnvidia-ml.so \
    ${CUDA_INSTALL_PREFIX}/nvml \
    ${CUDA_TARGET_PREFIX}/lib/stubs/libnvidia-ml.a \
"
# RDEPENDS:cuda-nvml-dev += ""

SUMMARY:cuda-nvprune = "NVIDIA CUDA Binary Pruning Utility"
FILES:cuda-nvprune += " \
    ${CUDA_INSTALL_PREFIX}/bin/nvprune \
"
RDEPENDS:cuda-nvprune += " glibc "

SUMMARY:cuda-nvrtc = "NVIDIA CUDA Runtime Compilation Library"
FILES:cuda-nvrtc += " \
    ${CUDA_TARGET_PREFIX}/include/nvrtc.h \
    ${CUDA_TARGET_PREFIX}/include/nvrtc_builtin_types.h \
    ${CUDA_TARGET_PREFIX}/lib/libnvrtc.so* \
    ${CUDA_TARGET_PREFIX}/lib/libnvrtc-builtins.so* \
    ${CUDA_TARGET_PREFIX}/lib/libnvrtc_static.a \
    ${CUDA_TARGET_PREFIX}/lib/libnvrtc-builtins_static.a \
    ${CUDA_TARGET_PREFIX}/lib/stubs/libnvrtc.so \
"
RDEPENDS:cuda-nvrtc += " glibc libgcc libstdc++ "

SUMMARY:cuda-nvtx = "NVIDIA Tools Extension Library"
FILES:cuda-nvtx += " \
    ${CUDA_TARGET_PREFIX}/include/nvtx3 \
    ${CUDA_TARGET_PREFIX}/include/nvToolsExt.h \
    ${CUDA_TARGET_PREFIX}/lib/libnvToolsExt.so* \
    ${CUDA_TARGET_PREFIX}/lib/libnvtx3interop.so* \
"
RDEPENDS:cuda-nvtx += " glibc libgcc libstdc++ "

SUMMARY:cuda-opencl = "NVIDIA CUDA OpenCL Development Files"
FILES:cuda-opencl += " \
    ${CUDA_TARGET_PREFIX}/include/CL \
    ${CUDA_TARGET_PREFIX}/lib/libOpenCL.so* \
"
RDEPENDS:cuda-opencl += " glibc "

SUMMARY:cuda-profiler-api = "NVIDIA CUDA Profiler API"
FILES:cuda-profiler-api += " \
    ${CUDA_TARGET_PREFIX}/include/cuda-profiler-api.h \
"
# RDEPENDS:cuda-profiler-api += ""

SUMMARY:cuda-sandbox-dev = "NVIDIA CUDA Sandbox Development Files"
FILES:cuda-sandbox-dev += " \
    ${CUDA_TARGET_PREFIX}/include/nvsandboxutils.h \
    ${CUDA_TARGET_PREFIX}/include/nvsandboxutils_loader.h \
    ${CUDA_TARGET_PREFIX}/lib/stubs/libnvidia-sandboxutils_loader.a \
"
# RDEPENDS:cuda-sandbox-dev += ""

SUMMARY:cuda-sanitizer-api = "NVIDIA CUDA Compute Sanitizer API"
FILES:cuda-sanitizer-api += " \
    ${CUDA_TARGET_PREFIX}/include/sanitizer* \
    ${CUDA_INSTALL_PREFIX}/compute-sanitizer \
"
RDEPENDS:cuda-sanitizer-api += " glibc libgcc "

PRIVATE_LIBS:cuda-sanitizer-api += " \
    libInterceptorInjectionTarget.so \
"

SUMMARY:cuda_tileiras = "NVIDIA CUDA Tile IR Assembler"
FILES:cuda_tileiras += " \
    ${CUDA_INSTALL_PREFIX}/bin/tileiras \
"
RDEPENDS:cuda_tileiras += " glibc "


SUMMARY:libcublas = "NVIDIA CUDA Basic Linear Algebra Libraries"
FILES:libcublas += " \
    ${CUDA_TARGET_PREFIX}/lib/libcublas.so* \
    ${CUDA_TARGET_PREFIX}/lib/libcublas_static.a \
    ${CUDA_TARGET_PREFIX}/lib/libcublasLt.so* \
    ${CUDA_TARGET_PREFIX}/lib/libcublasLt_static.a \
    ${CUDA_TARGET_PREFIX}/lib/libnvblas.so* \
    ${CUDA_TARGET_PREFIX}/lib/stubs/libcublas.so \
    ${CUDA_TARGET_PREFIX}/lib/stubs/libcublasLt.so \
    ${CUDA_TARGET_PREFIX}/include/cublas*.h \
    ${CUDA_TARGET_PREFIX}/include/nvblas.h \
"
RDEPENDS:libcublas += " glibc libgcc libstdc++ "

SUMMARY:libcufft = "NVIDIA CUDA Fast Fourier Transform Libraries"
FILES:libcufft += " \
    ${CUDA_TARGET_PREFIX}/lib/libcufft.so* \
    ${CUDA_TARGET_PREFIX}/lib/libcufft_static.a \
    ${CUDA_TARGET_PREFIX}/lib/libcufftw.so* \
    ${CUDA_TARGET_PREFIX}/lib/libcufftw_static.a \
    ${CUDA_TARGET_PREFIX}/lib/stubs/libcufft.so \
    ${CUDA_TARGET_PREFIX}/lib/stubs/libcufftw.so \
    ${CUDA_TARGET_PREFIX}/include/cufft*.h \
"
RDEPENDS:libcufft += " glibc libgcc "

SUMMARY:libcufile = "NVIDIA GPUDirect Storage Libraries"
FILES:libcufile += " \
    ${CUDA_TARGET_PREFIX}/lib/libcufile.so* \
    ${CUDA_TARGET_PREFIX}/lib/libcufile_static.a \
    ${CUDA_TARGET_PREFIX}/lib/libcufile_rdma.so* \
    ${CUDA_TARGET_PREFIX}/lib/libcufile_rdma_static.a \
    ${CUDA_TARGET_PREFIX}/include/cufile*.h \
    ${CUDA_INSTALL_PREFIX}/gds \
"
RDEPENDS:libcufile += "  \
    glibc \
    libgcc \
    libstdc++ \
    bash \
    numactl \
    rdma-core \
    nvidia-compute \
"

SUMMARY:libcuobjclient = "NVIDIA CUDA Object Client Library"
FILES:libcuobjclient += " \
    ${CUDA_TARGET_PREFIX}/lib/libcuobjclient.so* \
    ${CUDA_TARGET_PREFIX}/include/cuobjclient.h \
    ${CUDA_TARGET_PREFIX}/include/cuobjtelem.h \
"
RDEPENDS:libcuobjclient += " \
    glibc \
    libgcc \
    libstdc++ \
    libcufile \
    rdma-core \
"


SUMMARY:libcurand = "NVIDIA CUDA Random Number Generation Library"
FILES:libcurand += " \
    ${CUDA_TARGET_PREFIX}/lib/libcurand.so* \
    ${CUDA_TARGET_PREFIX}/lib/libcurand_static.a \
    ${CUDA_TARGET_PREFIX}/lib/stubs/libcurand.so \
    ${CUDA_TARGET_PREFIX}/include/curand*.h \
"
RDEPENDS:libcurand += " glibc libgcc "

SUMMARY:libcusolver = "NVIDIA CUDA Solver Libraries"
FILES:libcusolver += " \
    ${CUDA_TARGET_PREFIX}/lib/libcusolver.so* \
    ${CUDA_TARGET_PREFIX}/lib/libcusolver_static.a \
    ${CUDA_TARGET_PREFIX}/lib/libcusolverMg.so* \
    ${CUDA_TARGET_PREFIX}/lib/libcusolver_lapack_static.a \
    ${CUDA_TARGET_PREFIX}/lib/libcusolver_metis_static.a \
    ${CUDA_TARGET_PREFIX}/lib/libmetis_static.a \
    ${CUDA_TARGET_PREFIX}/lib/stubs/libcusolver.so \
    ${CUDA_TARGET_PREFIX}/lib/stubs/libcusolverMg.so \
    ${CUDA_TARGET_PREFIX}/include/cusolver*.h \
"
RDEPENDS:libcusolver += " glibc libgcc libstdc++ libcublas libcusparse libnvjitlink "

SUMMARY:libcusparse = "NVIDIA CUDA Sparse Matrix Libraries"
FILES:libcusparse += " \
    ${CUDA_TARGET_PREFIX}/lib/libcusparse.so* \
    ${CUDA_TARGET_PREFIX}/lib/libcusparse_static.a \
    ${CUDA_TARGET_PREFIX}/lib/stubs/libcusparse.so \
    ${CUDA_TARGET_PREFIX}/include/cusparse*.h \
    ${CUDA_INSTALL_PREFIX}/src \
"
RDEPENDS:libcusparse += " glibc libgcc "

SUMMARY:libnpp = "NVIDIA Performance Primitives Libraries"
FILES:libnpp += " \
    ${CUDA_TARGET_PREFIX}/lib/libnpp*.so* \
    ${CUDA_TARGET_PREFIX}/lib/libnpp*_static.a \
    ${CUDA_TARGET_PREFIX}/lib/stubs/libnpp*.so \
    ${CUDA_TARGET_PREFIX}/include/npp*.h \
"
RDEPENDS:libnpp += " glibc libgcc "

SUMMARY:libnvfatbin = "NVIDIA CUDA Fat Binary Library"
FILES:libnvfatbin += " \
    ${CUDA_TARGET_PREFIX}/lib/libnvfatbin.so* \
    ${CUDA_TARGET_PREFIX}/lib/libnvfatbin_static.a \
    ${CUDA_TARGET_PREFIX}/lib/stubs/libnvfatbin.so \
    ${CUDA_TARGET_PREFIX}/include/nvFatbin.h \
    ${CUDA_TARGET_PREFIX}/lib/pkgconfig/nvfatbin-*.pc \
"
RDEPENDS:libnvfatbin += " glibc libgcc "

SUMMARY:libnvjitlink = "NVIDIA CUDA JIT Linker Library"
FILES:libnvjitlink += " \
    ${CUDA_TARGET_PREFIX}/lib/libnvJitLink.so* \
    ${CUDA_TARGET_PREFIX}/lib/libnvJitLink_static.a \
    ${CUDA_TARGET_PREFIX}/lib/stubs/libnvJitLink.so \
    ${CUDA_TARGET_PREFIX}/include/nvJitLink.h \
    ${CUDA_TARGET_PREFIX}/lib/pkgconfig/nvjitlink-*.pc \
"
RDEPENDS:libnvjitlink += " glibc "

SUMMARY:libnvjpeg = "NVIDIA CUDA JPEG Library"
FILES:libnvjpeg += " \
    ${CUDA_TARGET_PREFIX}/lib/libnvjpeg.so* \
    ${CUDA_TARGET_PREFIX}/lib/libnvjpeg_static.a \
    ${CUDA_TARGET_PREFIX}/lib/stubs/libnvjpeg.so \
    ${CUDA_TARGET_PREFIX}/include/nvjpeg.h \
"
RDEPENDS:libnvjpeg += " glibc libgcc "

SUMMARY:libnvptxcompiler = "NVIDIA CUDA PTX Compiler Library"
FILES:libnvptxcompiler += " \
    ${CUDA_TARGET_PREFIX}/lib/libnvptxcompiler_static.a \
    ${CUDA_TARGET_PREFIX}/include/nvPTXCompiler.h \
"
# RDEPENDS:libnvptxcompiler += ""

SUMMARY:libnvvm = "NVIDIA CUDA NVVM Compiler Libraries"
FILES:libnvvm += " "
# RDEPENDS:libnvvm += ""


SUMMARY:cuda_stubs = "NVIDIA CUDA Link-Time Stub Libraries"
FILES:cuda_stubs += " ${CUDA_TARGET_PREFIX}/lib/stubs "
RDEPENDS:cuda_stubs += " glibc"
EXCLUDE_PACKAGES_FROM_SHLIBS += " cuda_stubs"

###################################################################################################################

FILES:cuda-cudart-staticdev = " \
    ${CUDA_TARGET_PREFIX}/lib/libcudadevrt.a \
    ${CUDA_TARGET_PREFIX}/lib/libcudart_static.a \
"

FILES:cuda-culibos-staticdev = " \
    ${CUDA_TARGET_PREFIX}/lib/libculibos.a \
"

FILES:cuda-cupti-staticdev = " \
    ${CUDA_INSTALL_PREFIX}/extras/CUPTI/lib64/libcupti_static.a \
    ${CUDA_INSTALL_PREFIX}/extras/CUPTI/lib64/libnvperf_host_static.a \
"

FILES:cuda-nvcc-staticdev = " \
    ${CUDA_TARGET_PREFIX}/lib/libcufilt.a \
"

FILES:cuda-nvrtc-staticdev = " \
    ${CUDA_TARGET_PREFIX}/lib/libnvrtc_static.a \
    ${CUDA_TARGET_PREFIX}/lib/libnvrtc-builtins_static.a \
"

FILES:cuda_stubs-staticdev = " \
    ${CUDA_TARGET_PREFIX}/lib/stubs/*.a \
"

FILES:libcublas-staticdev = " \
    ${CUDA_TARGET_PREFIX}/lib/libcublas_static.a \
    ${CUDA_TARGET_PREFIX}/lib/libcublasLt_static.a \
"

FILES:libcufft-staticdev = " \
    ${CUDA_TARGET_PREFIX}/lib/libcufft_static.a \
    ${CUDA_TARGET_PREFIX}/lib/libcufftw_static.a \
"

FILES:libcufile-staticdev = " \
    ${CUDA_TARGET_PREFIX}/lib/libcufile_static.a \
    ${CUDA_TARGET_PREFIX}/lib/libcufile_rdma_static.a \
"

FILES:libcurand-staticdev = " \
    ${CUDA_TARGET_PREFIX}/lib/libcurand_static.a \
"

FILES:libcusolver-staticdev = " \
    ${CUDA_TARGET_PREFIX}/lib/libcusolver_static.a \
    ${CUDA_TARGET_PREFIX}/lib/libcusolver_lapack_static.a \
    ${CUDA_TARGET_PREFIX}/lib/libcusolver_metis_static.a \
    ${CUDA_TARGET_PREFIX}/lib/libmetis_static.a \
"

FILES:libcusparse-staticdev = " \
    ${CUDA_TARGET_PREFIX}/lib/libcusparse_static.a \
"

FILES:libnpp-staticdev = " \
    ${CUDA_TARGET_PREFIX}/lib/libnpp*_static.a \
"

FILES:libnvfatbin-staticdev = " \
    ${CUDA_TARGET_PREFIX}/lib/libnvfatbin_static.a \
"

FILES:libnvjitlink-staticdev = " \
    ${CUDA_TARGET_PREFIX}/lib/libnvJitLink_static.a \
"

FILES:libnvjpeg-staticdev = " \
    ${CUDA_TARGET_PREFIX}/lib/libnvjpeg_static.a \
"

FILES:libnvptxcompiler-staticdev = " \
    ${CUDA_TARGET_PREFIX}/lib/libnvptxcompiler_static.a \
"

# The NVIDIA sanitizer bundle includes 32-bit x86 helper binaries.
# Only silence the architecture check for this mixed-architecture package.
INSANE_SKIP:cuda-sanitizer-api += " arch "


CUDA_INSANE_SKIP ?= " \
    already-stripped \
    ldflags \
    textrel \
    dev-so \
    libdir \
"

python __anonymous() {
    packages = d.getVar("PACKAGES").split()
    skips = d.getVar("CUDA_INSANE_SKIP")

    for package in packages:
        d.appendVar("INSANE_SKIP:" + package, " " + skips)
}
