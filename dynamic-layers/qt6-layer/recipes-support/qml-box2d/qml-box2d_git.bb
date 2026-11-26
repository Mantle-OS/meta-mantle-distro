SUMMARY = "Box2D physics engine integration for Qt Quick"
DESCRIPTION = "Provides QML bindings for the Box2D two-dimensional physics \
engine, allowing Qt Quick applications to create and simulate rigid bodies, \
collision detection, joints and physical interactions within QML scenes."
HOMEPAGE = "https://github.com/qml-box2d/qml-box2d"
BUGTRACKER = "https://github.com/qml-box2d/qml-box2d/issues"
SECTION = "libs"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=7c1ecaedec0d10ec77d289e71b42165f"

inherit qt6-cmake cmake_extra_sanity

SRC_URI = " \
    git://github.com/qml-box2d/qml-box2d.git;branch=master;protocol=https \
    file://0001-update-install-paths-as-needed-for-qt6.patch \
"
SRCREV = "3a85439726d1ac4d082308feba45f23859ba71e0"

DEPENDS = " \
    qtbase6 \
    qtdeclarative6 \
    qtdeclarative6-native \
"

PACKAGECONFIG[qt6] = "-DUSE_QT6=ON,-DUSE_QT6=OFF"
# PACKAGECONFIG[system-box2d] = "-DUSE_SYSTEM_BOX2D=ON,-DUSE_SYSTEM_BOX2D=OFF, box2d"
# PACKAGECONFIG[16kb-pagesize] = "-DUSE_16KB_PAGESIZE=ON,-DUSE_16KB_PAGESIZE=OFF"
# PACKAGECONFIG[examples] = "-DBUILD_EXAMPLES=ON,-DBUILD_EXAMPLES=OFF"
PACKAGECONFIG[shared] = "-DBUILD_SHARED_LIBS=ON,-DBUILD_SHARED_LIBS=OFF"

EXTRA_OECMAKE:append = " \
    -DUSE_SYSTEM_BOX2D=OFF \
    -DUSE_16KB_PAGESIZE=OFF \
"

PACKAGECONFIG:append = " \
    qt6 \
    shared \
"

FILES:${PN} += " \
    ${QT6_INSTALL_QMLDIR}/Box2D/qmldir \
    ${QT6_INSTALL_QMLDIR}/Box2D/libqmlbox2d.so \
"
