SUMMARY = "Tools for accessing and converting various ebook file formats"
HOMEPAGE = "https://sourceforge.net/projects/ebook-tools"
SECTION = "text"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a6c399c223550f0049f693bfbef379ee"

SRC_URI = "https://sourceforge.net/projects/ebook-tools/files/ebook-tools/0.2.2/ebook-tools-0.2.2.tar.gz"
SRC_URI[sha256sum] = "cbc35996e911144fa62925366ad6a6212d6af2588f1e39075954973bbee627ae"

inherit office_cmake

DEPENDS += " \
    libxml2 \
    libzip \
"

PACKAGE_BEFORE_PN = "${PN}-utils"
FILES:${PN}-utils = " \
    ${bindir} \
"
