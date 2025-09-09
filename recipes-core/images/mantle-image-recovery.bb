DESCRIPTION = "Mantle Os recovery initramfs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r0"

VIRTUAL-RUNTIME_dev_manager = "busybox-mdev"
VIRTUAL-RUNTIME_init_manager = "busybox"

IMAGE_FEATURES = ""
IMAGE_LINGUAS = ""
FEATURE_INSTALL_OPTIONAL = ""
IMAGE_INSTALL = ""
export IMAGE_BASENAME = "mantle-image-recovery"
IMAGE_FSTYPES = "cpio.gz"

inherit image
usb_post() {
    echo "mantle_recovery" > ${IMAGE_ROOTFS}/${sysconfdir}/hostname
}
ROOTFS_POSTPROCESS_COMMAND := "usb_post;"

PACKAGE_INSTALL = "\
    base-passwd \
    busybox busybox-udhcpc \
    udev \
    initramfs-module-install-efi-wic \
    ldconfig \
    shadow shadow-base shadow-securetty \
    update-rc.d \
    udev-extraconf \
    util-linux-sulogin \
    zlib \
    parted \
    e2fsprogs-mke2fs \
    dosfstools \
    util-linux-blkid \
    ${VIRTUAL-RUNTIME_base-utils} \
"
