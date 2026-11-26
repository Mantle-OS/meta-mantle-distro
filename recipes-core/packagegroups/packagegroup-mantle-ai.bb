SUMMARY = "AI and machine learning software stack for Mantle OS on the x86 arch"
DESCRIPTION = "Collection of AI and machine learning runtimes, inference engines, \
hardware acceleration libraries, and supporting utilities for x86 and x86-64 \
Mantle OS systems."

LICENSE = "MIT"
PR = "r1"

PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup
ALLOW_EMPTY:${PN} = "1"

RRECOMMENDS:${PN} = " \
    cuda-toolkit cuda-userland \
    ggml \
    llama-cpp \
"

COMPATIBLE_MACHINE = "^(x86|x86-64)$"
