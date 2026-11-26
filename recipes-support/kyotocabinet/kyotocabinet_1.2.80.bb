SUMMARY = "High-performance key-value database library"
DESCRIPTION = "Kyoto Cabinet is a database library for managing \
key-value records using hash tables and B+ trees. It supports \
variable-length binary and string keys and values, concurrent \
access, and optional data compression."
HOMEPAGE = "https://dbmx.net/kyotocabinet/"
SECTION = "libs"

LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = " \
    https://dbmx.net/kyotocabinet/pkg/kyotocabinet-${PV}.tar.gz \
    file://0001-Remove-local-paths-config-in-file.patch \
"
SRC_URI[sha256sum] = "4c85d736668d82920bfdbdb92ac3d66b7db1108f09581a769dd9160a02def349"

inherit autotools
B = "${S}"

PACKAGECONFIG[debug] = "--enable-debug,--disable-debug"
PACKAGECONFIG[opt] = "--enable-opt,--disable-opt"
PACKAGECONFIG[profile] = "--enable-profile,--disable-profile"
PACKAGECONFIG[uyield] = "--enable-uyield,--disable-uyield"
PACKAGECONFIG[atomic] = "--enable-atomic,--disable-atomic"
PACKAGECONFIG[zlib] = "--enable-zlib,--disable-zlib,zlib"
PACKAGECONFIG[lzo] = "--enable-lzo,--disable-lzo,lzo"
PACKAGECONFIG[lzma] = "--enable-lzma,--disable-lzma,xz"

PACKAGECONFIG_CONFARGS:append = " --enable-shared"

PACKAGECONFIG ??= " \
    opt \
    atomic \
    zlib \
    lzo \
    lzma \
"
