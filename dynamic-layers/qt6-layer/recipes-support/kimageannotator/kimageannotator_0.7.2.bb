SUMMARY = "Qt library for image annotation and editing"
DESCRIPTION = "kImageAnnotator is a Qt-based library for annotating \
images with text, arrows, shapes, and other graphical elements. \
It provides interactive tools for highlighting, drawing, cropping, \
and modifying images, making it suitable for screenshot utilities \
and other applications requiring image annotation."

HOMEPAGE = "https://github.com/ksnip/kImageAnnotator"
SECTION = "libs"

LICENSE = "LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3000208d539ec061b899bce1d9ce9404"
PR = "r0"

inherit qt6-cmake cmake_extra_sanity

SRC_URI = "git://github.com/ksnip/kImageAnnotator.git;nobranch=1;protocol=https"
SRCREV = "c481a8936946da6c81cd9042eca3d7aeed8d6982"

DEPENDS:append = " \
    kcolorpicker \
    qtbase6 \
    qttools6-native \
    qtsvg6 \
"

EXTRA_OECMAKE:append = " \
    -DBUILD_WITH_QT6=ON \
    -DBUILD_EXAMPLE=OFF \
    -DCMAKE_POSITION_INDEPENDENT_CODE=ON \
"

FILES:${PN} += " \
    ${datadir}/kImageAnnotator \
"
