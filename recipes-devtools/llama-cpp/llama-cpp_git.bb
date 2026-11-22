SUMMARY = "llama cpp - inference enginge for machine learning"
DESCRIPTION = "Llama is an app that runs the latest open models on your computer. Chat with them, or use them in your other apps."
SECTION = "lib"
HOMEPAGE = "https://llama.app"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=223b26b3c1143120c87e2b13111d3e99"

SRC_URI += " \
    git://github.com/ggml-org/llama.cpp.git;branch=master;protocol=https; \
"
SRCREV = "b1b770d90d94cad9e9ea5b993bdbc383a522fb28"
PV = "1.0.0"

# require recipes-devtools/ggml/ggml-packageconfig.inc
PACKAGECONFIG ?= " \
    ggml \
    wasm-mem64 \
    shared \
    build-common \
    openssl \
    subprocess \
    tools \
    server \
    app \
    examples \
"

PACKAGECONFIG:append:x86-64 = " \
    ${@bb.utils.contains('LICENSE_FLAGS_ACCEPTED', 'nvidia_cuda', 'cuda', '', d)} \
"

inherit ${@bb.utils.contains('PACKAGECONFIG', 'cuda', 'cuda', 'cmake cmake_extra_sanity', d)}
PACKAGECONFIG[cuda] = ",, cuda-userland"

# option(LLAMA_BUILD_IS_DEV "llama: dev build" ON)
PACKAGECONFIG[build-is-dev] = " -DLLAMA_BUILD_IS_DEV=ON, -DLLAMA_BUILD_IS_DEV=OFF,"

# option(LLAMA_USE_SYSTEM_GGML "Use system libggml" OFF)
PACKAGECONFIG[ggml] = " -DLLAMA_USE_SYSTEM_GGML=ON, -DLLAMA_USE_SYSTEM_GGML=OFF, ggml"

# option(LLAMA_WASM_MEM64 "llama: use 64-bit memory in WASM builds" ON)
PACKAGECONFIG[wasm-mem64] = " -DLLAMA_WASM_MEM64=ON, -DLLAMA_WASM_MEM64=OFF,"

# option(BUILD_SHARED_LIBS "build shared libraries" ${BUILD_SHARED_LIBS_DEFAULT})
PACKAGECONFIG[shared] = " -DBUILD_SHARED_LIBS=ON, -DBUILD_SHARED_LIBS=OFF,"


PACKAGECONFIG[all-warnings] = " -DLLAMA_ALL_WARNINGS=ON, -DLLAMA_ALL_WARNINGS=OFF,"
PACKAGECONFIG[all-warnings-3rd-party] = " -DLLAMA_ALL_WARNINGS_3RD_PARTY=ON, -DLLAMA_ALL_WARNINGS_3RD_PARTY=OFF,"
PACKAGECONFIG[fatal-warnings] = " -DLLAMA_FATAL_WARNINGS=ON, -DLLAMA_FATAL_WARNINGS=OFF,"
PACKAGECONFIG[sanitize-thread] = " -DLLAMA_SANITIZE_THREAD=ON, -DLLAMA_SANITIZE_THREAD=OFF,"
PACKAGECONFIG[sanitize-address] = " -DLLAMA_SANITIZE_ADDRESS=ON, -DLLAMA_SANITIZE_ADDRESS=OFF,"
PACKAGECONFIG[sanitize-undefined] = " -DLLAMA_SANITIZE_UNDEFINED=ON, -DLLAMA_SANITIZE_UNDEFINED=OFF,"


PACKAGECONFIG[build-common] = " -DLLAMA_BUILD_COMMON=ON, -DLLAMA_BUILD_COMMON=OFF,"

PACKAGECONFIG[tests] = " -DLLAMA_BUILD_TESTS=ON  -DLLAMA_TESTS_INSTALL=ON,\
                         -DLLAMA_BUILD_TESTS=OFF -DLLAMA_TESTS_INSTALL=OFF,"

PACKAGECONFIG[tools] = " -DLLAMA_BUILD_TOOLS=ON  -DLLAMA_TOOLS_INSTALL=ON, \
                         -DLLAMA_BUILD_TOOLS=OFF -DLLAMA_TOOLS_INSTALL=OFF,"

