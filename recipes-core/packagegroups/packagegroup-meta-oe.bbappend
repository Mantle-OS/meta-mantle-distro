# The libyang package does not support armv5, therefore removing from the RDEPENDS of the layers package
# This is an upstream error, if I am not mistaken REVISIT-ME

RDEPENDS:packagegroup-meta-oe-extended:remove:armv5 = "libyang"
