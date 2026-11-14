SUMMARY = "CorelDraw file format importer library for LibreOffice"
HOMEPAGE = "https://wiki.documentfoundation.org/DLP/Libraries/libcdr"
SECTION = "text"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING.MPL;md5=815ca599c9df247a0c7f619bab123dad \
"
SRC_URI = " \
    https://dev-www.libreoffice.org/src/libcdr/${BPN}-${PV}.tar.xz \
    file://0001-Fix-build-with-ICU-68.patch \
"
SRC_URI[sha256sum] = "5666249d613466b9aa1e987ea4109c04365866e9277d80f6cd9663e86b8ecdd4"

inherit autotools pkgconfig

DEPENDS = "icu lcms librevenge"
