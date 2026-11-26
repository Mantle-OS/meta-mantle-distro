SUMMARY = "C++ library for international phone number processing"
DESCRIPTION = "Provides functionality for parsing, formatting, validating \
and comparing international telephone numbers. Includes support for \
phone number geocoding, carrier identification and time zone mapping."
HOMEPAGE = "https://github.com/google/libphonenumber"
BUGTRACKER = "https://github.com/google/libphonenumber/issues"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=363822a4d0f7a74c43ecdc2a4c11ae64"

inherit cmake cmake_extra_sanity

SRC_URI = " \
    git://github.com/google/libphonenumber.git;branch=master;protocol=https \
    file://0001-add-way-to-use-native-gen-tool.patch \
"
SRCREV = "543e633d35865a3cdfa4ae7bd20ba4a0f6bb7a0d"

S = "${UNPACKDIR}/${BP}/cpp"

DEPENDS:append = " \
    abseil-cpp \
    openjdk-21-jdk-native \
    libphonenumber-cpp-tools-native \
"

CXXFLAGS:append = " -Wno-error=deprecated-declarations"
EXTRA_OECMAKE:append:class-target = " -DGENERATE_GEOCODING_DATA=${STAGING_BINDIR_NATIVE}/generate_geocoding_data"
EXTRA_OECMAKE:append:class-nativesdk = " -DGENERATE_GEOCODING_DATA=${STAGING_BINDIR_NATIVE}/generate_geocoding_data"

PACKAGECONFIG[geodecoder] = "-DBUILD_GEOCODER=ON, -DBUILD_GEOCODER=OFF, "

PACKAGECONFIG[regen-metadata] = "-DREGENERATE_METADATA=ON, -DREGENERATE_METADATA=FF"
PACKAGECONFIG[alt-format] = "-DUSE_ALTERNATE_FORMATS=ON, -DUSE_ALTERNATE_FORMATS=OFF, "

PACKAGECONFIG[protobuf] = "-DUSE_PROTOBUF_LITE=ON, -DUSE_PROTOBUF_LITE=OFF, protobuf protobuf-native"


PACKAGECONFIG[boost] = "-DUSE_BOOST=ON, -DUSE_BOOST=OFF, boost"

PACKAGECONFIG[icu-regex] = "-DUSE_ICU_REGEXP=ON, -DUSE_ICU_REGEXP=OFF, icu"
PACKAGECONFIG[light-metadata] = "-DUSE_LITE_METADATA=ON, -DUSE_LITE_METADATA=OFF, "
PACKAGECONFIG[use-re2] = "-DUSE_RE2=ON, -DUSE_RE2=OFF, "
PACKAGECONFIG[use-stdmap] =  "-DUSE_RE2=ON, -DUSE_RE2=OFF, "
PACKAGECONFIG[shared] =  "-DBUILD_SHARED_LIBS=ON -DBUILD_STATIC_LIB=OFF, -DBUILD_SHARED_LIBS=OFF -DBUILD_STATIC_LIB=ON, "
PACKAGECONFIG[testing] = "-DBUILD_TESTING=ON, -DBUILD_TESTING=OFF, gtest"
PACKAGECONFIG[tools] = "-DBUILD_TOOLS_ONLY=ON, -DBUILD_TOOLS_ONLY=OFF, "
PACKAGECONFIG[use-stdmutex] = "-DUSE_STDMUTEX=ON, -DUSE_STDMUTEX=OFF, "
PACKAGECONFIG[use-pthreads] = "-DUSE_POSIX_THREAD=ON, -DUSE_POSIX_THREAD=OFF, "

PACKAGECONFIG:append = " \
    geodecoder \
    regen-metadata \
    alt-format \
    protobuf \
    icu-regex \
"

do_install:append() {
    sed -i \
        -e 's|${RECIPE_SYSROOT}${libdir}/libicuuc.so|icuuc|g' \
        -e 's|${RECIPE_SYSROOT}${libdir}/libicui18n.so|icui18n|g' \
        -e 's|${RECIPE_SYSROOT}${libdir}/libprotobuf-lite.so|protobuf-lite|g' \
        ${D}${libdir}/cmake/libphonenumber/libphonenumber-targets.cmake
}
