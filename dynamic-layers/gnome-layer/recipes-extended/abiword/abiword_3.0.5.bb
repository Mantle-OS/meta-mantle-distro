SUMMARY = "AbiWord is free word processing program similar to Microsoft(r) Word"
HOMEPAGE = "http://www.abiword.org"
SECTION = "x11/office"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=ecd3ac329fca77e2d0e412bec38e1c20"
DEPENDS  = " \
    perl-native \
    gtk+ \
    gtkmathview \
    wvware-tools \
    fribidi \
    libjpeg-turbo \
    libpng \
    librsvg \
    libwmf-native \
    asio \
    evolution-data-server \
    libxslt \
    redland rasqal \
"
RDEPENDS:${PN}:append:libc-glibc = " \
    glibc-gconv-ibm850 glibc-gconv-cp1252 \
    glibc-gconv-iso8859-15 glibc-gconv-iso8859-1 \
"
RCONFLICTS:${PN} = "${PN}-embedded"

SRC_URI = " \
    http://www.abisource.com/downloads/${BPN}/${PV}/source/${BP}.tar.gz \
    file://0001-plugins-aiksaurus-Makefile.am-remove-uncomplete-opti.patch \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=c5edcc3ccd864b19004d14e9c1c9a26a"

SRC_URI[sha256sum] = "1257247e9970508d6d1456d3e330cd1909c4b42b25e0f0a1bc32526d6f3a21b4"

#want 3.x from 3.x.y for the installation directory
SHRT_VER = "${@d.getVar('PV').split('.')[0]}.${@d.getVar('PV').split('.')[1]}"

inherit features_check autotools-brokensep pkgconfig mime-xdg

REQUIRED_DISTRO_FEATURES = "x11"

PACKAGECONFIG ??= " \
    collab-backend-xmpp \
    libical \
"
PACKAGECONFIG[libical] = "--with-libical,--without-libical,libical raptor2"
PACKAGECONFIG[spell] = "--enable-spell,--disable-spell,enchant"
PACKAGECONFIG[collab-backend-xmpp] = "--enable-collab-backend-xmpp,--disable-collab-backend-xmpp,libgsf libxml2 loudmouth"
PACKAGECONFIG[collab-backend-tcp] = "--enable-collab-backend-tcp,--disable-collab-backend-tcp,libgsf libxml2"
PACKAGECONFIG[collab-backend-service] = "--enable-collab-backend-service,--disable-collab-backend-service,libgsf libxml2 libsoup-2.4 gnutls"
PACKAGECONFIG[collab-backend-telepathy] = "--enable-collab-backend-telepathy,--disable-collab-backend-telepathy,libgsf libxml2 telepathy-glib telepathy-mission-control"
PACKAGECONFIG[collab-backend-sugar] = "--enable-collab-backend-sugar,--disable-collab-backend-sugar,libgsf libxml2 dbus-glib"

EXTRA_OECONF = " \
    --disable-static  \
    --enable-plugins \
    --enable-clipart \
    --enable-templates \
    --without-gnomevfs \
    --with-gtk2 \
    --with-libwmf-config=${STAGING_DIR} \
"

CXXFLAGS += "-std=c++11"
LDFLAGS += "-lgmodule-2.0"

do_compile() {
    cd goffice-bits2
    make goffice-paths.h
    make libgoffice.la
    cd ${B}
    oe_runmake
}

PACKAGES += " ${PN}-clipart ${PN}-strings ${PN}-systemprofiles ${PN}-templates "

