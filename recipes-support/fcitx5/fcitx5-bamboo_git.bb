SUMMARY = "Bamboo Vietnamese input method integration for Fcitx5"
DESCRIPTION = "Fcitx5 Bamboo provides Vietnamese text input using the \
Bamboo input method engine. It integrates the Go-based Bamboo core \
with the Fcitx5 input method framework."
HOMEPAGE = "https://github.com/fcitx/fcitx5-bamboo"
SECTION = "inputmethods"

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68"

require fcitx5.inc
inherit gtk-icon-cache

SRC_URI = " \
    ${FCITX5_BAMBOO_SRC_URI} \
    file://0001-Enable-use-of-gomod-from-system.patch \
"

SRCREV = "${FCITX5_BAMBOO_SRCREV}"
PV = "${FCITX5_BAMBOO_PV}"

DEPENDS:append = " \
    fcitx5 \
    ${PN}-gomod \
"

EXTRA_OECMAKE:append = " \
    -DUSE_SYSTEM_BAMBOO=ON \
"

FILES:${PN} += " \
    ${libdir}/fcitx5/libbamboo.so \
    ${datadir}/fcitx5/inputmethod/bamboo.conf \
    ${datadir}/fcitx5/addon/bamboo.conf \
    ${datadir}/fcitx5/bamboo/vietnamese.cm.dict \
    ${datadir}/metainfo/org.fcitx.Fcitx5.Addon.Bamboo.metainfo.xml \
    ${datadir}/icons/hicolor/scalable/*/* \
"

