PACKAGES =+ " \
    ${PN}-base6 \
    ${PN}-base6-dev \
    ${PN}-declarative6 \
    ${PN}-declarative6-dev \
    ${PN}-websockets6 \
    ${PN}-websockets6-dev \
"

ALLOW_EMPTY:${PN} = "1"

SUMMARY:${PN}-base6 = "QCoro coroutine support for Qt6 Base"
DESCRIPTION:${PN}-base6 = "Provides the foundational C++20 coroutine \
integration with Qt6 Base, including asynchronous tasks, generators, \
signals, timers, threads, processes, I/O devices, D-Bus and networking."

FILES:${PN}-base6 = " \
    ${libdir}/libQCoro6Core.so.* \
    ${libdir}/libQCoro6DBus.so.* \
    ${libdir}/libQCoro6Network.so.* \
"

SUMMARY:${PN}-base6-dev = "Development files for QCoro Qt6 Base"
DESCRIPTION:${PN}-base6-dev = "Headers, linker symlinks, CMake package \
configuration files and qmake module definitions for the foundational \
QCoro Qt6 Base components."

FILES:${PN}-base6-dev = " \
    ${includedir}/qcoro6/qcoro/concepts_p.h \
    ${includedir}/qcoro6/qcoro/config.h \
    ${includedir}/qcoro6/qcoro/coroutine.h \
    ${includedir}/qcoro6/qcoro/impl \
    ${includedir}/qcoro6/qcoro/macros_p.h \
    ${includedir}/qcoro6/qcoro/mixins_p.h \
    ${includedir}/qcoro6/qcoro/waitoperationbase_p.h \
    ${includedir}/qcoro6/qcoro/task.h \
    ${includedir}/qcoro6/qcoro/qcoro.h \
    ${includedir}/qcoro6/qcoro/qcoroabstractsocket.h \
    ${includedir}/qcoro6/qcoro/qcoroasyncgenerator.h \
    ${includedir}/qcoro6/qcoro/qcorocore*.h \
    ${includedir}/qcoro6/qcoro/qcorodbus*.h \
    ${includedir}/qcoro6/qcoro/qcorofuture.h \
    ${includedir}/qcoro6/qcoro/qcorofwd.h \
    ${includedir}/qcoro6/qcoro/qcorogenerator.h \
    ${includedir}/qcoro6/qcoro/qcoroiodevice.h \
    ${includedir}/qcoro6/qcoro/qcorolazytask.h \
    ${includedir}/qcoro6/qcoro/qcorolocalsocket.h \
    ${includedir}/qcoro6/qcoro/qcoronetwork*.h \
    ${includedir}/qcoro6/qcoro/qcoroprocess.h \
    ${includedir}/qcoro6/qcoro/qcorosignal.h \
    ${includedir}/qcoro6/qcoro/qcorotask.h \
    ${includedir}/qcoro6/qcoro/qcorotcpserver.h \
    ${includedir}/qcoro6/qcoro/qcorothread.h \
    ${includedir}/qcoro6/qcoro/qcorotimer.h \
    ${includedir}/qcoro6/QCoro/QCoro \
    ${includedir}/qcoro6/QCoro/Task \
    ${includedir}/qcoro6/QCoro/QCoroAbstractSocket \
    ${includedir}/qcoro6/QCoro/QCoroAsyncGenerator \
    ${includedir}/qcoro6/QCoro/QCoroCore \
    ${includedir}/qcoro6/QCoro/QCoroDBus* \
    ${includedir}/qcoro6/QCoro/QCoroFuture \
    ${includedir}/qcoro6/QCoro/QCoroFwd \
    ${includedir}/qcoro6/QCoro/QCoroGenerator \
    ${includedir}/qcoro6/QCoro/QCoroIODevice \
    ${includedir}/qcoro6/QCoro/QCoroLazyTask \
    ${includedir}/qcoro6/QCoro/QCoroLocalSocket \
    ${includedir}/qcoro6/QCoro/QCoroNetwork* \
    ${includedir}/qcoro6/QCoro/QCoroProcess \
    ${includedir}/qcoro6/QCoro/QCoroSignal \
    ${includedir}/qcoro6/QCoro/QCoroTask \
    ${includedir}/qcoro6/QCoro/QCoroTcpServer \
    ${includedir}/qcoro6/QCoro/QCoroThread \
    ${includedir}/qcoro6/QCoro/QCoroTimer \
    ${libdir}/libQCoro6Core.so \
    ${libdir}/libQCoro6DBus.so \
    ${libdir}/libQCoro6Network.so \
    ${libdir}/cmake/QCoro6 \
    ${libdir}/cmake/QCoro6Core \
    ${libdir}/cmake/QCoro6Coro \
    ${libdir}/cmake/QCoro6DBus \
    ${libdir}/cmake/QCoro6Network \
    ${QT6_INSTALL_MKSPECSDIR}/modules/qt_QCoroCore.pri \
    ${QT6_INSTALL_MKSPECSDIR}/modules/qt_QCoroCoro.pri \
    ${QT6_INSTALL_MKSPECSDIR}/modules/qt_QCoroDBus.pri \
    ${QT6_INSTALL_MKSPECSDIR}/modules/qt_QCoroNetwork.pri \
"

