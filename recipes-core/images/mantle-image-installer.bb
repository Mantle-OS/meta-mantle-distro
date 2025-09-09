DESCRIPTION = "Offline installer for Mantle Os"
LICENSE = "MIT"
PR = "r0"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
inherit core-image

LABELS_LIVE = "install"
LIVE_ROOTFS_TYPE = "wic"
ROOT_LIVE = "root=/dev/ram0 console=ttyS0,115200n8 console=tty0,115200n8 init=/init"

SYSLINUX_DEFAULT_CONSOLE = ""
SYSLINUX_SERIAL = ""
SYSLINUX_SERIAL_TTY = ""
INITRD_IMAGE_LIVE = "mantle-image-recovery"
inherit image-live

require mantle-image-core.bb

IMAGE_FSTYPES = "wic iso"
INITRAMFS_IMAGE_BUNDLE = "1"
