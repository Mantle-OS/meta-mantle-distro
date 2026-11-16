SUMMARY = "${DESCRIPTION}"
DESCRIPTION = "Mantle OS XFCE Desktop Testing Image."
LICENSE = "MIT"

inherit features_check
require mantle-image-full.bb

REQUIRED_DISTRO_FEATURES = "opengl x11 pam"

IMAGE_FEATURES += " splash "

IMAGE_INSTALL:append = " \
    packagegroup-core-x11 \
    packagegroup-xfce-base \
    packagegroup-xfce-extended \
"

export IMAGE_BASENAME = "mantle-image-xfce"
SYSTEMD_DEFAULT_TARGET = "graphical.target"
