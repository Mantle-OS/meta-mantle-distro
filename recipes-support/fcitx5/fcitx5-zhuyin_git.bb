SUMMARY = "Zhuyin input method engine for Fcitx5"
DESCRIPTION = "Fcitx5 Zhuyin provides Traditional Chinese text input \
using the Zhuyin (Bopomofo) phonetic system, integrating LibZhuyin \
for phonetic conversion and candidate selection."

HOMEPAGE = "https://github.com/fcitx/fcitx5-zhuyin"
BUGTRACKER = "https://github.com/fcitx/fcitx5-zhuyin/issues"
SECTION = "inputmethods"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=fed54355545ffd980b814dab4a3b312c"

require fcitx5.inc
inherit gtk-icon-cache

SRC_URI = " \
    ${FCITX5_ZHUYIN_SRC_URI} \
    file://0001-cross-compile-support-Fcitx5Utils_DIR-and-NATIVE_PIN.patch \
"

SRCREV = "${FCITX5_ZHUYIN_SRCREV}"
PV = "${FCITX5_ZHUYIN_PV}"

PACKAGECONFIG[berkeley-db] = "-DLIBZHUYIN_DATABASE_FORMAT=BerkeleyDB, , db"
PACKAGECONFIG[kyotocabinet] = "-DLIBZHUYIN_DATABASE_FORMAT=KyotoCabinet, , kyotocabinet"
PACKAGECONFIG[data] = "-DENABLE_DATA=ON, -DENABLE_DATA=OFF"

PACKAGECONFIG ??= " \
    berkeley-db \
"

PACKAGECONFIG:append:class-target = " data"

DEPENDS:append = " \
    fcitx5 \
    libpinyin libpinyin-native \
"

EXTRA_OECMAKE:append:class-target = " \
    -DNATIVE_PINYIN_TOOLS:PATH=${STAGING_BINDIR_NATIVE} \
"

PACKAGES =+ " \
    ${PN}-data \
    ${PN}-icons \
"

FILES:${PN} += " \
    ${libdir}/fcitx5/zhuyin.so \
    ${datadir}/fcitx5/addon/zhuyin.conf \
    ${datadir}/fcitx5/inputmethod/zhuyin.conf \
    ${datadir}/fcitx5/lua/imeapi/extensions/zhuyin.lua \
    ${datadir}/metainfo/org.fcitx.Fcitx5.Addon.Zhuyin.metainfo.xml \
"

SUMMARY:${PN}-data = "Language model data for the Fcitx5 Zhuyin input method"
DESCRIPTION:${PN}-data = "Binary language models, phrase indexes, \
phonetic indexes, statistical dictionaries and symbol tables used \
by the Fcitx5 Zhuyin input method engine."

FILES:${PN}-data = " \
    ${datadir}/fcitx5/zhuyin \
"

SUMMARY:${PN}-icons = "Icons for the Fcitx5 Zhuyin input method"
DESCRIPTION:${PN}-icons = "Hicolor theme icons for the Fcitx5 Zhuyin \
input method in multiple resolutions."

FILES:${PN}-icons = " \
    ${datadir}/icons/hicolor \
"

RDEPENDS:${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'data', '${PN}-data', '', d)}"
RRECOMMENDS:${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'data', '${PN}-icons', '', d)}"
