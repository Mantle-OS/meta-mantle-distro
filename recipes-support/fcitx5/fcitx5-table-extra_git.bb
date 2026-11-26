SUMMARY = "Extra Fcitx5 input method tables"
DESCRIPTION = "Provides additional multilingual table-based input methods for Fcitx5, including extra dictionaries and input method configurations."
SECTION = "inputmethods"
HOMEPAGE = "https://github.com/fcitx/fcitx5-table-extra"

LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-3.0-or-later.txt;md5=1c76c4cc354acaac30ed4d5eefea7245"

require libime.inc
inherit gtk-icon-cache

SRC_URI = " \
    ${FCITX5_TABLE_EXTRA_SRC_URI} \
    file://0001-cmake-use-native-LibIME-tabledict-when-cross-compili.patch \
"

SRCREV = "${FCITX5_TABLE_EXTRA_SRCREV}"
PV = "${FCITX5_TABLE_EXTRA_PV}"

PACKAGES =+ " \
    ${PN}-icons \
    ${PN}-tables \
"

FILES:${PN} += " \
    ${datadir}/metainfo \
"

SUMMARY:${PN}-icons = "Fcitx5 extra input method icons"
DESCRIPTION:${PN}-icons = "Icon resources for the extra Fcitx5 table-based input methods."
FILES:${PN}-icons = " \
    ${datadir}/icons/hicolor/ \
"

SUMMARY:${PN}-tables = "Fcitx5 extra input method tables"
DESCRIPTION:${PN}-tables = "Compiled dictionaries and input method configurations for extra Fcitx5 table-based input methods."
FILES:${PN}-tables = " \
    ${datadir}/fcitx5/inputmethod/ \
    ${datadir}/fcitx5/table/ \
"

