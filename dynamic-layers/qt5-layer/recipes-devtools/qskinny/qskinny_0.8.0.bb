SUMMARY = "QSkinny"
DESCIPTION = "The (Q)Skinny library is a framework built on top of the Qt scene graph and very few core classes from Qt/Quick. It offers a set of lightweight controls, that can be used from C++ and/or QML."
HOMEPAGE = "https://github.com/uwerat/qskinny"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE.md;md5=b090613b0f65399acfdef2a8d6d33711"

SRCREV ?= "f22c11af478ecb0d877eed85eb2dad0120b82830"
SRC_URI = "git://github.com/uwerat/qskinny.git;branch=master;protocol=https"

PV = "0.8.0"

S = "${WORKDIR}/git"

REQUIRED_DISTRO_FEATURES =  " opengl"

inherit cmake_qt5

PACKAGECONFIG[pedantic]         = " -DBUILD_PEDANTIC=ON, -DBUILD_PEDANTIC=OFF, "
PACKAGECONFIG[shared]           = " -DBUILD_QSKDLL=ON, -DBUILD_QSKDLL=OFF, "
PACKAGECONFIG[qmlexport]        = " -DBUILD_QML_EXPORT=ON, -DBUILD_QML_EXPORT=OFF, "
PACKAGECONFIG[tools]            = " -DBUILD_TOOLS=ON, -DBUILD_TOOLS=OFF, "
PACKAGECONFIG[tools-standalone] = " -DBUILD_TOOLS_STANDALONE=ON, -DBUILD_TOOLS_STANDALONE=OFF, "
PACKAGECONFIG[virtual-keyboard] = " -DBUILD_INPUTCONTEXT=ON, -DBUILD_INPUTCONTEXT=OFF, "
PACKAGECONFIG[examples]         = " -DBUILD_EXAMPLES=ON, -DEXAMPLES=OFF, "
PACKAGECONFIG[playground]       = " -DBUILD_PLAYGROUND=ON, -DBUILD_PLAYGROUND=OFF, "
# note even thought his is called libpinyin6" its not really qt6 infact it does not link to qt at all
PACKAGECONFIG[pinyin]           = " -DENABLE_PINYIN=ON, -DENABLE_PINYIN=OFF, libpinyin6"
PACKAGECONFIG[hunspell]         = " -DENABLE_HUNSPELL=ON , -DENABLE_HUNSPELL=OFF, hunspell"
PACKAGECONFIG[docs]             = ",, doxygen-native "
PACKAGECONFIG[ensure-skins]     = " -DENABLE_ENSURE_SKINS=ON, -DENABLE_ENSURE_SKINS=-OFF, "

PACKAGECONFIG ?= " \
    shared \
    qmlexport \
    tools \
    virtual-keyboard pinyin hunspell \
    examples \
    playground \
    ensure-skins \
    docs \
"

DEPENDS += " \
    pkgconfig-native \
    qtbase qtbase-native \
    qttools-native \
    qtdeclarative \
    qtquickcontrols \
    qtgraphicaleffects \
    qtsvg \
    ${@bb.utils.contains('DISTRO_FEATURES', 'webengine', 'qwebengine', '', d)} \
"

#OE_EXTRA_CMAKE:append = " -DDCMAKE_PREFIX_PATH=

BUILD_DOCS = "${@bb.utils.contains('PACKAGECONFIG', 'docs', 'yes', 'no', d)}"
do_compile:append(){
    if [[ $BUILD_DOCS == "yes" ]];
    then
        # run the doxygen crazy ness opn the docs/ folder. then later we will handle the packaging Thjey have some script for this
        cd ${S}/docs
        doxygen
        find html -name 'class_*.html' -exec sed -i \
            -e '/Detailed Description/d' \
            -e '0,/Definition at line/{//d;}' \
            -e 's/Inheritance diagram for .*://' \
            {} \;
        cd -
    fi
}


do_install:append(){
    ## for some reason they  blindly ship all the scripts lol
    rm ${D}${libbase_dir}/cmake/QSkinny/scripts/QSkinnySvg2Qvg.mac.sh
    rm ${D}${libbase_dir}/cmake/QSkinny/scripts/QSkinnySvg2Qvg.win.bat
}


