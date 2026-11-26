SUMMARY = "CSKK Japanese input method engine for Fcitx5"
DESCRIPTION = "Fcitx5 CSKK provides Japanese text input using the \
Simple Kana Kanji conversion (SKK) input method, integrating the \
Rust-based LibCSKK library for Kana-to-Kanji conversion, dictionary \
lookup and candidate selection."
HOMEPAGE = "https://github.com/fcitx/fcitx5-cskk"
BUGTRACKER = "https://github.com/fcitx/fcitx5-cskk/issues"
SECTION = "inputmethods"
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

require fcitx5.inc
inherit gtk-icon-cache

SRC_URI = " \
    ${FCITX5_CSKK_SRC_URI} \
    file://0001-Add-cross-compile-anmd-a-number-of-build-options.patch \
"

SRCREV = "${FCITX5_CSKK_SRCREV}"
PV = "${FCITX5_CSKK_PV}"

## Pending GoogleTest cross-compilation patch
PACKAGECONFIG[system-googletest] = "-DUSE_SYSTEM_GTEST=ON,-DUSE_SYSTEM_GTEST=OFF,googletest"
PACKAGECONFIG[tests] = "-DBUILD_TESTS=ON, -DBUILD_TESTS=OFF, googletest"

DEPENDS:append = " \
    fcitx5 \
    libcskk \
"

EXTRA_OECMAKE:append = " \
    -DBUILD_DEB=OFF \
    -DGOOGLETEST=OFF \
    -DSKK_DICT_DEFAULT_PATH=${datadir}/skk/SKK-JISYO.L \
    -DSKK_DICT_DEFAULT_ENCODING=euc-jp \
"
FILES:${PN}:append = " \
    ${libdir}/fcitx5/fcitx5-cskk.so \
    ${datadir}/metainfo/org.fcitx.Fcitx5.Addon.Cskk.metainfo.xml \
    ${datadir}/fcitx5/inputmethod/cskk.conf \
    ${datadir}/fcitx5/addon/cskk.conf \
    ${datadir}/fcitx5/cskk \
"

PACKAGES =+ " ${PN}-icons"
SUMMARY:${PN}-icons = "Icons for the Fcitx5 CSKK input method"
DESCRIPTION:${PN}-icons = "Hicolor theme icons for the Fcitx5 CSKK \
Japanese input method in multiple resolutions."
FILES:${PN}-icons = " ${datadir}/icons/hicolor"
RRECOMMENDS:${PN} += "${PN}-icons"
