package me.yangcx.preview.ui.single

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.core.view.postDelayed
import com.alexvasilkov.gestures.animation.ViewPosition
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_single_image_preview.*
import me.yangcx.preview.entity.ImageData
import me.yangcx.preview.entity.PreviewFinishedEvent
import me.yangcx.preview.entity.PreviewStartEvent
import me.yangcx.preview.utils.ImageLoadUtils
import me.yangcx.preview.varia.Constants
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * create by 97457
 * create at 2018/12/06
 */
internal abstract class ActivityBaseSingle(@LayoutRes private val layoutRes: Int) : AppCompatActivity() {
    protected abstract val requestOptions: RequestOptions

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

    private val showStatus by lazy {
        intent.getBooleanExtra(Constants.KEY_SHOW_STATUS, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatus()
        setContentView(layoutRes)
        EventBus.getDefault().register(this)
        givSingleImage.positionAnimator.addPositionUpdateListener(this::onPositionUpdate)
        ImageLoadUtils.loadImage(
                imageData, givSingleImage,
                requestOptions
        )
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

    private fun hideStatus() {
        if (!showStatus) {
            window.decorView.systemUiVisibility = View.INVISIBLE
        }
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