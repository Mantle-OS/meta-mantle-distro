PACKAGES =+ " ${PN}-vulkan"
SUMMARY:${PN}-vulkan = "GGML Vulkan computation backend"
DESCRIPTION:${PN}-vulkan = "Vulkan backend for GGML tensor operations, providing GPU-accelerated computation through the Vulkan API and SPIR-V compute shaders."
SECTION:${PN}-vulkan = "libs"

inherit features_check
REQUIRED_DISTRO_FEATURES = "vulkan"

GGML_VULKAN_DEFAULT_PKGCONF ?= " vulkan"

#######
# VULKAN OPTIONS
#######
PACKAGECONFIG[vulkan] = " -DGGML_VULKAN=ON, -DGGML_VULKAN=OFF, vulkan-loader vulkan-headers spirv-headers shaderc-native"

# option(GGML_VULKAN_CHECK_RESULTS            "ggml: run Vulkan op checks"                      OFF)
PACKAGECONFIG[vulkan-check-results] = " -DGGML_VULKAN_CHECK_RESULTS=ON, -DGGML_VULKAN_CHECK_RESULTS=OFF, "

# option(GGML_VULKAN_DEBUG                    "ggml: enable Vulkan debug output"                OFF)
PACKAGECONFIG[vulkan-debug] = " -DGGML_VULKAN_DEBUG=ON, -DGGML_VULKAN_DEBUG=OFF, "

# option(GGML_VULKAN_MEMORY_DEBUG             "ggml: enable Vulkan memory debug output"         OFF)
PACKAGECONFIG[vulkan-memory-debug] = " -DGGML_VULKAN_MEMORY_DEBUG=ON, -DGGML_VULKAN_MEMORY_DEBUG=OFF, "

# option(GGML_VULKAN_SHADER_DEBUG_INFO        "ggml: enable Vulkan shader debug info"           OFF)
PACKAGECONFIG[vulkan-shader-debug-info] = " -DGGML_VULKAN_SHADER_DEBUG_INFO=ON, -DGGML_VULKAN_SHADER_DEBUG_INFO=OFF, "

# option(GGML_VULKAN_VALIDATE                 "ggml: enable Vulkan validation"                  OFF)
PACKAGECONFIG[vulkan-validate] = " -DGGML_VULKAN_VALIDATE=ON, -DGGML_VULKAN_VALIDATE=OFF, "

# option(GGML_VULKAN_RUN_TESTS                "ggml: run Vulkan tests"                          OFF)
PACKAGECONFIG[vulkan-run-tests] = " -DGGML_VULKAN_RUN_TESTS=ON, -DGGML_VULKAN_RUN_TESTS=OFF, "

FILES:${PN}-vulkan = " ${libdir}/libggml-vulkan.so.* "
RDEPENDS:${PN}-vulkan:class-target = " \
    lib${PN}-base \
    vulkan-loader \
"
