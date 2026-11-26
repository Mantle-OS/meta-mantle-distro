inherit qt6-cmake

EXTRA_OE_CMAKE:append = " -DQT_MAJOR_VERSION=6"

PACKAGECONFIG[gui] = " -DENABLE_GUI=ON, -DENABLE_GUI=OFF, qtbase6 fcitx5-qt6"
PACKAGECONFIG[browser] = " -DENABLE_BROWSER=ON, -DENABLE_BROWSER=OFF, qtwebengine6"
PACKAGECONFIG:append:class-target = " \
    gui \
    ${@bb.utils.contains('DISTRO_FEATURES', 'webengine', 'browser', '', d)} \
"

PACKAGES =+ " ${PN}-qt6"
SUMMARY:${PN}-qt6 = "Fcitx5 Chinese Addons Qt6 configuration tools"
DESCRIPTION:${PN}-qt6 = "Provides Qt6 graphical configuration modules for managing Pinyin dictionaries and custom phrases in Fcitx5."
FILES:${PN}-qt6 = " \
    ${libdir}/fcitx5/qt6/libcustomphraseeditor.so \
    ${libdir}/fcitx5/qt6/libpinyindictmanager.so \
"

RDEPENDS:${PN}-qt6 += " ${PN}-pinyin"
