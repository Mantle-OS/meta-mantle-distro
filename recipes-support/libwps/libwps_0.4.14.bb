SUMMARY = "Import filter library for MS Works"
HOMEPAGE = "https://sourceforge.net/projects/libwps/"
SECTION = "text"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING.MPL;md5=815ca599c9df247a0c7f619bab123dad \
"

SRC_URI = " \
    https://sourceforge.net/projects/libwps/files/libwps/libwps-0.4.14/libwps-0.4.14.tar.xz \
    file://0001-avoid-hide-base-overload-wkssubdoc.patch \
"
SRC_URI[sha256sum] = "365b968e270e85a8469c6b160aa6af5619a4e6c995dbb04c1ecc1b4dd13e80de"

inherit autotools-brokensep pkgconfig

DEPENDS = "librevenge"
