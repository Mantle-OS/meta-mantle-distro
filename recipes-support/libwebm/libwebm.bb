SUMMARY = "Lib WebM"
DESCRIPTION = "WebM is an open, royalty-free, media file format designed for the web. \
  WebM defines the file container structure, video and audio formats. \
  WebM files consist of video streams compressed with the VP8 or VP9 video codecs and audio streams compressed with the Vorbis or Opus audio codecs."
HOMEPAGE = "https://www.webmproject.org/code"
SECTION = "lib"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE.TXT;md5=6e8dee932c26f2dab503abf70c96d8bb"

SRC_URI += " \
    git://github.com/webmproject/libwebm.git;branch=main;protocol=https; \
"
SRCREV = "f2a982d748b80586ae53b89a2e6ebbc305848b8c"

PV = "0.3.4"
S = "${WORKDIR}/git"

inherit cmake cmake_extra_sanity

PACKAGECONFIG[shared] = " -DBUILD_SHARED_LIBS=ON, -DBUILD_SHARED_LIBS=OFF,"
PACKAGECONFIG[webmts] = " -DENABLE_WEBMTS=ON, -DENABLE_WEBMTS=OFF,"
PACKAGECONFIG[webminfo] = " -DENABLE_WEBMINFO=ON, -DENABLE_WEBMINFO=OFF,"
PACKAGECONFIG[tests] = " -DENABLE_TESTS=ON, -DENABLE_TESTS=OFF, googletest"
PACKAGECONFIG[iwyu] = " -DENABLE_IWYU=ON, -DENABLE_IWYU=OFF, python3-native"
PACKAGECONFIG[werror] = " -DENABLE_WERROR=ON, -DENABLE_WERROR=OFF,"
PACKAGECONFIG[webm-parser] = " -DENABLE_WEBM_PARSER=ON, -DENABLE_WEBM_PARSER=OFF,"
PACKAGECONFIG[sample-programs] = " -DENABLE_SAMPLE_PROGRAMS=ON, -DENABLE_SAMPLE_PROGRAMS=OFF,"

PACKAGECONFIG ?= " \
    shared \
    webmts \
    webminfo \
    sample-programs \
"

do_install:append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'sample-programs', 'true', 'false', d)}; then
        install -d ${D}${bindir}
        install -m 0755 ${B}/mkvparser_sample ${D}${bindir}/
        install -m 0755 ${B}/mkvmuxer_sample ${D}${bindir}/
        install -m 0755 ${B}/dumpvtt ${D}${bindir}/
        install -m 0755 ${B}/vttdemux ${D}${bindir}/
    fi

    if ${@bb.utils.contains('PACKAGECONFIG', 'webminfo', 'true', 'false', d)}; then
        install -d ${D}${bindir}
        install -m 0755 ${B}/webm_info ${D}${bindir}/
    fi
}

PACKAGES =+ " \
    ${PN}-examples \
"

SUMMARY:${PN}-examples = "Libwebm sample programs and utilities"
FILES:${PN}-examples = " \
    ${bindir}/mkvparser_sample \
    ${bindir}/mkvmuxer_sample \
    ${bindir}/dumpvtt \
    ${bindir}/vttdemux \
    ${bindir}/webm_info \
"
RDEPENDS:${PN}-examples = "${PN}"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
