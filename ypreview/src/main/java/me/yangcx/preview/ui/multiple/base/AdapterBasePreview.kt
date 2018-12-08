package me.yangcx.preview.ui.multiple.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.alexvasilkov.gestures.commons.RecyclePagerAdapter
import com.alexvasilkov.gestures.views.GestureImageView
import com.bumptech.glide.request.RequestOptions
import me.yangcx.preview.entity.ImageData
import me.yangcx.preview.utils.ImageLoadUtils

/**
 *
 * create by 97457
 * create at 2018/11/18 0018
 */
internal abstract class AdapterBasePreview(private val inflater: LayoutInflater, private val requestOptions: RequestOptions, private val dataList: List<ImageData>) : RecyclePagerAdapter<RecyclePagerAdapter.ViewHolder>() {

    protected abstract fun createView(inflater: LayoutInflater, container: ViewGroup): View

    fun getGestureImageView(position: Int): GestureImageView? {
        return getViewHolder(position)?.itemView as? GestureImageView
    }

    override fun onCreateViewHolder(container: ViewGroup): ViewHolder {
        return RecyclePagerAdapter.ViewHolder(createView(inflater, container))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        ImageLoadUtils.loadImage(item, holder.itemView as ImageView, requestOptions)
    }

    override fun getCount(): Int {
        return dataList.size
    }
}