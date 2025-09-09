DESCRIPTION = "Mantle Os for docker"
LICENSE = "MIT"
PR = "r0"

require mantle-image-core.bb
IMAGE_INSTALL:remove = " \
    ${MACHINE_ESSENTIAL_EXTRA_RDEPENDS} \
    ${MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS} \
    ${MACHINE_EXTRA_RDEPENDS} \
    ${MACHINE_EXTRA_RRECOMMENDS} \
"
# IMAGE_CONTAINER_NO_DUMMY = "1"
inherit image-container
IMAGE_FSTYPES = "tar.bz2"
