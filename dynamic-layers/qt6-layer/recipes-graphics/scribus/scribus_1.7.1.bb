SUMMARY = "Scribus: Open source desktop publishing"
HOMEPAGE = "https://www.scribus.net/"
SECTION = "graphics"
LICENSE = "BSD-3-Clause AND GPL-2.0-or-later AND LGPL-2.0-or-later AND MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=8624a5a8d9594ce303aa8ece9bc8af28"

SRC_URI = " \
    git://github.com/scribusproject/scribus.git;branch=master;protocol=https \
    file://0001-get-the-smart-pointer.patch \
    file://0002-qt6-cast-unit-for-the-unicode.patch \
"
SRCREV = "7e6ec5f5e3c8361582fe64b0919d851c407aab58"

inherit qt6-cmake office_cmake 
inherit pkgconfig python3native 
inherit gtk-icon-cache mime mime-xdg
inherit features_check 

REQUIRED_DISTRO_FEATURES = " opengl"
#<< OpenSceneGraph for 3D PDF Annocations
# if (WANT_NOOSG)
# https://github.com/ros/meta-ros/blob/master/meta-ros2/recipes-support/openscenegraph/openscenegraph_3.6.5.bb

# note the naming here. There is a script in upscream taht 
# renames qtX to qtX6 this way distro's can have both qt5 and qt6 installed 
SCIBUS_QT_STACK = " \
    qtbase6 \
    qt5compat6 \
    qttools6-native \
    qtsvg6 \
"

DEPENDS += " \
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

EXTRA_OECMAKE:append = " -DWANT_CPP17=ON"

FILES:${PN} += " \
    ${prefix}/license/ads \
    ${datadir}/mime/packages/*.xml \
    ${datadir}/metainfo/*.xml \
"
