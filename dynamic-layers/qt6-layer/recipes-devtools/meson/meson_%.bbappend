FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"
SRC_URI:append = " \
    file://0001-add-native-support-for-cross-qt.patch \
"