PACKAGECONFIG[examples] = " -DLLAMA_BUILD_EXAMPLES=ON, -DLLAMA_BUILD_EXAMPLES=OFF,"
PACKAGECONFIG[server] = " -DLLAMA_BUILD_SERVER=ON, -DLLAMA_BUILD_SERVER=OFF,"
PACKAGECONFIG[app] = " -DLLAMA_BUILD_APP=ON, -DLLAMA_BUILD_APP=OFF,"
PACKAGECONFIG[ui] = " -DLLAMA_BUILD_UI=ON, -DLLAMA_BUILD_UI=OFF,"
PACKAGECONFIG[openssl] = " -DLLAMA_OPENSSL=ON, -DLLAMA_OPENSSL=OFF, openssl"
PACKAGECONFIG[subprocess] = " -DLLAMA_SUBPROCESS=ON, -DLLAMA_SUBPROCESS=OFF,"
PACKAGECONFIG[llguidance] = " -DLLAMA_LLGUIDANCE=ON, -DLLAMA_LLGUIDANCE=OFF, cargo-native "
PACKAGECONFIG[mtmd] = " -DLLAMA_BUILD_MTMD=ON, -DLLAMA_BUILD_MTMD=OFF,"


DEPENDS:append = " \
    git-native \
    pkgconfig-native \
"

######################################
# UNSUPPORTED
######################################
# PACKAGECONFIG[connect] = " -DLLAMA_CONNECT=ON, -DLLAMA_CONNECT=OFF,"
# PACKAGECONFIG[use-prebuilt-ui] = " -DLLAMA_USE_PREBUILT_UI=ON, -DLLAMA_USE_PREBUILT_UI=OFF,"
EXTRA_OECMAKE:append = " \
    -DEMSCRIPTEN=OFF \
    -DLLAMA_CONNECT=OFF \
    -DLLAMA_USE_PREBUILT_UI=OFF \
"

PACKAGES =+ " \
    ${PN}-impl \
    ${PN}-server \
    ${PN}-tools \
    ${PN}-app \
    ${PN}-examples \
    ${PN}-tests \
"

FILES:${PN}-server = " \
    ${bindir}/llama-server \
    ${bindir}/llama-cli \
"

FILES:${PN}-app = " ${bindir}/llama"

FILES:${PN}-tools = " \
    ${bindir}/llama-batched-bench \
    ${bindir}/llama-gguf-split \
    ${bindir}/llama-imatrix \
    ${bindir}/llama-bench \
    ${bindir}/llama-completion \
    ${bindir}/llama-perplexity \
    ${bindir}/llama-quantize \
    ${bindir}/llama-tokenize \
    ${bindir}/llama-tts \
    ${bindir}/llama-fit-params \
    ${bindir}/llama-results \
    ${bindir}/llama-mtmd-cli \
    ${bindir}/llama-cvector-generator \
    ${bindir}/llama-export-lora \
"


FILES:${PN}-examples = " \
    ${bindir}/llama-batched \
    ${bindir}/llama-debug \
    ${bindir}/llama-embedding \
    ${bindir}/llama-eval-callback \
    ${bindir}/llama-gguf-hash \
    ${bindir}/llama-gguf \
    ${bindir}/llama-idle \
    ${bindir}/llama-lookahead \
    ${bindir}/llama-lookup \
    ${bindir}/llama-lookup-create \
    ${bindir}/llama-lookup-merge \
    ${bindir}/llama-lookup-stats \
    ${bindir}/llama-parallel \
    ${bindir}/llama-passkey \
    ${bindir}/llama-retrieval \
    ${bindir}/llama-simple \
    ${bindir}/llama-simple-chat \
    ${bindir}/llama-speculative \
    ${bindir}/llama-speculative-simple \
    ${bindir}/llama-gen-docs \
    ${bindir}/llama-finetune \
    ${bindir}/llama-diffusion-cli \
    ${bindir}/llama-convert-llama2c-to-ggml \
"

FILES:${PN}-tests = " "


SUMMARY:${PN}-impl = "llama.cpp Tool Implementation Libraries"
DESCRIPTION:${PN}-impl = "Shared implementation libraries used by llama.cpp command-line tools, server, and unified application."
SECTION:${PN}-impl = "libs"

FILES:${PN}-impl = " \
    ${libdir}/libllama-completion-impl.so \
    ${libdir}/libllama-quantize-impl.so \
    ${libdir}/libllama-batched-bench-impl.so \
    ${libdir}/libllama-server-impl.so \
    ${libdir}/libllama-perplexity-impl.so \
    ${libdir}/libllama-cli-impl.so \
    ${libdir}/libllama-bench-impl.so \
    ${libdir}/libllama-fit-params-impl.so \
"

INSANE_SKIP:${PN}-impl += "dev-so"
