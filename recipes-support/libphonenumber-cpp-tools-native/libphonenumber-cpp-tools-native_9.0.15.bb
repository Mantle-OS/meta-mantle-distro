SUMMARY = "Native geocoding data generator for libphonenumber"
DESCRIPTION = "Provides the native build-time utility used to generate \
geocoding data for the libphonenumber C++ library."
HOMEPAGE = "https://github.com/google/libphonenumber"
BUGTRACKER = "https://github.com/google/libphonenumber/issues"
SECTION = "devel"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://../../cpp/LICENSE;md5=363822a4d0f7a74c43ecdc2a4c11ae64"

inherit cmake cmake_extra_sanity native

SRC_URI = " \
    git://github.com/google/libphonenumber.git;branch=master;protocol=https \
    file://0001-add-absl-for-yocto-build.patch \
"

SRCREV = "543e633d35865a3cdfa4ae7bd20ba4a0f6bb7a0d"

S = "${UNPACKDIR}/${BP}/tools/cpp"

DEPENDS:append = " abseil-cpp-native"

PACKAGECONFIG ??= ""
PACKAGECONFIG[testing] = "-DBUILD_TESTING=ON, -DBUILD_TESTING=OFF, gtest"
