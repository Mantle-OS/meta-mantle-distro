# The debug source directory may be absent when packaging GTK4.
# Preserve upstream's unity-source rewriting when the directory exists.

rewrite_unity_sources() {
    if [ -d "${PKGD}/${TARGET_DBGSRC_DIR}" ]; then
        find "${PKGD}/${TARGET_DBGSRC_DIR}" -type f -name '*-unity*.c' \
            -exec sed -i \
                -e 's|${B}|${TARGET_DBGSRC_DIR}|g' \
                -e 's|${S}|${TARGET_DBGSRC_DIR}|g' {} +
    fi
}
