SUMMARY = "Transform bitmaps into vector graphics"
HOMEPAGE = "http://potrace.sourceforge.net"
SECTION = "lib"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=6ccdcb5472e5e7e8061f4128ef20e049"

SRC_URI = "http://downloads.sourceforge.net/${BPN}/${BP}.tar.gz"
SRC_URI[sha256sum] = "be8248a17dedd6ccbaab2fcc45835bb0502d062e40fbded3bc56028ce5eb7acc"
PV = "1.16"

DEPENDS = "zlib"

inherit autotools

EXTRA_OECONF = "--with-libpotrace"
