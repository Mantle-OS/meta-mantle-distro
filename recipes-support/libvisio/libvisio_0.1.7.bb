SUMMARY = "A library for import of Microsoft Visio diagrams"
HOMEPAGE = "http://wiki.documentfoundation.org/DLP/Libraries/libvisio"
SECTION = "text"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING.MPL;md5=815ca599c9df247a0c7f619bab123dad \
"
SRC_URI = "http://dev-www.libreoffice.org/src/${BPN}/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "812a21fee1f7b096612ca83fdafb0db7"
SRC_URI[sha256sum] = "8faf8df870cb27b09a787a1959d6c646faa44d0d8ab151883df408b7166bea4c"

inherit autotools pkgconfig

DEPENDS = "icu libxml2 librevenge"
