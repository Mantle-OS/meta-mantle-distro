SUMMARY = "Kotlin JVM compiler (native)"
DESCRIPTION = "Prebuilt JetBrains Kotlin 2.4 command-line compiler for native OpenEmbedded builds, including JVM bytecode compilation and Kotlin runtime libraries."
HOMEPAGE = "https://kotlinlang.org/"
SECTION = "devel"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${UNPACKDIR}/Apache-2.0;md5=3b83ef96387f14655fc854ddc3c6bd57"


SRC_URI = " \
    https://github.com/JetBrains/kotlin/releases/download/v${PV}/kotlin-compiler-${PV}.zip;subdir=${BPN}-${PV} \
    file://Apache-2.0 \
"

SRC_URI[sha256sum] = "7cc140e76daf416a0424a5557f99afd7ca89ebd463ea1101261e3e01c33e75cd"

inherit java
inherit native


DEPENDS:append = " unzip-native"

# S = "${UNPACKDIR}/${BPN}-${PV}/kotlin-compiler-${PV}"
S = "${UNPACKDIR}/${BPN}-${PV}/kotlinc"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${libdir}/kotlin/${BPN}-${PV}
    cp -R --no-dereference --preserve=mode,timestamps,links -v "${S}/." "${D}${libdir}/kotlin/${BPN}-${PV}/"

    find "${D}${libdir}/kotlin/${BPN}-${PV}/bin" -type f -name "*.bat" -delete || true
    chmod 0755 "${D}${libdir}/kotlin/${BPN}-${PV}/bin/"* || true

    install -d ${D}${bindir}
    ln -sf ../lib/kotlin/${BPN}-${PV}/bin/kotlinc ${D}${bindir}/kotlinc
    ln -sf ../lib/kotlin/${BPN}-${PV}/bin/kotlin  ${D}${bindir}/kotlin
}

FILES:${PN} = " \
    ${bindir}/kotlinc \
    ${bindir}/kotlin \
    ${libdir}/kotlin/${BPN}-${PV} \
"
