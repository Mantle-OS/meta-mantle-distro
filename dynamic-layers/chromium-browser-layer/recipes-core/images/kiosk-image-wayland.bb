SUMMARY = "${DESCRIPTION}"
DESCRIPTION = "EMAC Chromium Kiosk Wayland Image"
LICENSE = "MIT"

require recipes-core/images/emac-image-core.bb

inherit features_check

REQUIRED_DISTRO_FEATURES = "wayland"
IMAGE_FEATURES:append = " hwcodecs weston qt5"

IMAGE_INSTALL:append = " \
    chromium-kiosk-wayland \
    kiosk-extras-wayland \
"
