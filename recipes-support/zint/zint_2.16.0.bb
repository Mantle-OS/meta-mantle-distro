SUMMARY = "Barcode encoding library and utilities"
DESCRIPTION = "Zint is a barcode encoding library supporting \
more than 50 barcode symbologies, including Code 128, Data Matrix, \
QR Code, PDF417, Aztec, MaxiCode, GS1 DataBar, and UPC/EAN. \
It provides a shared library and optional command-line and \
graphical frontends."

HOMEPAGE = "https://zint.org.uk"
SECTION = "libs"

LICENSE = "BSD-3-Clause AND GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7a60e5da828a98fbdf48c9a327c7cdfa"

inherit cmake cmake_extra_sanity

SRC_URI = " git://github.com/zint/zint.git;branch=master;protocol=https;"
SRCREV = "55541e139e62b9209b71cd9b0ba9010cec28b1d9"

EXTRA_OECMAKE:append = " \
    -DZINT_DEBUG=OFF \
    -DZINT_NOOPT=OFF \
    -DZINT_SANITIZE=OFF \
    -DZINT_COVERAGE=OFF \
"

# zint
PACKAGECONFIG[tests] = " -DZINT_TEST=ON, -DZINT_TEST=OFF, "
PACKAGECONFIG[shared] = " -DZINT_SHARED=ON -DZINT_STATIC=OFF, -DZINT_SHARED=OFF -DZINT_STATIC=ON, "
PACKAGECONFIG[frontend] = " -DZINT_FRONTEND=ON, -DZINT_FRONTEND=OFF, "
PACKAGECONFIG[gs1se] = "-DZINT_USE_GS1SE=ON, -DZINT_USE_GS1SE=OFF,"
PACKAGECONFIG[png] = " -DZINT_USE_PNG=ON, -DZINT_USE_PNG=OFF, libpng"
PACKAGECONFIG[uninstall] = " -DZINT_UNINSTALL=ON, -DZINT_UNINSTALL=OFF,"

# zint-qt goes into  dynamic layers
PACKAGECONFIG:append = " \
    shared \
    gs1se \
    png \
    frontend \
    uninstall \
"

PACKAGES =+ " ${PN}-frontend"
SUMMARY:${PN}-frontend = "Zint command-line barcode generator"
DESCRIPTION:${PN}-frontend = "Command-line utility for generating barcodes using the Zint encoding library."
FILES:${PN}-frontend = " \
    ${bindir}/zint \
    ${mandir}/man1/zint.1* \
"
