SUMMARY = "Mantle OS self-hosted build environment"
DESCRIPTION = "Package groups providing the tools required to build Mantle OS"
LICENSE = "MIT"
PR = "r1"

PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup

ALLOW_EMPTY:${PN} = "1"

PACKAGES = " \
    ${PN} \
    ${PN}-toolchain \
    ${PN}-python3 \
    ${PN}-compression \
    ${PN}-graphics \
    ${PN}-networking \
    ${PN}-applications \
"

SUMMARY:${PN}-toolchain = "Mantle self-hosted toolchain"
DESCRIPTION:${PN}-toolchain = "Compilers and build toolchain components"
RDEPENDS:${PN}-toolchain = " \
    gcc \
    gcc-symlinks \
    g++ \
    g++-symlinks \
    cpp \
    cpp-symlinks \
    binutils \
    glibc-dev \
    libstdc++-dev \
    libstdc++-staticdev \
    linux-libc-headers-dev \
    make \
    cmake \
    ninja \
    meson \
    autoconf \
    automake \
    libtool \
    m4 \
    pkgconf \
    bison \
    flex \
    patch \
    diffutils \
    findutils \
    gettext \
    ccache \
    cargo \
    rust \
"

SUMMARY:${PN}-python3 = "Mantle self-hosted Python tools"
DESCRIPTION:${PN}-python3 = "Python runtime, modules and build tools"
RDEPENDS:${PN}-python3 = " \
    python3 \
    python3-pip \
    python3-setuptools \
    python3-pylint \
    python3-wheel \
    python3-pyyaml \
    python3-distro \
    python3-jsonschema \
    libnewt \
    python3-colorlog \
    python3-kconfiglib \
    python3-websockets \
    python3-pexpect \
    python3-git \
    python3-jinja2 \
    python3-subunit \
    python3-shtab \
    python3-kas \
"

SUMMARY:${PN}-compression = "Mantle self-hosted compression tools"
DESCRIPTION:${PN}-compression = "Archive and compression utilities required by the Mantle OS build environment"
RDEPENDS:${PN}-compression = " \
    cpio \
    tar \
    bzip2 \
    zstd \
    pigz \
    lz4 \
    unzip \
    xz \
    zstd \
"

SUMMARY:${PN}-graphics = "Mantle self-hosted graphics tools"
DESCRIPTION:${PN}-graphics = "Graphics, terminal, and display-related libraries and utilities"
RDEPENDS:${PN}-graphics = " \
    mesa \
    ncurses \
    xterm \
"

SUMMARY:${PN}-networking = "Mantle self-hosted networking tools"
DESCRIPTION:${PN}-networking = "Network utilities and source control tools"
RDEPENDS:${PN}-networking = " \
    socat \
    iputils-ping \
    wget \
    curl \
    rsync \
    git \
    git-lfs \
    mercurial \
    iproute2 \
    openssh-keygen openssh-scp openssh-ssh openssh-sftp \
    inetutils-telnet \
    socat \
"

SUMMARY:${PN}-applications = "Mantle self-hosted system utilities"
DESCRIPTION:${PN}-applications = "Administration and general build utilities"
RDEPENDS:${PN}-applications = " \
    gnupg \
    nano \
    tmux \
    vim \
    sudo \
    gosu \
    chrpath \
    less \
    file \
    procps \
    tree \
    texinfo \
    gawk \
    diffstat \
    bash-completion \
    lsb-release \
    aws-cli-v2 \
"


## Make the meta package depend on all the others
RDEPENDS:${PN} = " \
    ${PN}-toolchain \
    ${PN}-python3 \
    ${PN}-compression \
    ${PN}-graphics \
    ${PN}-networking \
    ${PN}-applications \
"
