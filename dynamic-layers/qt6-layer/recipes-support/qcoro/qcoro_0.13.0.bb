SUMMARY = "C++20 coroutine support for Qt applications"
DESCRIPTION = "QCoro is a C++ library integrating C++20 coroutines \
with the Qt event loop and asynchronous APIs. It provides coroutine \
support for Qt signals, timers, networking, D-Bus, WebSockets, QML \
and Qt Quick, allowing asynchronous operations to be expressed using \
standard C++ coroutine syntax."

HOMEPAGE = "https://github.com/danvratil/qcoro"
BUGTRACKER = "https://github.com/danvratil/qcoro/issues"
SECTION = "libs"

LICENSE = "MIT AND BSD-3-Clause AND GFDL-1.3-or-later"
LIC_FILES_CHKSUM = " \
    file://LICENSES/MIT.txt;md5=387248cdf922178ee3fdc379e28542dc \
    file://LICENSES/BSD-3-Clause.txt;md5=71f739ef75581cae312e8c711bcdab16 \
    file://LICENSES/GFDL-1.3-or-later.txt;md5=e0771ae6a62dc8a2e50b1d450fea66b7 \
"

SRC_URI = "git://github.com/danvratil/qcoro.git;branch=main;protocol=https"
SRCREV = "98f23d8d8d67e9e24217d268927faec97c713fb0"

inherit qt6-cmake cmake_extra_sanity

PACKAGECONFIG[shared] = "-DBUILD_SHARED_LIBS=ON, -DBUILD_SHARED_LIBS=OFF, "
PACKAGECONFIG[examples] = "-DQCORO_BUILD_EXAMPLES=ON, -DQCORO_BUILD_EXAMPLES=OFF, "
PACKAGECONFIG[testing] = "-DQCORO_BUILD_TESTING=ON, -DQCORO_BUILD_TESTING=OFF, "
PACKAGECONFIG[asan] = "-DQCORO_ENABLE_ASAN=ON, -DQCORO_ENABLE_ASAN=OFF, "
PACKAGECONFIG[no-deprecated-task-h] = "-DQCORO_DISABLE_DEPRECATED_TASK_H=ON, -DQCORO_DISABLE_DEPRECATED_TASK_H=OFF, "
PACKAGECONFIG[dbus] = "-DQCORO_WITH_QTDBUS=ON, -DQCORO_WITH_QTDBUS=OFF, "
PACKAGECONFIG[qt6-networking] = "-DQCORO_WITH_QTNETWORK=ON, -DQCORO_WITH_QTNETWORK=OFF, "
PACKAGECONFIG[qt6-websockets] = "-DQCORO_WITH_QTWEBSOCKETS=ON, -DQCORO_WITH_QTWEBSOCKETS=OFF, qtwebsockets6"
PACKAGECONFIG[qml] = "-DQCORO_WITH_QML=ON, -DQCORO_WITH_QML=OFF, qtdeclarative6 qtdeclarative6-native"
PACKAGECONFIG[quick] = "-DQCORO_WITH_QTQUICK=ON, -DQCORO_WITH_QTQUICK=OFF, qtdeclarative6 qtdeclarative6-native"
PACKAGECONFIG[qtests] = "-DQCORO_WITH_QTTEST=ON, -DQCORO_WITH_QTTEST=OFF, qttools6-native"

PACKAGECONFIG:append = " \
    shared \
    dbus \
    qt6-networking \
    qt6-websockets \
    qml \
    quick \
"

EXTRA_OECMAKE:append = " \
    -DCMAKE_POSITION_INDEPENDENT_CODE=ON \
    -DECM_MKSPECS_INSTALL_DIR=${QT6_INSTALL_MKSPECSDIR}/modules \
"

require qcoro-packaging.inc
