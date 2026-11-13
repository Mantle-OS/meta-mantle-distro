SUMMARY = "ggml - tensor library for machine learning"
DESCRIPTION = "The main goal of ggml is to be a simple, portable, and efficient tensor library for machine learning with minimal setup."
SECTION = "lib"
HOMEPAGE = "https://github.com/ggml-org"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=223b26b3c1143120c87e2b13111d3e99"

SRC_URI += " \
    git://github.com/ggml-org/ggml.git;branch=master;protocol=https; \
"
SRCREV = "57e2c2ca2a7cae756b1c4dfafa0cdfef9051d234"
PV = "0.29.0"

S = "${WORKDIR}/git"

require ggml-packageconfig.inc

PACKAGECONFIG ?= " \
    ${GGML_DEFAULT_PKGCONF} \
"

inherit ${@bb.utils.contains('PACKAGECONFIG', 'cuda', 'cuda_cmake', 'cmake cmake_extra_sanity', d)}

DEPENDS:append = " \
    git-native \
    pkgconfig-native \
"

do_install:prepend() {
    # Remove sysroot references from CUDA compiler identification artifacts.
    if [ -d "${B}/CMakeFiles" ]; then
        find "${B}/CMakeFiles" -path '*/CompilerIdCUDA/tmp/*.cpp' \
            -exec sed -i \
                -e "s|${RECIPE_SYSROOT_NATIVE}||g" \
                -e "s|${RECIPE_SYSROOT}||g" \
                {} +
    fi
}

FILES:${PN}:append = " ${libdir}/libggml.so.* "
PACKAGES =+ " lib${PN}-base"
SUMMARY:lib${PN}-base = "GGML base tensor and computation library"
FILES:lib${PN}-base = " ${libdir}/libggml-base.so.* "

