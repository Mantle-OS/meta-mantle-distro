SUMMARY = "Rime input method engine"
DESCRIPTION = "A modular and extensible input method engine supporting Chinese phonetic \
and shape-based input methods, YAML-based schemas, Spelling Algebra, chord typing, \
and Traditional and Simplified Chinese conversion."
SECTION = "inputmethods"
HOMEPAGE = "https://github.com/rime/librime"

LICENSE = "BSD-3-Clause AND GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e644d7ab18e9e31eb61c7574b714b7e5"

inherit cmake cmake_extra_sanity pkgconfig
SRC_URI = " \
    git://github.com/rime/librime.git;branch=master;protocol=https;name=rime \
    file://0001-fix-simplifier-handle-OpenCC-converters-without-conversion-chains.patch \
"

SRCREV_rime = "33e78140250125871856cdc5b42ddc6a5fcd3cd4"
SRCREV_FORMAT = "rime_octagram_lua_charcode_proto_predict"

require librime-plugins.inc

## internal depends
DEPENDS:append = " \
    yaml-cpp \
    boost \
    marisa-trie \
    leveldb \
    opencc \
"

PACKAGECONFIG[shared] = "-DBUILD_SHARED_LIBS=ON,-DBUILD_SHARED_LIBS=OFF"
PACKAGECONFIG[static-deps] = "-DBUILD_STATIC=ON,-DBUILD_STATIC=OFF"
PACKAGECONFIG[merged-plugins] = "-DBUILD_MERGED_PLUGINS=ON,-DBUILD_MERGED_PLUGINS=OFF"
PACKAGECONFIG[data] = "-DBUILD_DATA=ON,-DBUILD_DATA=OFF"
PACKAGECONFIG[sample] = "-DBUILD_SAMPLE=ON,-DBUILD_SAMPLE=OFF"
PACKAGECONFIG[test] = "-DBUILD_TEST=ON,-DBUILD_TEST=OFF,googletest"
PACKAGECONFIG[separate-libs] = "-DBUILD_SEPARATE_LIBS=ON,-DBUILD_SEPARATE_LIBS=OFF"
PACKAGECONFIG[logging] = "-DENABLE_LOGGING=ON,-DENABLE_LOGGING=OFF,glog gflags"
PACKAGECONFIG[stderr] = "-DALSO_LOG_TO_STDERR=ON,-DALSO_LOG_TO_STDERR=OFF"
PACKAGECONFIG[asan] = "-DENABLE_ASAN=ON,-DENABLE_ASAN=OFF"
PACKAGECONFIG[private-headers] = "-DINSTALL_PRIVATE_HEADERS=ON,-DINSTALL_PRIVATE_HEADERS=OFF"
PACKAGECONFIG[external-plugins] = "-DENABLE_EXTERNAL_PLUGINS=ON,-DENABLE_EXTERNAL_PLUGINS=OFF"
PACKAGECONFIG[threading] = "-DENABLE_THREADING=ON,-DENABLE_THREADING=OFF"
PACKAGECONFIG[timestamp] = "-DENABLE_TIMESTAMP=ON,-DENABLE_TIMESTAMP=OFF"
PACKAGECONFIG[x11] = ",,virtual/libx11"


PACKAGECONFIG ??= " \
    shared \
    external-plugins \
    logging \
    threading \
    timestamp \
    test \
    octagram-plugin \
    lua-plugin \
    charcode-plugin \
    proto-plugin \
    predict-plugin \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)} \
"

CXXFLAGS:append = " -DGLOG_USE_GLOG_EXPORT -DGLOG_USE_GFLAGS"
EXTRA_OECMAKE += " \
    -DRIME_DATA_DIR=rime-data \
    -DRIME_PLUGINS_DIR=rime-plugins \
"

FILES:${PN} += " \
    ${libdir}/librime.so.* \
    ${bindir}/rime_deployer \
    ${bindir}/rime_dict_manager \
    ${bindir}/rime_patch \
    ${bindir}/rime_table_decompiler \
"

BBCLASSEXTEND = "native nativesdk"
