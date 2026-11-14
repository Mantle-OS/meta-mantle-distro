SUMMARY = "Wayland image with office recipes"
DESCRIPTION = "A simple image to test office recipes on wayland"
LICENSE = "MIT"

inherit features_check
require core-image-office-base.bb

REQUIRED_DISTRO_FEATURES = "opengl wayland"
IMAGE_FEATURES:append = " weston"
IMAGE_INSTALL:append =  " weston wayland "
