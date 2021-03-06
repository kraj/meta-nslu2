nslu2_pack_image () {
	slugimage -p \
		-b ${STAGING_LIBDIR}/nslu2-binaries/RedBoot \
		-s ${STAGING_LIBDIR}/nslu2-binaries/SysConf \
		-k ${DEPLOY_DIR_IMAGE}/zImage-${MACHINE}.bin \
		-L ${STAGING_DIR_HOST}/loader/apex-nslu2.bin \
		-r Flashdisk:${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.jffs2 \
		-m ${STAGING_FIRMWARE_DIR}/NPE-B \
		-t ${STAGING_LIBDIR}/nslu2-binaries/Trailer \
		-o ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}-nslu2.bin
	slugimage -F -p \
		-b ${STAGING_LIBDIR}/nslu2-binaries/RedBoot \
		-s ${STAGING_LIBDIR}/nslu2-binaries/SysConf \
		-k ${DEPLOY_DIR_IMAGE}/zImage-${MACHINE}.bin \
		-L ${STAGING_DIR_HOST}/loader/apex-nslu2-16mb.bin \
		-r Flashdisk:${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.jffs2 \
		-m ${STAGING_FIRMWARE_DIR}/NPE-B \
		-t ${STAGING_LIBDIR}/nslu2-binaries/Trailer \
		-o ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}-nslu2-16mb.bin
}

EXTRA_IMAGEDEPENDS_append_nslu2 = ' slugimage-native nslu2-linksys-firmware ixp4xx-npe apex-nslu2 apex-nslu2-16mb'
IMAGE_POSTPROCESS_COMMAND_append_nslu2 = " nslu2_pack_image; "
