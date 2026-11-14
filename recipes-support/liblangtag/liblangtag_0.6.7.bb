SUMMARY = "An interface library to access tags for identifying languages"
HOMEPAGE = "http://tagoh.bitbucket.org/liblangtag/"
SECTION = "text"
LICENSE = "LGPL-3.0-only"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=e6a600fd5e1d9cbde2d983680233ad02 \
"

DEPENDS = " \
    libxml2 \
    autoconf-archive-native \
"

SRC_URI = " \
    https://bitbucket.org/tagoh/${BPN}/downloads/${BPN}-${PV}.tar.bz2 \
"

SRC_URI[sha256sum] = "5ed6bcd4ae3f3c05c912e62f216cd1a44123846147f729a49fb5668da51e030e"
inherit autotools pkgconfig gobject-introspection
AUTOTOOLS_AUTORECONF = "yes"

PACKAGECONFIG ?= " gtk-doc"
PACKAGECONFIG[gtk-doc] = "\
    --enable-gtk-doc,--disable-gtk-doc,\
    gtk-doc-native libxslt-native docbook-xsl-stylesheets-native python3-pygments-native\
"

export GIR_EXTRA_LIBS_PATH = "${B}/liblangtag/.libs"

EXTRA_OECONF += " --disable-test --enable-gtk-doc"
EXTRA_AUTORECONF += "-I m4macros -I m4"

do_configure:prepend() {
    rm -f ${S}/configure
}

BBCLASSEXTEND = "native"
