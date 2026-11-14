SUMMARY = "C++ client library for the CMIS interface"
HOMEPAGE = "https://github.com/tdf/libcmis"
SECTION = "text"
LICENSE = "GPL-2.0-only AND LGPL-2.1-only AND MPL-1.1"
LIC_FILES_CHKSUM = " \
    file://COPYING.MPL;md5=0117647fecb9a932c25a7bbfc0333c37 \
    file://COPYING.LGPL;md5=4fbd65380cdd255951079008b364516c \
    file://COPYING.GPL;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
"

SRCREV = "bda92cc01837ef933d7b6d5f94dceba6f47ec7e1"
SRC_URI = " \
    git://github.com/tdf/libcmis.git;protocol=https;branch=master \
    file://0001-Avoid-cross-compile-unsafe-paths.patch \
"
PV = "0.6.2"

inherit autotools pkgconfig

DEPENDS += " \
    curl \
    libxml2 \
    cppunit \
    boost \
"

EXTRA_OECONF = " \
    --without-man \
"

do_configure:prepend() {
    touch ${S}/README
}
