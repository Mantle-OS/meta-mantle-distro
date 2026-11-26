
# name             file name   Type
# librime-octagram LICENSE     BSD-3-Clause
# librime-lua      LICENSE     BSD-3-Clause "New" or "Revised"
# librime-charcode NONE        UNKNOWN
# librime-proto    LICENSE     BSD-3-Clause "New" or "Revised"
# librime-predict  LICENSE     BSD-3-Clause "New" or "Revised"

SRC_URI:append = " \
    git://github.com/lotem/librime-octagram.git;protocol=https;name=octagram;nobranch=1;subdir=${S}/plugins/librime-octagram \
    git://github.com/hchunhui/librime-lua.git;protocol=https;name=lua;nobranch=1;subdir=${S}/plugins/librime-lua \
    git://github.com/rime/librime-charcode.git;protocol=https;name=charcode;nobranch=1;subdir=${S}/plugins/librime-charcode \
    git://github.com/lotem/librime-proto.git;protocol=https;name=proto;nobranch=1;subdir=${S}/plugins/librime-proto \
    git://github.com/rime/librime-predict.git;protocol=https;name=predict;nobranch=1;subdir=${S}/plugins/librime-predict \
"

LIC_FILES_CHKSUM:append = " \
    file://${S}/plugins/librime-octagram/LICENSE;md5=d32239bcb673463ab874e80d47fae504 \
    file://${S}/plugins/librime-lua/LICENSE;md5=0295c8ce4ec05f7131665d42e10bb662 \
    file://${S}/plugins/librime-proto/LICENSE;md5=a6da6bcf42ade0d01dc4d49958288b42 \
    file://${S}/plugins/librime-predict/LICENSE;md5=a6da6bcf42ade0d01dc4d49958288b42 \
"



SRCREV_octagram = "dfcc15115788c828d9dd7b4bff68067d3ce2ffb8"
SRCREV_lua = "ec52e48ea18f11af37717a01c337f853215cf70b"
SRCREV_charcode = "55e7f563e999802d41a13ba02657c1be4b2011b4"
SRCREV_proto = "657a923cd4c333e681dc943e6894e6f6d42d25b4"
SRCREV_predict = "920bd41ebf6f9bf6855d14fbe80212e54e749791"

PACKAGECONFIG[octagram-plugin] = ","
PACKAGECONFIG[lua-plugin] = ",,lua"
PACKAGECONFIG[charcode-plugin] = ",,"
PACKAGECONFIG[charcode-icu-plugin] = "-DBUILD_WITH_ICU=ON,-DBUILD_WITH_ICU=OFF,icu"
PACKAGECONFIG[proto-plugin] = "-DCAPNP_EXECUTABLE=${STAGING_BINDIR_NATIVE}/capnp \
                               -DCAPNPC_CXX_EXECUTABLE=${STAGING_BINDIR_NATIVE}/capnpc-c++, , \
    capnproto capnproto-native"

PACKAGECONFIG[predict-plugin] = ","

do_configure:prepend() {
   PLUGINS_TO_REMOVE="\
        ${@bb.utils.contains('PACKAGECONFIG', 'octagram-plugin', '', '${S}/plugins/librime-octagram', d)} \
        ${@bb.utils.contains('PACKAGECONFIG', 'lua-plugin', '', '${S}/plugins/librime-lua', d)} \
        ${@bb.utils.contains('PACKAGECONFIG', 'charcode-plugin', '', '${S}/plugins/librime-charcode', d)} \
        ${@bb.utils.contains('PACKAGECONFIG', 'proto-plugin', '', '${S}/plugins/librime-proto', d)} \
        ${@bb.utils.contains('PACKAGECONFIG', 'predict-plugin', '', '${S}/plugins/librime-predict', d)} \
    "

    if [ -n "$(echo "${PLUGINS_TO_REMOVE}" | tr -d '[:space:]')" ]; then
        rm -rf ${PLUGINS_TO_REMOVE}
    fi
}

PACKAGES =+ " \
    ${PN}-octagram \
    ${PN}-lua \
    ${PN}-charcode \
    ${PN}-proto \
    ${PN}-predict \
"

SUMMARY:${PN}-octagram = "Rime Octagram language model plugin"
DESCRIPTION:${PN}-octagram = "Provides Octagram language modeling support for the Rime input method engine."
LICENSE:${PN}-octagram = "GPL-3.0-only"
FILES:${PN}-octagram = "${libdir}/rime-plugins/librime-octagram.so"
RDEPENDS:${PN}-octagram += "${PN}"

SUMMARY:${PN}-lua = "Rime Lua scripting plugin"
DESCRIPTION:${PN}-lua = "Provides Lua scripting support for extending Rime input methods and input schemas."
LICENSE:${PN}-lua = "BSD-3-Clause"
FILES:${PN}-lua = "${libdir}/rime-plugins/librime-lua.so"
RDEPENDS:${PN}-lua += "${PN} lua"

SUMMARY:${PN}-charcode = "Rime character encoding plugin"
DESCRIPTION:${PN}-charcode = "Provides additional character encoding and conversion functionality for the Rime input method engine."
LICENSE:${PN}-charcode = "BSD-3-Clause"
FILES:${PN}-charcode = "${libdir}/rime-plugins/librime-charcode.so"
RDEPENDS:${PN}-charcode += "${PN}"

SUMMARY:${PN}-proto = "Rime Cap'n Proto integration plugin"
DESCRIPTION:${PN}-proto = "Provides Cap'n Proto serialization and protocol integration for the Rime input method engine."
LICENSE:${PN}-proto = "BSD-3-Clause"
FILES:${PN}-proto = "${libdir}/rime-plugins/librime-proto.so"
RDEPENDS:${PN}-proto += "${PN}"

SUMMARY:${PN}-predict = "Rime predictive input plugin"
DESCRIPTION:${PN}-predict = "Provides predictive text input functionality for the Rime input method engine."
LICENSE:${PN}-predict = "BSD-3-Clause"
FILES:${PN}-predict = "${libdir}/rime-plugins/librime-predict.so"
RDEPENDS:${PN}-predict += "${PN}"

