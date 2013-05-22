# This package builds the devio program
DESCRIPTION = "devio - block devio io"
HOMEPAGE = "http://devio.sourceforge.net/"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=4678a47c4b06971c2a612c7ef01b3d6f"

SRC_URI = "${SOURCEFORGE_MIRROR}/devio/devio-${PV}.tar.gz"
SRC_URI[md5sum] = "5d332c2bffc0791367bcf3368ba1a0d1"
SRC_URI[sha256sum] = "a71e87f49f52cd90dbd45431f65e83d18e073fb2669f91c29c59019b175cd5a8"

# Source directory
S = "${WORKDIR}/devio-${PV}"

# Just the one package at present

# Set the install dir to /sbin, not /usr/sbin, because devio is used
# during bootstrap (we want it to be posible to mount /usr separately)
sbindir_class-target = "${base_sbindir}"

# From 1.2 devio installs to bindir, not sbindir, so set that to /bin
# for the same reason
bindir = "${base_bindir}"

inherit autotools
BBCLASSEXTEND = "nativesdk native"
