SUMMARY = "${DESCRIPTION}"
DESCRIPTION = "Mantle Os console only image"
LICENSE = "MIT"

require mantle-image-core.bb

IMAGE_FEATURES:remove = "package-management"
IMAGE_INSTALL:remove = "\
    rpm \
    dnf \
"

