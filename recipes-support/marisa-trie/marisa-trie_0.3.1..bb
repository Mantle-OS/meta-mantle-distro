SUMMARY = "marisa-trie"
DESCIPTION = "MARISA: Matching Algorithm with Recursively Implemented StorAge"
HOMEPAGE = "https://github.com/s-yata/marisa-trie"
LICENSE = "BSD-2-Clause | LGPL-2.1-or-later"
LIC_FILES_CHKSUM = " \
    file://COPYING.md;beginline=5;endline=15;md5=3baa7b0570fbf938dee6004b276102d9 \
    file://COPYING.md;beginline=17;endline=34;md5=28cbda254a47232a086345a5ca8e4c97 \
"

inherit cmake cmake_extra_sanity

SRC_URI = " \
    git://github.com/s-yata/marisa-trie.git;branch=master;protocol=https \
    file://0001-cmake-Add-shared-build-and-remove-install-config.patch \
"

SRCREV = "38180f6d3653cd2c88dd7047711e75f48f84527c"
PV = "0.3.1"
S = "${WORKDIR}/git"

PACKAGECONFIG[shared] = " -DBUILD_SHARED_LIBS=ON, -DBUILD_SHARED_LIBS=OFF, "
PACKAGECONFIG[march-native] = " -DENABLE_NATIVE_CODE=ON, -DENABLE_NATIVE_CODE=OFF, "
PACKAGECONFIG[tools] = " -DENABLE_TOOLS=ON, -DENABLE_TOOLS=OFF, "
PACKAGECONFIG[asan] = " -DENABLE_ASAN=ON, -DENABLE_ASAN=OFF, "
PACKAGECONFIG[ubsan] = " -DENABLE_UBSAN=ON, -DENABLE_UBSAN=OFF, "
PACKAGECONFIG[gperftools] = " -DENABLE_GPERFTOOLS_PROFILER=ON, -DENABLE_GPERFTOOLS_PROFILER=OFF, gperftools"
PACKAGECONFIG[coverage] = " -DENABLE_COVERAGE=ON -DBUILD_TESTING=ON, -DENABLE_COVERAGE=OFF, "
PACKAGECONFIG[static-stdlib] = " -DENABLE_STATIC_STDLIB=ON, -DENABLE_STATIC_STDLIB=OFF, "
PACKAGECONFIG[tests] = " -DBUILD_TESTING=ON, -DBUILD_TESTING=OFF, "

PACKAGECONFIG ?= " \
    shared \
    tools \
"
PACKAGECONFIG:class-native = " \
    shared \
    tools \
"

BBCLASSEXTEND = "native nativesdk"