## EXAMPLES
PACKAGES:append = " \
    ${PN}-example-desktop \
    ${PN}-example-gallery \
    ${PN}-example-layouts \
    ${PN}-example-mycontrols \
    ${PN}-example-qvgviewer \
    ${PN}-example-thumbnails \
    ${PN}-example-tabview \
    ${PN}-example-iotdashboard \
    ${PN}-example-boxes  \
    ${PN}-example-buttons \
    ${PN}-example-frames \
    ${PN}-example-glabels \
"

SUMMARY:${PN}-example-desktop  = " Desktop example application for QSkinny"
FILES:${PN}-example-desktop  = ""

SUMMARY:${PN}-example-gallery  = "gallery example application for QSkinny"
FILES:${PN}-example-gallery  = ""

SUMMARY:${PN}-example-layouts = "layouts example application for QSkinny"
FILES:${PN}-example-layouts = ""

SUMMARY:${PN}-example-mycontrols = "controls example application for QSkinny"
FILES:${PN}-example-mycontrols = ""

SUMMARY:${PN}-example-qvgviewer = "Svg viewer example application for QSkinny"
FILES:${PN}-example-qvgviewer = ""

SUMMARY:${PN}-example-thumbnails = "Thumbnails example application for QSkinny"
FILES:${PN}-example-thumbnails = " "

SUMMARY:${PN}-example-tabview = "TabView example application for QSkinny"
FILES:${PN}-example-tabview = ""

SUMMARY:${PN}-example-iotdashboard = "iotdashboard example application for QSkinny This is the main application demo"
FILES:${PN}-example-iotdashboard = ""
## add the systemd and sys5 shit here for auto start on this app (iotdashboard)

SUMMARY:${PN}-example-boxes  = " boxes example application for QSkinny"
FILES:${PN}-example-boxes  = ""

SUMMARY:${PN}-example-buttons = "buttons example application for QSkinny"
FILES:${PN}-example-buttons = ""

SUMMARY:${PN}-example-frames = "frames example application for QSkinny"
FILES:${PN}-example-frames = ""

SUMMARY:${PN}-example-glabels = "glabels example application for QSkinny"
FILES:${PN}-example-glabels = "\
\
"

## for the first run so we get the errors then from there we can really package
FILES:${PN} = ""
FILES:${PN}-dev = ""
FILES:${PN}-dbg = ""
FILES:${PN}-docs = ""

# PLAYGROUND
PACKAGES:append = " \
    ${PN}-playground-anchors \
    ${PN}-playground-dials \
    ${PN}-playground-dialogbuttons \
    ${PN}-playground-fonts \
    ${PN}-playground-gradients \
    ${PN}-playground-iconbrowser \
    ${PN}-playground-invoker \
    ${PN}-playground-shadows \
    ${PN}-playground-shapes \
    ${PN}-playground-charts \
    ${PN}-playground-plots \
"

SUMMARY:${PN}-playground-anchors = "anchors playground example application for QSkinny"
FILES:${PN}-playground-anchors = ""

SUMMARY:${PN}-playground-dials = "dials playground example application for QSkinny"
FILES:${PN}-playground-dials = ""

SUMMARY:${PN}-playground-dialogbuttons = "dialog buttons playground example application for QSkinny"
FILES:${PN}-playground-dialogbuttons = ""

SUMMARY:${PN}-playground-fonts = "fonts playground example application for QSkinny"
FILES:${PN}-playground-fonts = ""

SUMMARY:${PN}-playground-gradients = "gradients playground example application for QSkinny"
FILES:${PN}-playground-gradients = ""

SUMMARY:${PN}-playground-iconbrowser = " playground example application for QSkinny"
FILES:${PN}-playground-iconbrowser = ""

SUMMARY:${PN}-playground-invoker = "invoker playground example application for QSkinny"
FILES:${PN}-playground-invoker = ""

SUMMARY:${PN}-playground-shadows = "shadows playground example application for QSkinny"
FILES:${PN}-playground-shadows = ""

SUMMARY:${PN}-playground-shapes = "shapes playground example application for QSkinny"
FILES:${PN}-playground-shapes = ""

SUMMARY:${PN}-playground-charts = "charts playground example application for QSkinny"
FILES:${PN}-playground-charts = ""

SUMMARY:${PN}-playground-plots = "plots playground example application for QSkinny"
FILES:${PN}-playground-plots = ""

