inherit cmake cmake_extra_sanity
# NVIDIA installs the toolkit into a versioned prefix.
CUDA_INSTALL_PREFIX ?= "/usr/local/cuda-13.3"
CUDA_TOOLKIT_DIR ?= "${RECIPE_SYSROOT_NATIVE}${CUDA_INSTALL_PREFIX}"
# Target CUDA headers and libraries.
CUDA_TARGET_DIR ?= "${RECIPE_SYSROOT}${CUDA_INSTALL_PREFIX}"
CUDA_TARGET_ARCH ?= "x86_64-linux"
CUDA_TARGET_PREFIX ?= "${CUDA_TARGET_DIR}/targets/${CUDA_TARGET_ARCH}"

# Resolve transitive CUDA shared-library dependencies during target linking.
LDFLAGS:append:class-target = " \
    ${TOOLCHAIN_OPTIONS} \
    -Wl,-rpath-link,${CUDA_TARGET_PREFIX}/lib \
    -Wl,-rpath-link,${CUDA_TARGET_PREFIX}/lib/stubs \
"
