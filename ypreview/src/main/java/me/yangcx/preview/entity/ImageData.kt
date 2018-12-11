package me.yangcx.preview.entity

import android.os.Parcel
import android.os.Parcelable

/**
 * 图片数据
 * create by 97457
 * create at 2018/12/06 0006
 */
data class ImageData(
		//缩略图数据
		val thumbnailData: String,
		//原图数据
		val origin: String,
		//图片显示策略
		val imageShowType: ImageShowType
					) : Parcelable {

	@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
	constructor(parcel: Parcel) : this(
			parcel.readString(),
			parcel.readString(),
			ImageShowType.valueOf(parcel.readString())
									  )

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(thumbnailData)
		parcel.writeString(origin)
		parcel.writeString(imageShowType.name)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<ImageData> {
		override fun createFromParcel(parcel: Parcel): ImageData {
			return ImageData(parcel)
		}

		override fun newArray(size: Int): Array<ImageData?> {
			return arrayOfNulls(size)
		}
	}
}