SUMMARY = "Hamcrest Java matcher library"
DESCRIPTION = "Hamcrest 3.0 framework for writing declarative matcher objects used in Java testing."
HOMEPAGE = "https://hamcrest.org/JavaHamcrest/"
SECTION = "java"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${UNPACKDIR}/BSD-3-Clause;md5=71f739ef75581cae312e8c711bcdab16"

SRC_URI = " \
    https://repo1.maven.org/maven2/org/hamcrest/hamcrest/${PV}/hamcrest-${PV}-sources.jar;subdir=${BPN}-${PV} \
    file://BSD-3-Clause \
"

SRC_URI[sha256sum] = "7a4050b1898f7e1aa395cf2be78fb6683f9e2766fcb8e1507926b204fa24d1bf"

inherit java

JAVA_SOURCES_JARS = "${UNPACKDIR}/hamcrest-sources.jar"
JAVA_JAR_NAME = "hamcrest.jar"

BBCLASSEXTEND = "native"
