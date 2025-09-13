SUMMARY = "Spell checking support for Hebrew"
HOMEPAGE =  "http://ivrix.org.il/projects/spell-checker/"
SECTION = "text"
LICENSE = "AGPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=73f1eb20517c55bf9493b7dd6e480788"

SRC_URI = " \
    http://hspell.sourceforge.net/hspell-1.4.tar.gz \
    file://0001-bring-in-debian-patches.patch \
    file://0002-update-perl-path.patch \
    file://0003-remove-strip-and-lets-oe-do-that.patch \
"
SRC_URI[sha256sum] = "7310f5d58740d21d6d215c1179658602ef7da97a816bc1497c8764be97aabea3"

inherit autotools-brokensep pkgconfig gettext

DEPENDS = " zlib perl aspell hunspell hunspell-native "
RDEPENDS:${PN} = " perl"

PACKAGECONFIG = " linginfo fatverb "
PACKAGECONFIG[fatverb] = "--enable-fatverb, , "
PACKAGECONFIG[linginfo] = "--enable-linginfo, , "

EXTRA_OECONF += " --enable-shared"

PARALLEL_MAKE = ""

# so we have to patch this file and its not writable out of the tarball so ....
do_patch[prefuncs] += "genprefixes_make_writable"
genprefixes_make_writable() {
    chmod +w "${S}/genprefixes.pl"
}

do_compile(){
    export PERL5LIB="${S}:${B}:${S}/src"
    export PERL_USE_UNSAFE_INC=1
    oe_runmake hunspell;
}

FILES:${PN} += "\
    ${bindir}/hspell* \
    ${libdir}/libhspell.so.* \
    ${datadir}/hspell/* \
    ${mandir}/man1/* \
    ${mandir}/man3/*"

FILES:${PN}-dev += " \
    ${includedir} ${libdir}/libhspell.so \
"
FILES:${PN}-staticdev += " \
    ${libdir}/libhspell.a \
"

BBCLASSEXTEND = "native nativesdk"

