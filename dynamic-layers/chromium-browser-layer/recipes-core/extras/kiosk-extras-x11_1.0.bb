SUMMARY = "Extra files for kiosk-image"
LICENSE = "MIT"

SRC_URI = " \
    file://wait-for-any-online.service \
    file://Loading.jpg \
"

DEPENDS = "chromium-kiosk-x11"
RDEPENDS:${PN} = "chromium-kiosk-x11"

do_compile[noexec] = "1"

do_install(){
    install -d ${D}/home/kiosk/
    install -m 0644 ${WORKDIR}/Loading.jpg ${D}/home/kiosk/
    chown -R kiosk:kiosk ${D}/home/kiosk

    install -d "${D}${systemd_system_unitdir}/"

    if [[ -f "${D}${systemd_system_unitdir}/systemd-networkd-wait-online.service" ]]; then
        rm "${D}${systemd_system_unitdir}/systemd-networkd-wait-online.service"
    fi

    install -m 0644 "${UNPACKDIR}/wait-for-any-online.service" "${D}${systemd_system_unitdir}/systemd-networkd-wait-online.service"
}

FILES:${PN}:append = " \
    /home/kiosk/Loading.jpg \
"

inherit useradd
USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "--system --create-home \
    --shell /bin/sh \
    -U -G video,input \
    kiosk"
