do_install:append() {
    for f in ${D}${libdir}/cmake/LibVNCServer/LibVNCServerTargets*.cmake; do
        [ -f "$f" ] || continue
        sed -E -i \
            -e 's#(/usr/(lib64|lib|lib32))/lib([A-Za-z0-9_+.-]+)\.so([0-9.])*#-l\3#g' \
            "$f"
        sed -E -i -e 's#(;|-l)([A-Za-z0-9_+.-]+);-l\2#;\1\2#g' "$f"
        sed -E -i -e 's#;;#;#g' "$f"
    done

    for pc in ${D}${libdir}/pkgconfig/*.pc; do
        [ -f "$pc" ] || continue
        sed -i \
            -e 's#[[:space:]]-I/usr/include##g' \
            -e 's#[[:space:]]-L/usr/lib64##g' \
            -e 's#[[:space:]]-L/usr/lib32##g' \
            -e 's#[[:space:]]-L/usr/lib##g' \
            "$pc"
    done
}
