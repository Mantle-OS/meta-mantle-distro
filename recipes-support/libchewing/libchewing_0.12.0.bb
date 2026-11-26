SUMMARY = "Intelligent phonetic input method library for Traditional Chinese"
DESCRIPTION = "Libchewing provides an intelligent Zhuyin (Bopomofo) phonetic \
input method engine for Traditional Chinese. It supports phrase selection, \
predictive input, and user phrase learning, and provides a C API for integration \
with input method frameworks such as Fcitx5."
HOMEPAGE = "https://github.com/chewing/libchewing"
SECTION = "libs"

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

inherit cmake cmake_extra_sanity pkgconfig

SRC_URI = " \
    gitsm://github.com/chewing/libchewing.git;branch=master;protocol=https \
    file://0001-Cross-compile-and-remove-annyoing-cmake-helper-tool.patch \
    file://0002-data-Really-use-the-CHEWING_CLI-var-in-cmake.patch;patchdir=data \
"

SRCREV = "05ae6bcb9309c466a1b32d69c146bc583be04747"

DEPENDS:append = " \
    ncurses \
    git-native \
    chewing-capi chewing-capi-native \
"

PACKAGECONFIG[shared] = "-DBUILD_SHARED_LIBS=ON, -DBUILD_SHARED_LIBS=OFF, "
# PACKAGECONFIG[sqlite3] = "-DWITH_SQLITE3=true, -DWITH_SQLITE3=false, sqlite3"

# option(BUILD_DOC "Build documentation" ON)
PACKAGECONFIG[docs] = "-DBUILD_DOC=true, -DBUILD_DOC=false, "

# option(BUILD_DATA "Build chewing dictionaries" ON)
PACKAGECONFIG[data] = "-DBUILD_DATA=ON, -DBUILD_DATA=OFF, "

# option(USE_VALGRIND "Use valgrind when testing" true)
PACKAGECONFIG[valgrind] = "-DUSE_VALGRIND=true, -DUSE_VALGRIND=false, valgrind"


# BUILD_TESTING
PACKAGECONFIG[testing] = "-DBUILD_TESTING=ON, -DBUILD_TESTING=OFF"

PACKAGECONFIG:append = " \
    shared \
    data \
"


EXTRA_OECMAKE:append = " \
    -DUSE_SYSTEM_CHEWING_CLI=ON \
    -DCHEWING_CLI=${STAGING_BINDIR_NATIVE}/chewing-cli \
"

FILES:${PN} += " \
    ${datadir}/libchewing \
"
