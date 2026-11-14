SUMMARY = "Mantle self-hosted toolchain"
DESCRIPTION = "Compilers and build toolchain components"
LICENSE = "MIT"
PR = "r1"

PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup

PACKAGECONFIG[rust] = ""
PACKAGECONFIG[golang] = ""
PACKAGECONFIG[llvm] = ""
PACKAGECONFIG[cmake] = ""
PACKAGECONFIG[autotools] = ""
PACKAGECONFIG[meson] = ""
PACKAGECONFIG[parser_generators] = ""

PACKAGECONFIG ?= " \
    autotools \
    rust \
    llvm \
    cmake \
    parser_generators \
"

PACKAGES = "\
    ${PN} \
    ${PN}-essentials \
"

SUMMARY:${PN}-essentials = "a bag of tools to build things"
DESCRIPTION:${PN}-essentials = "tools that play well with toolchains"
LICENSE:${PN}-essentials = "MIT"
RDEPENDS:${PN}-essentials = "\
    binutils \
    linux-libc-headers-dev \
    m4 \
    make \
    pkgconf \
    patch diffutils findutils \
    gettext \
    ccache \
"

RDEPENDS:${PN} = " \
    gcc gcc-symlinks \
    g++ g++-symlinks \
    cpp cpp-symlinks \
    glibc-dev \
    libstdc++-dev libstdc++-staticdev \
    ${PN}-essentials \
    ${@bb.utils.contains('PACKAGECONFIG', 'parser_generators', 'bison flex', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'meson', 'meson', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'autotools', 'autoconf automake libtool', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'cmake', 'cmake ninja', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'llvm', 'clang llvm', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'golang', 'go go-runtime', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'rust', 'rust cargo', '', d)} \
"
