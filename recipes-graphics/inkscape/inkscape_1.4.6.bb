SUMMARY = "Inkscape is a Free and open source vector graphics editor"
HOMEPAGE = "https://inkscape.org/"
SECTION = "graphics"
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=46f815712c095f667139ef42f2270d57"

SRC_URI = "gitsm://gitlab.com/inkscape/inkscape.git;protocol=https;branch=1.4.x"
SRCREV = "ebf0e940d050842d608d57f6a04e6daaa5c65f37"
PV = "1.4.6"

inherit office_cmake pkgconfig gettext gtk-icon-cache bash-completion mime-xdg

DEPENDS = "\
    harfbuzz \
    pango \
    cairo \
    fontconfig \
    gsl \
    bdwgc \
    lcms \
    double-conversion \
    glib-2.0-native \
    libpng \
    potrace \
    gtk+3 gtkmm3 gdk-pixbuf  \
    boost \
    libxslt \
    libxml2 \
    zlib \
    lib2geom \
"

DEPENDS:append = " \
    libepoxy \
    freetype \
    glibmm \
    libjpeg-turbo \
    librevenge \
    libsigc++-2.0 \
    python3-native python3-cachecontrol python3-tinycss2 \
    libsoup-2.4 \
    aspell \
"
RDEPENS:${PN} += " \
    desktop-file-utils \
    hicolor-icon-theme \
"

PACKAGECONFIG ?= " \
    shared \
    gnu-readline \
    svg2 \
    openmp \
    poppler \
    imagemagick \
    libcdr \
    libvisio \
    libwebp \
    gspell \
    gsourceview \
    lcms \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '',d)} \
    jemalloc \
"

PACKAGECONFIG[shared] = "-DBUILD_SHARED_LIBS=ON, -DBUILD_SHARED_LIBS=OFF, "
PACKAGECONFIG[gnu-readline] = "-DWITH_GNU_READLINE=ON, -DWITH_GNU_READLINE=OFF, readline"
PACKAGECONFIG[svg2] = "-DWITH_SVG2=ON, -DWITH_SVG2=OFF, librsvg"
PACKAGECONFIG[openmp] = "-DWITH_OPENMP=ON, -DWITH_OPENMP=OFF, openmp"
PACKAGECONFIG[poppler] = "-DENABLE_POPPLER=ON -DENABLE_POPPLER_CAIRO=ON, \
                          -DENABLE_POPPLER=OFF -DENABLE_POPPLER_CAIRO=OFF, poppler"
PACKAGECONFIG[imagemagick] = "-DWITH_IMAGE_MAGICK=ON, -DWITH_IMAGE_MAGICK=OFF, imagemagick"
PACKAGECONFIG[libcdr] = "-DWITH_LIBCDR=ON, -DWITH_LIBCDR=OFF, libcdr"
PACKAGECONFIG[libvisio] = "-DWITH_LIBVISIO=ON, -DWITH_LIBVISIO=OFF, libvisio"
PACKAGECONFIG[libwebp] = "-DWITH_LIBWPG=ON, -DWITH_LIBWPG=OFF, libwebp"
PACKAGECONFIG[gspell] = "-DWITH_GSPELL=ON, -DWITH_GSPELL=OFF, gspell"
PACKAGECONFIG[gsourceview] = "-DWITH_GSOURCEVIEW=ON, -DWITH_GSOURCEVIEW=OFF, gtksourceview4"
PACKAGECONFIG[nls] = "-DWITH_NLS=ON, -DWITH_NLS=OFF, "
PACKAGECONFIG[lcms] = "-DENABLE_LCMS=ON, -DENABLE_LCMS=OFF, lcms"
PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, libx11"
PACKAGECONFIG[manpages-compressed] = "-DWITH_MANPAGE_COMPRESSION=ON, -DWITH_MANPAGE_COMPRESSION=OFF, gzip-native"

# Blocker needs a recipe.
# option(WITH_GRAPHICS_MAGICK "Compile with support of GraphicsMagick for raster extensions and image import resolution" ON)

PACKAGECONFIG[jemalloc] = "-DWITH_JEMALLOC=ON, -DWITH_JEMALLOC=OFF, jemalloc"
PACKAGECONFIG[testing] = "-DBUILD_TESTING=ON, -DBUILD_TESTING=OFF, "

EXTRA_OECMAKE:append = " -DWITH_INTERNAL_2GEOM=OFF "

# option(ENABLE_BINRELOC "Enable relocatable binaries" OFF)
FILES:${PN} += " \
    ${datadir}/metainfo \
    ${libdir}/inkscape/libinkscape_base.so.* \
"

FILES:${PN}-dev += " \
    ${libdir}/inkscape/libinkscape_base.so \
"
