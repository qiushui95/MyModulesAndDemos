package me.yangcx.demos.ui.preview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.flexbox.FlexboxLayoutManager
import com.uber.autodispose.autoDisposable
import me.yangcx.common.extend.click
import me.yangcx.demos.entity.PostEvent
import me.yangcx.preview.entity.ImageData
import me.yangcx.recycler.binder.BaseBinder
import me.yangcx.recycler.holder.BaseHolder
import org.greenrobot.eventbus.EventBus

/**
 * create by 97457
 * create at 2018/12/06 0006
 */
class BinderOfImage : BaseBinder<ImageData>() {

	companion object {
		const val TAG_ITEM_CLICK = "4LaSyVTTIosNdDWa"
	}

	override fun createView(inflater: LayoutInflater, parent: ViewGroup): View {
		return ImageView(inflater.context)
			.apply {
				layoutParams = FlexboxLayoutManager.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					300
				).apply {
					setMargins(2, 2, 2, 2)
					flexGrow = 1f
					scaleType = ImageView.ScaleType.CENTER_CROP
				}
			}
	}

	override fun initThis(holder: BaseHolder, itemView: View) {
		itemView.click()
			.autoDisposable(holder)
			.subscribe {
				EventBus.getDefault().post(PostEvent(TAG_ITEM_CLICK, holder.adapterPosition))
			}
	}

	override fun drawUi(holder: BaseHolder, itemView: View, data: ImageData) {
		Glide.with(itemView)
			.load(data.thumbnailData)
			.apply(
				RequestOptions()
					.dontTransform()
			).into(itemView as ImageView)
	}
}