#base
PACKAGECONFIG += "\
    cups \
    mtdev \
    tslib \
    harfbuzz \
    ${@bb.utils.contains("DISTRO_FEATURES", "x11", " gtk ", "", d)} \
    ${@bb.utils.contains("DISTRO_FEATURES", "opengl", " eglfs ", "", d)} \
    ${@bb.utils.contains("MACHINE_FEATURES", "i915", " eglfs gbm gl kms linuxfb vulkan wayland qtwaylandscanner xcb ", "", d)} \
"


DEPENDS:x86 += " mesa libdrm "
PACKAGECONFIG:x86 += " kms eglfs "

#net
PACKAGECONFIG += "\
    brotli libproxy gssapi \
"

# sql
PACKAGECONFIG += "\
    sql-mysql \
    sql-odbc \
    sql-psql \
    sql-sqlite \
"

PACKAGECONFIG:append:class-native = " \
    sql-sqlite \
    accessibility \
"
