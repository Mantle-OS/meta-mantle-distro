SUMMARY = "Fcitx5 Qt5 Library"
DESCRIPTION = "This is a library to support generic input method implementation."
LICENSE = "LGPL-2.1-or-later & BSD-3-Clause"
LIC_FILES_CHKSUM = " \
    file://${WORKDIR}/git/LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68 \
    file://${WORKDIR}/git/LICENSES/BSD-3-Clause.txt;md5=954f4d71a37096249f837652a7f586c0 \
"

inherit cmake_qt5 features_check
require recipes-support/fcitx5/fcitx5-git.inc
SRCREV = "${FCITX5_QT_SRCREV}"
SRC_URI = " \
    ${FCITX5_QT_SRC_URI} \
    file://0001-add-path-to-Fcitx5Utils_DIR.patch \
"

PV = "${FCITX5_QT_PV}"


ANY_OF_DISTRO_FEATURES = "x11 wayland"
ANY_OF_DISTRO_FEATURES:class-native = ""

S = "${WORKDIR}/git"

DEPENDS:append = " \
    pkgconfig-native \
    ecm-kf6-native \
    libxkbcommon \
    gettext \
    qtbase \
"

PACKAGECONFIG[x11] = " -DENABLE_X11=ON, -DENABLE_X11=OFF, libx11 libxcb libxkbcommon libxfixes libxinerama libxrandr xcb-util-wm libxkbfile xcb-util-keysyms xcb-util xcb-util-image"

# !BUILD_ONLY_PLUGIN  then Fcitx5Utils
# option(BUILD_ONLY_PLUGIN "Build only plugin" Off)
PACKAGECONFIG[plugin] = "-DBUILD_ONLY_PLUGIN=OFF, -DBUILD_ONLY_PLUGIN=ON, fcitx5 fcitx5-native"

# If Not then shared
# option(BUILD_STATIC_PLUGIN "Build plugin as static" Off)
PACKAGECONFIG[shared] = "-DBUILD_STATIC_PLUGIN=OFF, -DBUILD_STATIC_PLUGIN=ON, "

# option(WITH_FCITX_PLUGIN_NAME "Enable plugin name with fcitx" On)
PACKAGECONFIG[fcitx-plugin-name] = " -DWITH_FCITX_PLUGIN_NAME=ON, -DWITH_FCITX_PLUGIN_NAME=OFF, "


PACKAGECONFIG ?= " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)} \
    fcitx-plugin-name \
    plugin \
    shared \
"

EXTRA_OECMAKE += " \
    -DENABLE_QT4=OFF \
    -DENABLE_QT6=OFF \
    -DENABLE_QT5=ON \
    -DCMAKE_INSTALL_QT5PLUGINDIR=${OE_QMAKE_PATH_PLUGINS} \
"

PACKAGES =+  " \
    ${PN}-frontend-qt5 \
"

FILES:${PN}-frontend-qt5 = " \
    ${libdir}/qt5/plugins/platforminputcontexts/libfcitx5platforminputcontextplugin.so \
    ${libexecdir}/fcitx5-qt5-gui-wrapper \
    ${datadir}/applications/org.fcitx.fcitx5-qt5-gui-wrapper.desktop \
    ${libdir}/fcitx5/qt5/libfcitx-quickphrase-editor5.so \
    ${bindir}/fcitx5-qt5-immodule-probing \
"
