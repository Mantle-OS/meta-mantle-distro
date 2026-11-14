require ${BPN}.inc

FILESEXTRAPATHS =. "${FILE_DIRNAME}/${PN}:"

inherit native

DEPENDS += " \
    cairo-native \
    libepoxy-native \
    curl-native \
    libpng-native \
    jpeg-native \
    libxml2-native \
    graphite2-native \
    harfbuzz-native \
    icu-native \
    expat-native \
    lcms-native \
    nss-native \
    cppunit-native \
    hunspell-native \
    mythes-native \
    clucene-core-native \
    glm-native \
    hyphen-native \
    unzip-native \
    liblangtag-native \
    lpsolve-native \
    mdds-2.0-native \
"

SRC_URI += " \
    file://0001-saxparser-output-calling-parametrs-for-debug.patch \
    file://0002-cppuhelper-defaultbootstrap-output-debug-information.patch \
    file://0003-add-gengal-debug-information.patch \
    file://0004-gengal-fix-path-to-redirectrc.patch \
    file://0005-Do-not-check-download-lib-unnecessary-for-native-bui.patch \
    file://0006-Do-not-fetch-fonts-for-native-tools.patch \
"

EXTRA_OECONF += " \
    --without-java \
    \
    --disable-gui \
    --disable-extensions \
    --disable-extension-integration \
    \
    --disable-mariadb-sdbc \
    --disable-postgresql-sdbc \
    --disable-lotuswordpro \
    --disable-firebird-sdbc \
    --disable-openssl \
    --disable-scripting-beanshell \
    --disable-scripting-javascript \
    --disable-pdfimport \
    --disable-coinmp \
    --disable-opencl \
    --disable-zxing \
"

do_configure() {
    olddir=`pwd`
    cd ${S}
    aclocal --system-acdir=${STAGING_DATADIR_NATIVE}/aclocal/ -I ${S}/m4
    gnu-configize
    autoconf
    cd $olddir
    PYTHON_CFLAGS=-I${STAGING_INCDIR_NATIVE}/${PYTHON_DIR}${PYTHON_ABI} PYTHON_LIBS="-L${STAGING_LIBDIR_NATIVE} -lpython${PYTHON_BASEVERSION}${PYTHON_ABI}" oe_runconf
    # fake for cross-toolset
    cp -f ${B}/config_host.mk ${B}/config_build.mk
    cp -f ${B}/config_host_lang.mk ${B}/config_build_lang.mk
    cp -rf ${B}/config_host ${B}/config_build
}

# for debugging - we can as we're native
CXXFLAGS += "-g -O0 -DSAL_LOG_INFO -DSAL_LOG_WARN"
LDFLAGS += "-g"

# these seem to get lost in our tailored build
LDFLAGS += "-ldl"


do_compile() {
    BUILDDIR=${B} oe_runmake cross-toolset
    # gengal was not designed for build - we need to add it and it's dependencies
    BUILDDIR=${B} oe_runmake Executable_gengal
    BUILDDIR=${B} oe_runmake Library_ucb1
    BUILDDIR=${B} oe_runmake Library_configmgr
    BUILDDIR=${B} oe_runmake Library_fwk
    BUILDDIR=${B} oe_runmake Library_i18npool
    BUILDDIR=${B} oe_runmake Library_pyuno
}

LOBUILDTOOLS = " \
    bestreversemap \
    cfgex \
    cpp \
    gencoll_rule \
    genconv_dict \
    genindex_data \
    idxdict \
    propex \
    saxparser \
    svidl \
    ulfex \
    unoidl-check \
    xrmex \
"

do_install() {
    install -d ${D}/${bindir}
    for name in ${LOBUILDTOOLS} ; do
        install "${B}/workdir/LinkTarget/Executable/$name" ${D}/${bindir}
    done
    install "${B}/workdir/Headers/Executable/unoidl-write" ${D}/${bindir}

    # icu creates a gendict. To avoid conflicts rename in sysroot
    install "${B}/workdir/LinkTarget/Executable/gendict" ${D}/${bindir}/gendict_libre

    # install sdk binaries
    install ${B}/instdir/sdk/bin/* ${D}/${bindir}

    # install libraries and defaults
    install -d ${D}/${libdir}
    for name in `find ${B}/instdir/program -type f` ; do
        install "$name" ${D}/${libdir}
    done

    # move saxparser.rdb to libdir - we'll need it for cross building
    cp -rf ${B}/workdir/Rdb/saxparser.rdb ${D}/${libdir}
    # fix library path - otherwise cross lib is pulled for native saxparse
    sed -i 's:LO_LIB_DIR:URE_INTERNAL_LIB_DIR:g' ${D}/${libdir}/saxparser.rdb

    # gengal script and binary to expected location
    install ${S}/svx/source/gengal/gengal.sh ${D}/${bindir}/gengal
    mv ${D}/${libdir}/gengal.bin ${D}/${bindir}

    # unoconv
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/git/unoconv/unoconv ${D}/${bindir}
}
