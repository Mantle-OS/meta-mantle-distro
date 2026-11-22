SUMMARY = "Gradle version 8 build automation tool (native)"
DESCRIPTION = "Prebuilt Gradle 8 build automation system for native OpenEmbedded builds, supporting JVM-based projects and dependency management."

require gradle.inc
PV = "8.14.7"
SRC_URI[sha256sum] = "903bfc4bc7622323124054c10f509246212a1902948bdaf26c379cca8735e856"

do_install() {
    install -d ${D}${bindir} ${D}${datadir}/${BPN}
    cp -a ${S}/* ${D}${datadir}/${BPN}/
    ln -sf ../share/${BPN}/bin/gradle ${D}${bindir}/gradle-8
}
