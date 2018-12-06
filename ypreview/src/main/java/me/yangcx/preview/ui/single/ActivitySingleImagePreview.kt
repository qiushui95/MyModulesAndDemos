package me.yangcx.preview.ui.single

import com.bumptech.glide.request.RequestOptions
import me.yangcx.preview.R

/**
 * 单张矩形图片预览界面
 * create by 97457
 * create at 2018/12/06 0006
 */
internal class ActivitySingleImagePreview : ActivityBaseSingle(R.layout.activity_single_image_preview) {
    companion object {
        internal lateinit var requestOptions: RequestOptions
    }

    override val requestOptions: RequestOptions
        get() = ActivitySingleImagePreview.requestOptions

}