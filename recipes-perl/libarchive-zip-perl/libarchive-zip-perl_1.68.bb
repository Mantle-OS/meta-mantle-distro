## TODO move this over to recipes-devtools/perl
DESCRIPTION = "perl interface to ZIP archive files"
SECTION = "libs"
LICENSE = "Artistic-1.0 OR GPL-1.0-or-later"
LIC_FILES_CHKSUM = "file://README.md;md5=d0b8e31dbb1fb53f203b23a64d26122b"

PV = "1.68"

SRC_URI = "https://cpan.metacpan.org/authors/id/P/PH/PHRED/Archive-Zip-${PV}.tar.gz"
SRC_URI[sha256sum] = "984e185d785baf6129c6e75f8eb44411745ac00bf6122fb1c8e822a3861ec650"

inherit cpan

DEPENDS += "zlib"

S = "${UNPACKDIR}/Archive-Zip-${PV}"

EXTRA_PERLFLAGS = "-I ${STAGING_LIBDIR_NATIVE}/perl-native/perl/${@get_perl_version(d)}"

BBCLASSEXTEND = "native"
