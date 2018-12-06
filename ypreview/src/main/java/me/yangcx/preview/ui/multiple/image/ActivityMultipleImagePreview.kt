package me.yangcx.preview.ui.multiple.image

import com.bumptech.glide.request.RequestOptions
import me.yangcx.preview.R
import me.yangcx.preview.ui.multiple.base.ActivityBaseMultiple
import me.yangcx.preview.ui.multiple.base.AdapterBasePreview

/**
 * 多张图片预览界面
 * create by 97457
 * create at 2018/12/06 0006
 */
internal class ActivityMultipleImagePreview :
    ActivityBaseMultiple(R.layout.activity_multiple_image_preview) {
    companion object {
        internal lateinit var requestOptions: RequestOptions
    }

    override val adapter: AdapterBasePreview by lazy {
        AdapterMultipleImage(layoutInflater, requestOptions, imageList)
    }
}