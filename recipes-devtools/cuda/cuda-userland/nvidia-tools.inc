PACKAGES =+ " nvidia-tools"

SUMMARY:nvidia-tools = "NVIDIA GPU Management and Configuration Utilities"
DESCRIPTION:nvidia-tools = "NVIDIA userspace utilities for GPU monitoring, diagnostics, driver configuration, persistence management, application profiles, and supporting administrative tools."
SECTION:nvidia-tools = "admin"

# License files from NVIDIA's extracted driver:
# LICENSE;md5=92aa2e2af6aa0bcba1c3fe49da021937
# supported-gpus/LICENSE;md5=3b4ecd9f96b20c7cdbabe7686cf9a35d
# LICENSE:nvidia-tools = "UPDATE_ME"

FILES:nvidia-tools = " \
    /usr/bin/nvidia-bug-report.sh \
    /usr/bin/nvidia-debugdump \
    /usr/bin/nvidia-modprobe \
    /usr/bin/nvidia-ngx-updater \
    /usr/bin/nvidia-persistenced \
    /usr/bin/nvidia-settings \
    /usr/bin/nvidia-smi \
    /usr/bin/nvidia-xconfig \
    /usr/lib/libnvidia-gtk2.so.610.43.02 \
    /usr/lib/libnvidia-gtk3.so.610.43.02 \
    /usr/share/applications/nvidia-settings.desktop \
    /usr/share/man/man1/nvidia-modprobe.1.gz \
    /usr/share/man/man1/nvidia-persistenced.1.gz \
    /usr/share/man/man1/nvidia-settings.1.gz \
    /usr/share/man/man1/nvidia-smi.1.gz \
    /usr/share/man/man1/nvidia-xconfig.1.gz \
    /usr/share/nvidia/nvidia-application-profiles-610.43.02-key-documentation \
    /usr/share/nvidia/nvidia-application-profiles-610.43.02-rc \
    /usr/share/nvidia/nvoptix.bin \
    /usr/share/pixmaps/nvidia-settings.png \
"

# cario
DEPENDS:nvidia-tools +=  " gtk+3"
RDEPENDS:nvidia-tools = " \
    gtk+3 \
    gtk+ \
    libx11 libx11-xcb \
    libxext \
    gdk-pixbuf \
    glib-2.0 \
    at-spi2-core \
    pango cairo \
"
