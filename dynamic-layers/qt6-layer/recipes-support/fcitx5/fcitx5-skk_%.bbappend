inherit qt6-cmake

PACKAGECONFIG[qt6] = "-DENABLE_QT=ON, -DENABLE_QT=OFF, qtbase6 fcitx5-qt6"
PACKAGECONFIG:append = " qt6"

PACKAGES =+ " ${PN}-qt6"
SUMMARY:${PN}-qt6 = "Qt6 graphical configuration module for Fcitx5 SKK"
DESCRIPTION:${PN}-qt6 = "Provides the Qt6 graphical interface for configuring SKK dictionaries and input rules."
FILES:${PN}-qt6 = " \
    ${libdir}/fcitx5/qt6/libfcitx5-skk-config.so \
"

RDEPENDS:${PN}-qt6 += "${PN}"
