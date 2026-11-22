SUMMARY = "Fcitx5 Lua"
DESCRIPTION = "Lua addon loader and scripting support for Fcitx5."
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68"

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
FILES:${PN}-addonloader = " \
    ${libdir}/fcitx5/libluaaddonloader.so \
    ${datadir}/fcitx5/addon/luaaddonloader.conf \
    ${datadir}/metainfo/org.fcitx.Fcitx5.Addon.Lua.metainfo.xml \
"

SUMMARY:${PN}-imeapi = "Fcitx5 Lua input method API"
FILES:${PN}-imeapi = " \
    ${datadir}/fcitx5/addon/imeapi.conf \
    ${datadir}/fcitx5/lua/imeapi/ \
"

RDEPENDS:${PN}-addonloader += " ${PN}"
RDEPENDS:${PN}-imeapi += " ${PN}-addonloader"
