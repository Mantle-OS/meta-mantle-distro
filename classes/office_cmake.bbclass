# classes/office_cmake.bbclass
# Always-on scrub of CMake/pkg-config/binconfig artifacts installed under ${D}.
# Relies on cmake.bbclass for layout; we do not modify EXTRA_OECMAKE here.

inherit cmake

# Native/nativesdk: still scrub, but skip fatal staging check to avoid false positives.
do_install:prepend:class-native() {
    no_staging_check=true
}
do_install:prepend:class-nativesdk() {
    no_staging_check=true
}

do_install:append() {
    # Quick sanity: duplicated /usr//usr hints at a broken upstream install prefix.
    if ( cd ${D} 2>/dev/null && grep -qr 'usr//usr' ); then
        bbfatal 'usr//usr found - check upstream CMake install dirs!'
    fi

    # Targets to scrub: CMake exports/configs, pkg-config .pc, and legacy *-config helpers.
    default_preds="-name '*.cmake' -o -name '*Targets*.cmake' -o -name '*Config*.cmake' -o -name '*.pc'"

    # Rewrite absolute build/sysroot paths to relocatable forms and normalize _IMPORT_PREFIX.
    find "${D}" -type f \( ${default_preds} \) -print0 2>/dev/null | \
    while IFS= read -r -d '' f; do
        # Normalize the line CMake writes with an absolute path:
        #   set(_IMPORT_PREFIX "/abs/path/.../image/usr")
        # or:
        #   get_filename_component(_IMPORT_PREFIX "/abs/path/.../image/usr" PATH)
        sed -i \
          -e 's#^set(_IMPORT_PREFIX ".*")#set(_IMPORT_PREFIX "")#' \
          -e 's#^get_filename_component(_IMPORT_PREFIX ".*" PATH)#set(_IMPORT_PREFIX "")#' \
          \
          -e "s#${D}${prefix}#\${_IMPORT_PREFIX}${prefix}#g" \
          -e "s#${RECIPE_SYSROOT}${prefix}#\${_IMPORT_PREFIX}${prefix}#g" \
          -e "s#${RECIPE_SYSROOT_NATIVE}${prefix}#\${_IMPORT_PREFIX}${prefix}#g" \
          \
          -e "s#${D}##g" \
          -e "s#${TMPDIR}##g" \
          -e "s#${RECIPE_SYSROOT_NATIVE}##g" \
          -e "s#${RECIPE_SYSROOT}##g" \
          -e "s#${B}##g" \
          -e "s#${S}##g" \
          -e "s#${WORKDIR}##g" \
          "$f" || true
    done

    # Legacy *-config helpers sometimes bake absolute paths; normalize them too.
    if [ -d "${D}${bindir}" ]; then
        find "${D}${bindir}" -maxdepth 1 -type f -name '*-config' -print0 2>/dev/null | \
        while IFS= read -r -d '' c; do
            sed -i \
              -e "s#${D}##g" \
              -e "s#${TMPDIR}##g" \
              -e "s#${RECIPE_SYSROOT_NATIVE}##g" \
              -e "s#${RECIPE_SYSROOT}##g" \
              -e 's#^prefix=.*#prefix=/usr#g' \
              -e 's#^exec_prefix=.*#exec_prefix=/usr#g' \
              "$c" || true
        done
    fi

    # Final guard: only fatal for target builds.
    if [ "x$no_staging_check" = "xtrue" ]; then
        return 0
    fi

    error=
    find "${D}" -type f -name '*.cmake' -print0 2>/dev/null | \
    while IFS= read -r -d '' k; do
        if grep -qE "${TMPDIR}|${RECIPE_SYSROOT}|${RECIPE_SYSROOT_NATIVE}" "$k"; then
            bbwarn "$k contains build/sysroot paths"
            error=true
        fi
    done
    if [ "x$error" != "x" ]; then
        bbfatal "One or more installed files still reference build/sysroot paths."
    fi
}

# After sysroot population, re-check exported CMake files Yocto tracks (if present).
do_populate_sysroot[postfuncs] += "do_sysroot_cmake_sanity"
do_sysroot_cmake_sanity() {
    # Only meaningful for target builds; native/nativesdk set no_staging_check.
    if [ -f "${CMAKEINSTALLED}" ] && [ "x$no_staging_check" != "xtrue" ]; then
        error=
        while IFS= read -r f; do
            [ -f "$f" ] || continue
            if grep -qE "${TMPDIR}|${RECIPE_SYSROOT}|${RECIPE_SYSROOT_NATIVE}" "$f"; then
                bbwarn "$f contains build/sysroot paths"
                error=true
            fi
            if grep -q ';${libdir}\b' "$f" || grep -q '"${libdir}/lib' "$f"; then
                bbwarn "$f contains literal \${libdir} references"
                error=true
            fi
            if grep -q ';${includedir}\b' "$f" || grep -q '"${includedir}' "$f"; then
                bbwarn "$f contains literal \${includedir} references"
                error=true
            fi
        done < "${CMAKEINSTALLED}"
        if [ "x$error" != "x" ]; then
            bbfatal "Non-relocatable references remain in CMake exports."
        fi
    fi
}