RDEPENDS:${PN}-base6-dev += " \
    ${@bb.utils.contains('PACKAGECONFIG', 'shared', '${PN}-base6', '', d)} \
"


SUMMARY:${PN}-declarative6 = "QCoro coroutine support for Qt6 Declarative"
DESCRIPTION:${PN}-declarative6 = "Provides C++20 coroutine integration \
with Qt6 QML and Qt Quick, including asynchronous task handling \
and coroutine-based image providers."

FILES:${PN}-declarative6 = " \
    ${libdir}/libQCoro6Qml.so.* \
    ${libdir}/libQCoro6Quick.so.* \
"

RDEPENDS:${PN}-declarative6 += "${PN}-base6"

SUMMARY:${PN}-declarative6-dev = "Development files for QCoro Qt6 Declarative"
DESCRIPTION:${PN}-declarative6-dev = "Headers, linker symlinks, CMake \
package configuration files and qmake module definitions for QCoro \
integration with Qt6 QML and Qt Quick."

FILES:${PN}-declarative6-dev = " \
    ${includedir}/qcoro6/qcoro/qcoroqml*.h \
    ${includedir}/qcoro6/qcoro/qcoroquick*.h \
    ${includedir}/qcoro6/qcoro/qcoroimageprovider.h \
    ${includedir}/qcoro6/QCoro/QCoroQml* \
    ${includedir}/qcoro6/QCoro/QCoroImageProvider \
    ${libdir}/libQCoro6Qml.so \
    ${libdir}/libQCoro6Quick.so \
    ${libdir}/cmake/QCoro6Qml \
    ${libdir}/cmake/QCoro6Quick \
    ${QT6_INSTALL_MKSPECSDIR}/modules/qt_QCoroQml.pri \
    ${QT6_INSTALL_MKSPECSDIR}/modules/qt_QCoroQuick.pri \
"

RDEPENDS:${PN}-declarative6-dev += " \
    ${PN}-base6-dev \
    ${@bb.utils.contains('PACKAGECONFIG', 'shared', '${PN}-declarative6', '', d)} \
"


SUMMARY:${PN}-websockets6 = "QCoro coroutine support for Qt6 WebSockets"
DESCRIPTION:${PN}-websockets6 = "Provides asynchronous C++20 coroutine \
integration with Qt6 WebSocket clients and servers."

FILES:${PN}-websockets6 = " \
    ${libdir}/libQCoro6WebSockets.so.* \
"

RDEPENDS:${PN}-websockets6 += "${PN}-base6"

SUMMARY:${PN}-websockets6-dev = "Development files for QCoro Qt6 WebSockets"
DESCRIPTION:${PN}-websockets6-dev = "Headers, linker symlinks, CMake \
package configuration files and qmake module definitions for QCoro \
integration with Qt6 WebSockets."

FILES:${PN}-websockets6-dev = " \
    ${includedir}/qcoro6/qcoro/qcorowebsocket*.h \
    ${includedir}/qcoro6/QCoro/QCoroWebSocket* \
    ${libdir}/libQCoro6WebSockets.so \
    ${libdir}/cmake/QCoro6WebSockets \
    ${QT6_INSTALL_MKSPECSDIR}/modules/qt_QCoroWebSockets.pri \
"

RDEPENDS:${PN}-websockets6-dev += " \
    ${PN}-base6-dev \
    ${@bb.utils.contains('PACKAGECONFIG', 'shared', '${PN}-websockets6', '', d)} \
"


RDEPENDS:${PN} += " \
    ${@bb.utils.contains('PACKAGECONFIG', 'shared', '${PN}-base6', '', d)} \
"

RRECOMMENDS:${PN} += " \
    ${@bb.utils.contains('PACKAGECONFIG', 'shared qt6-websockets', '${PN}-websockets6', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'shared', bb.utils.contains_any('PACKAGECONFIG', 'qml quick', '${PN}-declarative6', '', d), '', d)} \
"

RDEPENDS:${PN}-dev += " \
    ${PN}-base6-dev \
"

RRECOMMENDS:${PN}-dev += " \
    ${@bb.utils.contains('PACKAGECONFIG', 'qt6-websockets', '${PN}-websockets6-dev', '', d)} \
    ${@bb.utils.contains_any('PACKAGECONFIG', 'qml quick', '${PN}-declarative6-dev', '', d)} \
"
