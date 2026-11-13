PACKAGES =+ " nvidia-systemd"

SUMMARY:nvidia-systemd = "NVIDIA GPU Driver Systemd Integration"
DESCRIPTION:nvidia-systemd = "NVIDIA systemd services, power management utilities, suspend and resume hooks, and D-Bus configuration for GPU driver integration."
SECTION:nvidia-systemd = "admin"

# License files from NVIDIA's extracted driver:
# LICENSE;md5=92aa2e2af6aa0bcba1c3fe49da021937
# supported-gpus/LICENSE;md5=3b4ecd9f96b20c7cdbabe7686cf9a35d
# LICENSE:nvidia-systemd = "UPDATE_ME"

FILES:nvidia-systemd = " \
    /usr/bin/nvidia-powerd \
    /usr/bin/nvidia-sleep.sh \
    /usr/lib/systemd/system-sleep/nvidia \
    /usr/lib/systemd/system/nvidia-hibernate.service \
    /usr/lib/systemd/system/nvidia-powerd.service \
    /usr/lib/systemd/system/nvidia-resume.service \
    /usr/lib/systemd/system/nvidia-suspend-then-hibernate.service \
    /usr/lib/systemd/system/nvidia-suspend.service \
    /usr/lib/systemd/system/systemd-hibernate.service.d/nvidia-suspend-nofreeze.conf \
    /usr/lib/systemd/system/systemd-hybrid-sleep.service.d/nvidia-suspend-nofreeze.conf \
    /usr/lib/systemd/system/systemd-suspend-then-hibernate.service.d/nvidia-suspend-nofreeze.conf \
    /usr/lib/systemd/system/systemd-suspend.service.d/nvidia-suspend-nofreeze.conf \
    /usr/share/dbus-1/system.d/nvidia-dbus.conf \
"

RDEPENDS:nvidia-systemd += " \
    bash \
"
