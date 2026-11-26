SUMMARY = "Testing and validation packagegroup for Fcitx5 input method framework and supporting components"
DESCRIPTION = "Collection of input method engines, language processing libraries, \
dictionaries, and integration modules for the Fcitx5 input method framework."

# These packages are not meant to be installed directly as some of the
# packages may conflict with each other. Specifying them allows them to be
# built, validated and made available in the package repository.

LICENSE = "MIT"
PR = "r1"

PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup

ALLOW_EMPTY:${PN} = "1"

PACKAGES = " \
    ${PN} \
    ${PN}-ime \
    ${PN}-modules \
"

RRECOMMENDS:${PN} = " \
    cppjieba \
    darts-clone \
    datrie \
    imath \
    kyotocabinet \
    libchewing \
    libcskk \
    libhangul \
    libpinyin \
    librime \
    librime-data \
    libskk \
    libthai \
    limonp \
    marisa-trie \
    opencc \
    xcb-imdkit \
    yoga \
    \
    ${@bb.utils.contains("BBFILE_COLLECTIONS", "gnome-layer", "fcitx5-gtk", "", d)} \
    ${@bb.utils.contains("BBFILE_COLLECTIONS", "qt6-layer", "fcitx5-qt6 fcitx5-mozc", "", d)} \
    \
    ${PN}-modules \
    ${PN}-ime \
"

SUMMARY:${PN}-modules = "Fcitx5 modules and language data"
DESCRIPTION:${PN}-modules = "Additional Fcitx5 components providing language \
dictionaries, input tables, and supporting input method functionality."

# Pending implementation and validation:
#     fcitx5-m17n
#
# Pending KDE Frameworks 6 integration:
#     fcitx5-configtool
#     fcitx5-wayland-binding-generator-native

RRECOMMENDS:${PN}-modules = " \
    fcitx5 \
    fcitx5-bamboo-gomod \
    fcitx5-bamboo \
    fcitx5-chewing \
    fcitx5-chinese-addons \
    fcitx5-cskk \
    fcitx5-hangul \
    fcitx5-libthai \
    fcitx5-lua \
    fcitx5-rime \
    fcitx5-sayura \
    fcitx5-skk \
    fcitx5-unikey \
    fcitx5-zhuyin \
"

SUMMARY:${PN}-ime = "LibIME input method extensions"
DESCRIPTION:${PN}-ime = "Additional language processing engines, dictionaries \
and input method extensions based on the LibIME framework."
RRECOMMENDS:${PN}-ime = " \
    libime \
    fcitx5-table-extra \
    fcitx5-table-other \
    libime-jyutping \
"
