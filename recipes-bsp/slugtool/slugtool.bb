SECTION = "unknown"
DESCRIPTION = "Slugtool is a small app to disassemble and reassemble \
flash images for the Linksys NSLU2 device."
PR = "r1"
LIC_FILES_CHKSUM = "file://README;startline=3;endline=5;md5=076766a8a44a4cc8e38b9ac73301c8ad"
LICENSE = "GPL"
SRC_URI = "http://www.lantz.com/filemgmt_data/files/slugtool.tar.gz;subdir=${BPN}-${PV} \
	   file://redboot_typo.patch"

SRC_URI[md5sum] = "d83e00e9c691984f36cb421d84873bc7"
SRC_URI[sha256sum] = "0a2080a48f8a52d10d49aa78a66027205920b76c8e901d07fb040759191aad9e"

do_compile () {
	${CC} ${CFLAGS} ${LDFLAGS} slugtool.c -o slugtool
}

do_install () {
	install -d ${D}${bindir}
	install -m 0755 slugtool ${D}${bindir}/
}

BBCLASSEXTEND = "native"
