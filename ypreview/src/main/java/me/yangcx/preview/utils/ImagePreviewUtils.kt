package me.yangcx.preview.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import com.alexvasilkov.gestures.animation.ViewPosition
import com.bumptech.glide.request.RequestOptions
import me.yangcx.preview.entity.ImageData
import me.yangcx.preview.ui.single.ActivitySingleAvatarPreview
import me.yangcx.preview.ui.single.ActivitySingleImagePreview
import me.yangcx.preview.varia.Constants
import kotlin.reflect.KClass

/**
 * 图片预览工具类
 * create by 97457
 * create at 2018/12/06
 */
object ImagePreviewUtils {
    private inline fun <reified T : Activity> createIntent(
        context: Context,
        clz: KClass<T>,
        imageData: ImageData,
        targetView: View,
        postingTag: String
    ): Intent {
        val intent = Intent(context, clz.java)
        intent.putExtra(Constants.KEY_IMAGE_DATA, imageData)
        intent.putExtra(Constants.KEY_VIEW_POSITION, ViewPosition.from(targetView).pack())
        intent.putExtra(Constants.KEY_TARGET_ID, targetView.id)
        intent.putExtra(Constants.KEY_TAG_POSTING, postingTag)
        return intent
    }

    fun previewSingleNormal(
        context: Context,
        imageData: ImageData,
        targetView: View,
        postingTag: String,
        requestOptions: RequestOptions = RequestOptions()
    ) {
        val intent = createIntent(
            context,
            ActivitySingleImagePreview::class,
            imageData,
            targetView,
            postingTag
        )
        ActivitySingleImagePreview.requestOptions = requestOptions
        context.startActivity(intent)
    }
    fun previewSingleAvatar(
        context: Context,
        imageData: ImageData,
        targetView: View,
        postingTag: String,
        requestOptions: RequestOptions = RequestOptions()
    ) {
        val intent = createIntent(
            context,
            ActivitySingleAvatarPreview::class,
            imageData,
            targetView,
            postingTag
        )
        ActivitySingleAvatarPreview.requestOptions = requestOptions
        context.startActivity(intent)
    }
}