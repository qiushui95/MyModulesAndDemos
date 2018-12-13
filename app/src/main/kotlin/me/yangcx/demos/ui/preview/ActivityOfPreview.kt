package me.yangcx.demos.ui.preview

import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.core.view.isInvisible
import com.alexvasilkov.gestures.animation.ViewPosition
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.activity_preview.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.register
import me.yangcx.common.annotation.BindLayoutRes
import me.yangcx.common.extend.click
import me.yangcx.demos.R
import me.yangcx.demos.base.BaseActivity
import me.yangcx.demos.entity.PostEvent
import me.yangcx.demos.varia.Constants
import me.yangcx.preview.entity.ImageData
import me.yangcx.preview.entity.PositionChangedEvent
import me.yangcx.preview.entity.PreviewChangeEvent
import me.yangcx.preview.entity.PreviewFinishedEvent
import me.yangcx.preview.entity.PreviewStartEvent
import me.yangcx.preview.utils.ImagePreviewUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.android.ext.android.inject

/**
 * 预览展示界面
 * create by 97457
 * create at 2018/12/06 0006
 */
@BindLayoutRes(R.layout.activity_preview)
class ActivityOfPreview : BaseActivity() {

	private val postingTag by lazy {
		"05zBAT8OHl0xZ50A"
	}

	private val startData by lazy {
		Constants.imageList.random()
	}
	private val endData by lazy {
		Constants.imageList.random()
	}
	private val adapter by inject<MultiTypeAdapter>()

	private val layoutManager by lazy {
		FlexboxLayoutManager(this).apply {
			alignItems = AlignItems.STRETCH
		}
	}

	override fun initAfterUi() {
		EventBus.getDefault().register(this)
		loadTop()
		initRecycler()
	}

	override fun onDestroy() {
		rvImage.adapter = null
		EventBus.getDefault().unregister(this)
		super.onDestroy()
	}

	private fun loadTop() {
		Glide.with(this)
				.load(startData.thumbnailData)
				.apply(
						RequestOptions()
								.centerCrop()
				).into(ivPreviewStart)

		Glide.with(this)
				.load(endData.thumbnailData)
				.apply(
						RequestOptions()
								.centerCrop()
				).into(ivPreviewEnd)
	}

	override fun onBindViewListener() {
		click(ivPreviewStart, ivPreviewEnd)
				.subscribe {
					when (it) {
						ivPreviewStart -> {
							ImagePreviewUtils.previewSingleNormal(this, startData, ivPreviewStart, postingTag)
						}

						ivPreviewEnd -> {
							ImagePreviewUtils.previewSingleAvatar(this, endData, ivPreviewEnd, postingTag)
						}
					}
				}
	}

	private fun initRecycler() {
		adapter.register(ImageData::class, BinderOfImage())
		rvImage.adapter = adapter
		rvImage.layoutManager = layoutManager
		adapter.items = Constants.imageList
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onPreviewStart(previewStartEvent: PreviewStartEvent) {
		if (previewStartEvent.checkTag(postingTag)) {
			when (previewStartEvent.targetId) {
				ivPreviewStart.id -> {
					ivPreviewStart.visibility = View.INVISIBLE
				}

				ivPreviewEnd.id -> {
					ivPreviewEnd.visibility = View.INVISIBLE
				}

				rvImage.id -> {
					rvImage.findViewHolderForAdapterPosition(previewStartEvent.position)
							?.itemView
							?.visibility = View.INVISIBLE
				}
			}
		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onPreviewFinished(previewFinishedEvent: PreviewFinishedEvent) {
		if (previewFinishedEvent.checkTag(postingTag)) {
			when (previewFinishedEvent.targetId) {
				ivPreviewStart.id -> {
					ivPreviewStart.visibility = View.VISIBLE
				}

				ivPreviewEnd.id -> {
					ivPreviewEnd.visibility = View.VISIBLE
				}

				rvImage.id -> {
					0.until(rvImage.childCount)
							.map {
								rvImage.getChildAt(it)
							}.filter {
								it.isInvisible
							}.forEach {
								it.visibility = View.VISIBLE
							}
				}
			}
		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onItemClick(postEvent: PostEvent<Int>) {
		if (postEvent.checkTag(BinderOfImage.TAG_ITEM_CLICK)) {
			val position = postEvent.data
			rvImage.findViewHolderForAdapterPosition(position)
					?.also {
						ImagePreviewUtils.previewMultipleImage(this, Constants.imageList, position, rvImage, it.itemView, postingTag)
					}
		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onPreviewChange(previewChangeEvent: PreviewChangeEvent) {
		if (previewChangeEvent.checkTag(postingTag)) {
			val position = previewChangeEvent.position
			when (previewChangeEvent.targetId) {
				rvImage.id -> {
					0.until(rvImage.childCount)
							.map {
								rvImage.getChildAt(it)
							}.filter {
								it.isInvisible
							}.forEach {
								it.visibility = View.VISIBLE
							}
					layoutManager.scrollToPosition(position)
					rvImage.doOnPreDraw { _ ->
						rvImage.findViewHolderForAdapterPosition(position)?.also {
							GlobalScope.launch(Dispatchers.IO) {
								val imageBitmap = ImagePreviewUtils.saveImageBitmap(it.itemView)
								EventBus.getDefault()
										.post(PositionChangedEvent(imageBitmap, ViewPosition.from(it.itemView)))
							}
							it.itemView.visibility = View.INVISIBLE
						}
					}
				}
			}
		}
	}
}