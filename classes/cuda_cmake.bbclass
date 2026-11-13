inherit cmake cmake_extra_sanity

# CUDA's nvcc runs on the build host.
# CUDA headers and libraries are provided by the target recipe.
DEPENDS:append:class-target = " cuda-toolkit-native cuda-toolkit"
# DEPENDS:append:class-native = " cuda-native"

# NVIDIA installs the toolkit into a versioned prefix.
CUDA_INSTALL_PREFIX ?= "/usr/local/cuda-13.3"
CUDA_TOOLKIT_DIR ?= "${RECIPE_SYSROOT_NATIVE}${CUDA_INSTALL_PREFIX}"

CUDA_COMPILER ?= "${CUDA_TOOLKIT_DIR}/bin/nvcc"

CUDA_HOST_COMPILER ?= "${OECMAKE_CXX_COMPILER}"
CUDA_HOST_FLAGS ?= "--sysroot=${RECIPE_SYSROOT}"

# Target CUDA headers and libraries.
CUDA_TARGET_DIR ?= "${RECIPE_SYSROOT}${CUDA_INSTALL_PREFIX}"
CUDA_TARGET_ARCH ?= "x86_64-linux"
CUDA_TARGET_PREFIX ?= "${CUDA_TARGET_DIR}/targets/${CUDA_TARGET_ARCH}"

CUDA_ARCHITECTURES ?= "120a"

# Resolve transitive CUDA shared-library dependencies during target linking.
LDFLAGS:append:class-target = " \
    ${TOOLCHAIN_OPTIONS} \
    -Wl,-rpath-link,${CUDA_TARGET_PREFIX}/lib \
    -Wl,-rpath-link,${CUDA_TARGET_PREFIX}/lib/stubs \
"

EXTRA_OECMAKE:append = " \
    -DCMAKE_CUDA_TOOLKIT_ROOT_DIR=${CUDA_TOOLKIT_DIR} \
    -DCUDAToolkit_ROOT=${CUDA_TOOLKIT_DIR} \
    -DCMAKE_CUDA_COMPILER=${CUDA_COMPILER} \
    -DCMAKE_CUDA_HOST_COMPILER=${CUDA_HOST_COMPILER} \
    -DCMAKE_CUDA_ARCHITECTURES=${CUDA_ARCHITECTURES} \
    -DCMAKE_CUDA_PROPAGATE_HOST_FLAGS=OFF \
    -DCMAKE_CUDA_FLAGS=-Xcompiler=${CUDA_HOST_FLAGS} \
"
