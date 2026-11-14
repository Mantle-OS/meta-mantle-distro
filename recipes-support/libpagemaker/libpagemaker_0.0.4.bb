SUMMARY = "A library for import of Adobe PageMaker documents"
HOMEPAGE = "http://wiki.documentfoundation.org/DLP/Libraries/libpagemaker"
SECTION = "text"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=815ca599c9df247a0c7f619bab123dad \
"
SRC_URI = " \
    http://dev-www.libreoffice.org/src/${BPN}/${BPN}-${PV}.tar.xz \
    file://0001-Workaround-build-error-with-gcc8.patch \
"
SRC_URI[md5sum] = "8395dfc5eef11c58d5a9efe3bfe64831"
SRC_URI[sha256sum] = "66adacd705a7d19895e08eac46d1e851332adf2e736c566bef1164e7a442519d"

inherit autotools pkgconfig

DEPENDS = "icu librevenge"
