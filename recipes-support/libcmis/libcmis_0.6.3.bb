SUMMARY = "C++ client library for the Content Management Interoperability Services (CMIS) protocol"
DESCRIPTION = "libcmis is a C++ client library implementing the Content Management \
Interoperability Services (CMIS) protocol. It provides access to content \
management repositories through a common interface and includes support \
for several CMIS-compatible services."
HOMEPAGE = "https://github.com/tdf/libcmis"
SECTION = "text"
LICENSE = "GPL-2.0-only AND LGPL-2.1-only AND MPL-1.1"
LIC_FILES_CHKSUM = " \
    file://COPYING.MPL;md5=0117647fecb9a932c25a7bbfc0333c37 \
    file://COPYING.LGPL;md5=4fbd65380cdd255951079008b364516c \
    file://COPYING.GPL;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
"

SRC_URI = " \
    git://github.com/tdf/libcmis.git;protocol=https;branch=master \
    file://0001-fix-configure-call-on-boost-1.9.2.patch \
"

SRCREV = "9221b621398213c988f00c3feb15d5a0601d5ec4"
PV = "0.6.3"

inherit autotools pkgconfig

DEPENDS += " \
    curl \
    libxml2 \
    cppunit \
    boost \
"
CXXFLAGS:append = " -Wno-error=unused-but-set-variable"
EXTRA_OECONF = " \
    --without-man \
"

do_configure:prepend() {
    touch ${S}/README
}
