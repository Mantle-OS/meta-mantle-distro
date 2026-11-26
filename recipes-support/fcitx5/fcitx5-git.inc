require fcitx5-data.inc

HOMEPAGE = "http://fcitx-im.org"

# DONE
FCITX5_SRC_URI = " \
    git://github.com/fcitx/fcitx5.git;branch=master;protocol=https; \
    ${EN_DICT_URL} \
"
FCITX5_SRCREV = "a578b409fe6e00dd0b8fe1f8dcdc4d527b0c9812"
FCITX5_PV = "5.1.23"

# DONE LIBIME
LIBIME_SRC_URI = " \
    gitsm://github.com/fcitx/libime.git;branch=master;protocol=https \
    ${LM_SC_ARPA_URL} \
    ${TABLE_URL} \
    ${DICT_URL} \
"
LIBIME_SRCREV = "53df6a248793f9a00c1769e658630de313f3e419"
LIBIME_PV = "1.1.18"

## DONE The Lua Plugin
FCITX5_LUA_SRC_URI = " git://github.com/fcitx/fcitx5-lua.git;branch=master;protocol=https;"
FCITX5_LUA_SRCREV = "9bf8d3dd2c6240d8e3e194d09547acbfa6a81204"
FCITX5_LUA_PV = "5.0.18"

# DONE Qt "Plugins"
FCITX5_QT_SRCREV = "115750044f3d0db16ca5859808c7da830fd1ff52"
FCITX5_QT_SRC_URI = "gitsm://github.com/fcitx/fcitx5-qt.git;branch=master;protocol=https;"
FCITX5_QT_PV = "5.1.16"

# DONE The GTK Plugins
FCITX5_GTK_SRC_URI = "git://github.com/fcitx/fcitx5-gtk.git;branch=master;protocol=https;"
FCITX5_GTK_SRCREV = "3b18d2ab7401d4233daf38bbc5896f1703685a43"
FCITX5_GTK_PV = "5.1.7"

## DONE FCITX5_CHINESE_ADDONS
FCITX5_CHINESE_ADDONS_SRC_URI = " \
    git://github.com/fcitx/fcitx5-chinese-addons.git;branch=master;protocol=https; \
    ${PY_TABLE_URL} \
    ${PY_STROKE_URL} \
"
FCITX5_CHINESE_ADDONS_SRCREV = "cc2db4477675bc10ea7ee709060d49ec9e8b9824"
FCITX5_CHINESE_ADDONS_PV = "5.1.15"

## DONE FCITX5_TABLE_OTHER
FCITX5_TABLE_OTHER_SRC_URI = " git://github.com/fcitx/fcitx5-table-other.git;branch=master;protocol=https;"
FCITX5_TABLE_OTHER_SRCREV = "0716685056e6c5f0710142164011ca0f2f8bc633"
FCITX5_TABLE_OTHER_PV = "5.1.8"

# DONE  FCITX5_TABLE_EXTRA
FCITX5_TABLE_EXTRA_SRC_URI = " git://github.com/fcitx/fcitx5-table-extra.git;branch=master;protocol=https;"
FCITX5_TABLE_EXTRA_SRCREV = "dbc7154a7f0b9fc04313160ae8066ed4d8cbc446"
FCITX5_TABLE_EXTRA_PV = "5.1.13"

## DONE LIBIME_JYUTPING
LIBIME_JYUTPING_SRC_URI = " \
    git://github.com/fcitx/libime-jyutping.git;branch=master;protocol=https \
    ${JYUTPING_DICT_URL} \
    ${JYUTPING_MODEL_URL} \
"
LIBIME_JYUTPING_SRCREV = "223beaf5ae0d6588496d592e8d746dc532e2b33b"
LIBIME_JYUTPING_PV = "1.0.18"

## UPGRADE LATER FCITX_M17N NEEDS
FCITX_M17N_SRC_URI = " git://github.com/fcitx/fcitx5-m17n.git;branch=master;protocol=https; "
FCITX_M17N_SRCREV = "298bfe6052366559b8ff1be741204c5a7887b904"
FCITX_M17N_PV = "5.1.8"

## DONE FCITX5_SKK set(REQUIRED_FCITX_VERSION 5.1.22)
FCITX5_SKK_SRC_URI = "git://github.com/fcitx/fcitx5-skk.git;branch=master;protocol=https;"
FCITX5_SKK_SRCREV = "ba3ff36cb15673018f16539ef63375b1f1aaa847"
FCITX5_SKK_PV = "5.1.11"

