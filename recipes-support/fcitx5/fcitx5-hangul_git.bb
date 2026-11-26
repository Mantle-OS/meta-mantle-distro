SUMMARY = "Hangul Korean input method engine for Fcitx5"
DESCRIPTION = "Fcitx5 Hangul provides Korean text input through the \
libhangul library, supporting Hangul composition, keyboard layouts, \
and Hanja conversion."
HOMEPAGE = "https://github.com/fcitx/fcitx5-hangul"
SECTION = "inputmethods"

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68"

require fcitx5.inc
inherit gtk-icon-cache

SRC_URI = " \
    ${FCITX5_HANGUL_SRC_URI} \
    file://0001-Use-Fcitx5Utils_DIR-if-cross-compile.patch \
"

SRCREV = "${FCITX5_HANGUL_SRCREV}"
PV = "${FCITX5_HANGUL_PV}"

DEPENDS:append = " \
    libhangul \
    fcitx5 \
"

PACKAGES =+ " ${PN}-icons"

FILES:${PN} += " \
    ${libdir}/fcitx5/libhangul.so \
    ${datadir}/fcitx5/inputmethod/hangul.conf \
    ${datadir}/fcitx5/addon/hangul.conf \
    ${datadir}/fcitx5/hangul/symbol.txt \
    ${datadir}/metainfo/org.fcitx.Fcitx5.Addon.Hangul.metainfo.xml \
"

SUMMARY:${PN}-icons = "Icons for the Fcitx5 Hangul input method"
DESCRIPTION:${PN}-icons = "Hicolor icons for the Fcitx5 Hangul \
Korean input method, including multiple resolutions for keyboard \
layout selection and desktop integration."
FILES:${PN}-icons = " \
    ${datadir}/icons/hicolor/16x16/*/* \
    ${datadir}/icons/hicolor/22x22/*/* \
    ${datadir}/icons/hicolor/24x24/*/* \
    ${datadir}/icons/hicolor/48x48/*/* \
    ${datadir}/icons/hicolor/64x64/*/* \
"
