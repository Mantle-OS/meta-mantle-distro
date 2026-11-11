
SUMMARY = "Git Large File Storage"
DESCRIPTION = "Git LFS is a command-line extension for managing large files with Git."
HOMEPAGE = "https://git-lfs.com/"
LICENSE = "MIT & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=FIXME"

GO_IMPORT = "github.com/git-lfs/git-lfs/v3"

SRC_URI = "git://github.com/git-lfs/git-lfs.git;protocol=https;branch=main"
SRCREV = "${AUTOREV}"

inherit go go-mod

RDEPENDS:${PN} += "git"

FILES:${PN} += "${bindir}/git-lfs"
