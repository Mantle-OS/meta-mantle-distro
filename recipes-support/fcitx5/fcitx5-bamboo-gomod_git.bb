SUMMARY = "Go-based Bamboo input method engine for Fcitx5"
DESCRIPTION = "The Go implementation of the Bamboo Vietnamese input \
method engine, providing a C-compatible interface for integration \
with Fcitx5."
HOMEPAGE = "https://github.com/fcitx/fcitx5-bamboo"
SECTION = "inputmethods"

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68"

GO_IMPORT = "bamboo"
GO_WORKDIR = "${GO_IMPORT}/bamboo"
GO_INSTALL = "bamboo/..."

inherit go-mod go-mod-update-modules

require fcitx5-bamboo-gomod-go-mods.inc
require fcitx5-bamboo-gomod-licenses.inc

SRC_URI = " \
    gitsm://github.com/fcitx/fcitx5-bamboo.git;branch=main;protocol=https;destsuffix=${GO_SRCURI_DESTSUFFIX} \
"

SRCREV = "fb02a246b4dc208f29fd34bea33a0b30745d746b"
PV = "1.0.11"

do_compile:append() {
    cd ${B}/src/${GO_WORKDIR}

    ${GO} build \
        -buildmode=c-archive \
        -trimpath \
        -o ${B}/bamboo-core.a \
        .
    cd -
}

do_install:append() {

    install -d ${D}${libdir}
    install -d ${D}${includedir}/bamboo
    install -d ${D}${libdir}/pkgconfig

    # Install the Go C archive
    install -m 0644 \
        ${B}/bamboo-core.a \
        ${D}${libdir}/libbamboo-core.a

    # Install the generated C header
    install -m 0644 \
        ${B}/bamboo-core.h \
        ${D}${includedir}/bamboo/

    # Export the Go engine to CMake consumers
    cat > ${D}${libdir}/pkgconfig/bamboo-core.pc <<'EOF'
prefix=${prefix}
exec_prefix=${exec_prefix}
libdir=${libdir}
includedir=${includedir}

Name: bamboo-core
Description: Go-based Bamboo Vietnamese input method C archive
Version: ${PV}
Libs: -L${libdir} -lbamboo-core
Cflags: -I${includedir}/bamboo
EOF

    # Remove unrelated translation maintenance script from Go sources
    rm -f ${D}${libdir}/go/src/${GO_IMPORT}/Messages.sh
}


FILES:${PN} += " \
    ${bindir}/bamboo \
"

FILES:${PN}-dev += " \
    ${includedir}/bamboo/bamboo-core.h \
    ${libdir}/pkgconfig/bamboo-core.pc \
"

FILES:${PN}-staticdev += " \
    ${libdir}/libbamboo-core.a \
"



