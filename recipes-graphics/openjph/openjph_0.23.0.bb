SUMMARY = "Open source implementation of High-throughput JPEG2000 (HTJ2K)"
SECTION = "libs"
HOMEPAGE = "https://htj2k.com/"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8002b855d3824d38715cad3ec7c5603c"

inherit office_cmake pkgconfig

SRC_URI = "git://github.com/aous72/OpenJPH.git;protocol=https;branch=master"
SRCREV = "d10f2ca97448dffae2a8f95c07cf90ca3783cad0"

PACKAGECONFIG[shared]        = "-DBUILD_SHARED_LIBS=ON,-DBUILD_SHARED_LIBS=OFF"
PACKAGECONFIG[tiff]          = "-DOJPH_ENABLE_TIFF_SUPPORT=ON,-DOJPH_ENABLE_TIFF_SUPPORT=OFF,tiff"
PACKAGECONFIG[tests]         = "-DOJPH_BUILD_TESTS=ON,-DOJPH_BUILD_TESTS=OFF"
PACKAGECONFIG[tools]         = "-DOJPH_BUILD_EXECUTABLES=ON,-DOJPH_BUILD_EXECUTABLES=OFF"
PACKAGECONFIG[stream-expand] = "-DOJPH_BUILD_STREAM_EXPAND=ON,-DOJPH_BUILD_STREAM_EXPAND=OFF"

PACKAGECONFIG:append = " \
    tiff shared tools \
"

# host tools "broadly" portable.
EXTRA_OECMAKE:append:class-native = " -DOJPH_DISABLE_SIMD=ON"
EXTRA_OECMAKE:append:class-nativesdk = " -DOJPH_DISABLE_SIMD=ON"

# Example: OPENJPH_FORCE_DISABLE = "AVX AVX2 AVX512"
OPENJPH_FORCE_DISABLE ?= ""
EXTRA_OECMAKE:append = " ${@' '.join(['-DOJPH_DISABLE_%s=ON' % x for x in (d.getVar('OPENJPH_FORCE_DISABLE') or '').split()])}"

python () {
    import re
    arch = (d.getVar('TARGET_ARCH') or '').strip()
    dflags = ' '.join(filter(None, [
        d.getVar('TARGET_CFLAGS'),
        d.getVar('TUNE_CCARGS'),
        d.getVar('SELECTED_OPTIMIZATION'),
    ]))

    def has(opt):
        return re.search(r'(^|\s)%s(\s|$)' % re.escape(opt), dflags) is not None

    disables = set()

    if arch in ('x86', 'i586', 'i686', 'x86_64'):
        # AVX family only if explicitly requested
        if not has('-mavx'):
            disables.add('AVX')
        if not has('-mavx2'):
            disables.add('AVX2')
        if not any(has(x) for x in (
            '-mavx512f','-mavx512bw','-mavx512dq','-mavx512vl',
            '-mavx512ifma','-mavx512vbmi','-mavx512bitalg','-mavx512vnni','-mavx512vp2intersect'
        )):
            disables.add('AVX512')

        if arch == 'x86_64':
            if not any(has(x) for x in ('-msse4.1','-msse4.2','-msse4')):
                disables.add('SSE4')
        else:
            if not any(has(x) for x in ('-msse', '-msse2', '-msse3', '-mssse3', '-msse4', '-msse4.1', '-msse4.2')):
                disables.update(['SSE','SSE2','SSSE3','SSE4'])

    elif arch in ('arm', 'armeb'):
        if not re.search(r'-mfpu=[^ ]*neon', dflags):
            disables.add('NEON')

    # aarch64 already ... 

    if disables:
        d.appendVar('EXTRA_OECMAKE', ' ' + ' '.join(f'-DOJPH_DISABLE_{x}=ON' for x in sorted(disables)))
}


PACKAGE_BEFORE_PN = " ${PN}-tools"
FILES:${PN}-tools = " \
    ${bindir}/ojph_compress \
    ${bindir}/ojph_expand \
"

BBCLASSEXTEND = "native nativesdk"

