SUMMARY = "Korean Hangul input method library"
DESCRIPTION = "Libhangul provides Hangul input method processing, \
Hanja dictionary support, and Hangul character classification \
for Korean text input applications."
HOMEPAGE = "https://github.com/libhangul/libhangul"
SECTION = "libs"

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343"

SRC_URI = " \
    git://github.com/libhangul/libhangul.git;branch=main;protocol=https \
"

SRCREV = "ac79b37b776a80e4ba453496ce4bb25fc48dd93f"

inherit cmake cmake_extra_sanity

DEPENDS:append = " \
    gettext-native \
"

PACKAGECONFIG[shared] = "-DBUILD_SHARED_LIBS=ON,-DBUILD_SHARED_LIBS=OFF"
PACKAGECONFIG[keyboards] = "-DENABLE_EXTERNAL_KEYBOARDS=ON,-DENABLE_EXTERNAL_KEYBOARDS=OFF,expat"
PACKAGECONFIG[tests] = "-DBUILD_TESTING=ON -DENABLE_UNIT_TEST=ON,-DBUILD_TESTING=OFF -DENABLE_UNIT_TEST=OFF,check"
PACKAGECONFIG[tools] = "-DENABLE_TOOLS=ON,-DENABLE_TOOLS=OFF"

PACKAGECONFIG ??= " \
    shared \
    keyboards \
    tools \
"

PACKAGES =+ " \
    ${PN}-tools \
"

SUMMARY:${PN}-tools = "Hangul input method command-line tool"
DESCRIPTION:${PN}-tools = "Command-line utility for exercising \
and testing the Hangul input method library."

do_install:append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'tools', 'true', 'false', d)}; then
        install -d ${D}${bindir}
        install -m 0755 ${B}/tools/hangul ${D}${bindir}/hangul
    fi
}

FILES:${PN}-tools = " \
    ${bindir}/hangul \
"

FILES:${PN} += " \
    ${datadir}/libhangul \
"
