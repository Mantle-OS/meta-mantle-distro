SUMMARY = "Rime input method engine for Fcitx5"
DESCRIPTION = "Provides Chinese text input for Fcitx5 using the Rime input method \
engine, supporting phonetic and shape-based input methods, configurable input \
schemas, and Traditional and Simplified Chinese conversion."
SECTION = "inputmethods"
HOMEPAGE = "https://github.com/fcitx/fcitx5-rime"

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68"

require fcitx5.inc
inherit gtk-icon-cache

SRC_URI = " \
    ${FCITX5_RIME_SRC_URI} \
    file://0001-Cross-compile-use-Threads-and-Fcitx5Utils_DIR.patch \
"

SRCREV = "${FCITX5_RIME_SRCREV}"
PV = "${FCITX5_RIME_PV}"

DEPENDS:append = " \
    fmt \
    libuv \
    librime librime-data \
    fcitx5 \
"

EXTRA_OECMAKE:append = " -DRIME_DATA_DIR=${datadir}/rime-data"

FILES:${PN} += " \
    ${libdir}/fcitx5/librime.so \
    ${datadir}/fcitx5/addon/rime.conf \
    ${datadir}/fcitx5/inputmethod/rime.conf \
    ${datadir}/rime-data/fcitx5.yaml \
    ${datadir}/metainfo/org.fcitx.Fcitx5.Addon.Rime.metainfo.xml \
"

PACKAGES =+ " ${PN}-icons"
SUMMARY:${PN}-icons = "Icons for the Fcitx5 Rime input method"
DESCRIPTION:${PN}-icons = "Provides hicolor icon assets for the Fcitx5 Rime input method, including 48x48 raster icons and scalable vector icons."
FILES:${PN}-icons = " \
    ${datadir}/icons/hicolor/48x48/ \
    ${datadir}/icons/hicolor/scalable/ \
"
