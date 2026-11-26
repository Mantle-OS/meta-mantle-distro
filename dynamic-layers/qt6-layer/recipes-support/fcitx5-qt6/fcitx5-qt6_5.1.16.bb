SUMMARY = "Fcitx5 Qt6 input method integration"
DESCRIPTION = "Qt6 input context plugin and libraries providing Fcitx5 input method support for Qt applications."
SECTION = "inputmethods"
HOMEPAGE = "https://github.com/fcitx/fcitx5-qt"

LICENSE = "LGPL-2.1-or-later AND BSD-3-Clause"
LIC_FILES_CHKSUM = " \
    file://LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68 \
    file://LICENSES/BSD-3-Clause.txt;md5=954f4d71a37096249f837652a7f586c0 \
"

inherit qt6-cmake features_check
require recipes-support/fcitx5/fcitx5-git.inc

SRCREV = "${FCITX5_QT_SRCREV}"
SRC_URI = " \
    ${FCITX5_QT_SRC_URI} \
    file://0001-add-path-to-Fcitx5Utils_DIR.patch \
"
PV = "${FCITX5_QT_PV}"


ANY_OF_DISTRO_FEATURES = "x11 wayland"
ANY_OF_DISTRO_FEATURES:class-native = ""

DEPENDS:append = " \
    pkgconfig-native \
    ecm-kf6-native \
    gettext-native \
    libxkbcommon \
    qtbase6 \
"

PACKAGECONFIG[x11] = " -DENABLE_X11=ON, -DENABLE_X11=OFF, libx11 libxcb libxkbcommon libxfixes libxinerama libxrandr xcb-util-wm libxkbfile xcb-util-keysyms xcb-util xcb-util-image"
# !BUILD_ONLY_PLUGIN  then Fcitx5Utils IE  option(BUILD_ONLY_PLUGIN "Build only plugin" Off)
PACKAGECONFIG[plugin] = "-DBUILD_ONLY_PLUGIN=OFF, -DBUILD_ONLY_PLUGIN=ON, fcitx5 fcitx5-native"
# If Not then shared IE option(BUILD_STATIC_PLUGIN "Build plugin as static" Off)
PACKAGECONFIG[shared] = "-DBUILD_STATIC_PLUGIN=OFF, -DBUILD_STATIC_PLUGIN=ON, "
PACKAGECONFIG[fcitx-plugin-name] = " -DWITH_FCITX_PLUGIN_NAME=ON, -DWITH_FCITX_PLUGIN_NAME=OFF, "
PACKAGECONFIG[wayland] = "-DENABLE_QT6_WAYLAND_WORKAROUND=ON, -DENABLE_QT6_WAYLAND_WORKAROUND=OFF, qtwayland6 qtwayland6-native"

PACKAGECONFIG ?= " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)} \
    fcitx-plugin-name \
    plugin \
    shared \
"

EXTRA_OECMAKE += " \
    -DENABLE_QT4=OFF \
    -DENABLE_QT5=OFF \
    -DENABLE_QT6=ON \
    -DCMAKE_INSTALL_QT6PLUGINDIR=${QT6_INSTALL_PLUGINSDIR} \
"

PACKAGES =+  " \
    ${PN}-frontend \
"

# // Add desciption and summary missiing REDEPENDS on qtsidde if not picked up over the packlageconfig mos os it should
FILES:${PN}-frontend = " \
    ${libdir}/qt6/plugins/platforminputcontexts/libfcitx5platforminputcontextplugin.so \
    ${libexecdir}/fcitx5-qt6-gui-wrapper \
    ${datadir}/applications/org.fcitx.fcitx5-qt6-gui-wrapper.desktop \
    ${libdir}/fcitx5/qt6/libfcitx-quickphrase-editor5.so \
    ${bindir}/fcitx5-qt6-immodule-probing \
"
RDEPENDS:${PN}-frontend += "${PN}"
RRECOMMENDS:${PN}-frontend += "fcitx5"
