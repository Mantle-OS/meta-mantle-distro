# SPDX-FileCopyrightText: 2020-2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
# SPDX-FileCopyrightText: 2025 Joseph Mills <josephjamesmills@gmail.com>
# SPDX-License-Identifier: MIT

QT_CONFIG = "${@bb.utils.contains('PV', '23.04.0', 'qt5', 'qt6', d)}"
PACKAGECONFIG:append = " ${QT_CONFIG}"
inherit qt6-cmake
