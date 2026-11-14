SUMMARY = "Library that parses file formats of QuarkXPress documents"
HOMEPAGE = "https://wiki.documentfoundation.org/DLP/Libraries/libqxp"
SECTION = "text"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"

SRC_URI = "http://dev-www.libreoffice.org/src/${BPN}/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "7011efa81fe8b6b1f2dd7d05d7bfb173"
SRC_URI[sha256sum] = "e137b6b110120a52c98edd02ebdc4095ee08d0d5295a94316a981750095a945c"

inherit autotools pkgconfig

DEPENDS = "icu librevenge"
