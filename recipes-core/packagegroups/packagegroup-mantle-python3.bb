SUMMARY:${PN} = "Mantle self-hosted Python tools"
DESCRIPTION: = "Python runtime, modules and build tools"
LICENSE = "MIT"
PR = "r1"

PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup

RDEPENDS:${PN} = " \
    python3 \
    python3-pip \
    python3-setuptools \
    python3-pylint \
    python3-wheel \
    python3-distro \
    python3-pyyaml \
    python3-jsonschema \
    python3-jinja2 \
"
