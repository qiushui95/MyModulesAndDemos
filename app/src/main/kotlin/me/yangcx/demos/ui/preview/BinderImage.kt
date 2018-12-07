package me.yangcx.demos.ui.preview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.google.android.flexbox.FlexboxLayoutManager
import me.yangcx.common.extend.click
import me.yangcx.demos.entity.PostEvent
import me.yangcx.preview.entity.ImageData
import me.yangcx.recycler.binder.BaseBinder
import org.greenrobot.eventbus.EventBus

/**
 * create by 97457
 * create at 2018/12/06 0006
 */
class BinderImage(lifecycleOwner: LifecycleOwner) : BaseBinder<ImageData>(lifecycleOwner) {
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
                    scaleType=ImageView.ScaleType.CENTER_CROP
                }
            }
    }

    override fun initThis(holder: RecyclerView.ViewHolder, itemView: View) {
        lifecycleOwner.click(itemView, disposeEvent = Lifecycle.Event.ON_DESTROY)
            .subscribe {
                EventBus.getDefault().post(
                    PostEvent(
                        TAG_ITEM_CLICK,
                        holder.adapterPosition
                    )
                )
            }
    }

    override fun drawUi(holder: RecyclerView.ViewHolder, itemView: View, data: ImageData) {
        Glide.with(itemView)
            .load(data.thumbnailData)
            .apply(
                RequestOptions()
                    .dontTransform()
            ).into(itemView as ImageView)
    }
}