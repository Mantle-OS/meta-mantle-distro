SUMMARY = "Ragel compiles executable finite state machines from regular languages. Ragel targets C, C++ and ASM. "
HOMEPAGE = "https://www.colm.net/open-source/ragel/"
SECTION = "introspection"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=b454748513fc18f7a002e2a2ae0f653d"

SRC_URI = "\
    git://github.com/adrian-thurston/ragel.git;branch=master;protocol=https \
    file://0001-configure-check-for-cross-compile.patch \
"

SRCREV = "0559d8f0b3e4450b72e8ced99766c32dfc8c9291"

PV = "7.0.4"

inherit autotools-brokensep pkgconfig
AUTORECONF = "1"

# native for the tools and target colm for the headers 
DEPENDS += " colm colm-native "
DEPENDS:class-nativesdk += " nativesdk-colm"


# EXTRA_OECONF:remove = "--disable-static"
EXTRA_OECONF:append = " \
    --disable-manual \
"

EXTRA_OECONF:append:class-native = " \
    --with-colm=${RECIPE_SYSROOT_NATIVE}/usr \
    --with-colm-tools=${RECIPE_SYSROOT_NATIVE}/usr \
"

EXTRA_OECONF:append:class-nativesdk = " \
    --with-colm=${RECIPE_SYSROOT_NATIVE}/usr \
    --with-colm-tools=${RECIPE_SYSROOT_NATIVE}/usr \
"

EXTRA_OECONF:append:class-target = " \
    --with-colm=${RECIPE_SYSROOT}/usr \
    --with-colm-tools=${RECIPE_SYSROOT_NATIVE}/usr \
"

CPPFLAGS:append = " \
    -I${S}/src/include \
"

BBCLASSEXTEND = "native nativesdk"
