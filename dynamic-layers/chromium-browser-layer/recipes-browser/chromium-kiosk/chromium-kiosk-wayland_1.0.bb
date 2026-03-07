SUMMARY = "Systemd files and default files for chromium kiosk"
LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302 \
"
inherit features_check
REQUIRED_DISTRO_FEATURES = "wayland"

SRC_URI = " \
    file://chromium-kiosk \
    file://chromium-kiosk.service \
"

RDEPENDS:${PN} = "chromium-ozone-wayland"

inherit systemd
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

SYSTEMD_SERVICE:${PN} = "\
    chromium-kiosk.service \
"

do_compile[noexec] = "1"

do_install(){
    install -d ${D}/${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/chromium-kiosk.service ${D}${systemd_system_unitdir}/

    install -d ${D}${sysconfdir}/default
    install -m 0644 ${WORKDIR}/chromium-kiosk ${D}${sysconfdir}/default/
}
