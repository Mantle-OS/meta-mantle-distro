SUMMARY = "A C++ PDF manipulation library"
HOMEPAGE = "https://github.com/podofo/podofo"

SECTION = "text"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"

inherit office_cmake

DEPENDS = " \
    zlib \
    openssl \
    libidn \
    libjpeg-turbo \
    tiff \
    libpng \
    libunistring \
    freetype \
    fontconfig \
    boost \
    pkgconfig-native \
    lua \
"
#  cppunit-native

SRC_URI = " \
    gitsm://github.com/podofo/podofo.git;branch=master;protocol=https \
    file://0001-disable-tests-for-now.patch \
"
SRCREV = "6667ddb434ef4271fab469d6b6e9623d1c12a0bf"
PV = "0.9.8"

EXTRA_OECMAKE:append = " -DPODOFO_BUILD_SHARED=TRUE"
