package me.yangcx.recycler.binder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.drakeet.multitype.ItemViewBinder
import me.yangcx.recycler.holder.BaseHolder

abstract class BaseBinder<T : Any> : ItemViewBinder<T, BaseHolder<T>>() {

	/**
	 * 创建显示View
	 */
	abstract fun createView(inflater: LayoutInflater, parent: ViewGroup): View

	/**
	 * 绘制item界面
	 */
	abstract fun drawUi(holder: RecyclerView.ViewHolder, itemView: View, data: T)

	/**
	 * View创建之后的回调
	 * create by 97457
	 * create at 2018/12/12、
	 */
	protected open fun onViewCreated(itemView: View) {}

	/**
	 * item进入屏幕回调
	 * create by 97457
	 * create at 2018/12/12
	 */
	protected open fun onAttached(holder: BaseHolder<T>, itemView: View) {}

	/**
	 * item移出屏幕回调
	 * create by 97457
	 * create at 2018/12/12
	 */
	protected open fun onDetached(holder: BaseHolder<T>, itemView: View) {}

	/**
	 * 数据局部变化、重绘局部界面
	 */
	protected open fun uiChanged(holder: RecyclerView.ViewHolder, itemView: View, data: T, changeList: List<String>) {
	}

	override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): BaseHolder<T> {
		val itemView = createView(inflater, parent)
		onViewCreated(itemView)
		return BaseHolder(itemView)
	}

	override fun onBindViewHolder(holder: BaseHolder<T>, item: T) {
		holder.data = item
		drawUi(holder, holder.itemView, item)
	}

	override fun onBindViewHolder(holder: BaseHolder<T>, item: T, changeList: MutableList<Any>) {
		val firstItem = changeList.firstOrNull()
		if (firstItem is List<*>) {
			val list = firstItem.mapNotNull {
				it as? String
			}
			if (list.isNotEmpty()) {
				holder.data = item
				uiChanged(holder, holder.itemView, item, list)
				return
			}
		}
		onBindViewHolder(holder, item)
	}

	final override fun onViewAttachedToWindow(holder: BaseHolder<T>) {
		holder.onAttached()
		super.onViewAttachedToWindow(holder)
		onAttached(holder, holder.itemView)
	}

	final override fun onViewDetachedFromWindow(holder: BaseHolder<T>) {
		holder.onDetached()
		super.onViewDetachedFromWindow(holder)
		onDetached(holder, holder.itemView)
	}
}