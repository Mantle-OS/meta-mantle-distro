SUMMARY = "A library for rendering glTF models"
HOMEPAGE = "https://wiki.documentfoundation.org/Development/libgltf"
SECTION = "text"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=9741c346eef56131163e13b9db1241b3 \
"

SRC_URI = " \
    https://dev-www.libreoffice.org/src/libgltf/libgltf-0.1.0.tar.gz \
"
SRC_URI[sha256sum] = "119e730fbf002dd0eaafa4930167267d7d910aa17f29979ca9ca8b66625fd2da"
inherit autotools pkgconfig features_check

REQUIRED_DISTRO_FEATURES = "opengl"

DEPENDS = " \
    glew glm \
    boost \
    libepoxy \
"
## TODO ADD RUNTIME OPENGL
