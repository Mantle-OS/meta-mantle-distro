SUMMARY = "limonp"
DESCRIPTION = "C++ headers(hpp) with Python style."
HOMEPAGE = "https://github.com/yanyiwu/limonp.git"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=f21f0e69950247ea70c0bbc6f1383299"

SRC_URI = " \
    git://github.com/yanyiwu/limonp.git;branch=master;protocol=https \
"

SRCREV = "9d74077dfcdf8073536c97a00bb79d7a3c3fdaba"
PV = "1.0.2"
S = "${WORKDIR}/git"
inherit cmake cmake_extra_sanity
PACKAGECONFIG[tests] = " -DENABLE_UNIT_TESTS=ON, -DENABLE_UNIT_TESTS=OFF, google-test"

BBCLASSEXTEND = "native nativesdk"
