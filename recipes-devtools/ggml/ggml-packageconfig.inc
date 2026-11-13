require ggml-cpu.inc
require ${@bb.utils.contains('LICENSE_FLAGS_ACCEPTED', 'nvidia_cuda', 'ggml-cuda.inc', 'ggml-no-cuda.inc', d)}
require ${@bb.utils.contains('DISTRO_FEATURES', 'vulkan', 'ggml-vulkan.inc', 'ggml-no-vulkan.inc', d)}

#######
# GENERAL OPTIONS
#######
PACKAGECONFIG[shared] = " \
    -DBUILD_SHARED_LIBS=ON -DGGML_STATIC=OFF, \
    -DBUILD_SHARED_LIBS=OFF -DGGML_STATIC=ON,"

# option(GGML_LTO    "ggml: enable link time optimization"             OFF)
## add cmake-data.... not sure why we would want this but ....
PACKAGECONFIG[lto] = " -DGGML_LTO=ON, -DGGML_LTO=OFF,"

# * we stray from with the default settings
# option(GGML_CCACHE "ggml: use ccache if available"                   ON)
## add ccache .... not sure why we would want this but ....
PACKAGECONFIG[ccache] = " -DGGML_CCACHE=ON, -DGGML_CCACHE=OFF,"

#######
# debug & build time
#######
# * we stray from with the default settings
# option(GGML_ALL_WARNINGS           "ggml: enable all compiler warnings"                   ON)
PACKAGECONFIG[all-warnings] = " -DGGML_ALL_WARNINGS=ON, -DGGML_ALL_WARNINGS=OFF,"

# option(GGML_ALL_WARNINGS_3RD_PARTY "ggml: enable all compiler warnings in 3rd party libs" OFF)
PACKAGECONFIG[all-warnings-3rdparty] = " -DGGML_ALL_WARNINGS_3RD_PARTY=ON, -DGGML_ALL_WARNINGS_3RD_PARTY=OFF,"

# option(GGML_GPROF                  "ggml: enable gprof"                                   OFF)
PACKAGECONFIG[gprof] = " -DGGML_GPROF=ON, -DGGML_GPROF=OFF,"

# option(GGML_FATAL_WARNINGS    "ggml: enable -Werror flag"    OFF)
PACKAGECONFIG[fatal-warnings] = " -DGGML_FATAL_WARNINGS=ON, -DGGML_FATAL_WARNINGS=OFF,"

#######
# sanitizers
#######
# option(GGML_SANITIZE_THREAD    "ggml: enable thread sanitizer"    OFF)
PACKAGECONFIG[sanitize-thread] = " -DGGML_SANITIZE_THREAD=ON, -DGGML_SANITIZE_THREAD=OFF,"

# option(GGML_SANITIZE_ADDRESS   "ggml: enable address sanitizer"   OFF)
PACKAGECONFIG[sanitize-address] = " -DGGML_SANITIZE_ADDRESS=ON, -DGGML_SANITIZE_ADDRESS=OFF,"

# option(GGML_SANITIZE_UNDEFINED "ggml: enable undefined sanitizer" OFF)
PACKAGECONFIG[sanitize-undefined] = " -DGGML_SANITIZE_UNDEFINED=ON, -DGGML_SANITIZE_UNDEFINED=OFF,"

#######
# DEFAULT PACKAGECONFIG
#######
GGML_DEFAULT_PKGCONF ?= " \
    shared \
    ${GGML_CPU_DEFAULT_PKGCONF} \
    ${GGML_CUDA_DEFAULT_PKGCONF} \
    ${GGML_VULKAN_DEFAULT_PKGCONF} \
"

#######
# FULLY UNSUPPORTED OPTIONS/DEVICES
#
# option(GGML_STATIC "ggml: static link libraries"                     OFF)
# option(GGML_NATIVE "ggml: optimize the build for the current system" ${GGML_NATIVE_DEFAULT})
EXTRA_OECMAKE:append = " \
    -DEMSCRIPTEN=OFF \
    -DGGML_BACKEND_DL=OFF \
    -DGGML_METAL_DEFAULT=OFF \
    -DGGML_NATIVE_DEFAULT=OFF \
    -DGGML_METAL=OFF \
    -DGGML_OPENVINO=OFF \
    -DGGML_OPENCL=OFF \
    -DGGML_HEXAGON=OFF \
    -DGGML_ET=OFF \
    -DGGML_SYCL=OFF \
    -DGGML_ZDNN=OFF \
    -DGGML_HIP=OFF \
    -DGGML_WEBGPU=OFF \
    -DGGML_MUSA=OFF \
    -DGGML_STATIC=OFF \
"
