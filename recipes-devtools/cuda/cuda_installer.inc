SUMMARY = "NVIDIA Local Installer"
SECTION = "dev"
HOMEPAGE = "https://docs.nvidia.com/cuda/archive/13.3.1"

LICENSE = "LicenseRef-CUDA-EULA"
LIC_FILES_CHKSUM += "\
    file://LicenseRef-CUDA-EULA;md5=44d5644ecb3b510fe6c729ceb722a61e \
"

PV ?= "13.3.1"
PR ?= "610.43.02"

CUDA_TARGET_ARCH = "x86_64-linux"
SRC_URI = " \
    https://developer.download.nvidia.com/compute/cuda/${PV}/local_installers/cuda_${PV}_${PR}_linux.run \
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

# RRECOMMENDS:${PN} += " \
#     nvidia-open-gpu-kernel-modules \
# "

DEPENDS += "\
    ncurses \
"

do_unpack() {
    cp ${DL_DIR}/cuda_${PV}_${PR}_linux.run  ${UNPACKDIR}/cuda_${PV}_${PR}_linux.run
    chmod +x ${UNPACKDIR}/cuda_${PV}_${PR}_linux.run
    sh ${UNPACKDIR}/cuda_${PV}_${PR}_linux.run --extract=${UNPACKDIR}/cuda-${PV}
}

do_configure() {
   echo "Okay"
   ## we will use this stack to move debugging vs non etc around.
}

do_compile() {
    echo "Okay"
}

do_compile() {
    echo "Okay"
}

do_install:class-native() {
    install -d ${D}${bindir}
    install -d ${D}${sbindir}
    install -d ${D}${libdir}
    install -d ${D}${includedir}
    install -d ${D}${datadir}
    install -d ${D}${libexecdir}
    install -d ${D}${docdir}/${BPN}
    install -d ${D}${licensedir}/${BPN}


    install -m 0644 ${S}/EULA.txt ${D}${licensedir}/${BPN}/EULA.txt

    for component in ${PACKAGECONFIG} ${CUDA_SUPPORT_LIBS}; do
        source="${S}/${component}"
        [ -d "$source" ] || continue

        for entry in "$source"/*; do
            [ -e "$entry" ] || [ -L "$entry" ] || continue

            name="${entry##*/}"

            # Architecture-specific target payload is handled separately.
            [ "$name" = "targets" ] && continue

            # Avoid copying dangling include/lib64 symlinks into targets/.
            [ -L "$entry" ] && continue

            case "$name" in
                bin)
                    dest="${D}${bindir}"
                    ;;
                sbin)
                    dest="${D}${sbindir}"
                    ;;
                lib|lib64)
                    dest="${D}${libdir}"
                    ;;
                include)
                    dest="${D}${includedir}"
                    ;;
                share)
                    dest="${D}${datadir}"
                    ;;
                libexec)
                    dest="${D}${libexecdir}"
                    ;;
                doc|docs|DOCS)
                    dest="${D}${docdir}/${BPN}"
                    ;;
                LICENSE|LICENSE.txt)
                    dest="${D}${licensedir}/${BPN}/${component}"
                    ;;
                *)
                    dest="${D}${datadir}/cuda/${component}/${name}"
                    # dest="${D}${datadir}/cuda/${component}/${name}"
                    # Preserve other component-specific directories rather
                    # than silently discarding them.
                    install -d "$dest"
                    cp -a "$entry/." "$dest/"
                    continue
                    ;;
            esac

                if [ -d "$entry" ]; then
                    install -d "$dest"
                    cp -a "$entry/." "$dest/"
                elif [ -f "$entry" ]; then
                    install -d "$dest"
                    cp -a "$entry" "$dest/"
                fi
        done
    done
}

do_install() {
    install -d ${D}${bindir}
    install -d ${D}${libdir}
    install -d ${D}${includedir}
    install -d ${D}${datadir}
    install -d ${D}${docdir}/${BPN}

    install -m 0644 ${S}/EULA.txt ${D}${docdir}/${BPN}/EULA.txt

    install_cuda_target() {
        component="$1"
        source="${S}/${component}/targets/${CUDA_TARGET_ARCH}"

        if [ ! -d "$source" ]; then
            return 0
        fi

        if [ -d "$source/include" ]; then
            cp -a "$source/include/." "${D}${includedir}/"
        fi

        if [ -d "$source/lib" ]; then
            cp -a "$source/lib/." "${D}${libdir}/"
        fi
    }

    for component in ${CUDA_SUPPORT_LIBS}; do
        install_cuda_target "$component"
    done

    for component in ${PACKAGECONFIG}; do
        install_cuda_target "$component"
    done
}


LICENSE_FLAGS_ACCEPTED = "commercial"
COMPATIBLE_MACHINE:x86-64 = "(.*)"
BBCLASSEXTEND = "native"
