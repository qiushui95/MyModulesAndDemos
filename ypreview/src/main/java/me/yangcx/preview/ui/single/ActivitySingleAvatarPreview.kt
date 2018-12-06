package me.yangcx.preview.ui.single

import com.bumptech.glide.request.RequestOptions
import me.yangcx.preview.R

/**
 * 单张头像预览界面
 * create by 97457
 * create at 2018/12/06 0006
 */
internal class ActivitySingleAvatarPreview : ActivityBaseSingle(R.layout.activity_single_avatar_preview) {
    companion object {
        internal lateinit var requestOptions: RequestOptions
    }

    override val requestOptions: RequestOptions
        get() = ActivitySingleAvatarPreview.requestOptions

}