DESCRIPTION = "QtKeychain"
HOMEPAGE = "https://github.com/frankosterfeld/qtkeychain"
SECTION = "lib"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=ebece3896618ecf0b60c4fade17f026f"

inherit kde6-ecm

SRC_URI = " \
    git://github.com/frankosterfeld/qtkeychain.git;nobranch=1;protocol=https \
"
SRCREV = "8bd4f4cfee5cc780274c2e247bf1c7df2bb46714"

PACKAGECONFIG[test-app] = "-DBUILD_TEST_APPLICATION=ON, -DBUILD_TEST_APPLICATION=OFF, "

# option(BUILD_QTQUICK_DEMO "Build QtQuick demo application" OFF)
PACKAGECONFIG[qquick-demo] = "-DBUILD_QTQUICK_DEMO=ON, -DBUILD_QTQUICK_DEMO=OFF, qtdeclarative6"

PACKAGECONFIG[qt6] = "-DBUILD_WITH_QT6=ON -DBUILD_WITH_QT5=OFF, -DBUILD_WITH_QT6=OFF, "
PACKAGECONFIG[translations] = "-DBUILD_TRANSLATIONS=ON, -DBUILD_TRANSLATIONS=OFF,  "
PACKAGECONFIG[shared] = "-DBUILD_SHARED_LIBS=ON, -DBUILD_SHARED_LIBS=OFF  "
PACKAGECONFIG[libsecret] = "-DLIBSECRET_SUPPORT=ON, -DLIBSECRET_SUPPORT=OFF, libsecret"

PACKAGECONFIG:append = " \
    qt6 \
    qquick-demo \
    shared \
    translations \
    libsecret \
"

PACKAGES =+ " ${PN}-data"
SUMMARY:${PN}-data = "data package for qt6keychain"
FILES:${PN}-data += " \
    ${datadir}/qt6keychain \
"
