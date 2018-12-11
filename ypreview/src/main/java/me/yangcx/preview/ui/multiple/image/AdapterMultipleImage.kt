package me.yangcx.preview.ui.multiple.image

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.request.RequestOptions
import me.yangcx.preview.R
import me.yangcx.preview.entity.ImageData
import me.yangcx.preview.ui.multiple.base.AdapterBasePreview

/**
 * create by 97457
 * create at 2018/12/06
 */
internal class AdapterMultipleImage(inflater: LayoutInflater, requestOptions: RequestOptions, dataList: List<ImageData>) : AdapterBasePreview(inflater, requestOptions, dataList) {

	override fun createView(inflater: LayoutInflater, container: ViewGroup): View {
		return inflater.inflate(R.layout.item_multiple_preview_image, container, false)
	}
}