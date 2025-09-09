do_install:append:class-native() {
    # Point at where the file actually landed under DESTDIR:
    if [ -f "${D}/usr/bin/yapp" ]; then
        sed -i 's|^#!.*|#!/usr/bin/env perl|' "${D}/usr/bin/yapp"
    fi
}
