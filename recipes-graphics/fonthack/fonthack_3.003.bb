SUMMARY = "Hack font (TTF) - prebuilt"
HOMEPAGE = "https://sourcefoundry.org/hack/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE.md;md5=b090613b0f65399acfdef2a8d6d33711"

SRC_URI = " \
    https://github.com/source-foundry/Hack/releases/download/v3.003/Hack-v3.003-ttf.zip;subdir=hack-ttf \
    file://LICENSE.md \
"

SRC_URI[sha256sum] = "0c2604631b1f055041c68a0e09ae4801acab6c5072ba2db6a822f53c3f8290ac"
S = "${WORKDIR}/hack-ttf"

inherit allarch fontcache

DEPENDS:append = " \
    fontconfig \
"

do_install() {
    install -d ${D}${datadir}/fonts/ttf/hack
    install -m 0644 ttf/*.ttf ${D}${datadir}/fonts/ttf/hack/
    install -d ${D}${datadir}/licenses/${PN}
}

FILES:${PN} += " \
    ${datadir}/fonts/ttf/hack/*.ttf \
    ${datadir}/licenses \
"

RDEPENDS:${PN} += " \
    fontconfig \
"

FONT_PACKAGES = "${PN}"
