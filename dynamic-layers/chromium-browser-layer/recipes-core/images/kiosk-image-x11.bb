SUMMARY = "${DESCRIPTION}"
DESCRIPTION = "Chromium Kiosk X11 Image"
LICENSE = "MIT"

require recipes-core/images/mantle-image-core.bb

inherit features_check

REQUIRED_DISTRO_FEATURES = "x11"
IMAGE_FEATURES:append = " x11-base"

IMAGE_INSTALL:append = " \
    chromium-kiosk-x11 \
    kiosk-extras-x11 \
"
