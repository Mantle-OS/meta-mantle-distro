SUMMARY = "${DESCRIPTION}"
DESCRIPTION = "Chromium Kiosk Wayland Image"
LICENSE = "MIT"

require recipes-core/images/mantle-image-core.bb

inherit features_check

REQUIRED_DISTRO_FEATURES = "wayland"
IMAGE_FEATURES:append = " hwcodecs weston"

IMAGE_INSTALL:append = " \
    chromium-kiosk-wayland \
    kiosk-extras-wayland \
"
