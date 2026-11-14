SUMMARY = "Office stack (apps, filters & runtime support) for Yocto"
DESCRIPTION = "Meta-package that pulls in document apps, a wide set of import/export filters, \
and common runtime support libraries used by office/document-processing tools."
LICENSE = "MIT"
PR = "r1"

PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup

ALLOW_EMPTY:${PN} = "1"

PACKAGES = " \
    ${PN} \
    ${PN}-filters \
    ${PN}-document-processing \
    ${PN}-graphics \
"

RRECOMMENDS:${PN} = "\
    ${PN}-filters \
"

SUMMARY:${PN}-filters = "Import/export filters for many office and legacy formats"
DESCRIPTION:${PN}-filters = "Libraries that add support for reading/writing formats such as AbiWord, Corel, ePub, iWork, FreeHand, GLTF assets, MS Publisher, MacWrite, ODF generators, PageMaker, Visio, WordPerfect, WPG and WPS."
RRECOMMENDS:${PN}-filters = " \
    libabw \
    libcdr \
    libe-book \
    libetonyek \
    libfreehand \
    libgltf \
    libmspub \
    libmwaw \
    libodfgen \
    libpagemaker \
    libvisio \
    libwpd \
    libwpg \
    libwps \
    libqxp \
    libzmf \
"    
    
SUMMARY:${PN}-document-processing = "Common runtime support libraries for document processing"
DESCRIPTION:${PN}-document-processing = "Helper libs used by the office stack: search/indexing, number conversion, shaping, math view, hyphenation, language tagging, text categorization, RDF, optimization, vectorization, etc."
RRECOMMENDS:${PN}-document-processing = " \
    clucene-core \
    double-conversion \
    graphite2 \
    gtkmathview \
    hyphen \
    libcmis \
    libexttextcat \
    liblangtag \
    librevenge \
    lpsolve \
    libmdds \
    mythes \
    potrace \
    rasqal redland \
    t1lib \
    wvware-tools \
    xlnt \
    ebook-tools \
    podofo \
"

SUMMARY:${PN}-graphics = "Various graphics libraies and programs like inkscape"
DESCRIPTION:${PN}-graphics = "Small set of graphics packages for the office suite"
RRECOMMENDS:${PN}-graphics = " \
    lib2geom \
    colm \
    inkscape \
    scribus \
"

