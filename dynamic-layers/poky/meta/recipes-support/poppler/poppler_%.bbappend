FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append = " \
    file://0002-compile-with-qt6.patch \
"

inherit qt6-cmake
EXTRA_OECMAKE:append = " -DENABLE_QT6=ON"
DEPENDS:append = " \
    qtbase6 qtbase6-native \
    qttools6-native \
"
