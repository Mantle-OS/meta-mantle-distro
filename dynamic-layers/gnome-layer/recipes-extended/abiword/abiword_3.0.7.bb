SUMMARY = "AbiWord is free word processing program similar to Microsoft(r) Word"
HOMEPAGE = "http://www.abiword.org"
SECTION = "editors"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=c5edcc3ccd864b19004d14e9c1c9a26a"


# https://gitlab.gnome.org/World/AbiWord/-/blob/main/docs/build/BUILD.TXT?ref_type=heads

DEPENDS  = ""

SRC_URI = "git://gitlab.gnome.org/World/AbiWord.git;branch=ABI-3-0-0-STABLE;protocol=https"

SRCREV = "3114dd2d8b0e96323000af09e86eaf39caa3040f"

PV = "3.0.7"

inherit features_check autotools-brokensep pkgconfig mime-xdg
REQUIRED_DISTRO_FEATURES = "x11"
