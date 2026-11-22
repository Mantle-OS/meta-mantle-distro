SUMMARY = "Open Chinese Convert"
DESCRIPTION =  "Open Chinese Convert (OpenCC, 開放中文轉換) is an open source project for high-quality conversion between Traditional Chinese, Simplified Chinese, Japanese Shinjitai, and regional wording across Mainland China, Taiwan, and Hong Kong. It provides dictionaries, a reusable library, conversion tools, and dictionary generation tools."
HOMEPAGE = "https://opencc.js.org"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=a53192195ed45e801ce0642da66d65a7"
SRC_URI = " \
    git://github.com/BYVoid/OpenCC.git;branch=master;protocol=https; \
    file://0002-Import-and-cross-compile-support-for-data-tools.patch \
"
SRCREV = "025f371dc76b598d77384fbdab90c937471844d8"
S = "${WORKDIR}/git"

inherit cmake cmake_extra_sanity python3targetconfig

PV = "1.4.2"

PACKAGECONFIG[docs] = " -DBUILD_DOCUMENTATION=ON, -DBUILD_DOCUMENTATION=OFF, doxygen-native"
PACKAGECONFIG[shared] = " -DBUILD_SHARED_LIBS=ON, -DBUILD_SHARED_LIBS=OFF, "
PACKAGECONFIG[jieba] = " -DBUILD_OPENCC_JIEBA_PLUGIN=ON, -DBUILD_OPENCC_JIEBA_PLUGIN=OFF, cppjieba limonp"

PACKAGECONFIG[darts-clone] = " -DUSE_SYSTEM_DARTS=ON , -DUSE_SYSTEM_DARTS=OFF, darts-clone"
PACKAGECONFIG[benchmark] = " -DENABLE_BENCHMARK=ON -DUSE_SYSTEM_GOOGLE_BENCHMARK=ON, -DENABLE_BENCHMARK=OFF -DUSE_SYSTEM_GOOGLE_BENCHMARK=OFF, googlebenchmark"
PACKAGECONFIG[tests] = " -DENABLE_GTEST=ON -DBUILD_TESTING=ON -DUSE_SYSTEM_GTEST=ON, -DENABLE_GTEST=OFF -DBUILD_TESTING=OFF, googletest"
PACKAGECONFIG[marisa] = " -DUSE_SYSTEM_MARISA=ON , -DUSE_SYSTEM_MARISA=OFF, marisa-trie"
PACKAGECONFIG[pybindings] = " \
    -DUSE_SYSTEM_PYBIND11=ON -DBUILD_PYTHON=ON -DPYBIND11_PYTHONLIBS_OVERWRITE=OFF -DPYTHON_INCLUDE_DIR=${STAGING_INCDIR}/${PYTHON_DIR}${PYTHON_ABI}, \
    -DUSE_SYSTEM_PYBIND11=OFF -DBUILD_PYTHON=OFF, \
    python3-pybind11 \
"
PACKAGECONFIG[rapidjson] = " -DUSE_SYSTEM_RAPIDJSON=ON, -DUSE_SYSTEM_RAPIDJSON=OFF, rapidjson"
PACKAGECONFIG[tclap] = " -DUSE_SYSTEM_TCLAP=ON, -DUSE_SYSTEM_TCLAP=OFF, tclap"

PACKAGECONFIG[full-profile] = " -DOPENCC_SOURCE_PACKAGE_PROFILE=full,, "
PACKAGECONFIG[opencc-profile] = " -DOPENCC_SOURCE_PACKAGE_PROFILE=opencc,, "
PACKAGECONFIG[opencc-jieba-profile] = " -DOPENCC_SOURCE_PACKAGE_PROFILE=opencc-jieba, ,"

PACKAGECONFIG[ocd2-format] = " -DOPENCC_DICT_FORMAT=ocd2, ,"
PACKAGECONFIG[ocd-format] = " -DOPENCC_DICT_FORMAT=ocd, ,"
PACKAGECONFIG[text-format] = " -DOPENCC_DICT_FORMAT=text, ,"

PACKAGECONFIG ?= " \
    shared \
    jieba \
    full-profile \
    ocd2-format \
    \
    darts-clone \
    marisa \
    pybindings \
    rapidjson \
    tclap \
"

EXTRA_OECMAKE:append = " \
    -DOPENCC_ENABLE_INSTALL=ON \
"

DEPENDS:append:class-target = " opencc-native"

EXTRA_OECMAKE:append:class-target = " \
    -DOPENCC_DICT_EXE=${STAGING_BINDIR_NATIVE}/opencc_dict \
    -DOPENCC_EXE=${STAGING_BINDIR_NATIVE}/opencc \
"

do_install:append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'pybindings', 'true', 'false', d)};
    then
        install -d ${D}${PYTHON_SITEPACKAGES_DIR}/opencc/clib

        install -m 0644 ${S}/python/opencc/__init__.py ${D}${PYTHON_SITEPACKAGES_DIR}/opencc/
        install -m 0644 ${S}/python/opencc/cli.py ${D}${PYTHON_SITEPACKAGES_DIR}/opencc/
        install -m 0644 ${S}/python/opencc/py.typed ${D}${PYTHON_SITEPACKAGES_DIR}/opencc/
        install -m 0644 ${S}/python/opencc/clib/__init__.py ${D}${PYTHON_SITEPACKAGES_DIR}/opencc/clib/

        install -m 0755 ${B}/opencc_clib*.so ${D}${PYTHON_SITEPACKAGES_DIR}/opencc/clib/
    fi
}
PACKAGES =+ " \
    ${PN}-tools \
    ${PN}-jieba-data \
    ${PN}-data \
    ${PN}-jieba \
    python3-${PN} \
"


SUMMARY:${PN}-tools = "OpenCC command-line conversion and dictionary utilities"
FILES:${PN}-tools = " \
    ${bindir}/opencc \
    ${bindir}/opencc_dict \
    ${bindir}/opencc_phrase_extract \
"

SUMMARY:${PN}-data = "OpenCC conversion dictionaries and configuration data"
FILES:${PN}-data = " \
    ${datadir}/opencc/*.json \
    ${datadir}/opencc/*.ocd2 \
"

SUMMARY:${PN}-jieba = "OpenCC Jieba word segmentation plugin"
FILES:${PN}-jieba = " \
    ${libdir}/opencc/plugins/libopencc-jieba.so \
"

SUMMARY:${PN}-jieba-data = "OpenCC Jieba dictionaries and configuration data"
FILES:${PN}-jieba-data = " \
    ${datadir}/opencc/jieba_dict/ \
    ${datadir}/opencc/*_jieba.json \
"

SUMMARY:python3-${PN} = "Python 3 bindings for Open Chinese Convert"
FILES:python3-${PN} = " \
    ${PYTHON_SITEPACKAGES_DIR}/opencc/ \
"

RDEPENDS:${PN}-tools += "${PN} ${PN}-data"
RDEPENDS:${PN}-jieba-data += "${PN}-data"
RDEPENDS:${PN}-jieba += "${PN} ${PN}-jieba-data"
RDEPENDS:${PN}-pylib += "${PN} ${PN}-data"

BBCLASSEXTEND = "native nativesdk"
