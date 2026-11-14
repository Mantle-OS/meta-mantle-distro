SUMMARY = "${DESCRIPTION}"
DESCRIPTION = "Builds the self to import into a docker containter"
LICENSE = "MIT"
PR = "r0"

require mantle-image-docker.bb

IMAGE_INSTALL:append = " \
    packagegroup-mantle-self \
"

