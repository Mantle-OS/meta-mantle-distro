FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
    file://0001-add-missing-cstdint-header.patch;patchdir=src/3rdparty/chromium \
    file://0001-perfetto-add-missing-cstdint.patch;patchdir=src/3rdparty/chromium \
"
PACKAGECONFIG:append = " proprietary-codecs"
