SUMMARY = "SKK Japanese input method engine for Fcitx5"
DESCRIPTION = "Provides Japanese text input using the Simple Kana to Kanji conversion (SKK) method through the libskk backend. Includes an optional Qt6 graphical configuration module."
SECTION = "inputmethods"
HOMEPAGE = "https://github.com/fcitx/fcitx5-skk"

LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-3.0-or-later.txt;md5=1c76c4cc354acaac30ed4d5eefea7245"

require fcitx5.inc
inherit gtk-icon-cache

SRC_URI = " \
    ${FCITX5_SKK_SRC_URI} \
    file://0001-cmake-use-Fcitx5Utils_DIR-when-cross-compiling.patch \
"
SRCREV = "${FCITX5_SKK_SRCREV}"
PV = "${FCITX5_SKK_PV}"

DEPENDS:append = " \
    libskk \
"

# SKK_PATH specifies the runtime directory containing SKK-JISYO.L.
# Upstream uses this path when generating its default dictionary list.
# It does not execute any native or target tools during compilation.
EXTRA_OECMAKE:append = " \
    -DSKK_PATH=${datadir}/skk \
"

PACKAGES =+ " ${PN}-icons"
FILES:${PN} += " \
    ${libdir}/fcitx5/skk.so \
    ${datadir}/fcitx5/inputmethod/skk.conf \
    ${datadir}/fcitx5/addon/skk.conf \
    ${datadir}/fcitx5/skk/ \
    ${datadir}/metainfo/org.fcitx.Fcitx5.Addon.Skk.metainfo.xml \
"
SUMMARY:${PN}-icons = "Icons for the Fcitx5 SKK input method"
DESCRIPTION:${PN}-icons = "Provides the hicolor icon theme resources used by the Fcitx5 SKK Japanese input method."
FILES:${PN}-icons = "${datadir}/icons/hicolor/"
