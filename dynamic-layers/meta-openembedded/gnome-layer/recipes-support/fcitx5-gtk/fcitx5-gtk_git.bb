SUMMARY = "Fcitx5 GTK input method integration"
DESCRIPTION = "Provides GTK input method modules for Fcitx5, along with a GObject client library for communicating with the Fcitx5 input method framework."
SECTION = "inputmethods"
HOMEPAGE = "https://github.com/fcitx/fcitx5-gtk"

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = " \
    file://LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68 \
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

# Use Yocto's native GObject Introspection wrappers so GIR and typelib generation uses the cross-compilation environment.
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
    ${PN}-gtk2 \
    ${PN}-gtk3 \
    ${PN}-gtk4 \
    ${PN}-gir \
    ${PN}-tools \
"

SUMMARY:${PN}-gtk3 = "Fcitx5 GTK3 input method module"
DESCRIPTION:${PN}-gtk3 = "Provides the GTK3 input method module that enables Fcitx5 text input integration in GTK3 applications."
FILES:${PN}-gtk3 = " \
    ${libdir}/gtk-3.0/3.0.0/immodules/im-fcitx5.so \
"
RDEPENDS:${PN}-gtk3 += " ${PN}"

SUMMARY:${PN}-gtk4 = "Fcitx5 GTK4 input method module"
DESCRIPTION:${PN}-gtk4 = "Provides the GTK4 input method module that enables Fcitx5 text input integration in GTK4 applications."
FILES:${PN}-gtk4 = " \
    ${libdir}/gtk-4.0/4.0.0/immodules/libim-fcitx5.so \
"
RDEPENDS:${PN}-gtk4 += " ${PN}"

SUMMARY:${PN}-gir = "Fcitx5 GClient GObject Introspection bindings"
DESCRIPTION:${PN}-gir = "Provides GObject Introspection typelib metadata for the Fcitx5 GClient library, enabling supported language bindings to access the client API."
FILES:${PN}-gir = " \
    ${libdir}/girepository-1.0/FcitxG-1.0.typelib \
"
RDEPENDS:${PN}-gir += " ${PN}"

SUMMARY:${PN}-tools = "Fcitx5 GTK input method probing utilities"
DESCRIPTION:${PN}-tools = "Provides GTK3 and GTK4 utilities for probing the availability and configuration of GTK input method modules."
FILES:${PN}-tools = " \
    ${bindir}/fcitx5-gtk3-immodule-probing \
    ${bindir}/fcitx5-gtk4-immodule-probing \
"


# GTK2 support is provided for completeness.
# WARNING: This configuration(GTK2) has never been built or tested.
# Keep PACKAGECONFIG[gtk2] disabled until the module is validated.
SUMMARY:${PN}-gtk2 = "Fcitx5 GTK2 input method module"
DESCRIPTION:${PN}-gtk2 = "Provides the GTK2 input method module for integrating Fcitx5 with legacy GTK2 applications."
# FILES:${PN}-gtk2 = " ${libdir}/gtk-2.0/2.10.0/immodules/im-fcitx5.so "
RDEPENDS:${PN}-gtk2 += "${PN}"
RRECOMMENDS:${PN}-gtk2 += "fcitx5"
