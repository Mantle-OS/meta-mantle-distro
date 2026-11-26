SUMMARY = "Chewing input method engine for Fcitx5"
DESCRIPTION = "Fcitx5 Chewing integrates the Chewing intelligent Zhuyin \
(Bopomofo) input method with Fcitx5, providing Traditional Chinese phonetic \
input through the libchewing engine."
HOMEPAGE = "https://github.com/fcitx/fcitx5-chewing"
SECTION = "inputmethods"

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68"

require fcitx5.inc
inherit gtk-icon-cache pkgconfig

SRC_URI = " \
    ${FCITX5_CHEWING_SRC_URI} \
    file://0001-Use-Fcitx5Utils_DIR-for-cross-compile.patch \
"
SRCREV = "${FCITX5_CHEWING_SRCREV}"
PV = "${FCITX5_CHEWING_PV}"

DEPENDS:append = " \
    fcitx5 \
    libchewing chewing-capi \
"

FILES:${PN} += " \
    ${libdir}/fcitx5/libchewing.so \
    ${datadir}/fcitx5/inputmethod/chewing.conf \
    ${datadir}/fcitx5/addon/chewing.conf \
    ${datadir}/metainfo/org.fcitx.Fcitx5.Addon.Chewing.metainfo.xml \
"

PACKAGES =+ " \
    ${PN}-icons \
"

SUMMARY:${PN}-icons = "Icons for the Fcitx5 Chewing input method"
DESCRIPTION:${PN}-icons = "Hicolor icon assets for the Fcitx5 Chewing \
Traditional Chinese Zhuyin input method, including icons at multiple \
resolutions for use by desktop environments and input method interfaces."
FILES:${PN}-icons = " \
    ${datadir}/icons/hicolor/16x16/*/* \
    ${datadir}/icons/hicolor/22x22/*/* \
    ${datadir}/icons/hicolor/24x24/*/* \
    ${datadir}/icons/hicolor/48x48/*/* \
"
