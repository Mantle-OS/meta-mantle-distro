require ${BPN}.inc

inherit gobject-introspection bash-completion gtk-icon-cache mime mime-xdg

MIME_XDG_PACKAGES = "${PN}"

SRC_URI += " \
    http://download.documentfoundation.org/libreoffice/src/${DIRV}/${BPN}-translations-${PV}.tar.xz;name=translations \
    file://0001-configure.ac-skip-some-cross-compile-sections-they-d.patch \
    file://0002-Makefile.in-avoid-building-target-cross-toolset.patch \
    file://0003-remove-paths-for-gb_Executable_get_command.patch \
    file://0004-ensure-that-native-gendict-build-by-libreoffice-is-u.patch \
    file://0005-add-a-new-gb_Rdb_get_target_for_build_native-and-use.patch \
    file://0006-Package.mk-workaround-icu-missing-error-for-without-.patch \
    file://0007-configure.ac-avoid-finding-calling-pg_config.patch \
    file://0008-avoid-downloading-by-git-submodules.patch \
    file://0009-Use-wrappers-for-gobject-introspection.patch \
    file://0010-Support-install-to-find-bash-completion.in.patch \
    file://0011-Skip-isystem-check.patch \
"

SRC_URI[translations.sha256sum] = "c8053f863c95c31a83a079bb2eefd9b666ffd59e40c4344098c04a924a54f6e1"

DEPENDS += " \
    ${BPN}-native \
    \
    curl \
    icu \
    expat \
    harfbuzz \
    openldap \
    nss \
    zlib \
    jpeg \
    libpng \
    libatomic-ops \
    lcms \
    harfbuzz \
    cppunit \
    glew \
    openssl \
    cups \
    gstreamer1.0-plugins-base \
    tiff \
    \
    glm \
    redland \
    libabw \
    libwps \
    libwpg \
    libwpd \
    libcdr \
    librevenge \
    libfreehand \
    libe-book \
    libmwaw \
    libetonyek \
    libvisio \
    libmspub \
    libpagemaker \
    libodfgen \
    libgltf \
    libexttextcat \
    clucene-core \
    hunspell \
    mythes \
    hyphen \
    graphite2 \
    liblangtag \
    lpsolve \
    gpgme \
    mdds-2.0 \
"

# necessary to let the call for python-config succeed
export BUILD_SYS
export HOST_SYS
export STAGING_LIBDIR
export STAGING_INCDIR

# Notes:
#
# 1. By default many many sources are downloaded from libreoffice mirrors.
#    This can be avoided by --with-system-.. To see what's still loaded check
#    log.do_compile.
# 2. in case of trouble in do_compile: configure with --enable-verbose might
#    help detecting culprit
# 3. --enable-scripting-javascript / rhino meta-java
# 4. Libreoffice Base embedded db / hsqldb meta-java

EXTRA_OECONF += " \
    --without-java \
    --with-lang=ALL \
    \
    --disable-coinmp \
    --with-tls=nss \
    --without-galleries \
    \
    --with-system-openldap \
    --with-system-libatomic_ops \
    --with-system-icu \
    --with-system-expat \
    --with-system-curl \
    --with-system-openssl \
    \
    --with-system-cppunit \
    --with-system-glm \
    --with-system-redland \
    --with-system-libabw \
    --with-system-libwps \
    --with-system-libwpg \
    --with-system-libwpd \
    --with-system-libcdr \
    --with-system-librevenge \
    --with-system-libfreehand \
    --with-system-libebook \
    --with-system-libmwaw \
    --with-system-libetonyek \
    --with-system-libvisio \
    --with-system-libmspub \
    --with-system-libpagemaker \
    --with-system-libodfgen \
    --with-system-libexttextcat \
    --with-system-clucene \
    --with-system-mythes \
    --with-system-altlinuxhyph \
    --with-system-gpgmepp \
    --with-system-libtiff \
    \
    --with-external-dict-dir=${datadir}/hunspell \
    --with-system-dicts \
"

PACKAGECONFIG ??= " \
    gtk3 \
    mariadb \
    postgresql \
"

