SUMMARY = "${DESCRIPTION}"
DESCRIPTION = "Mantle Os Plasma Mobile Testing Image"
LICENSE = "MIT"

require mantle-image-full.bb

inherit features_check
REQUIRED_DISTRO_FEATURES = " opengl x11 wayland pam"

IMAGE_INSTALL:append = " \
    plymouth \
    wayland \
    dbus \
    packagegroup-core-x11  xserver-xorg xwayland \
    sddm-qt6 sddm-qt6-config-plasma-mobile6 plasma-desktop6-sddm-theme \
    packagegroup-plasma-mobile6 \
    ttf-roboto ttf-noto-emoji-regular \
"

export IMAGE_BASENAME = "mantle-image-kde6"
SYSTEMD_DEFAULT_TARGET = "graphical.target"
