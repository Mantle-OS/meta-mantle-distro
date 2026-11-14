SUMMARY = "A Mixed Integer Linear Programming (MILP) solver"
HOMEPAGE = "http://lpsolve.sourceforge.net/5.5/"
SECTION = "text"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://README.txt;start_line=6;end_line=7;md5=bc6cdbcd98faaddcdde660c085611e7c"

inherit pkgconfig

SRC_URI = " \
    git://github.com/lp-solve/lp_solve.git;branch=main;protocol=https \
    file://Makefile_lpsolve55 \
    file://Makefile_lp_solve \
"

SRCREV = "305e32cbfae961d580336258caeaeaa7c5538b91"

do_configure() {
    install -m 0644 ${WORKDIR}/Makefile_lpsolve55 ${S}/lpsolve55/Makefile
    install -m 0644 ${WORKDIR}/Makefile_lp_solve ${S}/lp_solve/Makefile
}

SOLIBMAJOR = "5"
do_compile() {
    oe_runmake -C ${S}/lpsolve55 \
        CC="${CC}" AR="${AR}" RANLIB="${RANLIB}" \
        CFLAGS="${CFLAGS}" CPPFLAGS="${CPPFLAGS}" LDFLAGS="${LDFLAGS}" \
        SOLIBMAJOR="${SOLIBMAJOR}"
    oe_runmake -C ${S}/lp_solve \
        CC="${CC}" CFLAGS="${CFLAGS}" CPPFLAGS="${CPPFLAGS}" LDFLAGS="${LDFLAGS}" \
        SOLIBMAJOR="${SOLIBMAJOR}"
}

do_install() {
    install -d ${D}${bindir} ${D}${libdir} ${D}${includedir}/lpsolve
    install -m 0755 ${S}/lp_solve/lp_solve ${D}${bindir}/

    install -m 0644 ${S}/lpsolve55/liblpsolve55.so.${SOLIBMAJOR} ${D}${libdir}/
    ln -sf liblpsolve55.so.${SOLIBMAJOR} ${D}${libdir}/liblpsolve55.so

    install -m 0644 ${S}/lp*.h ${D}${includedir}/lpsolve/
}

BBCLASSEXTEND = "native"
