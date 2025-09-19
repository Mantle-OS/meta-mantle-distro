FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI += " \
    file://0001-fix-gi_dir-host-bleeding.patch \
"

EXTRA_OEMAKE:class-target += " VAPIGEN_GIRDIRS=${STAGING_DIR_HOST}/usr/share/gir-1.0 "
