#########
# CPU
#########

require ggml-tune.inc

PACKAGES =+ " ${PN}-cpu"
SUMMARY:${PN}-cpu = "GGML CPU computation backend"
DESCRIPTION:${PN}-cpu = "CPU backend for GGML tensor operations, supporting architecture-specific optimizations, SIMD instructions, and optional BLAS acceleration."
SECTION:${PN}-cpu = "libs"
GGML_CPU_DEFAULT_PKGCONF ?= " \
    cpu \
    cpu-accelerate \
    blas \
    blas-vendor-openblass \
    ${GGML_TUNE_DEFAULT_PKGCONF} \
"

# option(GGML_CPU                             "ggml: enable CPU backend"                        ON)
PACKAGECONFIG[cpu] = " -DGGML_CPU=ON, -DGGML_CPU=OFF, "

# option(GGML_SCHED_NO_REALLOC                "ggml: disallow reallocations in ggml-alloc (for debugging)" OFF)
PACKAGECONFIG[sched-no-realloc] = " -DGGML_SCHED_NO_REALLOC=ON, -DGGML_SCHED_NO_REALLOC=OFF, "

## this should be handled by llama_cpp build of ggml
# option(GGML_LLAMAFILE                       "ggml: use LLAMAFILE"                             ${GGML_LLAMAFILE_DEFAULT}) (OFF)
PACKAGECONFIG[llamafile] = " -DGGML_LLAMAFILE=ON, -DGGML_LLAMAFILE=OFF, "

# # ggml core
# set(GGML_SCHED_MAX_COPIES  "4" CACHE STRING "ggml: max input copies for pipeline parallelism")
SCHED_MAX_COPIES ?= "4"
OE_EXTRA_CMAKE:append = " \
    -DGGML_SCHED_MAX_COPIES=${SCHED_MAX_COPIES} \
"

#########
# 3rd party libs / backends
#########

# option(GGML_ACCELERATE                      "ggml: enable Accelerate framework"               ON)
PACKAGECONFIG[cpu-accelerate] = " -DGGML_ACCELERATE=ON, -DGGML_ACCELERATE=OFF, "

PACKAGECONFIG[blas] = " -DGGML_BLAS=ON, -DGGML_BLAS=OFF, openblas"
PACKAGECONFIG[blas-vendor-generic] = " -DGGML_BLAS_VENDOR=Generic,,"
PACKAGECONFIG[blas-vendor-openblass] = " -DGGML_BLAS_VENDOR=OpenBLAS,,"
PACKAGECONFIG[blas-vendor-flame] = " -DGGML_BLAS_VENDOR=FLAME,,"
PACKAGECONFIG[blas-vendor-atlas] = " -DGGML_BLAS_VENDOR=ATLAS,,"
PACKAGECONFIG[blas-vendor-flexi-blas] = " -DGGML_BLAS_VENDOR=FlexiBLAS,,"
PACKAGECONFIG[blas-vendor-intel] = " -DGGML_BLAS_VENDOR=Intel,,"
PACKAGECONFIG[blas-vendor-nvhpc] = " -DGGML_BLAS_VENDOR=NVHPC,,"

FILES:${PN}-cpu = " ${libdir}/libggml-cpu.so.* "
RDEPENDS:${PN}-cpu:class-target = " \
    lib${PN}-base \
    openblas \
"

#########
# Unsupported
# 3rd party libs / backends
#########
# set(GGML_BLAS_VENDOR ${GGML_BLAS_VENDOR_DEFAULT} CACHE STRING Generic is fine for now
# option(GGML_CPU_ALL_VARIANTS "ggml: build all variants of the CPU backend (requires GGML_BACKEND_DL)" OFF)  ## We are cross compiling so this one is a no for now
# Okay these I am going to leave out for now. There needs to be a table and a lookup __ funct here ...
# set(GGML_CPU_ARM_ARCH        "" CACHE STRING "ggml: CPU architecture for ARM")
# OE_EXTRA_CMAKE:append:arm " ${@bb.utils.contains('ARCH', 'arm', '-DGGML_CPU_ARM_ARCH=arm', '', d)} "
# OE_EXTRA_CMAKE:append:powerpc " ${@bb.utils.contains('ARCH', 'arm', '-DGGML_CPU_ARM_ARCH=arm', '', d)}
