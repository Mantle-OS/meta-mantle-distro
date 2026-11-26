SUMMARY = "Maliit virtual keyboard for Qt6"
DESCRIPTION = "Provides a graphical on-screen keyboard based on the Maliit \
input method framework. Supports multilingual text input, predictive text, \
spell checking, configurable keyboard layouts and themes for touchscreen \
devices."
HOMEPAGE = "https://github.com/maliit/keyboard"
BUGTRACKER = "https://github.com/maliit/keyboard/issues"
SECTION = "inputmethods"

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

inherit qt6-cmake cmake_extra_sanity gettext
inherit gsettings
inherit pkgconfig

SRC_URI = " \
    git://github.com/maliit/keyboard.git;nobranch=1;protocol=https \
    file://0001-qml-Fix-for-qt6.patch \
    file://0001-cmake-add-Qt6-support.patch \
"
SRCREV = "9673c8e8bd64e091df6a07346919e476285fb04f"

DEPENDS += " \
    maliit-framework-qt6 \
    \
    qtbase6 \
    qtdeclarative6 qtdeclarative6-native \
    qt5compat6 \
    qtmultimedia6 \
    \
    gettext-native \
    glibc \
    hunspell \
    anthy-unicode \
    \
    libpinyin \
    libchewing \
"
EXTRA_OECMAKE += " \
    -Denable-tests=OFF \
    -Denable-presage=OFF \
    -Dwith-qt6=ON \
    -DCMAKE_POSITION_INDEPENDENT_CODE=ON \
    -DPinyin_DATA_DIR=${libdir}/libpinyin/data \
"

FILES:${PN} += " \
    ${datadir}/glib-2.0/schemas/org.maliit.keyboard.maliit.gschema.xml \
    ${datadir}/metainfo/com.github.maliit.keyboard.metainfo.xml \
    ${datadir}/maliit/keyboard2/themes \
    ${datadir}/maliit/keyboard2/styles \
    ${datadir}/maliit/keyboard2/icons \
    ${datadir}/maliit/keyboard2/images \
    ${datadir}/maliit/keyboard2/devices \
    ${libdir}/maliit/keyboard2/languages \
    ${libdir}/maliit/keyboard2/qml \
    ${libdir}/maliit/plugins/libmaliit-keyboard-plugin.so \
"
