DESCRIPTION = "APEX Boot Loader Environment User Modification Tool"
SECTION = "misc"
PRIORITY = "optional"
HOMEPAGE = "http://wiki.buici.com/xwiki/bin/view/Apex+Boot+Loader/WebHome"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://../COPYING;md5=751419260aa954499f7abaabaa882bbe"

PR = "r2"

SRC_URI = "ftp://ftp.buici.com/pub/apex/apex-${PV}.tar.gz \
	file://find-apex-partition.patch \
	file://invalid-conversion.patch \
	"
S = "${WORKDIR}/apex-${PV}/usr"

oe_runmake() {
	oenote make ${PARALLEL_MAKE} LDFLAGS=${LDFLAGS} CROSS_COMPILE=${TARGET_PREFIX} "$@"
	make ${PARALLEL_MAKE} LDFLAGS=${LDFLAGS} CROSS_COMPILE=${TARGET_PREFIX} "$@" || die "oe_runmake failed"
}

do_install() {
	${STRIP} ${S}/apex-env
	install -d ${D}/${sbindir}
	install -m 755 ${S}/apex-env ${D}/${sbindir}
}

SRC_URI[md5sum] = "22fb46e76c8221c7bcc9734602367460"
SRC_URI[sha256sum] = "472e12897931d9e5dcf2d1e3332acfc467aafd0f62e612896a9c71dd0d16d950"
