SUMMARY = "Chewing input method engine for Fcitx5"

# FIXME: Placeholder license only. Verify the project's actual SPDX license.
# FIXME: Verify the license file path and md5; let BitBake report the correct
# checksum when the upstream file differs. A matching md5 is not license proof.
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68"

# FIXME: Confirm this is the appropriate shared include for fcitx5-chewing.
# The include owns lower-level metadata; do not require fcitx5-git.inc again.
require fcitx5.inc

SRC_URI = "${FCITX5_CHEWING_SRC_URI}"
SRCREV = "${FCITX5_CHEWING_SRCREV}"
PV = "${FCITX5_CHEWING_PV}"

# TODO: Verify upstream build dependencies and CMake options.
# TODO: Inspect actual do_install image and assign FILES/package splits.
# TODO: Add source patches only when demonstrated necessary.
