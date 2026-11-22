FILESEXTRAPATHS:prepend := "${THISDIR}/gradle:"
SUMMARY = "Gradle version 9 build automation tool (native)"
DESCRIPTION = "Prebuilt Gradle 9 build automation system for native OpenEmbedded builds, supporting JVM-based projects and dependency management."

require gradle.inc
PV = "9.9.1"
SRC_URI[sha256sum] = "d8a18a7a841dd5df825d35a685fad6f2d7f12ac0c280e57b67c27b4a520ba228"
do_install() {
    install -d ${D}${bindir} ${D}${datadir}/${BPN}
    cp -a ${S}/* ${D}${datadir}/${BPN}/
    ln -sf ../share/${BPN}/bin/gradle ${D}${bindir}/gradle-9
}



