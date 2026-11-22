SUMMARY = "CMake integration for Rust projects"
DESCRIPTION = "Corrosion integrates Rust's Cargo build system with \
CMake, allowing C and C++ projects to import Rust libraries and \
executables as CMake targets. It supports cross-compilation, \
mixed-language linking, and integration of Rust crates into \
existing CMake build workflows."

HOMEPAGE = "https://github.com/corrosion-rs/corrosion"
SECTION = "devel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=00e27ef99850ca58780e6734b27b1fda"

inherit cmake cmake_extra_sanity native

SRC_URI = " \
    git://github.com/corrosion-rs/corrosion.git;branch=master;protocol=https \
    file://0001-native-update-install-path-for-cross-compile.patch \
"
SRCREV = "1499b14e4906a2890f5cee1547c8848db261753d"


EXTRA_OECMAKE:append = " \
    -DCMAKE_INSTALL_PREFIX:PATH=${prefix_native} \
    -DCMAKE_INSTALL_LIBDIR:PATH=lib \
    -DCMAKE_INSTALL_DATADIR:PATH=share \
    -DCORROSION_INSTALL_ONLY=ON \
    -DCORROSION_BUILD_TESTS=OFF \
"

