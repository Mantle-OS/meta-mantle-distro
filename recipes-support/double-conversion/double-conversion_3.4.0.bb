SUMMARY = "Efficient binary-decimal and decimal-binary conversion routines"
SECTION = "libs"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=1ea35644f0ec0d9767897115667e901f"

SRC_URI = "git://github.com/google/double-conversion.git;branch=master;protocol=https"
SRCREV = "9dd6227ee3e29807183e56877f0282aeb40b8b1e"
PV = "3.4.0"

inherit cmake
EXTRA_OECMAKE += "-DBUILD_SHARED_LIBS=ON"
