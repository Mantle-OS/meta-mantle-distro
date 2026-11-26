SUMMARY = "Unikey Vietnamese input method engine for Fcitx5"
DESCRIPTION = "Fcitx5 Unikey provides Vietnamese text input using the \
Unikey engine, supporting Vietnamese input methods, character \
encoding conversion, and configurable keyboard layouts."
HOMEPAGE = "https://github.com/fcitx/fcitx5-unikey"
SECTION = "inputmethods"

LICENSE = "LGPL-2.1-or-later AND GPL-2.0-or-later"
LIC_FILES_CHKSUM = " \
    file://LICENSES/GPL-2.0-or-later.txt;md5=fed54355545ffd980b814dab4a3b312c \
    file://LICENSES/LGPL-2.0-or-later.txt;md5=6d2d9952d88b50a51a5c73dc431d06c7 \
"

require fcitx5.inc
inherit gtk-icon-cache

SRC_URI = " \
    ${FCITX5_UNIKEY_SRC_URI} \
    file://0001-Use-Fcitx5Utils_DIR-when-cross-compiling.patch \
"
SRCREV = "${FCITX5_UNIKEY_SRCREV}"
PV = "${FCITX5_UNIKEY_PV}"

FILES:${PN} += " \
    ${libdir}/fcitx5/libunikey.so \
    ${datadir}/fcitx5/inputmethod/unikey.conf \
    ${datadir}/fcitx5/addon/unikey.conf \
    ${datadir}/metainfo/org.fcitx.Fcitx5.Addon.Unikey.metainfo.xml \
"

PACKAGES =+ " ${PN}-icons"
SUMMARY:${PN}-icons = "Icons for the Fcitx5 Unikey input method"
DESCRIPTION:${PN}-icons = "Hicolor icons for the Fcitx5 Unikey \
Vietnamese input method, supporting input method selection and \
desktop integration."

