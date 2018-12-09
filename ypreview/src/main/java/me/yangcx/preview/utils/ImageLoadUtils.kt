package me.yangcx.preview.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import me.yangcx.preview.entity.ImageData
import me.yangcx.preview.entity.ImageShowType

/**
 * 图片加载工具类
 * create by 97457
 * create at 2018/12/06
 */
internal object ImageLoadUtils {
    /**
     * Glide加载图片
     * create by 97457
     * create at 2018/12/06
     */
    fun loadImage(imageData: ImageData, imageView: ImageView, requestOptions: RequestOptions) {
        val options = requestOptions.clone()
            .dontAnimate()
        val thumbnailRequest = Glide.with(imageView)
            .load(imageData.thumbnailData)
            .apply(options)
        val originRequest = Glide.with(imageView)
            .load(imageData.origin)
            .apply(options)
        when (imageData.imageShowType) {
            ImageShowType.ALL -> originRequest.thumbnail(thumbnailRequest).into(imageView)
            ImageShowType.JUST_THUMBNAIL -> thumbnailRequest.into(imageView)
            ImageShowType.JUST_ORIGIN -> originRequest.into(imageView)
        }
    }
}