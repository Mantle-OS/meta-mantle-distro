SUMMARY = "Data Matrix barcode encoding and decoding library"
DESCRIPTION = "libdmtx is an open-source C library for encoding and \
decoding Data Matrix two-dimensional barcodes. It provides an API \
for generating and reading Data Matrix symbols and supports \
integration into applications across multiple platforms."

HOMEPAGE = "https://github.com/dmtx/libdmtx"
SECTION = "libs"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b58872aaf3a9d51c1f002b9d7940f4f1"

inherit autotools

SRCREV = "500d7af6106713610a0f8b2d96aefa81912b96bc"
SRC_URI = "git://github.com/dmtx/libdmtx;branch=master;protocol=https"
