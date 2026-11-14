SUMMARY = "Font rendering capabilities for complex non-Roman writing systems"
HOMEPAGE = "https://github.com/tfussell/xlnt"
SECTION = "text"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=4a1677d641addb7678c5500246411979"

inherit cmake pkgconfig

PACKAGECONFIG ?= ""
PACKAGECONFIG[static] = "-DSTATIC=ON, -DSTATIC=OFF, "
PACKAGECONFIG[tests] = "-DTESTS=ON, -DTESTS=OFF, "
PACKAGECONFIG[samples] = "-DSAMPLES=ON, -DSAMPLES=OFF, "
PACKAGECONFIG[benchmarks] = "-DBENCHMARKS=ON, -DBENCHMARKS=OFF, "
PACKAGECONFIG[docs] = "-DDOCUMENTATION=ON, -DDOCUMENTATION=OFF, "

SRC_URI = "\
    gitsm://github.com/xlnt-community/xlnt.git;branch=master;protocol=https \
"
SRCREV = "3b25b084eab473bb6009b8e3d03ba5ff10bb4891"

PV = "1.6.1+git${SRCPV}"
