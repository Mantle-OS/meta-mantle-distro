SUMMARY = "Mantle networking tools"
DESCRIPTION = "Network utilities and tools"
LICENSE = "MIT"
PR = "r0"

PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup

RDEPENDS:${PN} = " \
    ${VIRTUAL-RUNTIME_network_manager} \
    dhcpcd netbase \
    wget curl rsync \
    iputils-ping \
"
