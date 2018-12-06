package me.yangcx.preview.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import com.alexvasilkov.gestures.animation.ViewPosition
import com.bumptech.glide.request.RequestOptions
import me.yangcx.preview.entity.ImageData
import me.yangcx.preview.ui.multiple.image.ActivityMultipleImagePreview
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
        postingTag: String,
        showStatus: Boolean
    ): Intent {
        val intent = Intent(context, clz.java)
        intent.putExtra(Constants.KEY_IMAGE_DATA, imageData)
        intent.putExtra(Constants.KEY_VIEW_POSITION, ViewPosition.from(targetView).pack())
        intent.putExtra(Constants.KEY_TARGET_ID, targetView.id)
        intent.putExtra(Constants.KEY_TAG_POSTING, postingTag)
        intent.putExtra(Constants.KEY_SHOW_STATUS, showStatus)
        return intent
    }

    private fun dontAnimate(context: Context) {
        if (context is Activity) {
            context.overridePendingTransition(0, 0)
        }
    }

    fun previewSingleNormal(
        context: Context,
        imageData: ImageData,
        targetView: View,
        postingTag: String,
        requestOptions: RequestOptions = RequestOptions(),
        showStatus: Boolean = false
    ) {
        val intent = createIntent(
            context,
            ActivitySingleImagePreview::class,
            imageData,
            targetView,
            postingTag,
            showStatus
        )
        ActivitySingleImagePreview.requestOptions = requestOptions
        context.startActivity(intent)
        dontAnimate(context)
    }

    fun previewSingleAvatar(
        context: Context,
        imageData: ImageData,
        targetView: View,
        postingTag: String,
        requestOptions: RequestOptions = RequestOptions(),
        showStatus: Boolean = false
    ) {
        val intent = createIntent(
            context,
            ActivitySingleAvatarPreview::class,
            imageData,
            targetView,
            postingTag,
            showStatus
        )
        ActivitySingleAvatarPreview.requestOptions = requestOptions
        context.startActivity(intent)
        dontAnimate(context)
    }

    fun previewMultipleImage(
        context: Context,
        imageList: List<ImageData>,
        startPosition: Int,
        containerView: View,
        targetView: View,
        postingTag: String,
        requestOptions: RequestOptions = RequestOptions(),
        showStatus: Boolean = false
    ) {
        val intent = Intent(context, ActivityMultipleImagePreview::class.java)
        intent.putExtra(Constants.KEY_IMAGE_DATA, Array(imageList.size) {
            imageList[it]
        })
        intent.putExtra(Constants.KEY_VIEW_POSITION, ViewPosition.from(targetView).pack())
        intent.putExtra(Constants.KEY_TARGET_ID, containerView.id)
        intent.putExtra(Constants.KEY_START_POSITION, startPosition)
        intent.putExtra(Constants.KEY_TAG_POSTING, postingTag)
        intent.putExtra(Constants.KEY_SHOW_STATUS, showStatus)
        ActivityMultipleImagePreview.requestOptions = requestOptions
        context.startActivity(intent)
        dontAnimate(context)
    }
}