SUMMARY = "darts clone"
DESCIPTION = "Darts-clone: A clone of Darts (Double-ARray Trie System)"
HOMEPAGE = "https://github.com/s-yata/darts-clone"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/COPYING.md;md5=1836c016e9acdea3fdd33916ad3c72f0"

inherit autotools
SRC_URI = " \
    git://github.com/s-yata/darts-clone.git;branch=master;protocol=https \
"
SRCREV = "87b71afd6cf784953e3c08f24c64203397f3b724"
PV = "0.34"
S = "${WORKDIR}/git"
BBCLASSEXTEND = "native nativesdk"
