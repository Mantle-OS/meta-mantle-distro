SUMMARY = "JUnit 4 Java build integration example"
DESCRIPTION = "DO NOT INSTALL THIS PACKAGE IN PRODUCTION. \
This recipe is an educational example for OEM developers using Mantle OS. \
It demonstrates how to compile Java sources with java.bbclass, \
declare dependencies on another Java library, configure the native compiler classpath, generate a JAR, and package the resulting application using OpenEmbedded. \
JUnit 4.13.2 is used to demonstrate these capabilities, not as the recommended testing framework for new applications."
HOMEPAGE = "https://junit.org/junit4/"
SECTION = "java"

LICENSE = "EPL-1.0"
LIC_FILES_CHKSUM = "file://${UNPACKDIR}/EPL-1.0;md5=d0fc088e4e5216422c217d39853d8601"

## patch the old code
SRC_URI = " \
    https://repo1.maven.org/maven2/junit/junit/${PV}/junit-${PV}-sources.jar;subdir=${BPN}-${PV} \
    file://EPL-1.0 \
    file://0001-junit4-compatibility-with-hamcrest-3.patch \
"

SRC_URI[sha256sum] = "34181df6482d40ea4c046b063cb53c7ffae94bdf1b1d62695bdf3adf9dea7e3a"

## the point show how to use java
inherit java

## show how to link to another project
DEPENDS:append = " hamcrest-native"

#JAVA_SOURCES_JARS    = "${WORKDIR}/junit4-sources.jar"
JAVA_ADDITIONAL_JARS = "${STAGING_DATADIR_NATIVE}/java/hamcrest.jar"
JAVA_JAR_NAME        = "junit4.jar"

RDEPENDS:${PN} += " hamcrest"
RPROVIDES:${PN} += " junit"

BBCLASSEXTEND = "native"
