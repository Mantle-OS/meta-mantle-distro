SUMMARY = "Mantle SND and VID"
DESCRIPTION = "Mantle build time multimedia stack"
LICENSE = "MIT"
PR = "r0"

PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup

PACKAGES = " \
    ${PN} \
    ${PN}-alsa \
    ${PN}-pipewire \
    ${PN}-jack \
    ${PN}-pulseaudio \
    ${PN}-ffmpeg \
    ${PN}-gstreamer \
"

# SND DEV
PACKAGECONFIG[alsa] = ""

PACKAGECONFIG[pipewire] = ""
PACKAGECONFIG[jack] = ""
PACKAGECONFIG[pulseaudio] = ""

PACKAGECONFIG[ffmpeg] = ""
PACKAGECONFIG[gstreamer] = ""

# SND CODEC LAND
PACKAGECONFIG[opus] = ""
PACKAGECONFIG[flac] = ""
PACKAGECONFIG[ogg] = ""
PACKAGECONFIG[vorbis] = ""
PACKAGECONFIG[wavpack] = ""

# Vid CODEC LAND
PACKAGECONFIG[x264] = ""
PACKAGECONFIG[x265] = ""
PACKAGECONFIG[vpx] = ""
PACKAGECONFIG[aom] = ""
PACKAGECONFIG[dav1d] = ""
PACKAGECONFIG[theora] = ""


PACKAGECONFIG ?= "\
    ogg opus x264 \
    ${@bb.utils.contains('DISTRO_FEATURES', 'alsa', 'alsa', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'jack', 'jack', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'pipewire', 'pipewire', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'gstreamer', 'gstreamer', '', d)} \
"


SUMMARY:${PN}-alsa = "ALSA audio runtime"
DESCRIPTION:${PN}-alsa = "ALSA libraries, mixer and playback utilities"
LICENSE:${PN}-alsa = "MIT"
RDEPENDS:${PN}-alsa = " \
    alsa-lib \
    alsa-utils \
    alsa-plugins \
    alsa-tools \
"

SUMMARY:${PN}-pipewire = "PipeWire multimedia server"
DESCRIPTION:${PN}-pipewire = "PipeWire audio routing and session management"
RDEPENDS:${PN}-pipewire = " \
    pipewire \
    wireplumber \
"

SUMMARY:${PN}-jack = "JACK audio"
DESCRIPTION:${PN}-jack = "JACK low-latency audio infrastructure"
RDEPENDS:${PN}-jack = " \
    jack \
"

SUMMARY:${PN}-pulseaudio = ""
DESCRIPTION:${PN}-pulseaudio = "FIXME"
LICENSE:${PN}-pulseaudio = "MIT"
RDEPENDS:${PN}-pulseaudio = " \
    pulseaudio \
"

SUMMARY:${PN}-gstreamer = "GStreamer multimedia framework"
DESCRIPTION:${PN}-gstreamer = "GStreamer framework, plugins and multimedia tools"
RDEPENDS:${PN}-gstreamer = " \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-good \
    gstreamer1.0-plugins-bad \
    gstreamer1.0-plugins-ugly \
    gstreamer1.0-libav \
"


RDEPENDS${PN} += "\
    ${@bb.utils.contains('PACKAGECONFIG', 'alsa', '${PN}-alsa', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'pipewire', '${PN}-pipewire', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'jack', '${PN}-jack', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'pulseaudio', '${PN}-pulseaudio', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'gstreamer', '${PN}-gstreamer', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'opus', 'libopus', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'flac', 'flac', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'ogg', 'libogg', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'vorbis', 'libvorbis', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'wavpack', 'wavpack', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'x264', 'x264', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'x265', 'x265', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'vpx', 'libvpx', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'aom', 'aom', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'dav1d', 'dav1d', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'theora', 'libtheora', '', d)} \
"
