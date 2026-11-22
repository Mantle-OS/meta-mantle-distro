SUMMARY = "A library for import of Microsoft Publisher documents"
HOMEPAGE = "http://wiki.documentfoundation.org/DLP/Libraries/libmspub"
SECTION = "text"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING.MPL;md5=815ca599c9df247a0c7f619bab123dad \
"
SRC_URI = " \
    http://dev-www.libreoffice.org/src/${BPN}/${BPN}-${PV}.tar.xz \
    file://0001-Fix-build-with-gcc-10.patch \
"
SRC_URI[md5sum] = "ac6fa9c1c05ece27c58c05e11786fd3a"
SRC_URI[sha256sum] = "ef36c1a1aabb2ba3b0bedaaafe717bf4480be2ba8de6f3894be5fd3702b013ba"

inherit autotools pkgconfig

DEPENDS:append = " \
    icu \
    librevenge \
    boost \
"