PACKAGECONFIG[gtk3] = "--enable-gtk3 , --disable-gtk3, gtk+3 cairo"
PACKAGECONFIG[avahi] = "--enable-avahi, --disable-avahi, avahi"
PACKAGECONFIG[odk] = "--enable-odk, --disable-odk"
PACKAGECONFIG[mariadb] = "--with-system-mariadb, , mariadb"
PACKAGECONFIG[postgresql] = "--enable-postgresql-sdbc --with-system-postgresql, --disable-postgresql-sdbc, postgresql"

do_configure() {
    olddir=`pwd`
    cd ${S}
    aclocal --system-acdir=${STAGING_DATADIR_NATIVE}/aclocal/ -I ${S}/m4
    gnu-configize
    autoconf
    cd $olddir
    export PYTHON_CFLAGS=-I${STAGING_INCDIR}/${PYTHON_DIR}${PYTHON_ABI}
    export PYTHON_LIBS="-L${STAGING_LIBDIR} -lpython${PYTHON_BASEVERSION}${PYTHON_ABI}"
    oe_runconf

    mkdir -p ${B}/workdir/Executable

    # icu binaries are expected in our build tree
    mkdir -p ${B}/workdir/UnpackedTarball/icu/source/
    cd ${B}/workdir/UnpackedTarball/icu/source/
    icu_bindir=`find ${STAGING_DATADIR_NATIVE}/icu -name bin`
    ln -sf $icu_bindir

    # link to native saxparser.rdb - cross version of that file is useless
    sed -i 's:%STAGING_LIBDIR_NATIVE%:${STAGING_LIBDIR_NATIVE}:g' ${S}/solenv/gbuild/TargetLocations.mk

    # ensure gengal loads native libraries
    sed -i 's:%STAGING_LIBDIR_NATIVE%:${STAGING_LIBDIR_NATIVE}:g' ${S}/solenv/gbuild/Gallery.mk

    # adjust gpgme++ path
    sed -i 's:-I${includedir}/gpgme++:-I${STAGING_INCDIR}/gpgme++:g' ${B}/config_host.mk
}

do_compile:prepend() {
    # INTROSPECTION_SCANNER is exprted but INTROSPECTION_COMPILER is not. This
    # caused 'Permission denied' errors. So give a little help:
    export INTROSPECTION_COMPILER=${STAGING_BINDIR}/g-ir-compiler-wrapper
}

