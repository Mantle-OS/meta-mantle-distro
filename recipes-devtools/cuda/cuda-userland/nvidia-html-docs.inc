PACKAGES =+ " nvidia-html-docs"

SUMMARY:nvidia-html-docs = "NVIDIA Linux GPU Driver Documentation"
DESCRIPTION:nvidia-html-docs = "NVIDIA Linux GPU driver documentation, including the HTML user guide, installation and configuration instructions, supported GPU information, release notes, and licensing information."
SECTION:nvidia-html-docs = "doc"

# License files from NVIDIA's extracted driver:
# LICENSE;md5=92aa2e2af6aa0bcba1c3fe49da021937
# supported-gpus/LICENSE;md5=3b4ecd9f96b20c7cdbabe7686cf9a35d
# LICENSE:nvidia-html-docs = "UPDATE_ME"

FILES:nvidia-html-docs = " \
    /usr/share/doc/cuda-userland/LICENSE \
    /usr/share/doc/cuda-userland/NVIDIA_Changelog \
    /usr/share/doc/cuda-userland/README.txt \
    /usr/share/doc/cuda-userland/html/acknowledgements.html \
    /usr/share/doc/cuda-userland/html/addressingcapabilities.html \
    /usr/share/doc/cuda-userland/html/addtlresources.html \
    /usr/share/doc/cuda-userland/html/appendices.html \
    /usr/share/doc/cuda-userland/html/audiosupport.html \
    /usr/share/doc/cuda-userland/html/commonproblems.html \
    /usr/share/doc/cuda-userland/html/configlaptop.html \
    /usr/share/doc/cuda-userland/html/configmultxscreens.html \
    /usr/share/doc/cuda-userland/html/configtwinview.html \
    /usr/share/doc/cuda-userland/html/depth30.html \
    /usr/share/doc/cuda-userland/html/displaydevicenames.html \
    /usr/share/doc/cuda-userland/html/dma_issues.html \
    /usr/share/doc/cuda-userland/html/dpi.html \
    /usr/share/doc/cuda-userland/html/dynamicboost.html \
    /usr/share/doc/cuda-userland/html/dynamicpowermanagement.html \
    /usr/share/doc/cuda-userland/html/editxconfig.html \
    /usr/share/doc/cuda-userland/html/egpu.html \
    /usr/share/doc/cuda-userland/html/faq.html \
    /usr/share/doc/cuda-userland/html/flippingubb.html \
    /usr/share/doc/cuda-userland/html/framelock.html \
    /usr/share/doc/cuda-userland/html/gbm.html \
    /usr/share/doc/cuda-userland/html/glxsupport.html \
    /usr/share/doc/cuda-userland/html/gpunames.html \
    /usr/share/doc/cuda-userland/html/gsp.html \
    /usr/share/doc/cuda-userland/html/i2c.html \
    /usr/share/doc/cuda-userland/html/index.html \
    /usr/share/doc/cuda-userland/html/installationandconfiguration.html \
    /usr/share/doc/cuda-userland/html/installdriver.html \
    /usr/share/doc/cuda-userland/html/installedcomponents.html \
    /usr/share/doc/cuda-userland/html/introduction.html \
    /usr/share/doc/cuda-userland/html/kernel_open.html \
    /usr/share/doc/cuda-userland/html/kms.html \
    /usr/share/doc/cuda-userland/html/knownissues.html \
    /usr/share/doc/cuda-userland/html/minimumrequirements.html \
    /usr/share/doc/cuda-userland/html/newusertips.html \
    /usr/share/doc/cuda-userland/html/ngx.html \
    /usr/share/doc/cuda-userland/html/nvidia-debugdump.html \
    /usr/share/doc/cuda-userland/html/nvidia-ml.html \
    /usr/share/doc/cuda-userland/html/nvidia-peermem.html \
    /usr/share/doc/cuda-userland/html/nvidia-persistenced.html \
    /usr/share/doc/cuda-userland/html/nvidia-smi.html \
    /usr/share/doc/cuda-userland/html/nvidiasettings.html \
    /usr/share/doc/cuda-userland/html/nvpresent.html \
    /usr/share/doc/cuda-userland/html/openglenvvariables.html \
    /usr/share/doc/cuda-userland/html/optimus.html \
    /usr/share/doc/cuda-userland/html/powermanagement.html \
    /usr/share/doc/cuda-userland/html/primerenderoffload.html \
    /usr/share/doc/cuda-userland/html/procinterface.html \
    /usr/share/doc/cuda-userland/html/profiles.html \
    /usr/share/doc/cuda-userland/html/programmingmodes.html \
    /usr/share/doc/cuda-userland/html/randr14.html \
    /usr/share/doc/cuda-userland/html/retpoline.html \
    /usr/share/doc/cuda-userland/html/selectdriver.html \
    /usr/share/doc/cuda-userland/html/sli.html \
    /usr/share/doc/cuda-userland/html/supportedchips.html \
    /usr/share/doc/cuda-userland/html/vdpausupport.html \
    /usr/share/doc/cuda-userland/html/wayland-issues.html \
    /usr/share/doc/cuda-userland/html/xcompositeextension.html \
    /usr/share/doc/cuda-userland/html/xconfigoptions.html \
    /usr/share/doc/cuda-userland/html/xrandrextension.html \
    /usr/share/doc/cuda-userland/html/xwayland.html \
    /usr/share/doc/cuda-userland/pkg-history.txt \
    /usr/share/doc/cuda-userland/supported-gpus/LICENSE \
    /usr/share/doc/cuda-userland/supported-gpus/supported-gpus.json \
"

RDEPENDS:nvidia-html-docs = ""
