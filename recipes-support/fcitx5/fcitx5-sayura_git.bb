SUMMARY = "Sayura Sinhala input method engine for Fcitx5"
DESCRIPTION = "Fcitx5 Sayura provides a Sinhala input method based on \
the Sayura keyboard layout, ported from IBus-Sayura for use with the \
Fcitx5 input method framework."
HOMEPAGE = "https://github.com/fcitx/fcitx5-sayura"
SECTION = "inputmethods"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=fed54355545ffd980b814dab4a3b312c"

require fcitx5.inc
inherit gtk-icon-cache

SRC_URI = " \
    ${FCITX5_SAYURA_SRC_URI} \
    file://0001-cmake-Fix-Fcitx5Utils-lookup-when-cross-compiling.patch \
"

SRCREV = "${FCITX5_SAYURA_SRCREV}"
PV = "${FCITX5_SAYURA_PV}"

DEPENDS:append = " \
    fcitx5 \
"

PACKAGES =+ " \
    ${PN}-icons \
"

FILES:${PN} += " \
    ${libdir}/fcitx5/libsayura.so \
    ${datadir}/fcitx5/inputmethod/sayura.conf \
    ${datadir}/fcitx5/addon/sayura.conf \
    ${datadir}/metainfo/org.fcitx.Fcitx5.Addon.Sayura.metainfo.xml \
"

SUMMARY:${PN}-icons = "Icons for the Fcitx5 Sayura input method"
DESCRIPTION:${PN}-icons = "Hicolor icons for the Fcitx5 Sayura \
Sinhala input method, providing multiple resolutions and scalable \
artwork for input method selection and desktop integration."
FILES:${PN}-icons = " \
    ${datadir}/icons/hicolor/16x16/*/* \
    ${datadir}/icons/hicolor/24x24/*/* \
    ${datadir}/icons/hicolor/32x32/*/* \
    ${datadir}/icons/hicolor/48x48/*/* \
    ${datadir}/icons/hicolor/64x64/*/* \
    ${datadir}/icons/hicolor/128x128/*/* \
    ${datadir}/icons/hicolor/scalable/*/* \
"

