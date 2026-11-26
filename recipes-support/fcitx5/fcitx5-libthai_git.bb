DESCRIPTION = "Fcitx5 LibThai provides Thai language input method \
integration using the LibThai library, supporting Thai character \
processing, input validation, and word breaking."

HOMEPAGE = "https://github.com/fcitx/fcitx5-libthai"
SECTION = "inputmethods"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=fed54355545ffd980b814dab4a3b312c"

require fcitx5.inc

SRC_URI = " \
    ${FCITX5_LIBTHAI_SRC_URI} \
    file://0001-Use-Fcitx5Utils_DIR-when-cross-compiling.patch \
"

SRCREV = "${FCITX5_LIBTHAI_SRCREV}"
PV = "${FCITX5_LIBTHAI_PV}"

DEPENDS:append = " \
    libthai \
    fcitx5 \
"

FILES:${PN} += " \
    ${libdir}/fcitx5/libthai.so \
    ${datadir}/fcitx5/inputmethod/libthai.conf \
    ${datadir}/fcitx5/addon/libthai.conf \
    ${datadir}/metainfo/org.fcitx.Fcitx5.Addon.LibThai.metainfo.xml \
"
