SUMMARY = "Mantle OS for GNOME"
DESCRIPTION = "meta pacakge for The GNOME Desktop"
LICENSE = "MIT"
PR = "r0"

PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup features_check

REQUIRED_DISTRO_FEATURES = " \
    x11 \
    wayland \
    opengl \
    polkit \
    pam \
    systemd \
    gobject-introspection-data \
"

VIRTUAL-RUNTIME_de = "gnome-shell"
VIRTUAL-RUNTIME_dm = "gdm"
VIRTUAL-RUNTIME_wm = "mutter"
VIRTUAL-RUNTIME_network_manager = "networkmanager network-manager-applet networkmanager-nmcli"
VIRTUAL-RUNTIME_login_manager ??= "shadow-base"
VIRTUAL-RUNTIME_font  ?= "freetype"
VIRTUAL-RUNTIME_input ?= "libinput"

RDEPENDS:${PN} = " \
    packagegroup-gnome-desktop \
    packagegroup-gnome-apps \
    clutter-1.0 \
    clutter-gst-3.0 \
    clutter-gtk-1.0 \
    ${VIRTUAL-RUNTIME_dm} \
    ${VIRTUAL-RUNTIME_de} \
    ${VIRTUAL-RUNTIME_wm} \
    packagegroup-mantle \
"
