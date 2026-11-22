SUMMARY = "Git Large File Storage"
DESCRIPTION = "Git LFS is a command-line extension for managing large files with Git."
SECTION = "devel"
HOMEPAGE = "https://git-lfs.com/"

LICENSE = "MIT AND BSD-3-Clause"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE.md;md5=58e90ef3218ad8dd56d2b5790d035be1"

GO_IMPORT = "github.com/git-lfs/git-lfs/v3"
GO_INSTALL = "${GO_IMPORT}"

SRC_URI = "git://github.com/git-lfs/git-lfs.git;protocol=https;branch=main;destsuffix=${GO_SRCURI_DESTSUFFIX}"
SRCREV = "${AUTOREV}"

inherit go-mod go-mod-update-modules

require git-lfs-go-mods.inc
require git-lfs-licenses.inc

do_install:append() {
    rm -rf ${D}${libdir}/go/src/${GO_IMPORT}/debian
}

RDEPENDS:${PN} += "git"

FILES:${PN} += " \
    ${bindir}/git-lfs \
"
