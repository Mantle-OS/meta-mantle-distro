SUMMARY = "Library for generating OpenDocument Format documents"
DESCRIPTION = "libodfgen is a library for generating documents in the OpenDocument \
Format (ODF). It provides document generation functionality for applications \
and file format conversion libraries built around librevenge."
HOMEPAGE = "https://sourceforge.net/p/libwpd/wiki/libodfgen"
SECTION = "text"

LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING.MPL;md5=815ca599c9df247a0c7f619bab123dad"
SRC_URI = " \
    https://sourceforge.net/projects/libwpd/files/libodfgen/libodfgen-0.1.8/libodfgen-0.1.8.tar.xz \
    file://0001-odfgen-include-cstdint-for-uint8_t.patch \
"
SRC_URI[sha256sum] = "55200027fd46623b9bdddd38d275e7452d1b0ff8aeddcad6f9ae6dc25f610625"

inherit autotools pkgconfig perlnative

DEPENDS = " \
    librevenge \
    libxml2 \
"
