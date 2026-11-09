# cmake_extra_sanity comes from mantle-office
inherit cmake cmake_extra_sanity features_check
require fcitx5-git.inc

PACKAGECONFIG[shared]   = " -DBUILD_SHARED_LIBS=ON, -DBUILD_SHARED_LIBS=OFF, "
PACKAGECONFIG[test]     = " -DENABLE_TEST=ON, -DENABLE_TEST=OFF, "
PACKAGECONFIG[coverage] = " -DENABLE_COVERAGE=ON -DENABLE_TEST=ON, -DENABLE_COVERAGE=OFF, "
PACKAGECONFIG[docs]     = " -DENABLE_DOC=ON, -DENABLE_DOC=OFF, doxygen-native"

REQUIRED_DISTRO_FEATURES =  "systemd opengl"
REQUIRED_DISTRO_FEATURES:class-native = ""

ANY_OF_DISTRO_FEATURES = "x11 wayland"
ANY_OF_DISTRO_FEATURES:class-native = ""

S = "${WORKDIR}/git"

DEPENDS:append = " \
    pkgconfig-native \
    ecm-kf6-native \
"

PACKAGECONFIG:append:class-nativesdk = " ${PACKAGECONFIG:class-native}"
EXTRA_OECMAKE:append:class-nativesdk = " ${EXTRA_OECMAKE:class-native}"

BBCLASSEXTEND = "native nativesdk"
