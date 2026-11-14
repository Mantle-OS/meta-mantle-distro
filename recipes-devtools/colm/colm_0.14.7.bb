SUMMARY = "Colm: a language for analysis/transformation of computer languages"
DESCRIPTION = "Colm is a programming language and runtime designed for parsing and \
rewriting programs. Ragel 7 uses Colm during its build."
HOMEPAGE = "https://www.colm.net/open-source/colm/"
SECTION = "devel"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://COPYING;md5=b454748513fc18f7a002e2a2ae0f653d"

PV = "0.14.7"

SRC_URI = "git://github.com/adrian-thurston/colm.git;branch=master;protocol=https"
SRCREV = "e88bda068d4a25f2afa7f48821e0f539405c8c6a"

inherit autotools-brokensep pkgconfig

DEPENDS += " \
    libtool \
    automake \
    autoconf-archive \
    bison \
    flex \
"
DEPENDS:append:class-target = " colm-native"
RDEPENDS:${PN} += " bash"

PACKAGECONFIG ?= ""
# Blocker: no fig2dev https://sourceforge.net/projects/mcj/files/mcj-source/  requires some more recipes
#PACKAGECONFIG[docs] = "--enable-manual, --disable-manual, asciidoc-native python3-pygments-native"
EXTRA_OECONF:append = " --disable-manual --enable-shared"

PACKAGECONFIG ?= ""
PACKAGECONFIG[pool-malloc] = "--enable-pool-malloc, "
PACKAGECONFIG[java] = "JAVAC_BIN=javac, , openjdk-17"
PACKAGECONFIG[ruby] = "RUBY_BIN=ruby, , ruby"
PACKAGECONFIG[go] = "GO_BIN=go, , go"
PACKAGECONFIG[rust] = "RUST_BIN=rustc, , rust cargo"

CPPFLAGS:append = " -I${S}/src/include"

EXTRA_OECONF:remove = "--disable-static"
EXTRA_OECONF:append:class-target = " \
    --with-colm=${STAGING_DIR_NATIVE}/usr/ \
"
EXTRA_OECONF:append:class-nativesdk = " \
    --with-colm=${STAGING_DIR_NATIVE}/usr/ \
"

do_compile() {
    oe_runmake -C ${B}/src all
}

do_install() {
    oe_runmake -C ${B}/src install DESTDIR=${D}

    for lib in colm fsm; 
    do
        if [ -f ${D}${libdir}/lib${lib}-${PV}.so ]; 
        then
            ln -sf lib${lib}-${PV}.so ${D}${libdir}/lib${lib}.so
        fi
    done
}

FILES_SOLIBSDEV = ""
FILES:${PN} += " \
    ${libdir}/libcolm-*.so \
    ${libdir}/libfsm-*.so \
    ${datadir}/*.lm \
"

FILES:${PN}-dev = " \
    ${includedir}/colm/*.h \
    ${includedir}/aapl/*.h \
    ${includedir}/libfsm/*.h \
    ${libdir}/libcolm.so \
    ${libdir}/libfsm.so \
    ${libdir}/pkgconfig/*.pc \
"

BBCLASSEXTEND = "native nativesdk"
