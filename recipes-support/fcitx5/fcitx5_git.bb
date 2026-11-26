SUMMARY = "Fcitx5"
DESCRIPTION = "Fcitx5 is a generic input method framework supporting multiple languages, input methods, and graphical environments."
HOMEPAGE = "https://github.com/fcitx/fcitx5"
SECTION = "inputmethods"

LICENSE = "LGPL-2.1-or-later AND Unicode-DFS-2016"
LIC_FILES_CHKSUM = " \
    file://LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68 \
    file://LICENSES/Unicode-DFS-2016.txt;md5=daf288399a5d03ddccd02a70c48cfbce \
"

require fcitx5.inc

SRC_URI:append = " \
    ${FCITX5_SRC_URI} \
    file://0001-cmake-fix-cross-compilation-and-misc.patch \
"
SRCREV = "${FCITX5_SRCREV}"
PV = "${FCITX5_PV}"

PACKAGECONFIG[testing-addons] = " -DENABLE_TESTING_ADDONS=ON, -DENABLE_TESTING_ADDONS=OFF,"
PACKAGECONFIG[enchant] = "-DENABLE_ENCHANT=ON, -DENABLE_ENCHANT=OFF, enchant2"
PACKAGECONFIG[x11] = " -DENABLE_X11=ON, -DENABLE_X11=OFF, libx11 libxcb cairo libxkbcommon libxfixes libxinerama libxrandr xcb-util-wm libxkbfile xcb-util-keysyms xcb-util xcb-util-image xcb-imdkit"
PACKAGECONFIG[wayland] = "-DENABLE_WAYLAND=ON, -DENABLE_WAYLAND=OFF, wayland wayland-native wayland-protocols "
## TODO Move this over to its own bbappend ones kde starts to stablilize on master
PACKAGECONFIG[plasma-wayland-protocols] = " -DUSE_SYSTEM_PLASMA_WAYLAND_PROTOCOLS=ON, -DUSE_SYSTEM_PLASMA_WAYLAND_PROTOCOLS=OFF, plasma-wayland-protocols-kf6"
PACKAGECONFIG[dbus] = " -DENABLE_DBUS=ON , -DENABLE_DBUS=OFF, dbus"
PACKAGECONFIG[server] = " -DENABLE_SERVER=ON, -DENABLE_SERVER=OFF, "
PACKAGECONFIG[keyboard] = " -DENABLE_KEYBOARD=ON, -DENABLE_KEYBOARD=OFF, expat libxkbcommon iso-codes xkeyboard-config nlohmann-json"
PACKAGECONFIG[systemd] = " -DUSE_SYSTEMD=ON, -DUSE_SYSTEMD=OFF, systemd libuv dbus"
PACKAGECONFIG[xdg-autostart] = " -DENABLE_XDGAUTOSTART=ON, -DENABLE_XDGAUTOSTART=OFF,"
PACKAGECONFIG[flatpak-icon] = " -DUSE_FLATPAK_ICON=ON, -DUSE_FLATPAK_ICON=OFF,"
PACKAGECONFIG[emoji] = " -DENABLE_EMOJI=ON, -DENABLE_EMOJI=OFF, zlib"
PACKAGECONFIG[uuid] = " -DENABLE_LIBUUID=ON, -DENABLE_LIBUUID=OFF, util-linux-libuuid "
PACKAGECONFIG[spell-dict] = " -DBUILD_SPELL_DICT=ON, -DBUILD_SPELL_DICT=OFF, "
PACKAGECONFIG[system-yoga] = "-DUSE_SYSTEM_YOGA=ON, -DUSE_SYSTEM_YOGA=OFF, yoga"

PACKAGECONFIG[auto-backend] = "-DEVENT_LOOP_BACKEND=auto, ,"
PACKAGECONFIG[systemd-backend] = "-DEVENT_LOOP_BACKEND=systemd, ,"
PACKAGECONFIG[uv-backend] = "-DEVENT_LOOP_BACKEND=libuv, ,"
PACKAGECONFIG[no-backend] = "-DEVENT_LOOP_BACKEND=none, ,"

PACKAGECONFIG ?= " \
    testing-addons \
    test \
    enchant \
    dbus \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland plasma-wayland-protocols', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'keyboard', 'keyboard', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd systemd-backend', 'auto-backend', d)} \
    server \
    xdg-autostart \
    emoji \
    uuid \
    spell-dict \
    shared \
    system-yoga \
"

PACKAGECONFIG:class-native = " \
    spell-dict \
    shared \
    no-backend \
"

EXTRA_OECMAKE:append:class-target = " \
    -DISOCODES_ISO639_JSON:FILEPATH=${datadir}/iso-codes/json/iso_639-3.json \
    -DFCITX5_COMP_SPELL_DICT_EXECUTABLE=${RECIPE_SYSROOT_NATIVE}${libdir}/fcitx5/libexec/comp-spell-dict \
"

EXTRA_OECMAKE:append:class-native = " \
    -DUSE_SYSTEM_PLASMA_WAYLAND_PROTOCOLS=OFF \
    -DUSE_SYSTEM_YOGA=OFF \
    -DDL_INCLUDE_DIR=${STAGING_DIR_NATIVE}/usr/include \
    -DLibIntl_INCLUDE_DIR=${STAGING_DIR_NATIVE}/usr/include \
    -DPTHREAD_INCLUDE_DIR=${STAGING_INCDIR_NATIVE} \
"

