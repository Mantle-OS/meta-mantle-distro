SUMMARY = "X11 Sato image for office yocto recipes"
DESCRIPTION = "A simple image to test office recipes on x11"

LICENSE = "MIT"
inherit features_check

require core-image-office-base.bb

REQUIRED_DISTRO_FEATURES = "x11"
IMAGE_FEATURES:append = " x11-base x11-sato"
