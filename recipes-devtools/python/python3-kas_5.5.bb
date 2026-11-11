SUMMARY = "This tool provides an easy mechanism to setup bitbake based projects."
HOMEPAGE = "https://github.com/siemens/kas"
AUTHOR = "Jan Kiszka <jan.kiszka@siemens.com>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=59d1d9bbba03d20fe570d1553ae8d879"

inherit setuptools3 pypi

SRC_URI[md5sum] = "5641163576347b3ab8a16f380c43cdf4"
SRC_URI[sha256sum] = "ae6b7be8d4396dabcbbef9f7d4fb3d4516b9f5038920cc7914d86e3ebe2ef687"

PYPI_PACKAGE = "kas"
