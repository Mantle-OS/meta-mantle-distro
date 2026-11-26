inherit qt6-cmake

PACKAGECONFIG[qt6] = "-DENABLE_QT=ON -DUSE_QT6=ON, \
                      -DENABLE_QT=OFF -DUSE_QT6=OFF, \
    qtbase6 fcitx5-qt6 \
"
PACKAGECONFIG:append = " qt6"

PACKAGES =+ " \
    ${PN}-qt6 \
"
SUMMARY:${PN}-qt6 = "Qt6 configuration interface for Fcitx5 CSKK"
DESCRIPTION:${PN}-qt6 = "Qt6-based graphical configuration interface \
for managing dictionaries and input method settings used by the \
Fcitx5 CSKK Japanese input method."

FILES:${PN}-qt6 = " \
    ${libdir}/fcitx5/qt6/*.so \
"

RDEPENDS:${PN}-qt6 += "${PN}"

RRECOMMENDS:${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'qt6', '${PN}-qt6', '', d)}"
