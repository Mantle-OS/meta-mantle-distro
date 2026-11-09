SUMMARY = "Libime Jyutping"
DESCRIPTION = "A library make use of libime to implement jyutping (粵拼) input method."
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68"

require libime.inc

SRCREV = "${LIBIME_JYUTPING_SRCREV}"
SRC_URI = " \
    ${LIBIME_JYUTPING_SRC_URI} \
    file://0001-libime-jyutping-fix-CMake-paths-and-cross-compilatio.patch \
"
PV = "${LIBIME_JYUTPING_PV}"

DEPENDS:append:class-target = " ${PN}-native"
DEPENDS:append = " libime"

PACKAGECONFIG[engine] = " -DENABLE_ENGINE=ON, -DENABLE_ENGINE=OFF, fcitx5-chinese-addons"

PACKAGECONFIG:append:class-target = " \
    engine \
"

PACKAGES =+ " \
    ${PN}-engine \
"

SUMMARY:${PN}-engine = "Fcitx5 Jyutping input method engine"
FILES:${PN}-engine = " \
    ${libdir}/fcitx5/libjyutping.so \
    ${datadir}/fcitx5/addon/jyutping.conf \
    ${datadir}/fcitx5/inputmethod/jyutping.conf \
    ${datadir}/metainfo/org.fcitx.Fcitx5.Addon.Jyutping.metainfo.xml \
    ${datadir}/locale/*/LC_MESSAGES/fcitx5-jyutping.mo \
"

FILES:${PN}-tools = " \
    ${bindir}/libime_jyutpingdict \
"

FILES:${PN}-data = " \
    ${datadir}/libime/jyutping.dict \
    ${libdir}/libime/zh_HK.lm \
    ${libdir}/libime/zh_HK.lm.predict \
"

RDEPENDS:${PN}-engine += " \
    ${PN} \
    ${PN}-data \
    ${@bb.utils.contains('PACKAGECONFIG', 'engine', 'fcitx5-chinese-addons-punctuation', '', d)} \
"
