SUMMARY = "xcb-imdkit"
DESCIPTION = "xcb-imdkit is an implementation of xim protocol in xcb, comparing with the implementation of IMDkit with Xlib, and xim inside Xlib, it has less memory foot print, better performance, and safer on malformed client."
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSES/LGPL-2.1-only.txt;md5=fabba2a3bfeb22a6483d44e9ae824d3f"

inherit cmake cmake_extra_sanity features_check
REQUIRED_DISTRO_FEATURES =  "x11"

SRC_URI = " \
    git://github.com/fcitx/xcb-imdkit.git;branch=master;protocol=https \
    file://0001-Fix-ON-vs-On-in-UTHASH-check-and-add-ENABLE_TEST-lik.patch \
    file://0002-Fix-indent-and-spelling-issue.patch \
"

SRCREV = "44f5c8219bcae9e6afc2391dc50486efcf0bdf06"
PV = "1.0.9"
S = "${WORKDIR}/git"

# find_package(XCB REQUIRED COMPONENTS XCB UTIL KEYSYMS)
DEPENDS:append = " \
    pkgconfig-native \
    ecm-kf6-native \
    libxcb \
    xcb-util \
    xcb-util-keysyms \
"

PACKAGECONFIG[uthash] = " -DUSE_SYSTEM_UTHASH=ON, -DUSE_SYSTEM_UTHASH=OFF,  uthash"

PACKAGECONFIG[tests] = " -DENABLE_TESTS=ON, -DENABLE_TESTS=OFF,  "
PACKAGECONFIG ?= " \
    uthash \
"



