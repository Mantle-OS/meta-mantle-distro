SUMMARY = "Mantle JOB"
DESCRIPTION = "Package groups for job"
LICENSE = "MIT"
PR = "r0"

PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup

ALLOW_EMPTY:${PN} = "1"

## Make the meta package depend on all the others
RDEPENDS:${PN} = " \
    openssl \
    sodium \
    opus \
    flac \
    pipewire \
    ogg \
    vorbis vorbisenc vorbisfile \
    wavpack \
    zstd zstd-dev \
    zlib zlib-dev \
    libudev libudev-dev \
    yaml-cpp yaml-cpp-dev nlohmann-json \
    libusb-1.0 libusb-1.0-dev \
    libalsa libalsa-dev \
    libinput libinput-dev \
    libevdev libevdev-dev \
    ${VIRTUAL-RUNTIME_gl} \
    ${VIRTUAL-RUNTIME_gles} \
    ${VIRTUAL-RUNTIME_egl} \
    ${VIRTUAL-RUNTIME_gbm} \
    freetype \
    harfbuzz \
    libdrm \
"
