SUMMARY = "Mozc Japanese input method engine for Fcitx5"
DESCRIPTION = "Mozc is an open-source Japanese input method editor \
based on Google Japanese Input. It provides Kana-to-Kanji conversion, \
predictive text input, dictionary management and candidate selection, \
with integration into the Fcitx5 input method framework."

HOMEPAGE = "https://github.com/fcitx/mozc"
BUGTRACKER = "https://github.com/fcitx/mozc/issues"
SECTION = "inputmethods"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSES/BSD-3-Clause.txt;md5=7b3ab643b9ce041de515d1ed092a36d4"

require recipes-support/fcitx5/fcitx5.inc
inherit gtk-icon-cache
inherit qt6-cmake

SRC_URI = " \
    ${FCITX5_MOZC_SRC_URI} \
    file://0001-Add-support-for-the-cross-compile-of-the-protoc.patch \
"

SRCREV = "${FCITX5_MOZC_SRCREV}"
PV = "${FCITX5_MOZC_PV}"
DEPENDS:append = " \
    protobuf protobuf-native \
    qtbase6 \
    qttools6 qttools6-native \
    python3 python3-native \
    abseil-cpp-native abseil-cpp python3-absl-native \
    fcitx5 \
"
DEPENDS:append:class-target = " ${PN}-native"

PACKAGECONFIG[native-tools] = "-DBUILD_NATIVE_TOOLS=ON,-DBUILD_NATIVE_TOOLS=OFF"
PACKAGECONFIG[server] = "-DENABLE_SERVER=ON -DBUILD_SERVER=ON, -DENABLE_SERVER=OFF -DBUILD_SERVER=OFF"
PACKAGECONFIG[tools]  = "-DBUILD_TOOL=ON, -DBUILD_TOOL=OFF"
PACKAGECONFIG[fcitx5] = "-DBUILD_FCITX=ON, -DBUILD_FCITX=OFF"

PACKAGECONFIG ??= " \
    tools \
    fcitx5 \
"

PACKAGECONFIG:class-native = " native-tools"
PACKAGECONFIG:append:class-target = " server"
EXTRA_OECMAKE:append = " \
    -DProtobuf_PROTOC_EXECUTABLE=${STAGING_BINDIR_NATIVE}/protoc \
    -DNATIVE_MOZC_TOOLS=${STAGING_BINDIR_NATIVE} \
"

PACKAGES =+ " \
    ${PN}-server \
    ${PN}-tools \
    ${PN}-icons \
"

FILES:${PN}:append = " \
    ${libdir}/fcitx5/fcitx5-mozc.so \
    ${datadir}/fcitx5/addon/mozc.conf \
    ${datadir}/fcitx5/inputmethod/mozc.conf \
    ${datadir}/metainfo/org.fcitx.Fcitx5.Addon.Mozc.metainfo.xml \
"

SUMMARY:${PN}-server = "Mozc Japanese input method server"
DESCRIPTION:${PN}-server = "Mozc conversion server providing Japanese \
Kana-to-Kanji conversion, predictive text input and dictionary services."
FILES:${PN}-server = " \
    ${libdir}/mozc/mozc_server \
"

SUMMARY:${PN}-tools = "Mozc graphical configuration tools"
DESCRIPTION:${PN}-tools = "Qt6-based graphical utilities for Mozc \
configuration, dictionary management and word registration."
FILES:${PN}-tools = " \
    ${libdir}/mozc/mozc_tool \
"

SUMMARY:${PN}-icons = "Icons for the Fcitx5 Mozc input method"
DESCRIPTION:${PN}-icons = "Hicolor theme icons representing Mozc \
input modes, dictionary management and configuration utilities."
FILES:${PN}-icons = " \
    ${datadir}/icons/hicolor \
"

RDEPENDS:${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'server', '${PN}-server', '', d)}"
RRECOMMENDS:${PN} += " \
    ${PN}-icons \
    ${@bb.utils.contains('PACKAGECONFIG', 'tools', '${PN}-tools', '', d)} \
"
