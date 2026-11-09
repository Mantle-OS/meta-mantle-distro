SUMMARY = "cpp jieba"
DESCRIPTION = "CppJieba is the C++ version of the Jieba Chinese word segmentation tool."
HOMEPAGE = "http://github.com/yanyiwu/cppjieba.git"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=f21f0e69950247ea70c0bbc6f1383299"

inherit cmake cmake_extra_sanity
SRC_URI = " \
    git://github.com/yanyiwu/cppjieba.git;branch=master;protocol=https \
"

SRCREV = "b3602bef7d1f67521a61788a74fb5801a0e62cd3"
PV = "5.6.7"
S = "${WORKDIR}/git"


DEPENDS:append = " \
    limonp \
    googletest \
"

EXTRA_OECMAKE:append = " \
    -DCMAKE_CXX_STANDARD=14 \
    -DCMAKE_CXX_STANDARD_REQUIRED=ON \
"

BBCLASSEXTEND = "native nativesdk"
