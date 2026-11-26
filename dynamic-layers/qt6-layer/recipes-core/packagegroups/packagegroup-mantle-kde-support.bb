SUMMARY = "KDE6 and Qt6 supporting libraries and utilities"
DESCRIPTION = "Collection of supporting libraries, desktop integration services, \
and utilities used by KDE Plasma 6 and Qt6 applications. This package group \
provides a validation grouping for the KDE6 software stack without requiring \
installation of the complete Plasma desktop."

LICENSE = "MIT"
PR = "r1"

PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup

ALLOW_EMPTY:${PN} = "1"

RRECOMMENDS:${PN} = " \
    switcheroo-control \
    zxing-cpp \
    zint \
    packagekit \
    appstream \
    kcolorpicker \
    kimageannotator \
    packagekit-qt6 \
"
