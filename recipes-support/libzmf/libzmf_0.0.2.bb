SUMMARY = "Libzmf is a library that parses the file format of Zoner Callisto/Draw documents"
HOMEPAGE = "https://wiki.documentfoundation.org/DLP/Libraries/libzmf"
SECTION = "text"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"
SRC_URI = "http://dev-www.libreoffice.org/src/${BPN}/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "e4b089f35a85a042ba4da3c143b1dc5a"
SRC_URI[sha256sum] = "27051a30cb057fdb5d5de65a1f165c7153dc76e27fe62251cbb86639eb2caf22"

inherit autotools pkgconfig

DEPENDS += " \
    icu \
    zlib \
    libpng \
    librevenge \
"
