inherit qt6-cmake
PACKAGES =+ "${PN}-qt6"
SUMMARY:${PN}-qt6 = "Qt6 camera-based barcode reader for ZXing-C++"
DESCRIPTION:${PN}-qt6 = "ZXingQtCamReader is a Qt6 application that uses a camera to capture and decode barcodes through the ZXing-C++ library and Qt Multimedia."

EXTRA_OECMAKE:append = " -DZXING_EXAMPLES_QT=ON"

# find_package(Qt6 COMPONENTS Gui Multimedia Quick Widgets QUIET)
DEPENDS:append = " \
    qtbase6 \
    qtmultimedia6 \
    qtdeclarative6 qtdeclarative6-native \
"

DEPENDS:append:class-nativesdk = " \
    nativesdk-qtbase6 \
    nativesdk-qttools6 \
"

FILES:${PN}-qt6 = " ${bindir}/ZXingQtCamReader"
