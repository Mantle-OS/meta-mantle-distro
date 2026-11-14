SUMMARY = "Library for importing WordPerfect (tm) documents"
HOMEPAGE = "http://libwpd.sourceforge.net/"
SECTION = "text"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING.MPL;md5=815ca599c9df247a0c7f619bab123dad \
"

SRC_URI = " \
    http://dev-www.libreoffice.org/src/${BPN}-${PV}.tar.xz \
    file://0001-Add-include-cstddef-to-fix-build-with-gcc11.patch \
"
SRC_URI[md5sum] = "d5e44712c4674d499afb8e89d830fcad"
SRC_URI[sha256sum] = "2465b0b662fdc5d4e3bebcdc9a79027713fb629ca2bff04a3c9251fdec42dd09"

inherit autotools pkgconfig perlnative

DEPENDS = "librevenge"
