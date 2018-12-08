package me.yangcx.demos.ui.home

import android.view.View
import android.widget.TextView
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.github.lzyzsd.randomcolor.RandomColor
import com.google.android.flexbox.FlexboxLayoutManager
import me.yangcx.common.extend.click
import me.yangcx.demos.R
import me.yangcx.demos.entity.HomeButtonInfo
import me.yangcx.demos.entity.PostEvent
import me.yangcx.recycler.binder.BaseResBinder
import org.greenrobot.eventbus.EventBus

/**
 * create by 9745
 * create at 2018/12/08 0008
 */
class BinderHomeButton(lifecycleOwner: LifecycleOwner) : BaseResBinder<HomeButtonInfo<*>>(R.layout.item_home_button, lifecycleOwner) {
    companion object {
        const val TAG_ITEM_CLICK = "C4Rn2xO1pwg7VAGo9OoGLEA9uEYsuJju"
    }

    private val randomColor by lazy {
        RandomColor()
    }

    override fun initThis(holder: RecyclerView.ViewHolder, itemView: View) {
        lifecycleOwner.click(itemView)
                .subscribe {
                    EventBus.getDefault().post(PostEvent(TAG_ITEM_CLICK, data))
                }
        itemView.updateLayoutParams<FlexboxLayoutManager.LayoutParams> {
            flexGrow = 1f
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