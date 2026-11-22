SUMMARY = "2Geom: easy 2D graphics library"
HOMEPAGE = "https://gitlab.com/inkscape/lib2geom"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING-LGPL-2.1;md5=fad9b3332be894bab9bc501572864b29"

inherit office_cmake features_check pkgconfig

DEPENDS:append  = " \
    boost \
    double-conversion \
    glib-2.0 \
    gtk4 \
    pkgconfig-native \
    gsl \
    ragel-native \
"
# patch follow up. 
# https://gitlab.com/inkscape/lib2geom/-/issues/84
SRC_URI = " \
    git://gitlab.com/inkscape/lib2geom.git;branch=master;protocol=https \
"
SRCREV = "c7d8378ccc109bdb2ed30f0c8407725e93ffdd9b"
PV = "1.5.0"

REQUIRED_DISTRO_FEATURES = "opengl"

PACKAGECONFIG ?= " shared cairo gpl toys "

PACKAGECONFIG[shared] = "-D2GEOM_BUILD_SHARED=ON, -D2GEOM_BUILD_SHARED=OFF, "
PACKAGECONFIG[cairo] = ", , cairo"
PACKAGECONFIG[gpl]  = "-D2GEOM_USE_GPL_CODE=ON, -D2GEOM_USE_GPL_CODE=OFF, "
PACKAGECONFIG[toys]  = "-D2GEOM_TOYS=ON, -D2GEOM_TOYS=OFF, ragel-native gtk4"
# PACKAGECONFIG[toys-lpe] = "-D2GEOM_TOYS_LPE=ON, 2GEOM_TOYS_LPE=OFF, gtk+3"
PACKAGECONFIG[cython] = "   -D2GEOM_CYTHON_BUILD_SHARED=ON -D2GEOM_CYTHON_BINDINGS=ON, \
                            -D2GEOM_CYTHON_BUILD_SHARED=OFF -D2GEOM_CYTHON_BINDINGS=OFF, \
    python3-cython-native \
    python-native \
"

PACKAGECONFIG[testing] = " -D2GEOM_TESTING=ON, -D2GEOM_TESTING=OFF, googletest-native"
PACKAGECONFIG[profile] = "-DWITH_PROFILING=ON, -DWITH_PROFILING=OFF, "
PACKAGECONFIG[coverage] = "-DWITH_COVERAGE=ON, -DWITH_COVERAGE=OFF, "
PACKAGECONFIG[performance-tests] = "-D2GEOM_PERFORMANCE_TESTS=ON, -D2GEOM_PERFORMANCE_TESTS=OFF, "

EXTRA_OECMAKE:append = " -DCMAKE_SKIP_INSTALL_RPATH=ON"
