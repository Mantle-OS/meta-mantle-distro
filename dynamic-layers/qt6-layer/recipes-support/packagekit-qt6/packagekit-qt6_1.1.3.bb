SUMMARY = "Qt bindings for PackageKit"
SECTION = "libs"
HOMEPAGE = "https://github.com/PackageKit/PackageKit-Qt/"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c"

inherit kde6-cmake

SRC_URI = "git://https://github.com/PackageKit/PackageKit-Qt.git;branch=main;protocol=https"
SRCREV = "87d68852a19fef9961ee2658199d17f25e3dd442"
S = "${WORKDIR}/git"

DEPENDS:append = " \
    qtbase6 qtbase6-native \
    packagekit \
"

PACKAGECONFIG[qt6] = "-DBUILD_WITH_QT6=ON, -DBUILD_WITH_QT6=OFF"
PACKAGECONFIG:append = " qt6"
