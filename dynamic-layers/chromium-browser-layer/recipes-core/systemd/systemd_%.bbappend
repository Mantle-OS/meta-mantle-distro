FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append = " \
    file://wait-for-any-online.service \
"
do_install:append() {
    rm ${D}${rootlibexecdir}/systemd/system/systemd-networkd-wait-online.service
    install -m 0644 ${WORKDIR}/wait-for-any-online.service ${D}${rootlibexecdir}/systemd/system/systemd-networkd-wait-online.service
}
