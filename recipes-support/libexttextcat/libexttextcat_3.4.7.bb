SUMMARY = "N-Gram-Based Text Categorization library for language guessing"
HOMEPAGE = "http://www.freedesktop.org/wiki/Software/libexttextcat"
SECTION = "text"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=873b1664864cb88b5f5b4eca62deb23c"

SRC_URI = "git://github.com/LibreOffice/libexttextcat.git;branch=master;protocol=https"
SRCREV = "3b85d7e9fd34990f5be6a7e321c2e6b135064464"

inherit autotools pkgconfig
FILES:${PN} += "${datadir}/vala"
