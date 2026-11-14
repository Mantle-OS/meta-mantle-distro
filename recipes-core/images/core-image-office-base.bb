SUMMARY = "command image with the office recipes"
DESCRIPTION = "Simple image to test office commandline tools and framebuffer objects"
LICENSE = "MIT"

IMAGE_FEATURES += " \
    splash \
    ssh-server-openssh \
"

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    packagegroup-base-extended \
    packagegroup-core-full-cmdline \
    bash-completion \
    packagegroup-office-filters \
    packagegroup-office-document-processing \
    ${MACHINE_ESSENTIAL_EXTRA_RDEPENDS} \
    ${MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS} \
    ${MACHINE_EXTRA_RDEPENDS} \
    ${MACHINE_EXTRA_RRECOMMENDS} \
    ${DISTRO_EXTRA_RDEPENDS} \
"

inherit core-image

