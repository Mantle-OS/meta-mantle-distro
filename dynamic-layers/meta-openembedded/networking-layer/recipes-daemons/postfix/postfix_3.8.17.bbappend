# Force the compiler to use the C17 standard to avoid C23 keyword(bool, true,false...) conflicts
#TARGET_CFLAGS += "-std=gnu17"
#BUILD_CFLAGS += "-std=gnu17"
#EXTRA_OEMAKE += " CCARGS='-std=gnu17' "

DEPENDS:append = " \
    db \
    libnsl2 \
    rpcsvc-proto \
"
