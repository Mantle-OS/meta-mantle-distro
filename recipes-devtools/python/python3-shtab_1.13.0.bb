SUMMARY = "Automatically generate shell tab completion scripts for Python CLI apps"
HOMEPAGE = "https://github.com/tqdm/shtab"
AUTHOR = "Casper da Costa-Luis <casper.dcl@physics.org>"

LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENCE;md5=ce309b747f3c978ff61cbec85c8430d9"
inherit pypi python_setuptools_build_meta

DEPENDS += "python3-setuptools-scm-native"


SRC_URI[md5sum] = "cf88f274584b4ad96a3c065bc08a5b9b"
SRC_URI[sha256sum] = "0a5be9d7ab2b4549e9a38252abcd5ecf5ec6813ecd80c6b06fa851bff55a5085"

PYPI_PACKAGE = "shtab"
