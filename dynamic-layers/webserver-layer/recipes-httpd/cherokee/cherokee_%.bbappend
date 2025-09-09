FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"
SRC_URI += " file://0002-remove-double-am-init-automake.patch "
do_install:append(){
    rm -r ${D}/var/log
}
