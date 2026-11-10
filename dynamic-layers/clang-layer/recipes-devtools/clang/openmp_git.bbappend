# The native LLVM 18 OpenMP AMDGPU offloading plugin fails to link
# against hsa_amd_memory_async_copy_on_engine.
# Keep CPU OpenMP and other offloading plugins enabled.
EXTRA_OECMAKE:append:class-native = " -DLIBOMPTARGET_BUILD_AMDGPU_PLUGIN=OFF"
