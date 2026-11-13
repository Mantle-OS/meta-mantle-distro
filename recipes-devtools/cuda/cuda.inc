SUMMARY ?= "NVIDIA Local Installer"
SECTION ?= "dev"
HOMEPAGE ?= "https://docs.nvidia.com/cuda"

LICENSE = "LicenseRef-CUDA-EULA"
LICENSE_FLAGS = "nvidia_cuda"
LICENSE_FLAGS_DETAILS[nvidia_cuda] = "NVIDIA CUDA Toolkit requires acceptance of the NVIDIA SDK license agreement. Review https://docs.nvidia.com/cuda/eula/ and add nvidia_cuda to LICENSE_FLAGS_ACCEPTED to enable this recipe."
LIC_FILES_CHKSUM = "file://cuda-${PV}/EULA.txt;md5=a3c52c1f3e8953cbfbc50639ad4b63e1"

# How to accept remove before push obv  just for testing atm
LICENSE_FLAGS_ACCEPTED:append = " nvidia_cuda"

PV = "13.3.1"
PR = "610.43.02"

S = "${WORKDIR}/nvidia-installer"

inherit python3native

# later we will look at the other arm64 deal
CUDA_TARGET_ARCH = "x86_64-linux"
SRC_URI += " \
    https://developer.download.nvidia.com/compute/cuda/${PV}/local_installers/cuda_${PV}_${PR}_linux.run \
"

# X86_64  7c8d3eca60ee10d2c290bdc045f88f09 cuda_13.3.1_610.43.02_linux.run
SRC_URI[md5sum] = "7c8d3eca60ee10d2c290bdc045f88f09"
SRC_URI[sha256sum] = "9f98ec1f6c950401041d3f1308e221f0d5db8771a8e10569001b64caaee31a92"

CUDA_INSANE_SKIP ?= " \
    already-stripped \
    ldflags \
    textrel \
    dev-so \
    libdir \
"


## SBSA
# 90a4467e1ac192963b0713634d5a0e43 cuda_13.3.1_610.43.02_linux_sbsa.run

do_unpack() {
    # Handle the Toolkit
    MAIN_RUN_FILE="${S}/cuda_${PV}_${PR}_linux.run"
    install -d ${S}

    cp ${DL_DIR}/cuda_${PV}_${PR}_linux.run  ${MAIN_RUN_FILE}

    chmod +x ${MAIN_RUN_FILE}
    sh ${MAIN_RUN_FILE} --extract=${S}/cuda-${PV}
    test -d ${S}/cuda-${PV}

    # Handle the 2nd stage userland binaries
    ULAND_RUN_FILE="${S}/cuda-${PV}/NVIDIA-Linux-x86_64-${PR}.run"
    cd ${S}
    test -f ${ULAND_RUN_FILE}
    chmod +x ${ULAND_RUN_FILE}
    sh ${ULAND_RUN_FILE} --extract-only

    ULAND_DIR="${S}/NVIDIA-Linux-x86_64-${PR}"
    test -d ${ULAND_DIR}
    cd -

    # "stage" stage 3 the "closed" kernel drivers.
    # delete the kernel-open as that is built in the bsp->recipes-kernel->nevidia-open-kernel-modules
    mv ${ULAND_DIR}/kernel/ ${S}/
    rm -rf ${ULAND_DIR}/kernel-open/
}

do_configure() {
    echo "Overrided or append in parent please :) "
}

do_compile() {
    echo "Overrided or append in parent please :) "
}

do_install() {
    echo "Overrided or append in parent please :) "
}

COMPATIBLE_MACHINE:x86-64 = "(.*)"

