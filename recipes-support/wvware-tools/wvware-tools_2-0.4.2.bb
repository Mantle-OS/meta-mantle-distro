SUMMARY = "Programs for accessing Microsoft Word documents"
HOMEPAGE = "http://wvware.sourceforge.net/"
SECTION = "text"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=3bf50002aefd002f49e7bb854063f7e7"

DEPENDS = " libgsf glib-2.0 libpng imagemagick virtual/libiconv libxml2"

SRC_URI = " \
    https://sourceforge.net/projects/wvware/files/wv2-0.4.2.tar.bz2 \
    file://0001-install-wv2-cmake-libdir-cmake.patch \
    file://0002-wv2-0.4.2-disable-libtool.patch \
"

SRC_URI[sha256sum] = "9f2b6d3910cb0e29c9ff432f935a594ceec0101bca46ba2fc251aff251ee38dc"
S = "${UNPACKDIR}/wv2-0.4.2"

inherit office_cmake
PACKAGECONFIG ?= "zlib"
PACKAGECONFIG[zlib] = "-DWITH_ZLIB=ON,-DWITH_ZLIB=OFF,zlib"

CFLAGS:append  = " -I${STAGING_INCDIR}/libxml2"
CXXFLAGS:append = " -I${STAGING_INCDIR}/libxml2"
# Skip the runtime part of TRY_RUN()
EXTRA_OECMAKE:append = " \
    -DCMAKE_CXX_STANDARD=17 -DCMAKE_CXX_STANDARD_REQUIRED=ON \
    -DMODERN_ICONV_RUN=0 \
    -DMODERN_ICONV_COMPILE=TRUE \
    -DMODERN_ICONV_RUN__TRYRUN_OUTPUT_STDOUT= \
    -DMODERN_ICONV_RUN__TRYRUN_OUTPUT_STDERR= \
    -DWORDS_BIGENDIAN=0 \
    -DCMAKE_POLICY_VERSION_MINIMUM=3.5 \
"

EXTRA_OECMAKE:append:libc-musl = " \
    -DICONV_INCLUDE_DIR=${STAGING_INCDIR} \
    -DICONV_LIBRARIES=${STAGING_LIBDIR}/libiconv.so \
    -DICONV_REQUIRES_CONST=1 \
    -DCMAKE_POLICY_VERSION_MINIMUM=3.5 \
"

EXTRA_OECMAKE:append:libc-glibc = " -DICONV_REQUIRES_CONST=0"
LDFLAGS:append:libc-musl = " -liconv"

FILES:${PN}-dev += " \
    ${libdir}/cmake/wvWare \
"
