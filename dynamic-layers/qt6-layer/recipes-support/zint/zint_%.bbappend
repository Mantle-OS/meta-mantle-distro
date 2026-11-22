inherit qt6-cmake

DEPENDS:append = " \
    qtbase6 \
    qttools6 \
    qtsvg6 \
"

EXTRA_OECMAKE:append = " \
    -DZINT_USE_QT=ON \
    -DZINT_QT6=ON \
"

PACKAGES =+ " \
    ${PN}-qt6 \
    ${PN}-qt6-dev \
"

SUMMARY:${PN}-qt6 = "Qt6 graphical frontend for Zint barcode generation"
DESCRIPTION:${PN}-qt6 = "Qt6 graphical frontend for the Zint barcode \
encoding library, providing an interface for creating and exporting \
barcodes in supported symbologies."

FILES:${PN}-qt6 = " ${bindir}/zint-qt"
RDEPENDS:${PN}-qt6:append = " ${PN}"

FILES:${PN}-qt6-dev = " ${includedir}/qzint.h"
RDEPENDS:${PN}-qt6-dev:append = " ${PN}-dev"

