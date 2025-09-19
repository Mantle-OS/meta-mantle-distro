# make sure it gets into the packages. There  is a bug upstream that is causing 
# native perl packages to be missing there pm's etc 
EXTRA_CPANFLAGS:append:class-native = " \
  INSTALLVENDORLIB=${libdir}/perl5/vendor_perl/${@get_perl_version(d)} \
  INSTALLVENDORARCH=${libdir}/perl5/vendor_perl/${@get_perl_version(d)}/${@get_perl_hostarch(d)} \
"
