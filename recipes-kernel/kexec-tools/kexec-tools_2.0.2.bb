require kexec-tools_${PV}.inc

PR = "${INC_PR}.0"

EXTRA_OECONF = " --with-zlib=yes"
export LDFLAGS = "-L${STAGING_LIBDIR}"

PACKAGES =+ "kexec kdump"

FILES_kexec = "${sbindir}/kexec"
FILES_kdump = "${sbindir}/kdump"
