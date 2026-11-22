SUMMARY = "A library for import of import of Apple iWork documents"
HOMEPAGE = "http://wiki.documentfoundation.org/DLP/Libraries/libetonyek"
SECTION = "text"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"

SRC_URI = " \
    git://git.libreoffice.org/libetonyek;branch=master;protocol=https \
    file://0001-upgrade-call-to-mdd-3-0.patch \
"
SRCREV = "06467aeb7b4a7082d524d3433c166e12278a3104"
inherit autotools pkgconfig

PACKAGECONFIG ?= " langtag"
PACKAGECONFIG[docs] = " , --without-docs, doxygen-native graphviz-native"
PACKAGECONFIG[langtag] = " , --without-liblangtag, liblangtag"

EXTRA_OECONF:append = " --with-mdds='3.0' "

DEPENDS:append = " \
    glm \
    icu \
    lcms \
    libxml2 \
    librevenge \
    libmdds \
    zlib \
    gperf-native \
"
