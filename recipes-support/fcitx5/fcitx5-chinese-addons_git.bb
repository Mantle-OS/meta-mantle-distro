SUMMARY = "Chinese related addon for fcitx5"
DESCRIPTION = "This provides pinyin and table input method support for fcitx5"
LICENSE = "(LGPL-2.1-or-later | GPL-2.0-or-later)"
LIC_FILES_CHKSUM = " \
    file://${WORKDIR}/git/LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68 \
    file://${WORKDIR}/git/LICENSES/GPL-2.0-or-later.txt;md5=fed54355545ffd980b814dab4a3b312c \
"

require libime.inc
inherit ${@bb.utils.contains('BBFILE_COLLECTIONS', 'qt6-layer', 'qt6-cmake', 'cmake', d)}

SRCREV = "${FCITX5_CHINESE_ADDONS_SRCREV}"
SRC_URI = " \
    ${FCITX5_CHINESE_ADDONS_SRC_URI} \
    file://0001-cmake-threads-and-path-updates-for-cross-compile.patch \
"
PV = "${FCITX5_CHINESE_ADDONS_PV}"

DEPENDS:append:class-target = " ${PN}-native"
DEPENDS:append = " libime"

PACKAGECONFIG[opencc] = " -DENABLE_OPENCC=ON, -DENABLE_OPENCC=OFF, opencc"
PACKAGECONFIG[gui] = " -DENABLE_GUI=ON -DQT_MAJOR_VERSION=6, -DENABLE_GUI=OFF, qtbase6 fcitx5-qt6"
PACKAGECONFIG[browser] = " -DENABLE_BROWSER=ON, -DENABLE_BROWSER=OFF, qtwebengine6"
PACKAGECONFIG[cloudpinyin] = " -DENABLE_CLOUDPINYIN=ON, -DENABLE_CLOUDPINYIN=OFF, curl"

PACKAGECONFIG[lua] = ", ,fcitx5-lua lua"

PACKAGECONFIG:append:class-target = " \
    opencc \
    cloudpinyin \
    lua \
    ${@bb.utils.contains('BBFILE_COLLECTIONS', 'qt6-layer', bb.utils.contains('DISTRO_FEATURES', 'webengine', 'browser gui', 'gui', d), '', d)} \
"

PACKAGES =+ " \
    ${PN}-chttrans \
    ${PN}-fullwidth \
    ${PN}-pinyin \
    ${PN}-pinyinhelper \
    ${PN}-punctuation \
    ${PN}-table \
    ${PN}-lua \
    ${PN}-qt6 \
    ${PN}-cloudpinyin \
"

SUMMARY:${PN}-chttrans = "Fcitx5 Chinese conversion addon"
FILES:${PN}-chttrans = " \
    ${libdir}/fcitx5/libchttrans.so \
    ${datadir}/fcitx5/addon/chttrans.conf \
    ${datadir}/fcitx5/chttrans/ \
"

SUMMARY:${PN}-fullwidth = "Fcitx5 fullwidth character addon"
FILES:${PN}-fullwidth = " \
    ${libdir}/fcitx5/libfullwidth.so \
    ${datadir}/fcitx5/addon/fullwidth.conf \
"

SUMMARY:${PN}-pinyin = "Fcitx5 Pinyin and Shuangpin input methods"
FILES:${PN}-pinyin = " \
    ${libdir}/fcitx5/libpinyin.so \
    ${datadir}/fcitx5/addon/pinyin.conf \
    ${datadir}/fcitx5/inputmethod/pinyin.conf \
    ${datadir}/fcitx5/inputmethod/shuangpin.conf \
    ${datadir}/fcitx5/pinyin/ \
"

SUMMARY:${PN}-pinyinhelper = "Fcitx5 Pinyin helper module"
FILES:${PN}-pinyinhelper = " \
    ${libdir}/fcitx5/libpinyinhelper.so \
    ${datadir}/fcitx5/addon/pinyinhelper.conf \
    ${datadir}/fcitx5/pinyinhelper/ \
"

SUMMARY:${PN}-punctuation = "Fcitx5 punctuation addon"
FILES:${PN}-punctuation = " \
    ${libdir}/fcitx5/libpunctuation.so \
    ${datadir}/fcitx5/addon/punctuation.conf \
    ${datadir}/fcitx5/punctuation/ \
"

SUMMARY:${PN}-table = "Fcitx5 table-based Chinese input methods"
FILES:${PN}-table = " \
    ${libdir}/fcitx5/libtable.so \
    ${datadir}/fcitx5/addon/table.conf \
    ${datadir}/fcitx5/inputmethod/cangjie.conf \
    ${datadir}/fcitx5/inputmethod/db.conf \
    ${datadir}/fcitx5/inputmethod/erbi.conf \
    ${datadir}/fcitx5/inputmethod/qxm.conf \
    ${datadir}/fcitx5/inputmethod/wanfeng.conf \
    ${datadir}/fcitx5/inputmethod/wbpy.conf \
    ${datadir}/fcitx5/inputmethod/wbx.conf \
    ${datadir}/fcitx5/inputmethod/zrm.conf \
"

SUMMARY:${PN}-data = "Fcitx5 Chinese Addons shared resources"
FILES:${PN}-data = " \
    ${datadir}/icons/ \
    ${datadir}/locale/ \
    ${datadir}/metainfo/org.fcitx.Fcitx5.Addon.ChineseAddons.metainfo.xml \
"

SUMMARY:${PN}-tools = "Fcitx5 Chinese Addons conversion utilities"
FILES:${PN}-tools = " \
    ${bindir}/scel2org5 \
"


SUMMARY:${PN}-lua = "Fcitx5 Chinese Addons Pinyin Lua extension"
FILES:${PN}-lua = " \
    ${datadir}/fcitx5/lua/imeapi/extensions/pinyin.lua \
"
RDEPENDS:${PN}-lua = " \
    ${PN}-pinyin \
    fcitx5-lua-addonloader \
    fcitx5-lua-imeapi \
"

SUMMARY:${PN}-qt6 = "Fcitx5 Chinese Addons Qt6 configuration tools"
FILES:${PN}-qt6 = " \
    ${libdir}/fcitx5/qt6/libcustomphraseeditor.so \
    ${libdir}/fcitx5/qt6/libpinyindictmanager.so \
"
RDEPENDS:${PN}-qt6 += " \
    ${PN}-pinyin \
"

SUMMARY:${PN}-cloudpinyin = "Fcitx5 Cloud Pinyin addon"
FILES:${PN}-cloudpinyin = " \
    ${libdir}/fcitx5/libcloudpinyin.so \
    ${datadir}/fcitx5/addon/cloudpinyin.conf \
"
RDEPENDS:${PN}-cloudpinyin += " \
    fcitx5 \
"
