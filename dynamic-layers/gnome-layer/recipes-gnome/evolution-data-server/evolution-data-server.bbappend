FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI += " \
    file://0005-dont-bleed-into-host-for-vala-girdir.patch \
"

DEPENDS += " vala-native vala "
