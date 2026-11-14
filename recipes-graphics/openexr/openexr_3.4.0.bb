SUMMARY = "OpenEXR provides the specification and reference implementation of the EXR file format"
LICENSE = "BSD-3-Clause"
HOMEPAGE = "https://www.aswf.io/projects/"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=b0f98dc4bafd54ae93bbd833040c68a3"
SECTION = "libs"

SRC_URI = "git://github.com/AcademySoftwareFoundation/openexr.git;protocol=https;branch=RB-3.4"
SRCREV = "20a65852895894434bea88613f6d29ac8e88bd6e"

inherit office_cmake python3native pkgconfig

DEPENDS:append = " \
    imath \
    clang-native \
    zlib \
"

PACKAGECONFIG[libs] = "-DOPENEXR_BUILD_LIBS=ON , -DOPENEXR_BUILD_LIBS=OFF, imath"

PACKAGECONFIG[tools] = "-DOPENEXR_BUILD_TOOLS=ON -DOPENEXR_INSTALL_TOOLS=ON, \
                        -DOPENEXR_BUILD_TOOLS=OFF -DOPENEXR_INSTALL_TOOLS=OFF,  "

PACKAGECONFIG[examples] = "-DOPENEXR_BUILD_EXAMPLES=ON, -DOPENEXR_BUILD_EXAMPLES=OFF,  "
PACKAGECONFIG[testing] = "-DBUILD_TESTING=ON, -DBUILD_TESTING=OFF"
PACKAGECONFIG[docs] = "-DOPENEXR_INSTALL_DOCS=ON, -DOPENEXR_INSTALL_DOCS=OFF, doxygen-native help2man-native"
PACKAGECONFIG[py-bindings] = "-DOPENEXR_BUILD_PYTHON=ON, -DOPENEXR_BUILD_PYTHON=OFF, python3-native "
PACKAGECONFIG[threading] = "-DOPENEXR_ENABLE_THREADING=ON, -DOPENEXR_ENABLE_THREADING=OFF, "
PACKAGECONFIG[website] = "-DBUILD_WEBSITE=ON, -DBUILD_WEBSITE=OFF, python3-native python3-sphinx-native doxygen-native, "
PACKAGECONFIG[deflate] = "-DEXR_DEFLATE_LIB=ON, , libdeflate"
PACKAGECONFIG[openjph] = "-DEXR_OPENJPH_LIB=ON -DCMAKE_CXX_FLAGS='-I${STAGING_INCDIR}/openjph', , openjph"

PACKAGECONFIG:append  = " \
    libs \
    threading \
    tools \
    deflate \
    openjph \
" 

EXTRA_OECMAKE:append = " \
    -DOPENEXR_INSTALL=ON \
    -DOPENEXR_IS_SUBPROJECT=OFF \
    -DBUILD_SHARED_LIBS=ON \
"

BBCLASSEXTEND = "native nativesdk"
