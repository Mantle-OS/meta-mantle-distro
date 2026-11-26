PACKAGECONFIG[qt6] = "-DENABLE_QT=ON, -DENABLE_QT=OFF, qtbase6 fcitx5-qt6"

PACKAGECONFIG:append = " qt6"

inherit qt6-cmake
PACKAGES =+ " ${PN}-qt6 "
SUMMARY:${PN}-qt6 = "Qt6 configuration editors for Fcitx5 Unikey"
DESCRIPTION:${PN}-qt6 = "Qt6-based macro and keyboard mapping editors for the Fcitx5 Unikey Vietnamese input method."

FILES:${PN}-qt6 = " \
    ${libdir}/fcitx5/qt6/libfcitx5-unikey-keymap-editor.so \
    ${libdir}/fcitx5/qt6/libfcitx5-unikey-macro-editor.so \
"
