FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
    file://0001-spa-dynamic-h-fix-Cxx-designated-init.patch \
"

PACKAGECONFIG:append = " ffmpeg"
