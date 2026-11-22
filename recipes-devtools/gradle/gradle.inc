FILESEXTRAPATHS:prepend := "${THISDIR}/gradle:"
SUMMARY ?= "Gradle build automation tool (native)"
DESCRIPTION ?= "Prebuilt Gradle build automation system for native OpenEmbedded builds, supporting JVM-based projects and dependency management."
HOMEPAGE = "https://gradle.org/"
SECTION = "devel"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${UNPACKDIR}/Apache-2.0;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = " \
    https://services.gradle.org/distributions/gradle-${PV}-bin.zip;subdir=${BPN}-${PV} \
    file://Apache-2.0 \
"

inherit java
inherit native

S = "${UNPACKDIR}/${BPN}-${PV}/gradle-${PV}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES:${PN} = " \
    ${bindir}/gradle \
    ${datadir}/${BPN} \
"
