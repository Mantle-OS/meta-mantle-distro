SUMMARY = "Scribus: Open source desktop publishing"
HOMEPAGE = "https://www.scribus.net/"
SECTION = "graphics"
LICENSE = "BSD-3-Clause AND GPL-2.0-or-later AND LGPL-2.0-or-later AND MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=895a25e4c5e0b71915c3b38571994c7e"

SRC_URI = " \
    git://github.com/scribusproject/scribus.git;branch=master;protocol=https \
"

SRCREV = "57d17b7a5e14703f197d053ade7caa8844116ad9"

inherit qt6-cmake office_cmake 
inherit pkgconfig python3native 
inherit gtk-icon-cache mime mime-xdg
inherit features_check 

REQUIRED_DISTRO_FEATURES = " opengl"
#<< OpenSceneGraph for 3D PDF Annocations
# if (WANT_NOOSG)
# https://github.com/ros/meta-ros/blob/master/meta-ros2/recipes-support/openscenegraph/openscenegraph_3.6.5.bb

SCIBUS_QT_STACK = " \
    qtbase6 \
    qt5compat6 \
    qttools6-native \
    qtsvg6 \
"

DEPENDS:append = " \
    freetype \
    cairo harfbuzz \
    tiff \
    libjpeg-turbo \
    libpng \
    lcms \
    hunspell \
    poppler \
    libxml2 \
    openssl podofo \
    cups \
    boost \
    librevenge \
    fontconfig \
    ghostscript \
    libfreehand \
    libcdr \
    libmspub \
    libvisio \
    python3-native \
    pkgconfig-native \
    libzmf \
    libqxp \
    ${SCIBUS_QT_STACK} \
"
#option(WITH_BOOST "Enable support for Boost based enhancements" ON)


EXTRA_OECMAKE:append = " \
    -DWANT_CPP23=ON \
"

FILES:${PN} += " \
    ${datadir}/ads/license \
    ${datadir}/mime/packages/*.xml \
    ${datadir}/metainfo/*.xml \
"
