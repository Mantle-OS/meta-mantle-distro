SUMMARY = "${DESCRIPTION}"
DESCRIPTION = "Builds the simple gnome image with a couple of system  calls"
LICENSE = "MIT"
PR = "r0"

require mantle-image-full.bb

IMAGE_INSTALL:append = " \
    packagegroup-mantle-gnome \
"

