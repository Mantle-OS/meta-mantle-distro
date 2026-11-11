SUMMARY = "NVIDIA Local Installer"
SECTION = "dev"
HOMEPAGE = "https://docs.nvidia.com/cuda"

PV ?= "13.3.1"
PR ?= "610.43.02"

S = "${WORKDIR}/nvidia-installer"

inherit python3native

require cuda-package.inc

# later we will look at the other arm64 deal
CUDA_TARGET_ARCH = "x86_64-linux"
SRC_URI = " \
    https://developer.download.nvidia.com/compute/cuda/${PV}/local_installers/cuda_${PV}_${PR}_linux.run \
    file://nvidia_install_helper.py \
"

# 7c8d3eca60ee10d2c290bdc045f88f09 cuda_13.3.1_610.43.02_linux.run
SRC_URI[md5sum] = "7c8d3eca60ee10d2c290bdc045f88f09"
SRC_URI[sha256sum] = "9f98ec1f6c950401041d3f1308e221f0d5db8771a8e10569001b64caaee31a92"

## SBSA
# 90a4467e1ac192963b0713634d5a0e43 cuda_13.3.1_610.43.02_linux_sbsa.run

PACKAGECONFIG[cccl] = ""
PACKAGECONFIG[cuda_crt] = ""
PACKAGECONFIG[cuda_ctadvisor] = ""
PACKAGECONFIG[cuda_cudart] = ""
PACKAGECONFIG[cuda_culibos] = ""
PACKAGECONFIG[cuda_cuobjdump] = ""
PACKAGECONFIG[cuda_cupti] = ""
PACKAGECONFIG[cuda_cuxxfilt] = ""
PACKAGECONFIG[cuda_documentation] = ""
PACKAGECONFIG[cuda_gdb] = ""
PACKAGECONFIG[cuda_nvcc] = ""
PACKAGECONFIG[cuda_nvdisasm] = ""
PACKAGECONFIG[cuda_nvml_dev] = ""
PACKAGECONFIG[cuda_nvprune] = ""
PACKAGECONFIG[cuda_nvrtc] = ""
PACKAGECONFIG[cuda_nvtx] = ""
PACKAGECONFIG[cuda_opencl] = ""
PACKAGECONFIG[cuda_profiler_api] = ""
PACKAGECONFIG[cuda_sandbox_dev] = ""
PACKAGECONFIG[cuda_sanitizer_api] = ""
PACKAGECONFIG[cuda_tileiras] = ""

CUDA_SUPPORT_LIBS ?= "libcublas libcufft libcufile libcuobjclient libcurand libcusolver libcusparse libnpp libnvfatbin libnvjitlink libnvjpeg libnvptxcompiler libnvvm"

PACKAGECONFIG ?= "\
    cccl \
    cuda_crt \
    cuda_ctadvisor \
    cuda_cudart \
    cuda_culibos \
    cuda_cuobjdump \
    cuda_cupti \
    cuda_cuxxfilt \
    cuda_documentation \
    cuda_gdb \
    cuda_nvcc \
    cuda_nvdisasm \
    cuda_nvml_dev \
    cuda_nvprune \
    cuda_nvrtc \
    cuda_nvtx  \
    cuda_opencl  \
    cuda_profiler_api \
    cuda_sandbox_dev \
    cuda_sanitizer_api \
    cuda_tileiras \
"

RRECOMMENDS:${PN} += " \
    nvidia-open-gpu-kernel-modules \
"

# External libraries needed to expose target sysroot headers and shlibs.
DEPENDS:append = "\
    ncurses \
    numactl \
    python3-native \
    virtual/libc \
    gcc-runtime \
    libxml2 \
    gmp \
"
# for the python installer script
export CUDA_PACKAGES = "${PACKAGECONFIG}"
export CUDA_SUPPORT_LIBS
export CUDA_TARGET_ARCH

do_unpack() {
    cp ${DL_DIR}/cuda_${PV}_${PR}_linux.run  ${S}/cuda_${PV}_${PR}_linux.run
    chmod +x ${S}/cuda_${PV}_${PR}_linux.run
    sh ${S}/cuda_${PV}_${PR}_linux.run --extract=${S}/cuda-${PV}
}

do_configure() {
}

do_compile() {
   install -m 0644 ${THISDIR}/cuda/nvidia_install_helper.py ${S}/nvidia_install_helper.py
}

do_install() {
    python3 nvidia_install_helper.py \
        --source ${S}/cuda-${PV} \
        --mode target \
        --dest ${D} \
        --arch ${CUDA_TARGET_ARCH} \
        --prefix /usr/local/cuda-13.3

    # Scarthgap provides Python 3.12.  Remove NVIDIA CUDA-GDB variants linked against older Python versions.
    rm -f ${D}/usr/local/cuda-13.3/bin/cuda-gdb-python3.*


    #FIXME LATER check DISTRO_FEATURES FOR "multiarch" ....
    # Remove 32-bit Compute Sanitizer helpers from the x86-64 package.
    rm -rf ${D}/usr/local/cuda-13.3/compute-sanitizer/x86


    # FIXME: Restore GDS tools when the NVIDIA userspace driver is packaged.
    # These executables require libcuda.so.1.
    rm -f ${D}/usr/local/cuda-13.3/gds/tools/gdsio_verify
    rm -f ${D}/usr/local/cuda-13.3/gds/tools/gdsio
    rm -f ${D}/usr/local/cuda-13.3/gds/tools/gdscheck
    rm -f ${D}/usr/local/cuda-13.3/gds/tools/gds_stats
    rm -f ${D}/usr/local/cuda-13.3/gds/tools/gdscp
}


CUDA_INSANE_SKIP = " \
    already-stripped \
    ldflags \
    textrel \
    dev-so \
    libdir \
"
#     staticdev

COMPATIBLE_MACHINE:x86-64 = "(.*)"
BBCLASSEXTEND = "native"
