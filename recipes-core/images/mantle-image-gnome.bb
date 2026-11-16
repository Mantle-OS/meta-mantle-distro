SUMMARY = "${DESCRIPTION}"
DESCRIPTION = "Mantle Os GNOME desktop image."
LICENSE = "MIT"

inherit features_check
require mantle-image-full.bb

REQUIRED_DISTRO_FEATURES = " \
    opengl \
    x11 \
    wayland \
    pam \
    gobject-introspection-data \
"

IMAGE_INSTALL:append = " \
    packagegroup-gnome-apps \
    packagegroup-gnome-desktop \
    gdm \
    xserver-xorg \
    xwayland \
"

export IMAGE_BASENAME = "mantle-image-gnome"
SYSTEMD_DEFAULT_TARGET = "graphical.target"
