
python do_populate_lic:prepend() {
    import os
    import re
    import bb

    srcdir = d.getVar('S')

    checksum_map = {
        'Apache-2.0': [
            '3b83ef96387f14655fc854ddc3c6bd57'
        ],
        'BSD-3-Clause': [
            '71f739ef75581cae312e8c711bcdab16'
        ],
        'EPL-1.0': [
            'd0fc088e4e5216422c217d39853d8601'
        ],
    }

    # Extract license identifiers from SPDX expressions.
    # Operators AND/OR are separators, while WITH introduces an exception.
    # Examples:
    #   Apache-2.0
    #   Apache-2.0 AND BSD-3-Clause
    #   (Apache-2.0 OR EPL-1.0)
    #   GPL-2.0-only WITH Classpath-exception-2.0
    package_license_statement = d.getVar('LICENSE') or ''
    recipe_licenses = []

    expressions = re.split(r'\s+(?:AND|OR)\s+|[()]', package_license_statement)

    for expression in expressions:
        license_id = expression.split(' WITH ', 1)[0].strip()

        if license_id and license_id not in recipe_licenses:
            recipe_licenses.append(license_id)

    package_license_qa_whitelist = d.getVar('REUSE_LICENSECHECK_WHITELIST') or ''
    recipe_licenses_qa_whitelist = package_license_qa_whitelist.split()
    recipe_licenses_qa_whitelist.append('CC0-1.0')

    # Preserve any explicitly supplied license checksums.
    if d.getVar('LIC_FILES_CHKSUM'):
        bb.note("Skip LIC_FILES_CHKSUM computation, value already set to: %s" %
                d.getVar('LIC_FILES_CHKSUM'))
    else:
        checksum_entries = []
        layerdir = d.getVar('LAYERDIR')

        if not layerdir:
            bb.fatal("LAYERDIR is not defined for Java license checksum discovery")

        for license_id in recipe_licenses:
            license_file_path = os.path.join(layerdir, 'licenses', license_id)

            if not os.path.exists(license_file_path):
                bb.error("License %s not found in %s [reuse_license]" %
                         (license_id, license_file_path))
                continue

            if license_id not in checksum_map:
                bb.warn("QA Issue: %s has no checksum table entry [reuse_license]" %
                        license_id)
                continue

            md5chksum = bb.utils.md5_file(license_file_path)

            if md5chksum in checksum_map[license_id]:
                entry = "file://%s;md5=%s" % (license_file_path, md5chksum)
                checksum_entries.append(entry)
            else:
                bb.warn("QA Issue: %s checksum mismatch, md5sum=%s [reuse_license]" %
                        (license_id, md5chksum))

        if checksum_entries:
            d.setVar('LIC_FILES_CHKSUM', ' '.join(checksum_entries))
            bb.note("Set LIC_FILES_CHKSUM from known SPDX files to: %s" %
                    d.getVar('LIC_FILES_CHKSUM'))
        else:
            bb.warn("No valid Java license checksum entries generated [reuse_license]")

    # Optional QA check for licenses not represented in the SPDX expression.
    if d.getVar('JAVA_REUSE_LICENSECHECK_ENABLED') == '1':
        layerdir = d.getVar('LAYERDIR')

        if not layerdir:
            bb.fatal("LAYERDIR is not defined for Java REUSE license checking")

        licenses_dir = os.path.join(layerdir, 'licenses')

        if not os.path.isdir(licenses_dir):
            bb.fatal("Java REUSE license directory does not exist: %s" %
                     licenses_dir)

        src_licenses = os.listdir(licenses_dir)

        for license_file in src_licenses:
            license_name = license_file

            if license_name.endswith('.txt'):
                license_name = license_name[:-4]

            if license_name in checksum_map:
                if license_name in recipe_licenses:
                    continue

            if license_name in recipe_licenses_qa_whitelist:
                continue

            bb.warn("QA Issue: %s found in SRC but not in package license statement [reuse_license]" %
                    license_name)
}
