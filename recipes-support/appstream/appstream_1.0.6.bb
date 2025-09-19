SUMMARY = "AppStream is a collaborative effort for making machine-readable software metadata easily available."
HOMEPAGE = "https://github.com/ximion/appstream"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=435ed639f84d4585d93824e7da3d85da"

DEPENDS = " \
    appstream-native \
    curl-native \
    curl \
    gperf-native \
    glib-2.0 \
    libyaml \
    libxml2 \
    libxmlb \
    libxslt-native \
    itstool-native \
    docbook-xml-dtd4-native \
    docbook-xsl-stylesheets-native \
    python3-pygments-native \
"

inherit meson gobject-introspection gettext gi-docgen pkgconfig vala

GIR_MESON_OPTION = "gir"
GIDOCGEN_MESON_OPTION = "apidocs"

SRC_URI = " \
    https://www.freedesktop.org/software/appstream/releases/AppStream-${PV}.tar.xz \
    file://0001-remove-hardcoded-path.patch \
    file://0002-Do-not-build-qt-tests.patch \
    file://0003-cmake-appstream-qt-part.patch \
"

SRC_URI[sha256sum] = "db4439db6a33de3ca1041473501610844ddf1b72ae23016c05242c681c380b4d"
S = "${WORKDIR}/AppStream-${PV}"

PACKAGECONFIG:append = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)} \
    svg \
    zstd \
"

PACKAGECONFIG[systemd] = "-Dsystemd=true,-Dsystemd=false, systemd"
PACKAGECONFIG[stemming] = "-Dstemming=true,-Dstemming=false, libstemmer"
#PACKAGECONFIG[qt5] = "-Dqt=true -Dqt-versions=5, -Dqt=false, qtbase qtbase-native qttools-native"
PACKAGECONFIG[qt6] = "-Dqt=true -Dqt-versions=6, -Dqt=false, qtbase6 qtbase6-native qttools6-native"
PACKAGECONFIG[svg] = "-Dsvg-support=true, -Dsvg-support=false, librsvg"
PACKAGECONFIG[zstd] = "-Dzstd-support=true, -Dzstd-support=false, zstd"
PACKAGECONFIG[compose] = "-Dcompose=true, -Dcompose=false, composefs"
PACKAGECONFIG[apt] = "-Dapt-support=true, -Dapt-support=false, apt dpkg"

FILES:${PN} += "${datadir}"

EXTRA_OEMESON += "${@bb.utils.contains('GI_DATA_ENABLED', 'True', '-Dvapi=true', '-Dvapi=false', d)}"

BBCLASSEXTEND = "native"

