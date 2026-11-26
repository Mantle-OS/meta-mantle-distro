SUMMARY = "Misc packages for the KDE Desktop or Mobile"
DESCRIPTION = "These recipes are not in release support kf6 nor plasma so they live here. \
 browser, education, games, graphics, multimedia and system recipes are in the base package \
 while packagegroup-kde6-support holds all the supporint packages.\
"

SECTION = "x11"
LICENSE = "MIT"

PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup features_check
REQUIRED_DISTRO_FEATURES = "wayland kde_x11"

PACKAGES = " \
    ${PN} \
    ${@bb.utils.contains("BBFILE_COLLECTIONS", "qt6-layer ", " ${PN}-qt6", "", d)} \
"

#     corrosion  This is dumb and I go about removing everything that uses it
RRECOMMENDS:${PN} = " \
    zint \
    zxing-cpp \
    ddcutil \
    libphonenumber-cpp \
    libpinyin \
    libdmtx \
    libqalculate \
    ${PN}-support \
"

SUMMARY:${PN}-qt6 = "support recipes for kde6"
DESCRIPTION:${PN}-qt6 = "These packages are meant to support other parts of KDE6 like plasma and KF6"
RRECOMMENDS:${PN}-qt6 = " \
    qtkeychain \
    kcolorpicker \
    kimageannotator \
    sddm \
    qcoro \
    qml-box2d \
    maliit-keyboard-qt6 \
    maliit-framework-qt6 \
    qmltermwidget6 \
"
