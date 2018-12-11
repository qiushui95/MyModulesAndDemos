package me.yangcx.recycler.binder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.drakeet.multitype.ItemViewBinder
import me.yangcx.recycler.holder.BaseHolder

abstract class BaseBinder<T : Any> : ItemViewBinder<T, BaseHolder>() {
	protected lateinit var data: T
	/**
	 * 创建显示View
	 */
	abstract fun createView(inflater: LayoutInflater, parent: ViewGroup): View

	/**
	 * 初始化
	 */
	abstract fun initThis(holder: BaseHolder, itemView: View)

	/**
	 * 绘制item界面
	 */
	abstract fun drawUi(holder: BaseHolder, itemView: View, data: T)

	/**
	 * 数据局部变化、重绘局部界面
	 */
	protected open fun uiChanged(holder: BaseHolder, itemView: View, data: T, changeList: List<String>) {
	}

	override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): BaseHolder {
		val holder = BaseHolder(createView(inflater, parent))
		initThis(holder, holder.itemView)
		return holder
	}

	override fun onBindViewHolder(holder: BaseHolder, item: T) {
		this.data = item
		drawUi(holder, holder.itemView, item)
	}

	override fun onBindViewHolder(holder: BaseHolder, item: T, changeList: MutableList<Any>) {
		this.data = item
		val firstItem = changeList.firstOrNull()
		@Suppress("UNCHECKED_CAST")
		val list = firstItem as? List<String>
		if (list != null) {
			uiChanged(holder, holder.itemView, item, list)
		} else {
			onBindViewHolder(holder, item)
		}
	}

	override fun onViewRecycled(holder: BaseHolder) {
		holder.onDestroy()
		super.onViewRecycled(holder)
	}
}