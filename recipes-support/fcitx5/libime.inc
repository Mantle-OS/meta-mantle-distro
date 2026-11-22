require fcitx5.inc

PACKAGECONFIG[data] = " -DENABLE_DATA=ON, -DENABLE_DATA=OFF, "
PACKAGECONFIG[tools] = " -DENABLE_TOOLS=ON, -DENABLE_TOOLS=OFF,"

DEPENDS:append = " \
    boost \
    zstd \
"

DEPENDS:append:class-native = " fcitx5-native"
DEPENDS:append:class-target = " fcitx5 libime-native"
DEPENDS:append:class-nativesdk = " ${DEPENDS:append:class-native}"

PACKAGECONFIG ?= "\
    shared \
    data \
    tools \
"

PACKAGECONFIG:class-native ?= "\
    shared \
    tools \
"

PACKAGES =+ " \
    ${PN}-tools \
    ${PN}-data \
"

SUMMARY:${PN}-tools ?= "${PN} applications for language and dictionary utilities"
RDEPENDS:${PN}-tools += " ${PN}"

SUMMARY:${PN}-data = "${PN} data for language models and input method dictionaries"
RDEPENDS:${PN}-data += " ${PN}"

## what we use in all the patch sets to point back to the sys root for native tools
EXTRA_OECMAKE:append:class-target = " \
    -DLIBIME_NATIVE_BINDIR=${RECIPE_SYSROOT_NATIVE}${bindir} \
"
