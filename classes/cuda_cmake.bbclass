inherit cuda

# CUDA's nvcc runs on the build host.
# CUDA headers and libraries are provided by the target recipe.
DEPENDS:append:class-target = " cuda-toolkit-native cuda-toolkit"

CUDA_COMPILER ?= "${CUDA_TOOLKIT_DIR}/bin/nvcc"

# Use OpenEmbedded's C++ cross compiler as nvcc's host compiler.
CUDA_HOST_COMPILER ?= "${OECMAKE_CXX_COMPILER}"
CUDA_HOST_FLAGS ?= "--sysroot=${RECIPE_SYSROOT}"

CUDA_ARCHITECTURES ?= "120a"

# CUDA 13.3 with GCC 16.
CUDA_NVCC_FLAGS ?= " \
    --allow-unsupported-compiler \
    -Xcompiler=--sysroot=${RECIPE_SYSROOT} \
    -Xcompiler=-ffile-prefix-map=${S}=/usr/src/debug/${PN}/${PV} \
"

# Preserve the GCC 16 / CUDA 13.3 C++23 workaround.
# Do not set CMAKE_CUDA_STANDARD=23.
do_configure:prepend() {
    cat > "${WORKDIR}/cuda-gcc16-rules.cmake" <<'EOF'
set(CMAKE_CUDA23_STANDARD_COMPILE_OPTION "-std=c++23")
set(CMAKE_CUDA23_EXTENSION_COMPILE_OPTION "-std=c++23")
set(CMAKE_CUDA_STANDARD_REQUIRED ON)
EOF
}

EXTRA_OECMAKE:append = " \
    -DCMAKE_CUDA_TOOLKIT_ROOT_DIR=${CUDA_TOOLKIT_DIR} \
    -DCUDAToolkit_ROOT=${CUDA_TOOLKIT_DIR} \
    -DCMAKE_CUDA_COMPILER=${CUDA_COMPILER} \
    -DCMAKE_CUDA_HOST_COMPILER=${CUDA_HOST_COMPILER} \
    -DCMAKE_CUDA_ARCHITECTURES=${CUDA_ARCHITECTURES} \
    -DCMAKE_CUDA_PROPAGATE_HOST_FLAGS=OFF \
    -DCMAKE_CUDA_FLAGS:STRING='${CUDA_NVCC_FLAGS}' \
    -DCMAKE_USER_MAKE_RULES_OVERRIDE_CUDA=${WORKDIR}/cuda-gcc16-rules.cmake \
"
