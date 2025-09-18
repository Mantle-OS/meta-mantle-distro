SUMMARY = "A DBus packaging abstraction layer"
HOMEPAGE = "https://www.freedesktop.org/software/PackageKit/"
SECTION = "admin"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit meson pkgconfig gettext vala systemd
inherit features_check
REQUIRED_DISTRO_FEATURES = "gobject-introspection-data"

SRC_URI = "git://github.com/PackageKit/PackageKit.git;branch=main;protocol=https"
SRCREV = "0f33069752fe06f6daf3d977cd09665b059494d8"
S = "${WORKDIR}/git"

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS:append = " \
    glib-2.0 glib-2.0-native \
    gobject-introspection gobject-introspection-native \
    appstream \
    libxslt libxslt-native docbook-xml-dtd4 \
    polkit \
    sqlite3 \
"

PACKAGECONFIG[gstreamer] = "-Dgstreamer_plugin=true, -Dgstreamer_plugin=false, gstreamer1.0"
PACKAGECONFIG[apt] = "-Dpackaging_backend=apt, , apt dpkg"
PACKAGECONFIG[rpm] = "-Dpackaging_backend=dnf, , dnf rpm"
PACKAGECONFIG[dummy] = "-Dpackaging_backend=dummy, , "
PACKAGECONFIG[systemd] = "-Dsystemd=true, -Dsystemd=false, systemd"
PACKAGECONFIG[offline] = "-Doffline_update=true, -Doffline_update=false, "
# PACKAGECONFIG[elogind] = "-Delogind=true, -Delogind=false, "
PACKAGECONFIG[manpages] = "-Dman_pages=true, -Dman_pages=false, "
PACKAGECONFIG[gtk-doc] = "-Dgtk_doc=true, -Dgtk_doc=false, "
PACKAGECONFIG[bash-completion] = "-Dbash_completion=true, -Dbash_completion=false, , bash-completion, , "
PACKAGECONFIG[local-checkout] = "-Dlocal_checkout=true, -Dlocal_checkout=false, "
PACKAGECONFIG[gtk] = "-Dgtk_module=true, -Dgtk_module=false, \
 gtk+3 \
 fontconfig \
 pango \
 cairo \
"
PACKAGECONFIG[cron] = "-Dcron=true, -Dcron=false, , cronie, , "
PACKAGECONFIG[python-backend] = "-Dpython_backend=true, -Dpython_backend=false, python3-native python-pygobject-native"

PACKAGECONFIG ?= " \
    dummy \
    ${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)} \
"

EXTRA_OEMESON:append = " \
    -Delogind=false \
"

SYSTEMD_SERVICE:${PN} = "${BPN}.service"
SYSTEMD_AUTO_ENABLE = "disable"

FILES:${PN} += " \
    ${libdir}/girepository-1.0 \
    ${datadir} \
    ${libdir}/packagekit-backend \
"
