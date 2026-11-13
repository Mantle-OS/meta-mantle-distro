SUMMARY = "OpenBLAS is an optimized BLAS (Basic Linear Algebra Subprograms) library based on GotoBLAS2 1.13 BSD version."

DESCRIPTION = "OpenBLAS implements low-level routines for performing linear algebra operations such as vector addition, scalar multiplication, dot products, linear combinations, and matrix multiplication. \
  OpenBLAS makes these routines available on multiple platforms, covering server, desktop and mobile operating systems, as well as different architectures including x86, ARM, MIPS, PPC, RISC-V, and zarch."

HOMEPAGE = "http://www.openmathlib.org/OpenBLAS"
SECTION = "libs"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=5adf4792c949a00013ce25d476a2abc0"

inherit cmake cmake_extra_sanity

SRC_URI += " git://github.com/OpenMathLib/OpenBLAS.git;protocol=https;branch=develop; "
SRCREV = "e0166008be8e466242aa76b2ff75ce3f0fbf574a"
PV = "0.3.34"

S = "${WORKDIR}/git"

DEPENDS += " \
    openmp \
"

PACKAGECONFIG[shared] = " -DBUILD_SHARED_LIBS=ON -DBUILD_STATIC_LIBS=OFF, -DBUILD_STATIC_LIBS=ON -DBUILD_SHARED_LIBS=OFF,"
PACKAGECONFIG[cblas] = " -DBUILD_WITHOUT_CBLAS=OFF, -DBUILD_WITHOUT_CBLAS=ON,"
PACKAGECONFIG[lapack] = " -DBUILD_WITHOUT_LAPACK=OFF, -DBUILD_WITHOUT_LAPACK=ON,"
PACKAGECONFIG[lapacke] = " -DBUILD_WITHOUT_LAPACKE=OFF, -DBUILD_WITHOUT_LAPACKE=ON,"
PACKAGECONFIG[lapack-depr] = " -DBUILD_LAPACK_DEPRECATED=ON, -DBUILD_LAPACK_DEPRECATED=OFF,"
PACKAGECONFIG[tests] = " -DBUILD_TESTING=ON, -DBUILD_TESTING=OFF,"
PACKAGECONFIG[benchmarks] = " -DBUILD_BENCHMARKS=ON, -DBUILD_BENCHMARKS=OFF,"
PACKAGECONFIG[c-lapack] = " -DC_LAPACK=ON, -DC_LAPACK=OFF,"
PACKAGECONFIG[dynamic-arch] = "-DDYNAMIC_ARCH=ON, -DDYNAMIC_ARCH=OFF,"
PACKAGECONFIG[dynamic-older] = "-DDYNAMIC_OLDER=ON, -DDYNAMIC_OLDER=OFF,"
PACKAGECONFIG[relapack] = "-DBUILD_RELAPACK=ON, -DBUILD_RELAPACK=OFF,"
PACKAGECONFIG[locking] = "-DUSE_LOCKING=ON, -DUSE_LOCKING=OFF,"
PACKAGECONFIG[perl] = "-DUSE_PERL=ON, -DUSE_PERL=OFF,"
PACKAGECONFIG[warmup] = "-DNO_WARMUP=ON, -DNO_WARMUP=OFF,"
PACKAGECONFIG[fixed-libname] = "-DFIXED_LIBNAME=ON, -DFIXED_LIBNAME=OFF,"

LAPACK_STRLEN ?= ""
EXTRA_OECMAKE:append = " \
    ${@' -DLAPACK_STRLEN:STRING=${LAPACK_STRLEN}' if d.getVar('LAPACK_STRLEN') else ''} \
"

CPU_TARGET:corei7-64 = "NEHALEM"
CPU_TARGET:corei9-64 = "HASWELL"
# BARCELONA
CPU_TARGET:arm64 = "ARMV8"
CPU_TARGET:arm = "ARMV7"
CPU_TARGET:powerpc = "POWER6"

CPU_TARGET ?= ""
EXTRA_OECMAKE:append = " \
    ${@' -DTARGET=${CPU_TARGET}' if d.getVar('CPU_TARGET') else ''} \
"

PACKAGECONFIG ?= " \
    shared \
    cblas \
    lapack \
    dynamic-arch \
    warmup \
"
#     fixed-libname

BBCLASSEXTEND = "native nativesdk"
