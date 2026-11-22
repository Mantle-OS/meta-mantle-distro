DEPENDS:append  = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'pam', 'libxcrypt', '', d)} \
"
