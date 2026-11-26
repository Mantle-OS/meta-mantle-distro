SUMMARY = "Maliit input method framework"
DESCRIPTION = "Provides the core libraries, input method server and plugin \
infrastructure for the Maliit input method framework. Supports integration \
of virtual keyboards and other input methods with graphical applications \
and Wayland compositors."
HOMEPAGE = "https://github.com/maliit/framework"
BUGTRACKER = "https://github.com/maliit/framework/issues"
SECTION = "libs"

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=5c917f6ce94ceb8d8d5e16e2fca5b9ad"

inherit qt6-cmake cmake_extra_sanity
inherit features_check

REQUIRED_DISTRO_FEATURES = "wayland"

SRC_URI = " \
    git://github.com/maliit/framework.git;branch=qt6-support;protocol=https \
    file://0001-common-namespace-add-missing-include-QList.patch \
    file://0002-maliitpluginsconfig-fix-for-qt6.patch \
    file://0003-shellintegration-qwaylandinputpanelshellintegration-.patch \
    file://0004-debian-upstream-patch.patch \
"
#     https://sources.debian.org/data/main/m/maliit-framework/2.3.0-11/debian/patches/qt6.10.patch

SRCREV = "031a368a87e8aa8db62794a9be6139c27baafc26"
SRC_URI[sha256sum] = "0c0ce589821a38b484e18ea2f8f5f785462d8b80934d4b3193b85a23a1229291"

DEPENDS:append = " \
    qtbase6 \
    qtdeclarative6 \
    qtmultimedia6 \
    qtwayland6 qtwayland6-native \
    \
    glibc glib-2.0-native \
    wayland \
    python3-packaging-native \
"

EXTRA_OECMAKE += " \
    -Dwith-qt6=ON \
    -Denable-docs=OFF \
    -Denable-tests=OFF \
    -Denable-dbus-activation=ON \
    -DQtWaylandScanner_EXECUTABLE=${RECIPE_SYSROOT_NATIVE}/${libexecdir}/qt6/qtwaylandscanner \
    -DQT_PLUGINS_INSTALL_DIR=${QT6_INSTALL_PLUGINSDIR} \
    -DQT_MKSPECS_INSTALL_DIR=${QT6_INSTALL_MKSPECSDIR} \
    -DCMAKE_POSITION_INDEPENDENT_CODE=ON \
"

# workaround for leaked host paths by glib-genmarshal
# both are generated at compile time
do_compile:append() {
    sed -i 's:${S}::g' ${B}/maliitmarshallers.c
    sed -i 's:${S}::g' ${B}/maliitmarshallers.h
}

# workaround for leaked host paths to CMake imports
do_install:append() {
    if [ "0" -ne $(find ${D} \( -name "*.cmake" \) | wc -l) ]; then
        find ${D} \( -name "*.cmake" \) -exec \
            sed -i -e 's|${RECIPE_SYSROOT}/usr|\$\{_IMPORT_PREFIX\}|g' \
                {} \;
    fi
}


FILES:${PN} += " \
    ${QT6_INSTALL_PLUGINSDIR}/wayland-shell-integration/libinputpanel-shell.so \
    ${bindir}/maliit-server \
    ${datadir}/dbus-1/ \
"

FILES:${PN}-dev += " \
    ${QT6_INSTALL_MKSPECSDIR}/features/maliit-*.prf \
"
