SUMMARY = "Monitor configuration utility using DDC/CI"
DESCRIPTION = "ddcutil is a Linux utility and shared library for \
querying and changing monitor settings through DDC/CI. It supports \
brightness, contrast, color controls, input selection, and display \
detection using I2C and optionally USB. DRM and udev integration \
provide display discovery and hotplug monitoring without requiring X11."

HOMEPAGE = "https://www.ddcutil.com/"
SECTION = "utils"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PV = "3.0.2"

inherit autotools pkgconfig features_check
ANY_OF_DISTRO_FEATURES = "x11 systemd"
SRC_URI = " \
    git://github.com/rockowitz/ddcutil.git;protocol=https;branch=master \
"

SRCREV = "5085ae9bb1a8e5bd8db717d80dd80a279a0f05fb"

DEPENDS:append = " \
    i2c-tools \
    glib-2.0 \
    kmod \
    jansson \
    acl \
    libdrm \
"

CFLAGS += "-Wno-unused-but-set-variable"

# DRM is required by ddcutil 3.0.2.
# Disable timestamps for reproducible builds.
EXTRA_OECONF:append = " \
    --enable-drm=yes \
    --enable-build-timestamp=no \
"

PACKAGECONFIG ??= " \
    lib \
    udev \
    usb \
    dbus \
    watch-displays \
    envcmds \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)} \
"

# Core functionality
PACKAGECONFIG[lib] = "--enable-lib=yes,--enable-lib=no,zlib"
PACKAGECONFIG[envcmds] = "--enable-envcmds=yes,--enable-envcmds=no"
PACKAGECONFIG[watch-displays] = "--enable-watch-displays=yes,--enable-watch-displays=no"

# Device integration
PACKAGECONFIG[udev] = "--enable-udev=yes,--enable-udev=no,udev"
PACKAGECONFIG[usb] = "--enable-usb=yes,--enable-usb=no,libusb1"

# Desktop and IPC integration
PACKAGECONFIG[systemd] = "--enable-systemd=yes,--enable-systemd=no,systemd"
PACKAGECONFIG[dbus] = "--enable-dbus=yes,--enable-dbus=no,dbus"

# Explicit opt-in; disabled by default.
PACKAGECONFIG[x11] = "--enable-x11=yes,--enable-x11=no,virtual/libx11 libxrandr libxext"

do_install:append() {
    install -d ${D}${sysconfdir}/udev/rules.d
    cp -rf ${D}${datadir}/ddcutil/data/* ${D}${sysconfdir}/udev/rules.d
}

FILES:${PN} += " \
    ${datadir}/ddcutil \
    ${nonarch_libdir}/modules-load.d/ddcutil.conf \
"
