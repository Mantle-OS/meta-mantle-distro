PACKAGES =+ " nvidia-firmware"

SUMMARY:nvidia-firmware = "NVIDIA GPU System Processor Firmware"
DESCRIPTION:nvidia-firmware = "Proprietary NVIDIA GPU System Processor (GSP) firmware and supporting microcode required by NVIDIA Linux GPU kernel drivers."
SECTION:nvidia-firmware = "kernel/firmware"

# License files from NVIDIA's extracted driver:
# LICENSE;md5=92aa2e2af6aa0bcba1c3fe49da021937
# supported-gpus/LICENSE;md5=3b4ecd9f96b20c7cdbabe7686cf9a35d
# LICENSE:nvidia-firmware = "UPDATE_ME"

FILES:nvidia-firmware = " \
    ${nonarch_base_libdir}/firmware/nvidia/${PR} \
"

RDEPENDS:nvidia-firmware = ""

INSANE_SKIP:nvidia-firmware += "arch"
