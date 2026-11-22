SUMMARY = "Qt color selection widget with customizable color palettes"
DESCRIPTION = "kColorPicker is a Qt widget library that provides a \
color selection button with a popup palette. It supports selecting \
predefined colors, opening a color dialog, and adding custom colors \
to the palette for reuse in Qt applications."

HOMEPAGE = "https://github.com/ksnip/kColorPicker"
SECTION = "libs"

LICENSE = "LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3000208d539ec061b899bce1d9ce9404"
PR = "r0"

inherit qt6-cmake pkgconfig

SRC_URI = "git://github.com/ksnip/kColorPicker.git;nobranch=1;protocol=https"
SRCREV = "522e38b61e0786b97b2114c273a4551c869335be"

EXTRA_OECMAKE += " \
    -DBUILD_WITH_QT6=ON \
    -DBUILD_EXAMPLE=OFF \
    -DBUILD_SHARED_LIBS=ON \
"

DEPENDS:append = " qtbase6"
