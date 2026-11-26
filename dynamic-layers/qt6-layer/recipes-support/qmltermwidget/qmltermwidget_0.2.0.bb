SUMMARY = "Terminal emulator component for Qt Quick"
DESCRIPTION = "Provides a QML port of QTermWidget for embedding terminal \
emulation into Qt Quick applications. Supports terminal sessions, keyboard \
input, character rendering, color schemes and terminal escape sequences."
HOMEPAGE = "https://github.com/Swordfish90/qmltermwidget"
BUGTRACKER = "https://github.com/Swordfish90/qmltermwidget/issues"
SECTION = "libs"

LICENSE = "GPL-2.0-or-later AND BSD-3-Clause AND LGPL-2.0-or-later"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=4641e94ec96f98fabc56ff9cc48be14b \
    file://LICENSE.BSD-3-clause;md5=3775480a712fc46a69647678acb234cb \
    file://LICENSE.LGPL2+;md5=5f30f0716dfdd0d91eb439ebec522ec2 \
"

inherit qt6-qmake pkgconfig

SRC_URI = " \
    git://github.com/Swordfish90/qmltermwidget.git;nobranch=1;protocol=https \
    file://0001-Qt6-migration.patch \
    file://0002-Vt102Emulation-improve-QKeyPress-event-management.patch \
    file://0003-Remove-duplicate-files-from-install.patch \
"

# This hash is slightly ahead of the 0.2.0 release
SRCREV = "63228027e1f97c24abb907550b22ee91836929c5"

DEPENDS:append = " \
    qtbase6 qtbase6-native \
    qtdeclarative6 \
    qttools6 qttools6-native \
    qt5compat6 \
"

PARALLEL_MAKEINST = "-j1"
FILES:${PN} += " \
    ${QT6_INSTALL_QMLDIR}/QMLTermWidget/ \
"
