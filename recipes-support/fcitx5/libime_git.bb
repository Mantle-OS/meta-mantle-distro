SUMMARY = "LibIME"
DESCRIPTION = "This is a library to support generic input method implementation."
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68"

require libime.inc

SRCREV = "${LIBIME_SRCREV}"
SRC_URI = " \
    ${LIBIME_SRC_URI} \
    file://0001-use-the-Fcitx5Utils_DIR-from-Fcitx5.patch \
    file://0002-cross-compile-for-build-time-data.patch \
    file://0003-cmake-do-not-use-full-paths-on-includes.patch \
"

PV = "${LIBIME_PV}"

FILES:${PN}-tools = " \
    ${bindir}/libime_slm_build_binary \
    ${bindir}/libime_prediction \
    ${bindir}/libime_pinyindict \
    ${bindir}/libime_history \
    ${bindir}/libime_tabledict \
    ${bindir}/libime_migrate_fcitx4_table \
    ${bindir}/libime_migrate_fcitx4_pinyin \
"

FILES:${PN}-data = " \
    ${libdir}/libime/ \
    ${datadir}/libime/ \
"
