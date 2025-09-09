SUMMARY = "MantleOs ota repos. It builds all things supported by the MantleOs distro is the big boy"
PR = "r0"

# This packages themselves are not meant  to be installed directly as some of the
# packages may conflict with each other but specifying them allows them to be
# available in the package repository

PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup

PACKAGES = " \
    ${PN} \
    ${PN}-poky-world \
    ${PN}-oe-world \
    ${PN}-virtualization-world \
    ${PN}-clang-world \
    ${PN}-rust-world \
    ${PN}-qt6-world \
    ${@bb.utils.contains("BBFILE_COLLECTIONS", "qt5-layer ", " ${PN}-qt5-world ${PN}-qt5-extra-world ", "", d)} \
    ${PN}-java-world \
    ${@bb.utils.contains("BBFILE_COLLECTIONS", "chromium-browser-layer", "${PN}-chrome-world", "", d)} \
    ${@bb.utils.contains("BBFILE_COLLECTIONS", "firefox-browser-layer", "${PN}-firefox-world", "", d)} \
"
# dirty 
# ${PN}-mono-world 
# ${PN}-neural-network-world tensorflow fails here
# end dirty
RRECOMMENDS:${PN} = "\
    ${PN}-poky-world \
"

SUMMARY:${PN}-poky-world = "poky ALL Packagegroups"
RRECOMMENDS:${PN}-poky-world = "\
    packagegroup-base \
    packagegroup-core-boot \
    packagegroup-core-buildessential \
    coreutils ccache findutils quilt less ldd file tcl diffutils \
    perl-module-re perl-module-text-wrap \
    rust cargo \
    go go-runtime go-runtime-dev \
    packagegroup-core-nfs-server packagegroup-core-nfs-client \
    packagegroup-core-ssh-openssh \
    packagegroup-core-tools-debug \
    packagegroup-core-tools-profile \
    packagegroup-core-tools-testapps \
    ${@bb.utils.contains("DISTRO_FEATURES", "x11 ", "packagegroup-self-hosted ", "", d)} \
    packagegroup-core-eclipse-debug \
    ${@bb.utils.contains("DISTRO_FEATURES", "x11 ", " packagegroup-core-x11-sato packagegroup-core-x11-base packagegroup-core-x11 packagegroup-core-x11-xserver ", "", d)} \
    ${@bb.utils.contains("DISTRO_FEATURES", "wayland ", " packagegroup-core-weston ", "", d)} \
    packagegroup-core-base-utils \
    packagegroup-core-full-cmdline \
"


# Bring in everything that is openembedded
SUMMARY:${PN}-oe-world = "OE ALL Packagegroups"
RRECOMMENDS:${PN}-oe-world = "\
    packagegroup-meta-filesystems \
    packagegroup-meta-initramfs \
    ${@bb.utils.contains("DISTRO_FEATURES", "x11 ", "gdm gnome-menus gparted packagegroup-gnome-desktop packagegroup-gnome-apps packagegroup-core-clutter-core ", "", d)} \
    packagegroup-meta-multimedia \
    packagegroup-meta-networking \
    packagegroup-basic packagegroup-boot packagegroup-meta-oe \
    packagegroup-meta-perl packagegroup-meta-perl-extended \
    packagegroup-meta-python3 \
    packagegroup-meta-webserver \
    ${@bb.utils.contains("DISTRO_FEATURES", "x11 ", "packagegroup-xfce-multimedia packagegroup-xfce-base packagegroup-xfce-extended", "", d)} \
"

# disable kubernetes for i686 upstream needs to enable it if it can. Ill reach out at somepoint
SUMMARY:${PN}-virtualization-world = "meta-virtualization ALL Packagegroups"
RRECOMMENDS:${PN}-virtualization-world = "\
    ${@bb.utils.contains("TUNE_ARCH", "i686", "", "packagegroup-kubernetes-base", d )} \
    packagegroup-container \
"

# clang
SUMMARY:${PN}-clang-world = "clang"
RRECOMMENDS:${PN}-clang-world = "\
    clang \
"

# csharp 
SUMMARY:${PN}-mono-world = "c sharp world"
RRECOMMENDS:${PN}-mono-world = "\
    mono-upnp \
    dbus-sharp \
    dbus-sharp-glib \
    gtk-sharp \
    mono-xsp \
    monotools-server \
    taglib-sharp \
    fsharp \
    libgdiplus \
    mono-basic \
    mono-dev \
    msbuild \
    dotnet \
"

