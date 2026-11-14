# Krdp needs x264 support
# vaapi support (hardware encoding)
PACKAGECONFIG:append = " \
    gpl \
    vaapi \
    theora \
    libopus \
    libvorbis \
    x264 \
    openssl \
"
