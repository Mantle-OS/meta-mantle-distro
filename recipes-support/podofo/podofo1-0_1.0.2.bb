SUMMARY = "A C++ PDF manipulation library"
HOMEPAGE = "https://github.com/podofo/podofo"
SECTION = "text"
# FIXME
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=db979804f025cf55aabec7129cb671ed"

inherit office_cmake

DEPENDS = " \
    zlib \
    openssl \
    lcms \
    libjpeg-turbo \
    tiff \
    libpng \
    freetype \
    fontconfig \
    libxml2 \
"
SRC_URI = " \
    gitsm://github.com/podofo/podofo.git;branch=1.0.x;protocol=https \
"
SRCREV = "7df0491e50f62377f0e4874012efd0110b513a21"
PV = "1.0.2"

PACKAGECONFIG += " shared tools "
PACKAGECONFIG[shared] = "-DPODOFO_BUILD_STATIC=FALSE, -DPODOFO_BUILD_STATIC=TRUE, "
PACKAGECONFIG[tests] = "-DPODOFO_BUILD_TEST=TRUE, -DPODOFO_BUILD_TEST=FALSE, catch2-native"
PACKAGECONFIG[examples] = "-DPODOFO_BUILD_EXAMPLES=TRUE, -DPODOFO_BUILD_EXAMPLES=FALSE, "
PACKAGECONFIG[tools] = "-DPODOFO_BUILD_UNSUPPORTED_TOOLS=TRUE, -DPODOFO_BUILD_UNSUPPORTED_TOOLS=FALSE, "

