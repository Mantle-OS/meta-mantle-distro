#base
PACKAGECONFIG += "\
    cups \
    mtdev \
    tslib \
    ${@bb.utils.contains("DISTRO_FEATURES", "x11 ", " gtk ", "", d)} \
"

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
