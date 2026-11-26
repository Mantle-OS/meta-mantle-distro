SUMMARY = "Qalculate! library and CLI"
DESCRIPTION = "Qalculate! is a multipurpose mathematical calculation library \
and command-line calculator. It supports arbitrary-precision arithmetic, \
symbolic calculations, equation solving, unit conversions, mathematical \
functions, physical constants, statistical operations and currency conversion. \
It also provides optional plotting support through Gnuplot."
HOMEPAGE = "https://qalculate.github.io/"
BUGTRACKER = "https://github.com/Qalculate/libqalculate/issues"


LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=570a9b3749dd0463a1778803b12a6dce"

inherit autotools gettext pkgconfig

SRC_URI = " \
    git://github.com/Qalculate/libqalculate.git;branch=master;protocol=https \
"

SRCREV = "b245c4194a1a8dc14ba7ecf1e811e6d0b60592aa"

DEPENDS:append = " \
    intltool-native \
    gettext-native \
    gmp \
    mpfr \
    libxml2 \
"

PACKAGECONFIG[textport] = "--enable-textport, --disable-textport"
PACKAGECONFIG[docs] = "--enable-defs2doc, --disable-defs2doc,  doxygen-native"
PACKAGECONFIG[tests] = "--enable-tests, --disable-tests"
PACKAGECONFIG[unittests] = " --enable-unittests,  --disable-unittests"

PACKAGECONFIG[compiled-definitions] = "--enable-compiled-definitions, --disable-compiled-definitions"
PACKAGECONFIG[disable-insecure] = "--enable-insecure, --disable-insecure"

PACKAGECONFIG[curl] = "--with-libcurl, --without-libcurl, curl"
PACKAGECONFIG[icu] = "--with-icu, --without-icu, icu"

PACKAGECONFIG[gnuplot] = "--with-gnuplot-call, --without-gnuplot_call, gnuplot"
PACKAGECONFIG[readline] = "--with-readline, --without-readline, readline ncurses "

PACKAGECONFIG:append = " \
    textport \
    compiled-definitions \
    curl \
    icu \
    gnuplot \
    readline \
"

do_configure:prepend() {
    cp ${STAGING_DATADIR_NATIVE}/gettext/po/Makefile.in.in ${S}/po-defs/Makefile.in.in
    cp ${STAGING_DATADIR_NATIVE}/gettext/po/remove-potcdate.sed ${S}/po-defs/remove-potcdate.sed
}


FILES:${PN} += " \
    ${datadir}/qalculate \
"

PACKAGES =+ "${PN}-cli"
SUMMARY:${PN}-cli = "Qalculate! command-line calculator"
DESCRIPTION:${PN}-cli = "Qalculate CLI \
${DESCRIPTION}"
FILES:${PN}-cli = "${bindir}/qalc"
