SUMMARY = "${DESCRIPTION}"
DESCRIPTION = "Mantle Os console only image"
LICENSE = "MIT"

IMAGE_FEATURES += " ssh-server-openssh package-management hwcodecs splash "
IMAGE_INSTALL = "\
    ${MACHINE_ESSENTIAL_EXTRA_RDEPENDS} \
    ${MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS} \
    ${MACHINE_EXTRA_RDEPENDS} \
    ${MACHINE_EXTRA_RRECOMMENDS} \
    ${CORE_IMAGE_EXTRA_RDEPENDS} \
    ${MACHINE_FIRMWARE} \
    ${MACHINE_HWCODECS} \
    ${DISTRO_EXTRA_RDEPENDS} \
    sudo \
    adduser \
    ssh-pregen-hostkeys \
    packagegroup-base-zeroconf \
    packagegroup-base-extended \
    bash-completion \
"

inherit core-image
inherit extrausers

# This password is generated with `openssl passwd -6 mantle`
# This will all go away when the online installer is ready. Really should be after beta
PASSWD = "\$6\$uTVGIFTld8oXvlUI\$3r3sG3LbBr83XHMSmS54gd7LbhYoUPHJSyPCJR5LbLYu4AVuHJBkVld8HbRklGRneW3ZLBsfnZ1YzfVjDkkly1"
EXTRA_USERS_PARAMS = "\
    usermod -p '${PASSWD}' root; \
"
