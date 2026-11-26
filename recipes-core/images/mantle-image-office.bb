SUMMARY = "${DESCRIPTION}"
DESCRIPTION = "Mantle Os Office Testing Desktop Image."
LICENSE = "MIT"

inherit features_check
require mantle-image-full.bb

REQUIRED_DISTRO_FEATURES = "x11 wayland"

IMAGE_FEATURES += " \
    splash \
    ssh-server-openssh \
    weston \
"

IMAGE_INSTALL:append =  " "

IMAGE_INSTALL:append = "\
    weston wayland \
    packagegroup-office-filters \
    packagegroup-office-document-processing \
"

export IMAGE_BASENAME = "mantle-image-office"
SYSTEMD_DEFAULT_TARGET = "graphical.target"
