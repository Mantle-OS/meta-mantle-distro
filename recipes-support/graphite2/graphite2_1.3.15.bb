SUMMARY = "Font rendering capabilities for complex non-Roman writing systems"
HOMEPAGE = "http://sourceforge.net/projects/silgraphite"
SECTION = "text"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b0452d508cc4eb104de0226a5b0c8786"

inherit office_cmake

DEPENDS += "freetype"

SRC_URI = "git://github.com/silnrsi/graphite.git;branch=master;protocol=https"
SRCREV = "ca8d821e60a15b6c24e404c9086992c975d8e1cf"
PV = "1.3.15"

EXTRA_OECMAKE += "-DLIB_SUFFIX=${@d.getVar('baselib').replace('lib', '')}"

BBCLASSEXTEND = "native"
