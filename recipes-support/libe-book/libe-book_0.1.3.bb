SUMMARY = "A library for import of reflowable e-book formats"
HOMEPAGE = "http://sourceforge.net/projects/libebook"
SECTION = "text"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=815ca599c9df247a0c7f619bab123dad \
"

SRC_URI = " \
    https://dev-www.libreoffice.org/src/${BPN}-${PV}.tar.xz \
    file://0001-Fix-build-with-ICU-68.patch \
"
SRC_URI[md5sum] = "2956f1c5e7950b0018979a132165da8b"
SRC_URI[sha256sum] = "7e8d8ff34f27831aca3bc6f9cc532c2f90d2057c778963b884ff3d1e34dfe1f9"

inherit autotools pkgconfig

DEPENDS = " \
    icu \
    libxml2 \
    librevenge \
    liblangtag \
    boost-native \
    gperf-native \
"
