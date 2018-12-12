package me.yangcx.demos.ui.home

import android.view.View
import android.widget.TextView
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.github.lzyzsd.randomcolor.RandomColor
import com.google.android.flexbox.FlexboxLayoutManager
import com.uber.autodispose.autoDisposable
import me.yangcx.common.extend.click
import me.yangcx.demos.R
import me.yangcx.demos.entity.HomeButtonInfo
import me.yangcx.demos.entity.PostEvent
import me.yangcx.recycler.binder.BaseResBinder
import me.yangcx.recycler.holder.BaseHolder
import org.greenrobot.eventbus.EventBus

/**
 * create by 9745
 * create at 2018/12/08 0008
 */
class BinderOfHomeButton : BaseResBinder<HomeButtonInfo<*>>(R.layout.item_home_button) {

	companion object {
		const val TAG_ITEM_CLICK = "C4Rn2xO1pwg7VAGo9OoGLEA9uEYsuJju"
	}

	private val randomColor by lazy {
		RandomColor()
	}

	override fun onViewCreated(itemView: View) {
		itemView.updateLayoutParams<FlexboxLayoutManager.LayoutParams> {
			flexGrow = 1f
		}
	}

	override fun onAttached(holder: BaseHolder<HomeButtonInfo<*>>, itemView: View) {
		itemView.click()
				.autoDisposable(holder)
				.subscribe {
					EventBus.getDefault().post(PostEvent(TAG_ITEM_CLICK, holder.data))
				}
	}

	override fun drawUi(holder: RecyclerView.ViewHolder, itemView: View, data: HomeButtonInfo<*>) {
		if (itemView is TextView) {
			itemView.setBackgroundColor(randomColor.randomColor())
			itemView.setTextColor(randomColor.randomColor())
			itemView.text = data.title
		}
	}
}