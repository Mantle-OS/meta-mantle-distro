HOMEPAGE = "http://helm.cs.unibo.it/mml-widget/"
DEPENDS = "t1lib gtk+ popt libxslt-native libxml2"
SECTION = "text"
LICENSE = "LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6a6a8e020838b23406c81b19c1d46df6"

PR = "r3"
PV = "0.8.0+git${SRCPV}"

SRC_URI = " \
    git://github.com/GNOME/gtkmathview.git;branch=master;protocol=https \
    file://use_hostcxx.patch \
    file://0001-include-cstdio-to-get-printf-definitions.patch \
    file://0003-gcc-6.0-build-fixes.patch \
    file://0001-Fix-formatting-for-modern-c-11-compilers.patch \
    file://0001-build-register-bundled-Autoconf-macros.patch \
"
# file://0002-configure.ac-header-detection-of-hash_map-is-broken-.patch
SRCREV = "0bc2cfa0a47aed2c8a63abd989cb8da4dcceb2ec"

inherit features_check autotools pkgconfig

REQUIRED_DISTRO_FEATURES = "x11"

do_configure:append() {
    # avoid host polution inf pkg-config files
    sed -i "s:${STAGING_DIR_HOST}::g" `find -name '*.pc'`
}
