SUMMARY = "Rime shared input method data"
DESCRIPTION = "Provides shared Rime input method schemas, dictionaries, and \
configuration data used by Rime frontends such as Fcitx5 Rime."
SECTION = "inputmethods"
HOMEPAGE = "https://github.com/rime/plum"

LICENSE = "LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

SRC_URI = " \
    git://github.com/rime/plum.git;branch=master;protocol=https;name=plum \
    file://rime-install-wrapper \
"
SRCREV_plum = "b1be1969f914cc005add4090631b855db00c2591"


EXTRA_OEMAKE:append = " no_update=1"
#  cat preset-packages.conf
# package_list+=(
#     bopomofo
#     cangjie
#     essay
#     luna-pinyin
#     prelude
#     quick
#     stroke
#     terra-pinyin
# )
SRC_URI:append = " \
    gitsm://github.com/rime/rime-bopomofo.git;protocol=https;name=bopomofo;nobranch=1;subdir=${S}/package/rime/bopomofo; \
    gitsm://github.com/rime/rime-cangjie.git;protocol=https;name=cangjie;nobranch=1;subdir=${S}/package/rime/cangjie; \
    gitsm://github.com/rime/rime-essay.git;protocol=https;name=essay;nobranch=1;subdir=${S}/package/rime/essay; \
    gitsm://github.com/rime/rime-luna-pinyin.git;protocol=https;name=luna-pinyin;nobranch=1;subdir=${S}/package/rime/luna-pinyin; \
    gitsm://github.com/rime/rime-prelude.git;protocol=https;name=prelude;nobranch=1;subdir=${S}/package/rime/prelude; \
    gitsm://github.com/rime/rime-quick.git;protocol=https;name=quick;nobranch=1;subdir=${S}/package/rime/quick; \
    gitsm://github.com/rime/rime-stroke.git;protocol=https;name=stroke;nobranch=1;subdir=${S}/package/rime/stroke; \
    gitsm://github.com/rime/rime-terra-pinyin.git;protocol=https;name=terra-pinyin;nobranch=1;subdir=${S}/package/rime/terra-pinyin; \
"


SRCREV_bopomofo = "6085c9a38a4a728047862b33d67eee18aa86f3b9"
SRCREV_cangjie = "fcb3d7dd94ac1304663d575727d24d8107f6b082"
SRCREV_essay = "d95eeae056df429ecf993038c325a216f0b28c5b"
SRCREV_luna-pinyin = "84685a7e7793e43278ca29368977b79d74d37a03"
SRCREV_prelude = "082425ea0684bca36474415d4a0e8db9b016487e"
SRCREV_quick = "207d1efcc0dc9574510b61be861544edcf993736"
SRCREV_stroke = "1e8fff9b9494ddec23b0cbc526bcfd8171a6fd48"
SRCREV_terra-pinyin = "723e51bc266cf9464530c1ddedb856aa18e3da34"

SRCREV_FORMAT = "plum_bopomofo_cangjie_essay_luna-pinyin_prelude_quick_stroke_terra-pinyin"

do_install() {
    oe_runmake install \
        DESTDIR="${D}" \
        RIME_DATA_DIR="${datadir}/rime-data"

    install -d ${D}${bindir}
    install -d ${D}${datadir}/rime-plum/scripts

    install -m 755 ${S}/rime-install ${D}${datadir}/rime-plum/
    install -m 755 ${S}/scripts/*.sh ${D}${datadir}/rime-plum/scripts/
    install -m 644 ${S}/*-packages.conf ${D}${datadir}/rime-plum/

    install -m 755 ${UNPACKDIR}/rime-install-wrapper ${D}${bindir}/rime-install
}

PACKAGES =+ "rime-plum"

FILES:${PN} += " \
    ${datadir}/rime-data/ \
"

FILES:rime-plum = " \
    ${bindir}/rime-install \
    ${datadir}/rime-plum/ \
"

LICENSE:rime-plum = "LGPL-3.0-only"
RDEPENDS:rime-plum = " \
    bash \
    git \
"


# cat extra-packages.conf
## Extra packages
# package_list+=(
#     array
#     cantonese
#     combo-pinyin
#     double-pinyin
#     emoji
#     ipa
#     jyutping
#     middle-chinese
#     pinyin-simp
#     scj
#     soutzoe
#     stenotype
#     wubi
#     wugniu
# )
# PACKAGECONFIG[extras] = ","










