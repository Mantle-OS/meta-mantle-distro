FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI += " \
    file://0001-fix-gi_dir-host-bleeding.patch \
"

EXTRA_OEMAKE:class-target += " \
  VAPIGEN=${STAGING_BINDIR_NATIVE}/vapigen \
  VAPIGEN_GIRDIRS=${STAGING_DIR_HOST}/usr/share/gir-1.0 \
"
