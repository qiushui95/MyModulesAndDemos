package me.yangcx.recycler.binder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import me.drakeet.multitype.ItemViewBinder
import me.yangcx.recycler.holder.BaseHolder

abstract class BaseBinder<T>(protected val lifecycleOwner: LifecycleOwner) :
    ItemViewBinder<T, RecyclerView.ViewHolder>() {

    /**
     * 创建显示View
     */
    abstract fun createView(inflater: LayoutInflater, parent: ViewGroup): View

    /**
     * 初始化
     */
    abstract fun initThis(holder: RecyclerView.ViewHolder, itemView: View)

    /**
     * 绘制item界面
     */
    abstract fun drawUi(holder: RecyclerView.ViewHolder, itemView: View, data: T)

    /**
     * 数据局部变化、重绘局部界面
     */
    protected open fun uiChanged(
        holder: RecyclerView.ViewHolder,
        itemView: View,
        data: T,
        changeList: List<String>
    ) {

    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val holder = BaseHolder(createView(inflater, parent))
        initThis(holder, holder.itemView)
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: T) {
        drawUi(holder, holder.itemView, item)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: T,
        changeList: MutableList<Any>
    ) {
        val firstItem = changeList.firstOrNull()
        @Suppress("UNCHECKED_CAST")
        val list = firstItem as? List<String>
        if (list != null) {
            uiChanged(holder, holder.itemView, item, list)
        } else {
            onBindViewHolder(holder, item)
        }
    }

}