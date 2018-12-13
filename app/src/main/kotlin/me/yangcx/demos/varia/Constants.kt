package me.yangcx.demos.varia

import me.yangcx.preview.entity.ImageData
import me.yangcx.preview.entity.ImageShowType

/**
 * 常用公共数据
 * create by 9745
 * create at 2018/12/08 0008
 */
object Constants {

	//图片域名地址
	private const val HOST_IMAGE = "http://45.32.19.133/"
	//缩略图路径
	private const val URL_THUMBNAIL_FORMAT = "${HOST_IMAGE}WebImages/thumbnail/Image%d.webp"
	//原图路径
	private const val URL_ORIGIN_FORMAT = "${HOST_IMAGE}WebImages/origin/Image%d.webp"
	//图片数据列表
	val imageList by lazy {
		(1..29).map {
			ImageData(
					String.format(URL_THUMBNAIL_FORMAT, it),
					String.format(URL_ORIGIN_FORMAT, it),
					ImageShowType.ALL
			)
		}
	}

	private const val LARGE_IMAGE_PATH = "LargeImages/"
	const val LARGE_IMAGE_URL = "$HOST_IMAGE${LARGE_IMAGE_PATH}ChinaMap.jpg"
}