SUMMARY = "OpenSSL 3 runtime ABI compatibility libraries"
DESCRIPTION = "Co-installable OpenSSL 3 shared libraries for applications requiring libcrypto.so.3 or libssl.so.3."
HOMEPAGE = "https://www.openssl.org/"
SECTION = "libs/network"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=c75985e733726beaba57bc5253e96d04"

SRC_URI = "https://www.openssl.org/source/openssl-${PV}.tar.gz \
           file://0001-buildinfo-strip-sysroot-and-debug-prefix-map-from-co.patch \
           file://0001-Configure-do-not-tweak-mips-cflags.patch \
"
SRC_URI[sha256sum] = "603f5602e2eef00d77fbd429d34dcd5822bb301757a1bc9cdb24c670f1eb859a"

S = "${UNPACKDIR}/openssl-${PV}"
B = "${WORKDIR}/build"
do_configure[cleandirs] = "${B}"

inherit perlnative

EXTRA_OECONF = "no-tests"
EXTRA_OECONF:append:libc-musl = " no-async"
EXTRA_OECONF:append:libc-musl:powerpc64 = " no-asm"
EXTRA_OECONF:append:toolchain-clang:x86 = " -latomic"
DEPRECATED_CRYPTO_FLAGS ?= ""

EXTRA_OECONF:remove = "--disable-static"

do_configure () {
	# When we upgrade glibc but not uninative we see obtuse failures in openssl. Make
	# the issue really clear that perl isn't functional due to symbol mismatch issues.
	cat <<- EOF > ${WORKDIR}/perltest
	#!/usr/bin/env perl
	use POSIX;
	EOF
	chmod a+x ${WORKDIR}/perltest
	${WORKDIR}/perltest

	os=${HOST_OS}
	case $os in
	linux-gnueabi |\
	linux-gnuspe |\
	linux-musleabi |\
	linux-muslspe |\
	linux-musl )
		os=linux
		;;
	*)
		;;
	esac
	target="$os-${HOST_ARCH}"
	case $target in
	linux-arc | linux-microblaze*)
		target=linux-latomic
		;;
	linux-arm*)
		target=linux-armv4
		;;
	linux-aarch64*)
		target=linux-aarch64
		;;
	linux-i?86 | linux-viac3)
		target=linux-x86
		;;
	linux-gnux32-x86_64 | linux-muslx32-x86_64 )
		target=linux-x32
		;;
	linux-gnu64-x86_64)
		target=linux-x86_64
		;;
	linux-loongarch64)
		target=linux64-loongarch64
		;;
	linux-mips | linux-mipsel)
		# specifying TARGET_CC_ARCH prevents openssl from (incorrectly) adding target architecture flags
		target="linux-mips32 ${TARGET_CC_ARCH}"
		;;
	linux-gnun32-mips*)
		target=linux-mips64
		;;
	linux-*-mips64 | linux-mips64 | linux-*-mips64el | linux-mips64el)
		target=linux64-mips64
		;;
	linux-nios2* | linux-sh3 | linux-sh4 | linux-arc*)
		target=linux-generic32
		;;
	linux-powerpc)
		target=linux-ppc
		;;
	linux-powerpc64)
		target=linux-ppc64
		;;
	linux-powerpc64le)
		target=linux-ppc64le
		;;
	linux-riscv32)
		target=linux32-riscv32
		;;
	linux-riscv64)
		target=linux64-riscv64
		;;
	linux-sparc | linux-supersparc)
		target=linux-sparcv9
		;;
	mingw32-x86_64)
		target=mingw64
		;;
	esac

	# WARNING: do not set compiler/linker flags (-I/-D etc.) in EXTRA_OECONF, as they will fully replace the
	# environment variables set by bitbake. Adjust the environment variables instead.
	PERLEXTERNAL="$(realpath ${S}/external/perl/Text-Template-*/lib)"
	test -d "$PERLEXTERNAL" || bberror "PERLEXTERNAL '$PERLEXTERNAL' not found!"
	HASHBANGPERL="/usr/bin/env perl" PERL=perl PERL5LIB="$PERLEXTERNAL" \
	perl ${S}/Configure ${EXTRA_OECONF} ${DEPRECATED_CRYPTO_FLAGS} shared --prefix=${prefix} --openssldir=${libdir}/ssl-3-compat --libdir=${baselib} $target
	perl ${B}/configdata.pm --dump
}

# Install ONLY the runtime SONAME libraries. Do not install OpenSSL tools,
# headers, pkg-config files, providers, engines, or configuration: they are
# owned by the system OpenSSL 4 recipe.
do_install() {
    install -d ${D}${libdir}
    install -m 0755 ${B}/libcrypto.so.3 ${D}${libdir}/libcrypto.so.3
    install -m 0755 ${B}/libssl.so.3 ${D}${libdir}/libssl.so.3
}



PACKAGES =+ " \
    ${PN}-libcrypto \
    ${PN}-libssl \
"

SUMMARY:${PN}-libcrypto = "OpenSSL 3 Cryptographic Runtime Compatibility Library"
DESCRIPTION:${PN}-libcrypto = "Provides the OpenSSL 3 libcrypto.so.3 shared library for applications requiring the OpenSSL 3 cryptographic ABI. Coexists with the system OpenSSL 4 libraries without replacing their development headers, tools, or runtime interfaces."
FILES:${PN}-libcrypto = "${libdir}/libcrypto.so.3"

SUMMARY:${PN}-libssl = "OpenSSL 3 TLS Runtime Compatibility Library"
DESCRIPTION:${PN}-libssl = "Provides the OpenSSL 3 libssl.so.3 shared library for applications requiring the OpenSSL 3 SSL and TLS ABI. Allows existing binaries to run alongside applications using the system OpenSSL 4 libraries."
FILES:${PN}-libssl = "${libdir}/libssl.so.3"

RDEPENDS:${PN}-libssl = "${PN}-libcrypto"

RDEPENDS:${PN}-libssl = "${PN}-libcrypto"

CVE_PRODUCT = "openssl:openssl"
