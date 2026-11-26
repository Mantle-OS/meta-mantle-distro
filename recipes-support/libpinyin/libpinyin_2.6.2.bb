SUMMARY = "Intelligent Chinese Pinyin and Zhuyin input method libraries"
DESCRIPTION = "Libpinyin provides the core algorithms and language model \
infrastructure for intelligent sentence-based Chinese input methods, \
including Pinyin and Zhuyin phonetic input, phrase lookup, phonetic \
parsing, and statistical language model processing."

HOMEPAGE = "https://github.com/libpinyin/libpinyin"
BUGTRACKER = "https://github.com/libpinyin/libpinyin/issues"
SECTION = "libs"

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = " \
    git://github.com/libpinyin/libpinyin.git;branch=main;protocol=https \
    http://downloads.sourceforge.net/libpinyin/models/model20.text.tar.gz;name=model20;subdir=${S};unpack=0 \
    file://0001-autotools-Add-cross-compilation-support-for-model-da.patch \
"

SRCREV = "954ecc3522686fb30a78d12080da1145df3f24da"
SRC_URI[model20.sha256sum] = "59c68e89d43ff85f5a309489499cbcde282d2b04bd91888734884b7defcb1155"

inherit autotools pkgconfig

DEPENDS:append:class-target = " ${PN}-native"
DEPENDS:append = " \
    glib-2.0 \
    git-native \
"

PACKAGECONFIG[berkeley-db] = "--with-dbm=BerkeleyDB,,db"
PACKAGECONFIG[kyotocabinet] = "--with-dbm=KyotoCabinet,,kyotocabinet"
PACKAGECONFIG[zhuyin] = "--enable-libzhuyin,--disable-libzhuyin"
PACKAGECONFIG[tests] = "--enable-tests,--disable-tests"
PACKAGECONFIG[utils] = "--enable-utils,--disable-utils"
PACKAGECONFIG[data] = "--enable-data,--disable-data"
PACKAGECONFIG[docs] = "--enable-docs,--disable-docs"


PACKAGECONFIG ??= " \
    berkeley-db \
    utils \
"

PACKAGECONFIG:append:class-target = " data zhuyin"

EXTRA_OECONF:append:class-target = " \
    --with-system-tools=${STAGING_BINDIR_NATIVE} \
"

RDEPENDS:${PN} += "${PN}-data"
RDEPENDS:${PN}-dev += "${@bb.utils.contains('PACKAGECONFIG', 'zhuyin', 'libzhuyin', '', d)}"

PACKAGES =+ " \
    libzhuyin \
    libzhuyin-dev \
    ${PN}-data \
    ${PN}-tools \
"

SUMMARY:libzhuyin = "Intelligent Chinese Zhuyin input method library"
DESCRIPTION:libzhuyin = "Library providing phonetic parsing, phrase \
lookup, and statistical language model algorithms for intelligent \
sentence-based Chinese Zhuyin input methods."
FILES:libzhuyin = " \
    ${libdir}/libzhuyin.so.* \
"
RDEPENDS:libzhuyin += "${PN}-data"

SUMMARY:libzhuyin-dev = "Development files for libzhuyin"
DESCRIPTION:libzhuyin-dev = "Headers, library symlinks and pkg-config metadata for developing applications using libzhuyin."
FILES:libzhuyin-dev = " \
    ${includedir}/libpinyin-${PV}/zhuyin.h \
    ${includedir}/libpinyin-${PV}/zhuyin_custom2.h \
    ${libdir}/libzhuyin.so \
    ${libdir}/pkgconfig/libzhuyin.pc \
"

RDEPENDS:libzhuyin-dev = " \
    libzhuyin \
    ${PN}-dev \
"

SUMMARY:${PN}-data = "Language model data for Pinyin and Zhuyin"
DESCRIPTION:${PN}-data = "Shared binary language models, phrase indexes, \
phonetic indexes, punctuation tables, and statistical dictionaries \
used by the Pinyin and Zhuyin input method libraries."
FILES:${PN}-data = " \
    ${libdir}/libpinyin/data \
"

SUMMARY:${PN}-tools = "Libpinyin language model generation utilities"
DESCRIPTION:${PN}-tools = "Utilities for generating binary language \
models, importing interpolation data, and generating unigram data."
FILES:${PN}-tools = " \
    ${bindir}/gen_binary_files \
    ${bindir}/gen_unigram \
    ${bindir}/import_interpolation \
"

PROVIDES:append = " libzhuyin"
RPROVIDES:${PN}:append = " libzhuyin"

BBCLASSEXTEND = "native"
