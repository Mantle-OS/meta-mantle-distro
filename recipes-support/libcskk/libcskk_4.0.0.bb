SUMMARY = "Japanese Kana-to-Kanji conversion library"
DESCRIPTION = "LibCSKK implements the Simple Kana Kanji conversion \
(SKK) input method in Rust, providing a C-compatible application \
programming interface for integration with Japanese input method \
frameworks such as Fcitx5."

HOMEPAGE = "https://github.com/naokiri/cskk"
BUGTRACKER = "https://github.com/naokiri/cskk/issues"
SECTION = "libs/inputmethods"

LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=1ebbd3e34237af26da5dc08a4e440464"

SRC_URI = " \
    git://github.com/naokiri/cskk.git;branch=master;protocol=https \
"

SRCREV = "951f45c24cc2bd7e1dff8da6eb2f690ba447b7b6"

inherit cargo_c cargo-update-recipe-crates

require libcskk-crates.inc

DEPENDS:append = " libxkbcommon"
CARGO_MANIFEST_PATH = "${S}/cskk/Cargo.toml"

FILES:${PN}:append = " ${libdir}/cskk/libcskk.so.* "
FILES:${PN}-dev:append = " ${libdir}/cskk/libcskk.so"

PACKAGES =+ " ${PN}-data"
SUMMARY:${PN}-data = "Japanese input conversion rules for CSKK"
DESCRIPTION:${PN}-data = "Character conversion tables, Kana and ASCII \
form definitions, and input method rules used by the CSKK library, \
including standard and AZIK input schemes."
FILES:${PN}-data = " ${datadir}/libcskk"
RDEPENDS:${PN} += "${PN}-data"
