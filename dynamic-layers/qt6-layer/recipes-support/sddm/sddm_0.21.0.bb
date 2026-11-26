SUMMARY = "Simple Desktop Display Manager"
DESCRIPTION = "A Qt and QML based display manager providing graphical login, \
session management and configurable greeter themes for X11 and Wayland \
desktop environments."
HOMEPAGE = "https://github.com/sddm/sddm"
BUGTRACKER = "https://github.com/sddm/sddm/issues"
SECTION = "x11/base"

LICENSE = "CC-BY-3.0 AND GPL-2.0-only"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=4641e94ec96f98fabc56ff9cc48be14b \
    file://LICENSE.CC-BY-3.0;md5=36b7342d5619a3a0d3b581e89803ec25 \
"

inherit qt6-cmake cmake_extra_sanity pkgconfig systemd useradd

SRC_URI = " git://github.com/sddm/sddm;branch=develop;protocol=https"
SRCREV = "633c1bf5c08d6c8e5b8435a70edb281b9f7b1550"


inherit features_check
ANY_OF_DISTRO_FEATURES = " wayland x11"

DEPENDS:append = " \
    libpam \
    libxau \
    libxcb \
    libxkbcommon \
"

PACKAGECONFIG[man] = "-DBUILD_MAN_PAGES=ON, -DBUILD_MAN_PAGES=OFF, git-native "
PACKAGECONFIG[journald] = "-DENABLE_JOURNALD=ON, -DENABLE_JOURNALD, systemd"

PACKAGECONFIG[systemd] = "-DNO_SYSTEMD=OFF \
                           -DSYSTEMD_SYSTEM_UNIT_DIR=${systemd_system_unitdir} \
                           -DSYSTEMD_SYSUSERS_DIR=${nonarch_base_libdir}/sysusers.d \
                           -DSYSTEMD_TMPFILES_DIR=${nonarch_base_libdir}/tmpfiles.d, \
                          -DNO_SYSTEMD=ON, \
    systemd \
"




# PACKAGECONFIG[elogin] = "-DUSE_ELOGIND=ON, -DUSE_ELOGIND=OFF, "  needs elogind

PACKAGECONFIG[qt6] = "-DBUILD_WITH_QT6=ON \
                        -DQML_INSTALL_DIR=${QT6_INSTALL_QMLDIR}, \
                      -DBUILD_WITH_QT6=OFF, \
    qtbase6 qtdeclarative6 qttools6 qttools6-native \
"

PACKAGECONFIG[install-pam-config] = "-DINSTALL_PAM_CONFIGURATION=ON, -DINSTALL_PAM_CONFIGURATION=OFF, "

# Extract the virtual terminal number from the machine console configuration.
SDDM_INITIAL_VT ?= "${@next((tty[3:] \
    for tty in (entry.split(';')[-1] \
      for entry in (d.getVar('SERIAL_CONSOLES') or '').split()) \
        if tty.startswith('tty') and tty[3:].isdigit()), '')}"

SDDM_DEFAULT_DISPLAY_SERVER ?= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', 'x11', d)}"

PACKAGECONFIG:append = " \
    journald \
    systemd \
    install-pam-config \
    qt6 \
"

EXTRA_OECMAKE:append = " \
    -DSDDM_INITIAL_VT=${SDDM_INITIAL_VT} \
    -DSDDM_DEFAULT_DISPLAY_SERVER=${SDDM_DEFAULT_DISPLAY_SERVER} \
    -DDATA_INSTALL_DIR=${datadir}/sddm \
    -DDBUS_CONFIG_DIR=${datadir}/dbus-1/system.d \
    -DSTATE_DIR=${localstatedir}/lib/sddm \
    -DRUNTIME_DIR=/run/sddm \
    \
    -DSESSION_COMMAND=${datadir}/sddm/scripts/Xsession \
    -DWAYLAND_SESSION_COMMAND=${datadir}/sddm/scripts/wayland-session \
    \
    -DCONFIG_FILE=${sysconfdir}/sddm.conf \
    -DCONFIG_DIR=${sysconfdir}/sddm.conf.d \
    -DACCOUNTSSERVICE_DATA_DIR=${localstatedir}/lib/AccountsService \
    -DSYSTEM_CONFIG_DIR=${nonarch_libdir}/sddm/sddm.conf.d \
    -DLOG_FILE=${localstatedir}/log/sddm.log \
    -DDBUS_CONFIG_FILENAME=org.freedesktop.DisplayManager.conf \
    -DCOMPONENTS_TRANSLATION_DIR=${datadir}/sddm/translations-qt6 \
    \
    -DLOGIN_DEFS_PATH=${sysconfdir}/login.defs \
    -DCMAKE_POSITION_INDEPENDENT_CODE=ON \
"

do_configure:append() {
    # fix sysroot path
    sed -i 's:${STAGING_DIR_HOST}.*${libdir}:${libdir}:g' ${B}/src/common/Constants.h
}


do_install:append() {
    install -d ${D}${localstatedir}/lib/sddm
    chown -R sddm:sddm ${D}${localstatedir}/lib/sddm
    chmod 0750 ${D}${localstatedir}/lib/sddm
}

SYSTEMD_SERVICE:${PN} = "sddm.service"
USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "--system --home ${localstatedir}/lib/sddm --shell /bin/false --user-group --groups video sddm"

RDEPENDS:${PN} += "\
    bash \
    xcb-util \
    qtbase6-plugins \
    qtdeclarative6-plugins \
    qtdeclarative6-qmlplugins \
"

RRECOMMENDS:${PN} += " \
    qtvirtualkeyboard6-plugins \
    qtvirtualkeyboard6-qmlplugins \
"

FILES:${PN} += " \
    ${QT6_INSTALL_QMLDIR}/SddmComponents \
    ${nonarch_base_libdir}/sysusers.d/sddm.conf \
    ${nonarch_base_libdir}/tmpfiles.d/sddm.conf \
    ${datadir}/dbus-1/system.d/org.freedesktop.DisplayManager.conf \
    ${datadir}/sddm/flags \
    ${datadir}/sddm/faces \
    ${datadir}/sddm/scripts \
    ${datadir}/sddm/translations-qt6 \
    ${localstatedir}/lib/sddm \
"

PACKAGES =+ " \
  ${PN}-maya-theme \
  ${PN}-maldives-theme \
  ${PN}-elarun-theme \
"

FILES:${PN}-maya-theme = " \
    ${datadir}/sddm/themes/maya \
"
RDEPENDS:${PN}-maya-theme:append = " ${PN}"

FILES:${PN}-maldives-theme = " \
    ${datadir}/sddm/themes/maldives \
"
RDEPENDS:${PN}-maldives-theme:append = " ${PN}"


FILES:${PN}-elarun-theme = " \
    ${datadir}/sddm/themes/elarun \
"
RDEPENDS:${PN}-elarun-theme = " ${PN}"
