SUMMARY = "Double-array trie library"
DESCRIPTION = "An implementation of the double-array trie structure \
for efficient string indexing, incremental matching, lexical \
analysis, and dictionary operations."
HOMEPAGE = "https://github.com/tlwg/libdatrie"
SECTION = "libs"

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=4bf661c1e3793e55c8d1051bc5e0ae21"

SRC_URI = " \
    git://github.com/tlwg/libdatrie.git;branch=master;protocol=https \
"
SRCREV = "665edc5ec1639a81df004d4f92f07a73d5b96aba"

inherit autotools

PACKAGECONFIG[docs] = "--enable-doxygen-doc,--disable-doxygen-doc,doxygen-native"

PACKAGECONFIG ??= ""

PACKAGES =+ " \
    ${PN}-tools \
"

SUMMARY:${PN}-tools = "Double-array trie command-line utilities"
DESCRIPTION:${PN}-tools = "Command-line tools for constructing, \
inspecting, and manipulating double-array trie dictionaries."

FILES:${PN}-tools = " \
    ${bindir}/trietool \
    ${bindir}/trietool-0.2 \
    ${mandir}/man1/trietool.1* \
    ${mandir}/man1/trietool-0.2.1* \
"
BBCLASSEXTEND = "native"
