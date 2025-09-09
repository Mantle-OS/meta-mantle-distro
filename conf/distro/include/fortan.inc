FORTRAN:forcevariable = "${@bb.utils.contains('MANTLE_FORTRAN', 'TRUE', ',fortran', '', d)}"
