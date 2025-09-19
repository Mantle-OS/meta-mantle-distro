inherit  python3native

EXTRA_OEMESON:class-native = " \
    -Dpython=${PYTHON} \
"
DEPENDS += " python3-native python3-setuptools-native "

do_compile:prepend() {
        PYPATH=$(dirname $PYTHON)
        export PATH="$PYPATH:$PATH"
}
