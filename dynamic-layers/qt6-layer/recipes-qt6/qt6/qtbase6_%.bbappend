#base
PACKAGECONFIG:append:class-target = "\
    cups \
    dbus \
    mtdev \
    tslib \
    harfbuzz \
    brotli libproxy gssapi \
    sql-mysql \
    sql-odbc \
    sql-psql \
    sql-sqlite \
    ${@bb.utils.contains("DISTRO_FEATURES", "x11", " gtk ", "", d)} \
    ${@bb.utils.contains("DISTRO_FEATURES", "opengl", " eglfs ", "", d)} \
"


DEPENDS:append:x86:class-target = " mesa libdrm "
PACKAGECONFIG:append:x86:class-target = " eglfs gbm gl kms linuxfb vulkan wayland qtwaylandscanner xcb  "

DEPENDS:append:x86-64:class-target = " mesa libdrm "
PACKAGECONFIG:append:x86-64:class-target = " eglfs gbm gl kms linuxfb vulkan wayland qtwaylandscanner xcb "



PACKAGECONFIG:append:class-native = " \
    sql-sqlite \
    accessibility \
"
