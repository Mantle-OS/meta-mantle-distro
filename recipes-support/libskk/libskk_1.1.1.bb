SUMMARY = "Japanese SKK input method library"
DESCRIPTION = "Libskk is a GObject-based library implementing the Japanese SKK input method. It provides kana conversion, input rules, dictionary handling, and utilities for Japanese text input."
SECTION = "inputmethods"
HOMEPAGE = "https://github.com/ueno/libskk"

LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = " \
    git://github.com/ueno/libskk.git;branch=master;protocol=https \
    file://0001-build-fix-Vala-package-lookup-for-out-of-tree-builds.patch \
"
SRCREV = "d23aacfaf060da6dc07213060473365f4d202b33"
PV = "1.1.1"

inherit autotools pkgconfig vala gettext gobject-introspection

DEPENDS:append = " \
    glib-2.0 \
    libgee \
    json-glib \
    libxkbcommon \
    vala-native \
"

PACKAGECONFIG ??= ""

PACKAGECONFIG[fep] = "--enable-fep,--disable-fep,libfep"
PACKAGECONFIG[docs] = "--enable-docs,--disable-docs,valadoc-native"
PACKAGECONFIG[code-coverage] = "--enable-code-coverage,--disable-code-coverage"
PACKAGECONFIG[valgrind-tests] = "--enable-valgrind-tests,--disable-valgrind-tests,valgrind"

EXTRA_OECONF:append = " \
    --disable-maintainer-mode \
"

BBCLASSEXTEND = "native nativesdk"
