SUMMARY = "${DESCRIPTION}"
DESCRIPTION = "A Image with more tools"
LICENSE = "MIT"

require mantle-image-core.bb

IMAGE_FEATURES += " \
    splash \
    ssh-server-openssh \
"

IMAGE_INSTALL:append = " \
    ${MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS} \
    ${MACHINE_EXTRA_RRECOMMENDS} \
    ${DISTRO_EXTRA_RDEPENDS} \
    packagegroup-mantle \
"
