SUMMARY = "cpp jieba"
DESCRIPTION = "CppJieba is the C++ version of the Jieba Chinese word segmentation tool."
HOMEPAGE = "http://github.com/yanyiwu/cppjieba.git"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f21f0e69950247ea70c0bbc6f1383299"

inherit cmake cmake_extra_sanity
SRC_URI = " \
    git://github.com/yanyiwu/cppjieba.git;branch=master;protocol=https \
"
SRCREV = "cbdf949eadb8887f060473b5dc6d3194655c8bef"

PACKAGECONFIG[tests] = "-DCPPJIEBA_TOP_LEVEL_PROJECT=ON, -DCPPJIEBA_TOP_LEVEL_PROJECT=OFF, googletest"
DEPENDS:append = " \
    limonp \
"
PACKAGECONFIG ??= ""

EXTRA_OECMAKE:append = " \
    -DCMAKE_CXX_STANDARD=14 \
    -DCMAKE_CXX_STANDARD_REQUIRED=ON \
"

BBCLASSEXTEND = "native nativesdk"
