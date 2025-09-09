SUMMARY = "${DESCRIPTION}"
DESCRIPTION = "A Image with more tools"
LICENSE = "MIT"

require mantle-image-core.bb

IMAGE_FEATURES += " splash "
IMAGE_INSTALL:append = " \
    packagegroup-mantle-full \
"