# java FROM the read me
#| JVM / Architecture | arm | aarch64 | riscv64 | x64 | x86 |
#|--------------------|-----|---------|---------|-----|-----|
#| JRE 8              | ✅  | ✅      | ❌      | ✅  | ❌  |
#| JRE 11             | ✅  | ✅      | ❌      | ✅  | ❌  |
#| JRE 17             | ✅  | ✅      | ✅      | ✅  | ❌  |
#| JRE 21             | ❌  | ✅      | ✅      | ✅  | ❌  |
# create the subpackage
SUMMARY:${PN}-java-world = "Java runtimes/toolchains bundle (arch-aware)"
python __set_java_world_recos () {
    tune = (d.getVar('TUNE_ARCH') or '').strip()
    recos = ''
    if tune == 'x86_64':
        # x64: JRE 8/11/17/21
        recos = 'openjdk-21-jdk openjdk-21-jre ' \
                'openjdk-17-jdk openjdk-17-jre ' \
                'openjdk-11-jdk openjdk-11-jre ' \
                'openjdk-8-jdk  openjdk-8-jre'
    elif tune == 'aarch64':
        # arm64: JRE 8/11/17/21
        recos = 'openjdk-21-jdk openjdk-21-jre ' \
                'openjdk-17-jdk openjdk-17-jre ' \
                'openjdk-11-jdk openjdk-11-jre ' \
                'openjdk-8-jdk  openjdk-8-jre'
    elif tune.startswith('arm'):
        # arm (32-bit): JRE 8/11/17; no 21
        recos = 'openjdk-17-jdk openjdk-17-jre ' \
                'openjdk-11-jdk openjdk-11-jre ' \
                'openjdk-8-jdk  openjdk-8-jre'
    elif tune == 'riscv64':
        # riscv64: JRE 17/21 only
        recos = 'openjdk-21-jdk openjdk-21-jre ' \
                'openjdk-17-jdk openjdk-17-jre'
    else:
        # i586/i686 (x86) or anything unsupported: recommend nothing
        recos = ''

    d.setVar('RRECOMMENDS:%s-java-world' % d.getVar('PN'), recos)
}

# chromium
SUMMARY:${PN}-chrome-world = "chrome browsers"
RRECOMMENDS:${PN}-chrome-world = " \
    ${@bb.utils.contains("DISTRO_FEATURES", "x11", " chromium-x11 ", "", d)} \
    ${@bb.utils.contains("DISTRO_FEATURES", "wayland", "  chromium-ozone-wayland ", "", d)} \
"

# firefox needs py2 :( 
SUMMARY:${PN}-firefox-world = "firefox packages"
RRECOMMENDS:${PN}-firefox-world = "\
    ${@bb.utils.contains("BBFILE_COLLECTIONS", "meta-python2"", "firefox firefox-l10n-en-us", "", d)} \
"

# qt5
SUMMARY:${PN}-qt5-world  = "qt5 packages"
RRECOMMENDS:${PN}-qt5-world = " \
    qtbase \
    qtcoap \
    qtgamepad \
    qtimageformats \
    qtknx \
    qtlocation \
    qtlottie \
    qtmqtt \
    qtmultimedia \
    qtnetworkauth \
    qtopcua \
    qtpurchasing \
    qtdeclarative \
    qtquickcontrols \
    qtquickcontrols2 \
    qtquicktimeline \
    qtremoteobjects \
    qtscript \
    qtscxml \
    qtserialbus \
    qtserialport \
    qtsensors \
    qtsvg \
    qtxmlpatterns \
    qtwebchannel \
    qttranslations \
    qtvirtualkeyboard \
    qtwebsockets \
    qtxmlpatterns \
    ${@bb.utils.contains("DISTRO_FEATURES", "opengl", " qtcharts qtwebglplugin qt3d qtquick3d qtconnectivity qtgraphicaleffects qtdatavis3d ", "", d)} \
    ${@bb.utils.contains("DISTRO_FEATURES", "wayland", " qtwayland ", "", d)} \
    ${@bb.utils.contains("DISTRO_FEATURES", "webengine", " qtpdf qtwebengine qtwebkit qtwebview qt-kiosk-browser ", "", d)} \
    ${@bb.utils.contains("DISTRO_FEATURES", "x11", " qtx11extras ", "", d)} \
    qtsystems \
    qttools \
    qmllive \
    maliit-framework-qt5 maliit-plugins-qt5 \
"

# qt6
SUMMARY:${PN}-qt6-world  = "qt6 packages"
RRECOMMENDS:${PN}-qt6-world = " \
    ${@bb.utils.contains("BBFILE_COLLECTIONS", "qt6-layer", " packagegroup-qt6-essentials  packagegroup-qt6-addons packagegroup-qt6-modules ", "", d)} \
"

# meta-neural-network I need to look into this some more there are some broken things
# SUMMARY:${PN}-neural-network-world  = "some of the neural network stuff comming"
# RRECOMMENDS:${PN}-neural-network-world = "
#     ml-api
#     openblas
#     nnstreamer
#     nntrainer
#     googletest
#     ssat
# "
# tensorflow-lite

EXCLUDE_FROM_WORLD = "1"