## DONE FCITX5_RIME Version set(REQUIRED_FCITX_VERSION 5.1.22)
FCITX5_RIME_SRC_URI = " git://github.com/fcitx/fcitx5-rime.git;branch=master;protocol=https;"
FCITX5_RIME_SRCREV = "f5048611ea60e53955d5f013f608551e85d38f09"
FCITX5_RIME_PV = "5.1.17"

## DONE FCITX5_CHEWING
FCITX5_CHEWING_SRC_URI = " git://github.com/fcitx/fcitx5-chewing.git;branch=master;protocol=https;"
FCITX5_CHEWING_SRCREV = "60096e8ef49df21de6c155d7a0785a58fcccb955"
FCITX5_CHEWING_PV = "5.1.13"

# DONE FCITX5_SAYURA
FCITX5_SAYURA_SRC_URI = " git://github.com/fcitx/fcitx5-sayura.git;branch=master;protocol=https;"
FCITX5_SAYURA_SRCREV = "a69985130b6e6d69cae461c4e400d3847879a9d9"
FCITX5_SAYURA_PV = "5.1.7"

## DONE FCITX5_BAMBOO
FCITX5_BAMBOO_SRC_URI = " gitsm://github.com/fcitx/fcitx5-bamboo.git;branch=main;protocol=https;"
FCITX5_BAMBOO_SRCREV = "fb02a246b4dc208f29fd34bea33a0b30745d746b"
FCITX5_BAMBOO_PV = "1.0.11"

## DONE FCITX5_HANGUL
FCITX5_HANGUL_SRC_URI = " git://github.com/fcitx/fcitx5-hangul.git;branch=master;protocol=https;"
FCITX5_HANGUL_SRCREV = "ede437b2d38bcc37abf382d9136f3ccbf8e16c08"
FCITX5_HANGUL_PV = "5.1.11"

# DONE FCITX5_UNIKEY
FCITX5_UNIKEY_SRC_URI = " git://github.com/fcitx/fcitx5-unikey.git;branch=master;protocol=https;"
FCITX5_UNIKEY_SRCREV = "ed9170c35c1a6275d105e03d7f9bc4f674c1469c"
FCITX5_UNIKEY_PV = "5.1.12"

# DONE FCITX5_LIBTHAI
FCITX5_LIBTHAI_SRC_URI = " git://github.com/fcitx/fcitx5-libthai.git;branch=master;protocol=https;"
FCITX5_LIBTHAI_SRCREV = "fd10ea0a8b9f980892bd61384c5f5a56f0b8bc52"
FCITX5_LIBTHAI_PV = "5.1.11"

# DONE FCITX5_ZHUYIN
FCITX5_ZHUYIN_SRC_URI = " \
    git://github.com/fcitx/fcitx5-zhuyin.git;branch=master;protocol=https; \
    ${ZHUYIN_MODEL_URL} \
"
FCITX5_ZHUYIN_SRCREV = "24717651c0293464bb4252c13b6852769cb72c5b"
FCITX5_ZHUYIN_PV = "5.1.9"

# DONE FCITX5_CSKK
FCITX5_CSKK_SRC_URI = " git://github.com/fcitx/fcitx5-cskk.git;branch=master;protocol=https;"
FCITX5_CSKK_SRCREV = "e4a6ee05eaef642b125b5ed0534275d2bc8b6544"
FCITX5_CSKK_PV = "1.2.0"

# DONE FCITX5_MOZC
FCITX5_MOZC_SRC_URI = " gitsm://github.com/fcitx/mozc-cmake;branch=master;protocol=https;"
FCITX5_MOZC_SRCREV = "35b68cf9c84588590739bca7bbe8ffe24833ef79"
FCITX5_MOZC_PV = "2.17.2102.102.1"

# needs kf6 handle over there
# https://github.com/fcitx/fcitx5-wayland-binding-generator

# BLOCKER KDE6 FCITX5_CONFIGTOOL
FCITX5_CONFIGTOOL_SRC_URI = " git://github.com/fcitx/fcitx5-configtool.git;branch=master;protocol=https;"
FCITX5_CONFIGTOOL_SRCREV = "8b82a9a02e03211557e7dbe888f43afb76367656"
FCITX5_CONFIGTOOL_PV = "5.1.16"

## OPT
## FCITX5_KEYMAN
## OKAY this one has a missing  upstream dep "keyman_core"
## https://keyman.com/en/linux/ -> https://launchpad.net/~keymanapp/+archive/ubuntu/keyman/+packages kinda nasty
# FCITX5_KEYMAN_SRC_URI = " git://github.com/fcitx/fcitx5-keyman.git;branch=master;protocol=https;"
# FCITX5_KEYMAN_SRCREV = "565e192a18e33e0085506b665264e7f78f00f900"
# FCITX5_KEYMAN_PV = "1.1.6"
