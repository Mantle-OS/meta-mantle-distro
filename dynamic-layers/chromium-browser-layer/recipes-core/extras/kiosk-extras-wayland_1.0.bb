SUMMARY = "Extra files for kiosk-image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302 \
"

SRC_URI = " \
    file://weston.ini \
    file://Loading.jpg \
"

DEPENDS = "weston-init chromium-kiosk"
RDEPENDS:${PN} = "weston-init chromium-kiosk"

do_compile[noexec] = "1"

do_install(){
    install -d ${D}/home/kiosk/.config
    install -m 0644 ${WORKDIR}/weston.ini ${D}/home/kiosk/.config/
    install -m 0644 ${WORKDIR}/Loading.jpg ${D}/home/kiosk/
    chown -R kiosk:kiosk ${D}/home/kiosk
}

FILES:${PN}:append = " \
    /home/kiosk/.config/weston.ini \
    /home/kiosk/Loading.jpg \
"

inherit useradd

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "--system --create-home \
    --shell /bin/sh \
    -U -G video,input,wayland \
    kiosk"