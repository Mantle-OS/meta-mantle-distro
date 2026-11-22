SUMMARY = "C++ library for reading and writing barcodes"
DESCRIPTION = "ZXing-C++ is a barcode processing library supporting \
decoding and encoding of one-dimensional and two-dimensional barcode \
formats, including QR Code, Data Matrix, Aztec, PDF417, and common \
retail barcode formats. It provides native C++ APIs and optional \
C, Python, and Qt integration."

HOMEPAGE = "https://github.com/zxing-cpp/zxing-cpp"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fa818a259cbed7ce8bc2a22d35a464fc"

inherit cmake cmake_extra_sanity

SRC_URI = " git://github.com/zxing-cpp/zxing-cpp.git;branch=master;protocol=https;"
SRCREV = "287c85df6f961c8efbfb5ffd736cd9457b8b890e"

EXTRA_OECMAKE += " \
    -DZXING_TEST_DOTNET=OFF \
    -DZXING_TEST_GO=OFF \
    -DZXING_DEPENDENCIES=LOCAL \
    -DZXING_CLANG_TIDY=OFF \
    -DBUILD_SHARED_LIBS=ON \
    -DCMAKE_BUILD_TYPE=Release \
    -DZXING_USE_BUNDLED_ZINT=OFF \
"

DEPENDS:append = " \
    git-native \
    libwebp \
    zint \
"

PACKAGECONFIG[writer] = " -DZXING_WRITERS=ON, ZXING_WRITERS=OFF"
PACKAGECONFIG[reader] = " -DZXING_READERS=ON, -DZXING_READERS=OFF, "
PACKAGECONFIG[c-api] = " -DZXING_C_API=ON, -DZXING_C_API=OFF, libstb"
PACKAGECONFIG[experimental] = " -DZXING_EXPERIMENTAL_API=ON, -DZXING_EXPERIMENTAL_API=OFF, "
PACKAGECONFIG[examples] = " -DZXING_EXAMPLES=ON, -DZXING_EXAMPLES=OFF, qtmultimedia6 opencv"
PACKAGECONFIG[blackbox-tests] = " -DZXING_BLACKBOX_TESTS=ON, -DZXING_BLACKBOX_TESTS=OFF, googletest"
PACKAGECONFIG[unit-tests] = " -DZXING_UNIT_TESTS=ON, -DZXING_UNIT_TESTS=OFF, googletest"
PACKAGECONFIG[py-bindings] = " -DZXING_PYTHON_MODULE=ON, -DZXING_PYTHON_MODULE=OFF, python3-native"

PACKAGECONFIG:append = " \
    writer \
    reader \
    c-api \
    examples \
"
