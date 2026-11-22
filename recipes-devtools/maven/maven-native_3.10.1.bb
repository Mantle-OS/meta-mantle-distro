SUMMARY = "Apache Maven build automation tool (native)"
DESCRIPTION = "Prebuilt Apache Maven command-line build and dependency management tool for native OpenEmbedded builds."
HOMEPAGE = "https://maven.apache.org/"
SECTION = "devel"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${UNPACKDIR}/Apache-2.0;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV = "3.10.1"

SRC_URI = " \
    https://archive.apache.org/dist/maven/maven-3/${PV}/binaries/apache-maven-${PV}-bin.tar.gz;subdir=${BPN}-${PV} \
    file://Apache-2.0 \
"

SRC_URI[sha256sum] = "810b8348f0a40f9116c202093fae007ebeabf67699bf34d81ee7e272289e085a"

S = "${UNPACKDIR}/${BPN}-${PV}/apache-maven-${PV}"

inherit java

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${bindir} ${D}${datadir}/${BPN}
    cp -a ${S}/* ${D}${datadir}/${BPN}/
    ln -sf ../share/${BPN}/bin/mvn ${D}${bindir}/mvn
}

FILES:${PN} = " \
    ${bindir}/mvn \
    ${datadir}/${BPN} \
"

inherit native
