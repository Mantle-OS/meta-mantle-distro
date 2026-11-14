SUMMARY = "A library for import of Macromedia/Adobe FreeHand documents"
HOMEPAGE = "http://wiki.documentfoundation.org/DLP/Libraries/libfreehand"
SECTION = "text"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=815ca599c9df247a0c7f619bab123dad \
"
SRC_URI = " \
    http://dev-www.libreoffice.org/src/${BPN}/${BPN}-${PV}.tar.xz \
    file://0001-WaE-unnecessary-parentheses-in-declaration-of-paddin.patch \
    file://0002-Fix-build-with-gcc-9.patch \
    file://0003-Add-missing-semicolon-to-fix-build-with-icu-65.1.patch \
"
SRC_URI[md5sum] = "c3788f5686839fd097bd77d8f51c3d04"
SRC_URI[sha256sum] = "0e422d1564a6dbf22a9af598535425271e583514c0f7ba7d9091676420de34ac"

inherit autotools pkgconfig

DEPENDS = " \
    icu \
    lcms \
    librevenge \
    gperf-native \
"
