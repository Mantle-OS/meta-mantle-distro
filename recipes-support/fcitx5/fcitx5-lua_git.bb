SUMMARY = "Fcitx5 Lua"
DESCRIPTION = "Lua addon loader and scripting support for Fcitx5."
HOMEPAGE = "https://github.com/fcitx/fcitx5-lua"
SECTION = "inputmethods"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68"

require fcitx5.inc

SRCREV = "${FCITX5_LUA_SRCREV}"
SRC_URI = " \
    ${FCITX5_LUA_SRC_URI} \
    file://0001-Use-threads-and-Fcitx5Utils_DIR.patch \
"
PV = "${FCITX5_LUA_PV}"

PACKAGECONFIG[dlopen] = " -DUSE_DLOPEN=ON, -DUSE_DLOPEN=OFF, "

DEPENDS:append = " \
    lua \
    fcitx5 \
"

PACKAGES =+ " \
    ${PN}-addonloader \
    ${PN}-imeapi \
"

SUMMARY:${PN}-addonloader = "Fcitx5 Lua addon loader"
DESCRIPTION:${PN}-addonloader = "Provides the Fcitx5 addon loader for executing Lua scripts and integrating Lua-based functionality with the input method framework."
FILES:${PN}-addonloader = " \
    ${libdir}/fcitx5/libluaaddonloader.so \
    ${datadir}/fcitx5/addon/luaaddonloader.conf \
    ${datadir}/metainfo/org.fcitx.Fcitx5.Addon.Lua.metainfo.xml \
"

SUMMARY:${PN}-imeapi = "Fcitx5 Lua input method API"
DESCRIPTION:${PN}-imeapi = "Provides the Lua input method API and supporting scripts for implementing and extending input methods in Fcitx5."
FILES:${PN}-imeapi = " \
    ${datadir}/fcitx5/addon/imeapi.conf \
    ${datadir}/fcitx5/lua/imeapi/ \
"

RDEPENDS:${PN}-addonloader += " ${PN}"
RDEPENDS:${PN}-imeapi += " ${PN}-addonloader"
