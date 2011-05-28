DESCRIPTION = "NPE firmware for the IXP4xx line of devices"
LICENSE = "Intel"
PR = "r1"
DEPENDS = "ixp4xx-npe-native"
LIC_FILES_CHKSUM = "file://${WORKDIR}/Intel;md5=a1e183d5eb93b863adda0cb64e77a673"

# You need to download the IPL_ixp400NpeLibrary-3_0.zip file (without crypto) from:
# http://www.intel.com/design/network/products/npfamily/download_ixp400.htm
# "Intel IXP400 software - NPE microcode (non-crypto)" -> "3.0"
# and put it in your downloads directory so bitbake will find it.
# Make sure you *read* and accept the license - it is not a standard one.

SRC_URI = "http://You-Have-To-Download-The-Microcode-Manually-So-Please-Read-ixp4xx-npe_2.4.bb-For-Instructions/IPL_ixp400NpeLibrary-2_4.zip \
           file://Intel"

SRC_URI[md5sum] = "9a6dc3846041b899edf9eff8a906fb11"
SRC_URI[sha256sum] = "f764d0554e236357fc55d128a012cb6ac2ceb638023f4af88c8f509511f209fd"

do_unpack[depends] += "unzip-native:do_populate_sysroot"
S = "${WORKDIR}/ixp400_xscale_sw/src/npeDl"

COMPATIBLE_MACHINE = "(nslu2|ixp4xx|kixrp435)"

do_compile() {
	IxNpeMicrocode-${PV} -be
}

do_install() {
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

sysroot_stage_all_append() {
        sysroot_stage_dir ${D}${base_libdir}/firmware ${STAGING_FIRMWARE_DIR}
}

FILES_${PN} += "${base_libdir}/firmware/NPE-B ${base_libdir}/firmware/NPE-C"
