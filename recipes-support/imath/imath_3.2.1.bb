SUMMARY = "Imath is a basic, light-weight, and efficient C++ representation of 2D and 3D vectors and matrices"
SECTION = "libs"
HOMEPAGE = "https://imath.readthedocs.io/en/latest/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=0f34c2a8b1c102d683feca7a5835e921"

inherit office_cmake pkgconfig python3native

SRC_URI = "git://github.com/AcademySoftwareFoundation/Imath.git;protocol=https;branch=RB-3.2"
SRCREV = "fbcfb9833fe78277299080e8700fcf749a468bb0"

DEPENDS:append = " \
    clang-native \
"

PACKAGECONFIG[shared] = "-DBUILD_SHARED_LIBS=ON, , "
PACKAGECONFIG[py-bindings] = "-DPYBIND11=ON, -DPYBIND11=OFF, python3-native python3-pybind11-native boost"
PACKAGECONFIG[website] = "-DBUILD_WEBSITE=ON, -DBUILD_WEBSITE=OFF, python3-native python3-sphinx-native doxygen-native"
PACKAGECONFIG[testing] = " -DBUILD_TESTING=ON, -DBUILD_TESTING=OFF, "

EXTRA_OECMAKE = " \
    -DIMATH_INSTALL=ON \
"

PACKAGECONFIG:append = " shared"

#PACKAGEBEFORE
BBCLASSEXTEND = "native nativesdk"
