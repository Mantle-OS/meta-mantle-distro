# this will go away after ansipp get the rest of the gui framework.
SUMMARY = "initramfs-framework module for EFI installation option"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
RDEPENDS:${PN} = "\
    parted \
    e2fsprogs-mke2fs \
    dosfstools \
    util-linux-blkid \
    ${VIRTUAL-RUNTIME_base-utils} \
"

RRECOMMENDS:${PN} = "${VIRTUAL-RUNTIME_base-utils-syslog}"

PR = "r4"

inherit allarch

SRC_URI = "\
    file://init-install-efi-wic.sh \
    file://init \
"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/bin
    install -m 0755 ${WORKDIR}/init-install-efi-wic.sh ${D}/bin/install-efi-wic
    install -m 0755 ${WORKDIR}/init ${D}/init

    install -d ${D}/dev
    mknod -m 622 ${D}/dev/console c 5 1
}

FILES:${PN} = "\
    /bin/install-efi-wic \
    /init \
    /dev/console \
"
