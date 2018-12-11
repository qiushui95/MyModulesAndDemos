package me.yangcx.preview.ui.multiple.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnLayout
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.view.postDelayed
import androidx.core.view.updateLayoutParams
import androidx.viewpager.widget.ViewPager
import com.alexvasilkov.gestures.animation.ViewPosition
import com.alexvasilkov.gestures.transition.GestureTransitions
import com.alexvasilkov.gestures.transition.tracker.SimpleTracker
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_multiple_image_preview.*
import me.yangcx.preview.R
import me.yangcx.preview.entity.ImageData
import me.yangcx.preview.entity.PositionChangedEvent
import me.yangcx.preview.entity.PreviewChangeEvent
import me.yangcx.preview.entity.PreviewFinishedEvent
import me.yangcx.preview.entity.PreviewStartEvent
import me.yangcx.preview.glide.ImageCutTransformation
import me.yangcx.preview.utils.ImagePreviewUtils
import me.yangcx.preview.varia.Constants
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 *
 * create by 97457
 * create at 2018/12/06 0006
 */
internal abstract class ActivityBaseMultiple(@LayoutRes private val layoutRes: Int) : AppCompatActivity(),
																					  ViewPager.OnPageChangeListener {

	protected val imageList by lazy {
		intent.getParcelableArrayExtra(Constants.KEY_IMAGE_DATA_LIST)
				.mapNotNull {
					it as? ImageData
				}
	}
	private val startPosition by lazy {
		intent.getIntExtra(Constants.KEY_START_POSITION, 0)
	}
	private val postingTag by lazy {
		intent.getStringExtra(Constants.KEY_TAG_POSTING)
	}

	private val containerId by lazy {
		intent.getIntExtra(Constants.KEY_TARGET_ID, 0)
	}

	private val showStatus by lazy {
		intent.getBooleanExtra(Constants.KEY_SHOW_STATUS, false)
	}
	private lateinit var viewPosition: ViewPosition

	protected abstract val adapter: AdapterBasePreview

	private val pagerTrack by lazy {
		object : SimpleTracker() {
			override fun getViewAt(position: Int): View? {
				return adapter.getGestureImageView(position)
			}
		}
	}

	private val animator by lazy {
		GestureTransitions.from<Int>(ivMultipleImage)
				.into(vpMultipleImage, pagerTrack)
				.apply {
					addPositionUpdateListener { position, isLeaving ->
						val finished = isLeaving && position == 0f
						val entered = !isLeaving && position == 1f
						if (finished && backMultipleImagePreview.isVisible) {
							backMultipleImagePreview.visibility = View.INVISIBLE
						} else if (!finished && backMultipleImagePreview.isInvisible) {
							backMultipleImagePreview.visibility = View.VISIBLE
						}
						backMultipleImagePreview.alpha = position

						if (entered && tvMultipleIndicator.isInvisible) {
							tvMultipleIndicator.visibility = View.VISIBLE
						} else {
							tvMultipleIndicator.visibility = View.INVISIBLE
						}

						if (finished) {
							EventBus.getDefault().post(PreviewFinishedEvent(postingTag, containerId))
							runOnNextFrame {
								finish()
								overridePendingTransition(0, 0)
							}
						}
					}
				}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		hideStatus()
		setContentView(layoutRes)
		EventBus.getDefault().register(this)
		initViewPager()
		layoutBelow(ViewPosition.unpack(intent.getStringExtra(Constants.KEY_VIEW_POSITION)))
		loadBelow(intent.getStringExtra(Constants.KEY_IMAGE_DATA))
		ivMultipleImage.doOnLayout {
			runOnNextFrame {
				EventBus.getDefault()
						.post(PreviewStartEvent(postingTag, containerId, startPosition))
				animator.enter(startPosition, true)
				drawIndicator(startPosition)
			}
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		EventBus.getDefault().unregister(this)
		ImagePreviewUtils.cleanViewBitmaps(this)
	}

	private fun hideStatus() {
		if (!showStatus) {
			window.decorView.systemUiVisibility = View.INVISIBLE
		}
	}

	private fun initViewPager() {
		vpMultipleImage.adapter = adapter
		vpMultipleImage.currentItem = startPosition
		vpMultipleImage.addOnPageChangeListener(this)
	}

	private fun runOnNextFrame(onNextFrame: () -> Unit) {
		ivMultipleImage.postDelayed(17, onNextFrame)
	}

	private fun layoutBelow(viewPosition: ViewPosition) {
		this.viewPosition = viewPosition
		ivMultipleImage.updateLayoutParams {
			width = viewPosition.visible.width()
			height = viewPosition.visible.height()
		}
		ivMultipleImage.x = viewPosition.visible.left.toFloat()
		ivMultipleImage.y = viewPosition.visible.top.toFloat()
	}

	private fun loadBelow(filePath: String) {
		Glide.with(this)
				.load(filePath)
				.apply(
						RequestOptions()
								.transforms(ImageCutTransformation(viewPosition))
								.dontAnimate()
					  ).into(ivMultipleImage)
	}

	override fun onBackPressed() {
		if (!animator.isLeaving) {
			animator.exit(true)
		}
	}

	override fun onPageScrollStateChanged(state: Int) {

	}

	override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

	}

	override fun onPageSelected(position: Int) {
		drawIndicator(position)
		EventBus.getDefault().post(PreviewChangeEvent(postingTag, containerId, position))
	}

	private fun drawIndicator(position: Int) {
		tvMultipleIndicator.text =
				getString(R.string.stringIndicatorFormat, position + 1, imageList.size)
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onViewPositionChange(positionChangedEvent: PositionChangedEvent) {
		layoutBelow(positionChangedEvent.viewPosition)
		loadBelow(positionChangedEvent.viewBitmapPath)
	}
}