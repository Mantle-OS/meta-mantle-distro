do_install:append() {
    cfg="${D}${libdir}/cmake/Gpgmepp/GpgmeppConfig.cmake"
    if [ -f "$cfg" ]; then
        sed -i -E \
            -e 's|INTERFACE_INCLUDE_DIRECTORIES "[^"]*"|INTERFACE_INCLUDE_DIRECTORIES "${PACKAGE_PREFIX_DIR}/include/gpgme++;${PACKAGE_PREFIX_DIR}/include"|' \
            -e 's|IMPORTED_LOCATION "[^"]*"|IMPORTED_LOCATION "${PACKAGE_PREFIX_DIR}/${baselib}/libgpgmepp.so"|' \
            -e 's|INTERFACE_LINK_LIBRARIES "[^"]*"|INTERFACE_LINK_LIBRARIES "pthread;${PACKAGE_PREFIX_DIR}/${baselib}/libgpgme.so;-lassuan"|' \
            "$cfg"
    fi
}
FILES:${PN}-dev += " ${libdir}/cmake/Gpgmepp/* "
