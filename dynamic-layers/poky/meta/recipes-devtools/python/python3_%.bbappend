# /usr/bin/python -> /usr/bin/python3
do_install:append:class-target() {
    ln -sfn python3 ${D}${bindir}/python
}

FILES:${PN}-core:append:class-target = " \
    ${bindir}/python \
"
