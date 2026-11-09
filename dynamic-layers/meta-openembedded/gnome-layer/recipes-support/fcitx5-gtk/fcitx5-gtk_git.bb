SUMMARY = "Fcitx5 GTK Library"
DESCRIPTION = "GTK input method modules and GObject client library for Fcitx5."
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = " \
    file://${WORKDIR}/git/LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68 \
"

require recipes-support/fcitx5/fcitx5.inc
inherit gobject-introspection

SRCREV = "${FCITX5_GTK_SRCREV}"
SRC_URI = " \
    ${FCITX5_GTK_SRC_URI} \
    file://0001-Fix-install-path-for-gclient.patch \
"
PV = "${FCITX5_GTK_PV}"

DEPENDS:append = " \
    glib-2.0 \
    libxkbcommon \
"

FCITX5_GTK_NO_SNOOPER_APPS ?= ".*chrome.*,.*chromium.*,firefox.*,Do.*"
FCITX5_GTK_NO_PREEDIT_APPS ?= "gvim.*"
FCITX5_GTK_SYNC_MODE_APPS ?= "firefox.*"

PACKAGECONFIG[gtk2] = " -DENABLE_GTK2_IM_MODULE=ON, -DENABLE_GTK2_IM_MODULE=OFF, gtk+"
PACKAGECONFIG[gtk3] = " -DENABLE_GTK3_IM_MODULE=ON, -DENABLE_GTK3_IM_MODULE=OFF, gtk+3"
PACKAGECONFIG[gtk4] = " -DENABLE_GTK4_IM_MODULE=ON, -DENABLE_GTK4_IM_MODULE=OFF, gtk4"
PACKAGECONFIG[gir] = " -DENABLE_GIR=ON, -DENABLE_GIR=OFF, gobject-introspection"

PACKAGECONFIG[snooper] = " -DENABLE_SNOOPER=ON, -DENABLE_SNOOPER=OFF,"

PACKAGECONFIG:append:class-target = " \
    gtk3 \
    gtk4 \
    snooper \
    ${@bb.utils.contains('DISTRO_FEATURES', 'gobject-introspection-data', 'gir', '', d)} \
"

EXTRA_OECMAKE:append = " \
    -DBUILD_ONLY_PLUGIN=OFF \
    -DNO_SNOOPER_APPS='${FCITX5_GTK_NO_SNOOPER_APPS}' \
    -DNO_PREEDIT_APPS='${FCITX5_GTK_NO_PREEDIT_APPS}' \
    -DSYNC_MODE_APPS='${FCITX5_GTK_SYNC_MODE_APPS}' \
    -DGIR_SCANNER=${STAGING_BINDIR}/g-ir-scanner-wrapper \
    -DGIR_COMPILER=${STAGING_BINDIR}/g-ir-compiler-wrapper \
"

DEPENDS:append:class-target = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', ' libx11', '', d)} \
"

PACKAGES =+ " \
    ${PN}-gtk3 \
    ${PN}-gtk4 \
    ${PN}-gir \
    ${PN}-tools \
"

SUMMARY:${PN}-gtk3 = "Fcitx5 GTK3 input method module"
FILES:${PN}-gtk3 = " \
    ${libdir}/gtk-3.0/3.0.0/immodules/im-fcitx5.so \
"

SUMMARY:${PN}-gtk4 = "Fcitx5 GTK4 input method module"
FILES:${PN}-gtk4 = " \
    ${libdir}/gtk-4.0/4.0.0/immodules/libim-fcitx5.so \
"

SUMMARY:${PN}-gir = "Fcitx5 GClient GObject Introspection bindings"
FILES:${PN}-gir = " \
    ${libdir}/girepository-1.0/FcitxG-1.0.typelib \
"

FILES:${PN}-tools = " \
    ${bindir}/fcitx5-gtk3-immodule-probing \
    ${bindir}/fcitx5-gtk4-immodule-probing \
"

RDEPENDS:${PN}-gtk3 += " ${PN}"
RDEPENDS:${PN}-gtk4 += " ${PN}"
RDEPENDS:${PN}-gir += " ${PN}"
