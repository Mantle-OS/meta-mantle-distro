SUMMARY = "Capi for chewing rust"
HOMEPAGE = ""
SECTION = "libs"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = " \
    gitsm://github.com/chewing/libchewing.git;branch=master;protocol=https \
    file://0001-Add-cdylib-to-cargo-so-other-apps-and-libs-can-conso.patch \
"

SRCREV = "05ae6bcb9309c466a1b32d69c146bc583be04747"
inherit cargo cargo-update-recipe-crates pkgconfig

require chewing-capi-crates.inc


CARGO_BUILD_FLAGS:append = " --locked -p chewing_capi -p chewing-cli"
CARGO_MANIFEST_PATH = "${S}/capi/Cargo.toml"

CARGO_INSTALL_LIBRARIES = "1"

export CARGO_PROFILE_RELEASE_PANIC = "unwind"
RUSTFLAGS:append = " -C panic=unwind"

DEPENDS:append = " ncurses"
PACKAGECONFIG[sqlite] = "--features sqlite, ,sqlite3 pkgconfig"
PACKAGECONFIG:append = " \
    sqlite \
"

cargo_do_install:append() {
    install -d ${D}${libdir}/pkgconfig
    install -d ${D}${includedir}/chewing-capi

    # Install the static library
    install -m 0644 \
        ${B}/target/${CARGO_TARGET_SUBDIR}/libchewing_capi.a \
        ${D}${libdir}/

    # Install the shared library
    install -m 0755 \
        ${B}/target/${CARGO_TARGET_SUBDIR}/libchewing_capi.so \
        ${D}${libdir}/

    # Install the public headers
    install -m 0644 ${S}/capi/include/*.h ${D}${includedir}/chewing-capi/

    # Create pkg-config metadata for libchewing
    cat > ${D}${libdir}/pkgconfig/chewing_capi.pc <<'EOF'
prefix=${prefix}
exec_prefix=${exec_prefix}
libdir=${libdir}
includedir=${includedir}
Name: chewing_capi
Description: C ABI for libchewing (Rust)
Version: ${PV}
Libs: -L${libdir} -lchewing_capi
Cflags: -I${includedir}/chewing-capi
EOF
}


FILES_SOLIBSDEV = ""

PACKAGES =+ " \
    chewing-tools \
"

FILES:${PN} += " \
    ${libdir}/libchewing_capi.so \
    ${libdir}/pkgconfig/chewing_capi.pc \
"

FILES:chewing-tools = " \
    ${bindir}/chewing-cli \
"

FILES:{PN}-staticdev = " \
    ${libdir}/libchewing_capi.a \
"

BBCLASSEXTEND = "native"
