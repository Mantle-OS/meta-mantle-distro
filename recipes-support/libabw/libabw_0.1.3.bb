SUMMARY = "Library that parses the file format of AbiWord documents"
HOMEPAGE = "https://wiki.documentfoundation.org/DLP/Libraries/libabw"
SECTION = "text"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING.MPL;md5=815ca599c9df247a0c7f619bab123dad \
"

SRC_URI = "http://dev-www.libreoffice.org/src/${BPN}/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "0ec0331abbbf653f08eecceb1fb787a0"
SRC_URI[sha256sum] = "e763a9dc21c3d2667402d66e202e3f8ef4db51b34b79ef41f56cacb86dcd6eed"

inherit autotools pkgconfig

DEPENDS = " \
    gperf-native \
    libxml2 \
    librevenge \
"