FILES:${PN} += " \
    ${libdir}/lib${PN}-*.so \
    ${datadir}/mime-info \
    ${datadir}/icons/* \
    ${datadir}/appdata \
    ${datadir}/${PN}-${SHRT_VER}/glade \
    ${datadir}/${PN}-${SHRT_VER}/scripts \
    ${datadir}/${PN}-${SHRT_VER}/system.profile-en \
    ${datadir}/${PN}-${SHRT_VER}/system.profile-en_GB \
    ${datadir}/${PN}-${SHRT_VER}/templates/normal.awt \
    ${datadir}/${PN}-${SHRT_VER}/templates/normal.awt-en_GB \
    ${datadir}/${PN}-${SHRT_VER}/templates/Employee-Directory.awt \
    ${datadir}/${PN}-${SHRT_VER}/templates/Business-Report.awt \
    ${datadir}/${PN}-${SHRT_VER}/templates/Fax-Coversheet.awt \
    ${datadir}/${PN}-${SHRT_VER}/templates/Resume.awt \
    ${datadir}/${PN}-${SHRT_VER}/templates/Two-Columns.awt \
    ${datadir}/${PN}-${SHRT_VER}/templates/Memo.awt \
    ${datadir}/${PN}-${SHRT_VER}/templates/Press-Release.awt \
    ${datadir}/${PN}-${SHRT_VER}/certs \
    ${datadir}/${PN}-${SHRT_VER}/ui \
    ${datadir}/${PN}-${SHRT_VER}/xsl* \
    ${datadir}/${PN}-${SHRT_VER}/mime-info \
    ${datadir}/${PN}-${SHRT_VER}/Pr*.xml \
"

# don't steal /usr/lib/libabiword-3.0.so from ${PN}
# in this case it's needed in ${PN}
FILES:${PN}-dev = " \
    ${includedir} \
    ${libdir}/pkgconfig \
    ${libdir}/${PN}*.la \
    ${libdir}/lib${PN}*.la \
    ${libdir}/${PN}-${SHRT_VER}/plugins/*.la \
"
FILES:${PN}-doc += "${datadir}/${PN}-*/readme*"

FILES:${PN}-strings        += "${datadir}/${PN}-${SHRT_VER}/strings"
FILES:${PN}-systemprofiles += "${datadir}/${PN}-${SHRT_VER}/system.profile*"
FILES:${PN}-clipart        += "${datadir}/${PN}-${SHRT_VER}/clipart"
FILES:${PN}-strings        += "${datadir}/${PN}-${SHRT_VER}/AbiWord/strings"
FILES:${PN}-systemprofiles += "${datadir}/${PN}-${SHRT_VER}/AbiWord/system.profile*"
FILES:${PN}-templates      += "${datadir}/${PN}-${SHRT_VER}/templates"

PACKAGES_DYNAMIC += "^${PN}-meta.* ^${PN}-plugin-.*"

python populate_packages:prepend () {
    abiword_libdir    = d.expand('${libdir}/${PN}-${SHRT_VER}/plugins')
    do_split_packages(d, abiword_libdir, '(.*)\.so$', 'abiword-plugin-%s', 'Abiword plugin for %s', extra_depends='')

    metapkg = "abiword-meta"
    d.setVar('ALLOW_EMPTY:' + metapkg, "1")
    d.setVar('FILES:' + metapkg, "")
    blacklist = [ 'abiword-plugins-dbg', 'abiword-plugins', 'abiword-plugins-doc', 'abiword-plugins-dev', 'abiword-plugins-locale' ]
    metapkg_rdepends = []
    packages = d.getVar('PACKAGES').split()
    for pkg in packages[1:]:
        if not pkg in blacklist and not pkg in metapkg_rdepends and not pkg.count("-dev") and not pkg.count("-dbg") and not pkg.count("static") and not pkg.count("locale") and not pkg.count("abiword-doc"):
            print("Modifying %s" % pkg)
            metapkg_rdepends.append(pkg)
    d.setVar('RDEPENDS:' + metapkg, ' '.join(metapkg_rdepends))
    d.setVar('DESCRIPTION:' + metapkg, 'abiword-plugin meta package')
    packages.append(metapkg)
    d.setVar('PACKAGES', ' '.join(packages))
}

FILES:${PN}-plugin-openxml += "${datadir}/${PN}-${SHRT_VER}/omml_xslt"
