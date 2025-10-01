SUMMARY = "Manage multi-GPU's over dbus"
HOMEPAGE = "https://gitlab.freedesktop.org/hadess/switcheroo-control"
SECTION = "gnome"
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

inherit meson pkgconfig features_check
REQUIRED_DISTRO_FEATURES = "systemd opengl gobject-introspection-data"

SRC_URI = "git://gitlab.freedesktop.org/hadess/switcheroo-control.git;branch=master;protocol=https"
SRCREV = "2adfa2dff85b9886ce0fddca6863fe9f2eaf54a0"
S = "${WORKDIR}/git"

DEPENDS = " \
    dbus \
    libdrm \
    libgudev \
    glib-2.0 \
    gobject-introspection \
"

RDEPENDS:${PN} += " \
    systemd \
    dbus \
    python3-core \
    python3-pygobject \
"

FILES:${PN} += " \
  ${systemd_system_unitdir} \
  ${datadir}/dbus-1 \
"

do_install:append() {
    if [ -f ${D}${bindir}/switcherooctl ]; then
        sed -i -e '1s|^#!.*|#!/usr/bin/env python3|' ${D}${bindir}/switcherooctl
        sed -i -e 's|${RECIPE_SYSROOT_NATIVE}[^"'\'' ]*||g' ${D}${bindir}/switcherooctl
    fi
}

#BBCLASSEXTEND = "native nativesdk"
