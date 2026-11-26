SUMMARY = "Additional Fcitx5 input method tables"
DESCRIPTION = "Provides additional multilingual table-based input methods for Fcitx5, including Arabic, Thai, Vietnamese, and other language tables."
SECTION = "inputmethods"
HOMEPAGE = "https://github.com/fcitx/fcitx5-table-other"

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-3.0-only.txt;md5=1c76c4cc354acaac30ed4d5eefea7245"

require libime.inc
inherit gtk-icon-cache

SRC_URI = " \
    ${FCITX5_TABLE_OTHER_SRC_URI} \
    file://0001-cmake-use-native-LibIME-tabledict-when-cross-compili.patch \
"

SRCREV = "${FCITX5_TABLE_OTHER_SRCREV}"
PV = "${FCITX5_TABLE_OTHER_PV}"

PACKAGECONFIG[enable-deprecated] = "-DENABLE_DEPRECATED=ON, -DENABLE_DEPRECATED=OFF"

PACKAGES =+ " \
    ${PN}-icons \
    ${PN}-tables \
"

FILES:${PN} += " \
    ${datadir}/metainfo \
"

SUMMARY:${PN}-icons = "Fcitx5 additional input method icons"
DESCRIPTION:${PN}-icons = "Icon resources for the additional Fcitx5 table-based input methods."
FILES:${PN}-icons = " \
    ${datadir}/icons/hicolor/ \
"

SUMMARY:${PN}-tables = "Fcitx5 additional input method tables"
DESCRIPTION:${PN}-tables = "Compiled dictionaries and input method configurations for additional Fcitx5 table-based input methods."
FILES:${PN}-tables = " \
    ${datadir}/fcitx5/inputmethod/ \
    ${datadir}/fcitx5/table/ \
"
