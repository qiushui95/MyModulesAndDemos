package me.yangcx.preview.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.core.view.postDelayed
import com.alexvasilkov.gestures.animation.ViewPosition
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_single_image_preview.*
import me.yangcx.preview.R
import me.yangcx.preview.entity.ImageData
import me.yangcx.preview.entity.PreviewFinishedEvent
import me.yangcx.preview.entity.PreviewStartEvent
import me.yangcx.preview.utils.ImageLoadUtils
import me.yangcx.preview.varia.Constants
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * 单张图片预览界面
 * create by 97457
 * create at 2018/12/06 0006
 */
class ActivitySingleImagePreview : AppCompatActivity() {
    companion object {
        private lateinit var requestOptions: RequestOptions
        fun launch(
            context: Context,
            imageData: ImageData,
            targetView: View,
            postingTag: String,
            requestOptions: RequestOptions = RequestOptions()
        ) {
            val intent = Intent(context, ActivitySingleImagePreview::class.java)
            intent.putExtra(Constants.KEY_IMAGE_DATA, imageData)
            intent.putExtra(Constants.KEY_VIEW_POSITION, ViewPosition.from(targetView).pack())
            intent.putExtra(Constants.KEY_TARGET_ID, targetView.id)
            intent.putExtra(Constants.KEY_TAG_POSTING, postingTag)
            this.requestOptions = requestOptions
            context.startActivity(intent)
            if (context is Activity) {
                context.overridePendingTransition(0, 0)
            }
        }
    }

    private val imageData by lazy {
        intent.getParcelableExtra<ImageData>(Constants.KEY_IMAGE_DATA)
    }

    private val viewPosition by lazy {
        ViewPosition.unpack(intent.getStringExtra(Constants.KEY_VIEW_POSITION))
    }

    private val postingTag by lazy {
        intent.getStringExtra(Constants.KEY_TAG_POSTING)
    }

    private val targetId by lazy {
        intent.getIntExtra(Constants.KEY_TARGET_ID, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_image_preview)
        EventBus.getDefault().register(this)
        givSingleImage.positionAnimator.addPositionUpdateListener(this::onPositionUpdate)
        ImageLoadUtils.loadImage(imageData, givSingleImage, requestOptions)
        givSingleImage.doOnPreDraw {
            runOnNextFrame {
                EventBus.getDefault().post(PreviewStartEvent(postingTag, targetId))
                givSingleImage.positionAnimator.enter(viewPosition, true)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    private fun onPositionUpdate(position: Float, isLeaving: Boolean) {
        val isFinished = position == 0f && isLeaving
        backSingleImagePreview.alpha = position
        backSingleImagePreview.visibility = if (isFinished) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
        givSingleImage.visibility = backSingleImagePreview.visibility
        if (isFinished) {
            EventBus.getDefault().post(PreviewFinishedEvent(postingTag, targetId))
            givSingleImage.controller.settings.disableBounds()
            givSingleImage.positionAnimator.setState(0f, false, false)
            runOnNextFrame {
                finish()
                overridePendingTransition(0, 0)
            }
        }
    }

    private fun runOnNextFrame(onNextFrame: () -> Unit) {
        givSingleImage.postDelayed(17, onNextFrame)
    }

    override fun onBackPressed() {
        if (!givSingleImage.positionAnimator.isLeaving) {
            givSingleImage.positionAnimator.exit(true)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onChangeViewPosition(viewPosition: ViewPosition) {
        if (givSingleImage.positionAnimator.position > 0) {
            givSingleImage.positionAnimator.update(viewPosition)
        }
    }
}