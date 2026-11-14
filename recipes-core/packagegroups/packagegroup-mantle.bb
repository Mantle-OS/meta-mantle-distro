SUMMARY = "Mantle meta package"
DESCRIPTION = "meta package for mantle os"
LICENSE = "MIT"
PR = "r0"

PACKAGE_ARCH = "${TUNE_PKGARCH}"

VIRTUAL-RUNTIME_font ?= "freetype"
VIRTUAL-RUNTIME_input ?= "libinput"

inherit packagegroup

RDEPENDS:${PN} = " \
    bash-completion \
    sudo \
    adduser \
    ssh-pregen-hostkeys \
    ${PN}-multimedia \
    ${PN}-networking \
    ${PN}-python3 \
    ${PN}-gfx \
"
