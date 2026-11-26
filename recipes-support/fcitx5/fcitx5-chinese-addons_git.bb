SUMMARY = "Fcitx5 Chinese input method addons"
DESCRIPTION = "Provides Pinyin, table-based Chinese input methods, and related language support for Fcitx5."
SECTION = "inputmethods"
HOMEPAGE = "https://github.com/fcitx/fcitx5-chinese-addons"

LICENSE = "(LGPL-2.1-or-later OR GPL-2.0-or-later)"
LIC_FILES_CHKSUM = " \
    file://LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68 \
    file://LICENSES/GPL-2.0-or-later.txt;md5=fed54355545ffd980b814dab4a3b312c \
"

require libime.inc
inherit gtk-icon-cache cmake cmake_extra_sanity

SRCREV = "${FCITX5_CHINESE_ADDONS_SRCREV}"
SRC_URI = " \
    ${FCITX5_CHINESE_ADDONS_SRC_URI} \
    file://0001-cmake-fix-threads-and-native-LibIME-tools-for-cross-.patch \
"
PV = "${FCITX5_CHINESE_ADDONS_PV}"

DEPENDS:append:class-target = " ${PN}-native"
DEPENDS:append = " libime"

PACKAGECONFIG[opencc] = " -DENABLE_OPENCC=ON, -DENABLE_OPENCC=OFF, opencc nlohmann-json"
PACKAGECONFIG[cloudpinyin] = " -DENABLE_CLOUDPINYIN=ON, -DENABLE_CLOUDPINYIN=OFF, curl nlohmann-json"
PACKAGECONFIG[lua] = ", ,fcitx5-lua lua"

PACKAGECONFIG:append:class-target = " \
    opencc \
    cloudpinyin \
    lua \
"

PACKAGES =+ " \
    ${PN}-chttrans \
    ${PN}-fullwidth \
    ${PN}-pinyin \
    ${PN}-pinyinhelper \
    ${PN}-punctuation \
    ${PN}-table \
    ${PN}-lua \
    ${PN}-cloudpinyin \
"

SUMMARY:${PN}-chttrans = "Fcitx5 Chinese conversion addon"
DESCRIPTION:${PN}-chttrans = "Provides simplified and traditional Chinese character conversion for Fcitx5 input methods."
FILES:${PN}-chttrans = " \
    ${libdir}/fcitx5/libchttrans.so \
    ${datadir}/fcitx5/addon/chttrans.conf \
    ${datadir}/fcitx5/chttrans/ \
"

SUMMARY:${PN}-fullwidth = "Fcitx5 fullwidth character addon"
DESCRIPTION:${PN}-fullwidth = "Provides conversion between halfwidth and fullwidth characters for Fcitx5 input methods."
FILES:${PN}-fullwidth = " \
    ${libdir}/fcitx5/libfullwidth.so \
    ${datadir}/fcitx5/addon/fullwidth.conf \
"

SUMMARY:${PN}-pinyin = "Fcitx5 Pinyin and Shuangpin input methods"
DESCRIPTION:${PN}-pinyin = "Provides Pinyin and Shuangpin Chinese input methods for Fcitx5, including phonetic input and candidate selection."
FILES:${PN}-pinyin = " \
    ${libdir}/fcitx5/libpinyin.so \
    ${datadir}/fcitx5/addon/pinyin.conf \
    ${datadir}/fcitx5/inputmethod/pinyin.conf \
    ${datadir}/fcitx5/inputmethod/shuangpin.conf \
    ${datadir}/fcitx5/pinyin/ \
"

SUMMARY:${PN}-pinyinhelper = "Fcitx5 Pinyin helper module"
DESCRIPTION:${PN}-pinyinhelper = "Provides auxiliary Pinyin functionality for Fcitx5, including supporting data and input method integration."
FILES:${PN}-pinyinhelper = " \
    ${libdir}/fcitx5/libpinyinhelper.so \
    ${datadir}/fcitx5/addon/pinyinhelper.conf \
    ${datadir}/fcitx5/pinyinhelper/ \
"

SUMMARY:${PN}-punctuation = "Fcitx5 punctuation addon"
DESCRIPTION:${PN}-punctuation = "Provides Chinese punctuation handling and conversion for Fcitx5 input methods."
FILES:${PN}-punctuation = " \
    ${libdir}/fcitx5/libpunctuation.so \
    ${datadir}/fcitx5/addon/punctuation.conf \
    ${datadir}/fcitx5/punctuation/ \
"

SUMMARY:${PN}-table = "Fcitx5 table-based Chinese input methods"
DESCRIPTION:${PN}-table = "Provides table-based Chinese input methods for Fcitx5, including Cangjie, Erbi, Wubi, and other supported table layouts."
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
DESCRIPTION:${PN}-data = "Provides icons, translations, and application metadata for the Fcitx5 Chinese input method addons."
FILES:${PN}-data = " \
    ${datadir}/icons/hicolor/ \
    ${datadir}/locale/ \
    ${datadir}/metainfo/org.fcitx.Fcitx5.Addon.ChineseAddons.metainfo.xml \
"

SUMMARY:${PN}-tools = "Fcitx5 Chinese Addons conversion utilities"
DESCRIPTION:${PN}-tools = "Provides utilities for converting external Chinese input method dictionaries into formats supported by Fcitx5."
FILES:${PN}-tools = " \
    ${bindir}/scel2org5 \
"

SUMMARY:${PN}-lua = "Fcitx5 Chinese Addons Pinyin Lua extension"
DESCRIPTION:${PN}-lua = "Provides the Lua extension for integrating Pinyin functionality with the Fcitx5 Lua addon."
FILES:${PN}-lua = " \
    ${datadir}/fcitx5/lua/imeapi/extensions/pinyin.lua \
"
RDEPENDS:${PN}-lua = " \
    ${PN}-pinyin \
    fcitx5-lua-addonloader \
    fcitx5-lua-imeapi \
"

SUMMARY:${PN}-cloudpinyin = "Fcitx5 Cloud Pinyin addon"
DESCRIPTION:${PN}-cloudpinyin = "Provides cloud-based Pinyin candidate suggestions for Fcitx5 using supported online services."
FILES:${PN}-cloudpinyin = " \
    ${libdir}/fcitx5/libcloudpinyin.so \
    ${datadir}/fcitx5/addon/cloudpinyin.conf \
"

RDEPENDS:${PN}-cloudpinyin += " fcitx5"