DEPENDS:append = " \
    libxkbcommon \
    cairo \
    pango \
    gdk-pixbuf \
    librsvg \
    glib-2.0 \
    fmt \
"

DEPENDS:append:class-target = " ${PN}-native"

PACKAGES:append = " \
    ${PN}-tests \
    ${PN}-apps \
    ${PN}-wayland \
    ${PN}-x11 \
    ${PN}-dbus \
    ${PN}-classicui \
    ${PN}-keyboard \
    ${PN}-emoji \
    ${PN}-spell \
"

SUMMARY:${PN}-tests = "${PN} testing addons"
DESCRIPTION:${PN}-tests = "Provides testing input methods, frontends, and user interface addons for validating Fcitx5 functionality."
FILES:${PN}-tests = " \
    ${libdir}/fcitx5/libtestfrontend.so \
    ${libdir}/fcitx5/libtestim.so \
    ${libdir}/fcitx5/libtestui.so \
    ${datadir}/fcitx5/testing \
"

SUMMARY:${PN}-apps = "${PN} applications and utilities"
DESCRIPTION:${PN}-apps = "Provides Fcitx5 configuration, diagnostic, and remote control utilities, along with desktop integration resources."
FILES:${PN}-apps = " \
    ${bindir}/fcitx5-configtool \
    ${bindir}/fcitx5-diagnose \
    ${bindir}/fcitx5-remote \
    ${datadir}/applications/fcitx5-configtool.desktop \
    ${datadir}/applications/org.fcitx.Fcitx5.desktop \
    ${datadir}/metainfo/org.fcitx.Fcitx5.metainfo.xml \
    ${datadir}/icons/hicolor/*/apps/fcitx.* \
    ${datadir}/icons/hicolor/*/apps/org.fcitx.Fcitx5.* \
"

SUMMARY:${PN}-wayland = "${PN} Wayland integration"
DESCRIPTION:${PN}-wayland = "Provides Wayland input method integration, including the Wayland frontend and session launcher for Fcitx5."
FILES:${PN}-wayland = " \
    ${libdir}/fcitx5/libwayland.so \
    ${libdir}/fcitx5/libwaylandim.so \
    ${datadir}/fcitx5/addon/wayland.conf \
    ${datadir}/fcitx5/addon/waylandim.conf \
    ${libexecdir}/fcitx5-wayland-launcher \
    ${datadir}/applications/fcitx5-wayland-launcher.desktop \
"

SUMMARY:${PN}-dbus = "${PN} DBus integration"
DESCRIPTION:${PN}-dbus = "Provides D-Bus communication and frontend integration for Fcitx5, including service activation support."
FILES:${PN}-dbus = " \
    ${libdir}/fcitx5/libdbus.so \
    ${libdir}/fcitx5/libdbusfrontend.so \
    ${datadir}/fcitx5/addon/dbus.conf \
    ${datadir}/fcitx5/addon/dbusfrontend.conf \
    ${datadir}/dbus-1/services/org.fcitx.Fcitx5.service \
"

SUMMARY:${PN}-classicui = "${PN} classic user interface"
DESCRIPTION:${PN}-classicui = "Provides the classic Fcitx5 user interface, including input method windows, candidate displays, and theme resources."
FILES:${PN}-classicui = " \
    ${libdir}/fcitx5/libclassicui.so \
    ${datadir}/fcitx5/addon/classicui.conf \
    ${datadir}/fcitx5/themes \
"

SUMMARY:${PN}-keyboard = "${PN} keyboard input method"
DESCRIPTION:${PN}-keyboard = "Provides keyboard input method configuration and default input method settings for Fcitx5."
FILES:${PN}-keyboard = " \
    ${datadir}/fcitx5/addon/keyboard.conf \
    ${datadir}/fcitx5/default \
"

SUMMARY:${PN}-emoji = "${PN} emoji support"
DESCRIPTION:${PN}-emoji = "Provides emoji lookup and selection functionality for Fcitx5 input methods."
FILES:${PN}-emoji = " \
    ${libdir}/fcitx5/libemoji.so \
    ${datadir}/fcitx5/addon/emoji.conf \
    ${datadir}/fcitx5/emoji \
"

SUMMARY:${PN}-spell = "${PN} spell checking and dictionaries"
DESCRIPTION:${PN}-spell = "Provides spell checking functionality, dictionaries, and dictionary compilation utilities for Fcitx5."
FILES:${PN}-spell = " \
    ${libdir}/fcitx5/libspell.so \
    ${libdir}/fcitx5/libexec/comp-spell-dict \
    ${datadir}/fcitx5/addon/spell.conf \
    ${datadir}/fcitx5/spell \
"

SUMMARY:${PN}-x11 = "${PN} X11 integration"
DESCRIPTION:${PN}-x11 = "Provides X11 input method integration for Fcitx5, including XCB and XIM support."
FILES:${PN}-x11 = " \
    ${libdir}/fcitx5/libxcb.so \
    ${libdir}/fcitx5/libxim.so \
    ${datadir}/fcitx5/addon/xcb.conf \
    ${datadir}/fcitx5/addon/xim.conf \
    ${sysconfdir}/xdg/Xwayland-session.d/20-fcitx-x11 \
"
