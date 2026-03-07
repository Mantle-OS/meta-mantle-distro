SUMMARY = "Extra files for kiosk-image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302 \
"

SRC_URI = " \
    file://Loading.jpg \
"

DEPENDS = "chromium-kiosk-x11"
RDEPENDS:${PN} = "chromium-kiosk-x11"

do_compile[noexec] = "1"

do_install(){
    install -d ${D}/home/kiosk/
    install -m 0644 ${WORKDIR}/Loading.jpg ${D}/home/kiosk/
    chown -R kiosk:kiosk ${D}/home/kiosk
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