do_install() {
    make DESTDIR=${D} distro-pack-install

    # install LibreOfficeKit (gobject-introspection) manually - became necessary since 6.4.x
    install -m 0755 -d ${D}${libdir}/girepository-1.0
    install -m 0644 ${B}/workdir/CustomTarget/sysui/share/libreoffice/LOKDocView-0.1.typelib ${D}${libdir}/girepository-1.0/
    install -m 0755 -d ${D}${libdir}/gir-1.0
    install -m 0644 ${B}/workdir/CustomTarget/sysui/share/libreoffice/LOKDocView-0.1.gir ${D}${libdir}/gir-1.0/
    install -m 0755 ${B}/instdir/program/liblibreofficekitgtk.so ${D}${libdir}/
    # install LibreOfficeKit headers
    install -m 0755 -d ${D}${includedir}/LibreOfficeKit
    install -m 0644 ${S}/include/LibreOfficeKit/* ${D}${includedir}/LibreOfficeKit/

    # unoconv
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/git/unoconv/unoconv ${D}/${bindir}

    # remove some unneeded files
    rm -rf ${D}${libdir}/libreoffice/readmes
    rm -rf ${D}${libdir}/libreoffice/share/theme_definitions/ios
    rmdir ${D}${libdir}/libreoffice/share/theme_definitions
}


FILES:${PN} += " \
    ${datadir}/icons \
    ${datadir}/metainfo \
    ${datadir}/mime \
"

PACKAGES =+ "${PN}-odk ${PN}-officekit"

FILES:${PN}-odk = "${libdir}/libreoffice/sdk"
INSANE_SKIP:${PN}-odk += "dev-so staticdev"

FILES:${PN}-officekit = " \
    ${libdir}/girepository-1.0 \
    ${libdir}/liblibreofficekitgtk.so \
"

# based http://pkgs.fedoraproject.org/cgit/rpms/libreoffice.git/tree/libreoffice.spec
LO_LANGUAGE_FILES = " \
    ${libdir}/libreoffice/share/autocorr/*%{1}.dat \
    ${libdir}/libreoffice/program/resource/*%{1}/LC_MESSAGES \
    ${libdir}/libreoffice/share/registry/Langpack-%{1}.xcd \
    ${libdir}/libreoffice/share/registry/res/registry_%{1}.xcd \
    ${libdir}/libreoffice/share/registry/res/fcfg_langpack_%{1}.xcd \
"

python lo_do_split_locales() {
    import glob, subprocess

    packages = (d.getVar('PACKAGES') or "").split()

    datadir = d.getVar('datadir')
    if not datadir:
        bb.note("datadir not defined")
        return

    dvar = d.getVar('PKGD')
    pn = d.getVar('LOCALEBASEPN')

    if pn + '-locale' in packages:
        packages.remove(pn + '-locale')

    # extract locales from translation source path
    # this won't add en-US which comes with base sources
    locales = []
    transpaths = glob.glob('%s/translations/source/*' % d.getVar('S'))
    for l in transpaths:
        locale = l.replace('%s/translations/source/' % d.getVar('S'), '')
        if not locale in locales:
            locales.append(locale)

    # find the paths where locales are found
    # en-US is fallback so it should be implemented everywhere (= folder created)
    langfallback = 'en-US'
    findCMD = 'find %s -name "*%s*"' % (dvar, langfallback)
    fallbacksearchresult = subprocess.Popen(findCMD, stdout=subprocess.PIPE, shell=True, universal_newlines=True).communicate()[0]
    # uncomment to see if we need more entries in LO_LANGUAGE_FILES
    # bb.note("fallbacksearchresult = %s" % fallbacksearchresult.replace(dvar, ''))

    # check for files matching at each location / language
    langfiles = dict()
    for transvar in d.getVar('LO_LANGUAGE_FILES').split():
        filesfound = 0
        for locale in sorted(locales):
            pattern = transvar.replace('%{1}', locale)
            translocation = '%s%s' % (dvar, pattern)
            transfiles = glob.glob(translocation)
            if transfiles:
                if locale not in langfiles:
                    langfiles[locale] = ''
                langfiles[locale] = '%s %s' % (langfiles[locale], pattern)
                filesfound = 1
        if filesfound:
            # remove from fallbacksearchresult REVISIT
            pattern = transvar.replace('%{1}', langfallback)
            translocation = '%s%s' % (dvar, pattern)
            if translocation in fallbacksearchresult:
                fallbacksearchresult.replace(translocation, '')
        else:
            bb.warn('%s language file pattern not found:  %s' % (d.getVar('PN'), transvar))

    # setup packages for locales with files found
    summary = d.getVar('SUMMARY') or pn
    description = d.getVar('DESCRIPTION') or ""
    locale_section = d.getVar('LOCALE_SECTION')
    mlprefix = d.getVar('MLPREFIX') or ""
    for locale in locales:
        if locale in langfiles:
            ln = legitimize_package_name(locale)
            pkg = pn + '-locale-' + ln
            packages.insert(0, pkg)
            d.setVar('FILES:' + pkg, langfiles[locale] )
            d.setVar('RRECOMMENDS:' + pkg, '%svirtual-locale-%s' % (mlprefix, ln))
            d.setVar('RPROVIDES:' + pkg, '%s-locale %s%s-translation' % (pn, mlprefix, ln))
            d.setVar('SUMMARY:' + pkg, '%s - %s translations' % (summary, l))
            d.setVar('DESCRIPTION:' + pkg, '%s  This package contains language translation files for the %s locale.' % (description, l))
            if locale_section:
                d.setVar('SECTION:' + pkg, locale_section)

    d.setVar('PACKAGES', ' '.join(packages))

    return
}

PACKAGESPLITFUNCS:prepend = "lo_do_split_locales "

RDEPENDS:${PN} = "hunspell-dictionaries"
