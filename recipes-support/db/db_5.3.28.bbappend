# Force-silence the specific K&R style warnings that are flooding the log
TARGET_CFLAGS += " -Wno-error=implicit-function-declaration -Wno-error=int-conversion -Wno-old-style-definition"
BUILD_CFLAGS  += " -Wno-error=implicit-function-declaration -Wno-error=int-conversion -Wno-old-style-definition"

# Some modern GCC versions also trip on pointer types here
TARGET_CFLAGS += " -Wno-error=incompatible-pointer-types"
BUILD_CFLAGS  += " -Wno-error=incompatible-pointer-types"

EXTRA_OECONF:append:class-native = " --disable-cryptography"
