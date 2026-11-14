SUMMARY = "A collection of multi-dimensional data structures and indexing algorithms"
HOMEPAGE = "https://gitlab.com/mdds/mdds"
SECTION = "text"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=60a6093677ded88b5e28677e52a0c011"

inherit pkgconfig autotools-brokensep 

SRC_URI = " \
    git://gitlab.com/mdds/mdds.git;protocol=https;branch=master \
    file://0001-configure.ac-remove-fixed-paths-causing-trouble-when.patch \
"
SRCREV = "2f428361ac9914e9639f7dbc9cff285d7e1f6b2a"

PV = "3.1.0"

PACKAGECONFIG ?= ""
PACKAGECONFIG[docs] = "--enable-docs, , doxygen-native graphviz-native python3-sphinx-native"
PACKAGECONFIG[use_openmp] = "--enable-openmp, , openmp"

## https://gitlab.com/mdds/mdds/-/blob/master/.gitlab-ci.yml?ref_type=heads#L21
CXXFLAGS:append = " -Wunused-parameter"

DEPENDS = "boost"

FILES:${PN}-doc += "${docdir}/*"

BBCLASSEXTEND = "native"
