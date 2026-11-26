SUMMARY = "Thai language support library"
DESCRIPTION = "LibThai provides Thai language support routines including \
word breaking, input methods, character classification, string \
processing, and dictionary-based text analysis."
HOMEPAGE = "https://github.com/tlwg/libthai"
SECTION = "libs"

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=4bf661c1e3793e55c8d1051bc5e0ae21"

SRC_URI = " \
    git://github.com/tlwg/libthai.git;branch=master;protocol=https \
"
SRCREV = "e10e6af55634a993e60e80521ab03b1947044f38"

inherit autotools pkgconfig

DEPENDS:append = " \
    datrie \
"

PACKAGECONFIG[debug] = "--enable-debug,--disable-debug"
PACKAGECONFIG[ansi] = "--enable-ansi,--disable-ansi"
PACKAGECONFIG[docs] = "--enable-doxygen-doc,--disable-doxygen-doc,doxygen-native"
PACKAGECONFIG[dict] = "--enable-dict,--disable-dict,datrie-native"

PACKAGECONFIG ??= " \
    dict \
"

FILES:${PN} += " \
    ${datadir}/libthai/thbrk.tri \
"
BBCLASSEXTEND = "native"
