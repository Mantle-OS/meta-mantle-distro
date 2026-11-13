SUMMARY = "NVIDIA Local Toolkit Installer"
SECTION = "dev"

require cuda.inc
require cuda-package.inc
SRC_URI:append = " file://nvidia_install_helper.py"

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

CUDA_SUPPORT_LIBS ?= " \
    libcublas \
    libcufft \
    libcufile \
    libcuobjclient \
    libcurand \
    libcusolver \
    libcusparse \
    libnpp \
    libnvfatbin \
    libnvjitlink \
    libnvjpeg \
    libnvptxcompiler \
    libnvvm \
"

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

RRECOMMENDS:${PN}:class-target += " \
    nvidia-open-gpu-kernel-modules \
"

DEPENDS:append = " \
    python3-native \
"

DEPENDS:append:class-target = " \
    ncurses \
    numactl \
    libxml2 \
    gmp \
"

RDEPENDS:${PN}:append:class-target = " \
    glibc \
    libgcc \
    libstdc++ \
    libxml2 \
"


# for the python installer script
export CUDA_PACKAGES = "${PACKAGECONFIG}"
export CUDA_SUPPORT_LIBS
export CUDA_TARGET_ARCH


python __anonymous() {
    packages = d.getVar("PACKAGES").split()
    skips = d.getVar("CUDA_INSANE_SKIP")

    for package in packages:
        d.appendVar("INSANE_SKIP:" + package, " " + skips)
}


do_configure:append(){
    ## moves over othe python for the install step.
    install -m 0644 ${THISDIR}/cuda-toolkit/nvidia_install_helper.py ${S}/nvidia_install_helper.py
}

do_install:append() {
    # this scipt handles the toolkit
    python3 nvidia_install_helper.py \
        --source ${S}/cuda-${PV} \
        --mode target \
        --dest ${D} \
        --arch ${CUDA_TARGET_ARCH} \
        --prefix /usr/local/cuda-13.3


    install -d ${D}${datadir}/doc/cuda/
    mv ${D}/cuda-manifest.json ${D}${datadir}/doc/cuda/manifest.json
    mv ${D}/usr/local/cuda-13.3/EULA.txt ${D}${datadir}/doc/cuda/EULA.txt
    mv ${D}/usr/local/cuda-13.3/version.json  ${D}${datadir}/doc/cuda/version.json


    # Scarthgap provides Python 3.12.  Remove NVIDIA CUDA-GDB variants linked against older Python versions.
    rm -f ${D}/usr/local/cuda-13.3/bin/cuda-gdb-python3.*

    #FIXME LATER check DISTRO_FEATURES FOR "multiarch" ....
    # Remove 32-bit Compute Sanitizer helpers from the x86-64 package.
    rm -rf ${D}/usr/local/cuda-13.3/compute-sanitizer/x86

}


FILES:${PN} += " \
    ${CUDA_INSTALL_PREFIX}/bin/cuda-uninstaller \
    ${CUDA_INSTALL_PREFIX}/include \
    ${CUDA_INSTALL_PREFIX}/lib64 \
"

FILES:${PN}-doc += " \
    ${datadir}/doc/cuda/* \
"

SYSROOT_DIRS:append = " ${CUDA_INSTALL_PREFIX}"


SYSROOT_PREPROCESS_FUNCS:append:class-native = " cuda_native_sysroot_relocate"
cuda_native_sysroot_relocate() {
    install -d ${SYSROOT_DESTDIR}${STAGING_DIR_NATIVE}/usr/local

    mv ${SYSROOT_DESTDIR}${CUDA_INSTALL_PREFIX} \
       ${SYSROOT_DESTDIR}${STAGING_DIR_NATIVE}${CUDA_INSTALL_PREFIX}
}


## add the stub for the user land driver that is in the otehr package
SYSROOT_PREPROCESS_FUNCS:append:class-target = " cuda_target_sysroot_stubs"
cuda_target_sysroot_stubs() {
    install -d ${SYSROOT_DESTDIR}${CUDA_INSTALL_PREFIX}/targets/x86_64-linux/lib/stubs

    ln -sfn libcuda.so \
        ${SYSROOT_DESTDIR}${CUDA_INSTALL_PREFIX}/targets/x86_64-linux/lib/stubs/libcuda.so.1
}


inherit update-alternatives
ALTERNATIVE:${PN} = "cuda"
ALTERNATIVE_LINK_NAME[cuda] = "/usr/local/cuda"
ALTERNATIVE_TARGET[cuda] = "/usr/local/cuda-13.3"
ALTERNATIVE_PRIORITY[cuda] = "133"

BBCLASSEXTEND = "native"
