DESCRIPTION = "NPE firmware for the IXP4xx line of devices"
LICENSE = "Proprietary"
PR = "r1"
DEPENDS += "unzip-native"
DEPENDS_append_class-target = " ixp4xx-npe-native"
LIC_FILES_CHKSUM = "file://${WORKDIR}/Intel;md5=a1e183d5eb93b863adda0cb64e77a673"

# You need to download the IPL_ixp400NpeLibrary-2_4.zip file (without crypto) from:
# http://www.intel.com/content/www/us/en/intelligent-systems/previous-generation/intel-ixp400-network-processor-software.html?wapkw=c200+usb
# "Intel IXP400 software - NPE microcode (non-crypto)" -> "2.4"
# Direct link to above https://downloadcenter.intel.com/detail_desc.aspx?ProductID=2100&DwnldID=13757&agr=Y
# and put it in your downloads directory so bitbake will find it.
# Make sure you *read* and accept the license - it is not a standard one.

SRC_URI = "\
           http://downloadmirror.intel.com/12954/eng/IPL_ixp400NpeLibrary-2_4.zip;name=npelib \
           file://Intel \
           file://IxNpeMicrocode.h \
           "
SRC_URI[npelib.md5sum] = "9a6dc3846041b899edf9eff8a906fb11"
SRC_URI[npelib.sha256sum] = "f764d0554e236357fc55d128a012cb6ac2ceb638023f4af88c8f509511f209fd"

S = "${WORKDIR}/ixp400_xscale_sw/src/npeDl"

COMPATIBLE_MACHINE = "(nslu2|ixp4xx|kixrp435)*"

do_compile_class-target () {
	IxNpeMicrocode-${PV} -be
}

do_install_class-target () {
	install -d ${D}${base_libdir}/firmware/
	rm ${S}/NPE-B
	mv ${S}/NPE-B.* ${S}/NPE-B
	install ${S}/NPE-B ${D}${base_libdir}/firmware/
	rm ${S}/NPE-C
	mv ${S}/NPE-C.* ${S}/NPE-C
	install ${S}/NPE-C ${D}${base_libdir}/firmware/
	install -d ${D}${datadir}/common-licenses/
	install -m 0644 ${WORKDIR}/Intel ${D}${datadir}/common-licenses/
}

do_compile_class-native () {
        mv ${WORKDIR}/IxNpeMicrocode.h ${S}/
        ${CC} -Wall IxNpeMicrocode.c -o IxNpeMicrocode
}
do_install_class-native () {
        install -d ${D}${bindir}/
        install -m 0755 ${S}/IxNpeMicrocode ${D}${bindir}/IxNpeMicrocode-${PV}
}
sysroot_stage_all_append_class-target () {
	sysroot_stage_dir ${D}${base_libdir}/firmware ${STAGING_FIRMWARE_DIR}
}
FILES_${PN} += "${base_libdir}/firmware/NPE-B ${base_libdir}/firmware/NPE-C"

BBCLASSEXTEND = "native"
