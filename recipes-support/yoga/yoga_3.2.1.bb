SUMMARY = "Yoga layout engine"
DESCRIPTION = "Yoga is an embeddable, cross-platform layout engine implementing Flexbox and supporting modern layout capabilities."
HOMEPAGE = "https://www.yogalayout.dev/"
SECTION = "libs"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=901f6cd9846257b3a9c69dbd0a49caf1"

SRC_URI = " \
    git://github.com/react/yoga.git;branch=release-v3.2;protocol=https \
    file://0001-cmake-add-optional-tests-and-system-gtest.patch \
    file://0002-cmake-fix-relocatable-include-directory.patch \
"

SRCREV = "042f5013152eb81c1552dec945b88f7b95ca350f"

inherit cmake cmake_extra_sanity

PACKAGECONFIG[tests] = "-DBUILD_TESTS=ON -DUSE_SYSTEM_GTEST=ON, \
                        -DBUILD_TESTS=OFF -DUSE_SYSTEM_GTEST=OFF, \
    googletest \
"
PACKAGECONFIG[fuzz-tests] = "-DBUILD_FUZZ_TESTS=ON,-DBUILD_FUZZ_TESTS=OFF, clang-native"

PACKAGECONFIG ??= ""

