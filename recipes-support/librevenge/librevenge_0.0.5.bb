SUMMARY = "library for REVerses ENGineered formats filters"
HOMEPAGE = "http://sf.net/p/libwpd/librevenge/"
SECTION = "text"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING.MPL;md5=815ca599c9df247a0c7f619bab123dad"

SRC_URI = " \
    http://dev-www.libreoffice.org/src/${BPN}-${PV}.tar.bz2 \
"
#file://0001-Fix-build-with-gcc8.patch
SRC_URI[sha256sum] = "5892ca6796f7a2a93d580832e907e849b19d980b40d326a283b18877ab6de0c5"
inherit autotools pkgconfig
DEPENDS = "cppunit zlib boost"

do_install:append() {
    sed -i '/^Libs:/ s/$/ -lboost_system/' ${D}${libdir}/pkgconfig/librevenge-0.0.pc
}
