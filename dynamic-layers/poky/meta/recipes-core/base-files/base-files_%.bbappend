FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PR:append = ".mantle1"

RRECOMMENDS:${PN}:append = " nano"
