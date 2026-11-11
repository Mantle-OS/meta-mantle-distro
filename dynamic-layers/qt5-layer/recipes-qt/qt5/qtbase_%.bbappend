# mantle default qt5 base settings
# Note you should handle the graphics in your machine conf not here
# We want the hardware info in the hardware declare
# Example my-machine.conf
# PACKAGECONFIG:append:pn-qtbase = " kms eglfs gbm linuxfb vulkan"
#
PACKAGECONFIG:append = " \
    freetype \
    fontconfig \
    harfbuzz \
    jpeg \
    libpng \
    gif \
    ico \
    zlib \
    dbus \
    udev \
    cups \
    mtdev \
    tslib \
    openssl \
    sql-mysql \
    sql-odbc \
    sql-psql \
    sql-sqlite \
    ${@bb.utils.contains("DISTRO_FEATURES", "x11", "gtk", "", d)} \
"

# Native
PACKAGECONFIG:append:class-native = " \
    sql-sqlite \
    accessibility \
"
