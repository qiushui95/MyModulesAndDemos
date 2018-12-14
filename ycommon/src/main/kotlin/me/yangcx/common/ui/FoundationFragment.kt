package me.yangcx.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.yangcx.common.annotation.BindLayoutRes
import me.yangcx.common.annotation.UnbindLayoutException

/**
 * 基础Fragment
 * create by 97457
 * create at 2018/11/04 0004
 */
abstract class FoundationFragment : Fragment() {

	/**
	 * 设置布局View
	 * create by 97457
	 * create at 2018/12/13
	 */
	protected open fun setContentView(): Int {
		val annotation = javaClass.getAnnotation(BindLayoutRes::class.java)
		if (annotation != null && annotation.layoutRes > 0) {
			return annotation.layoutRes
		} else {
			throw UnbindLayoutException()
		}
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(setContentView(), container, false)
	}


	/**
	 * 在初始化
	 * create by 97457
	 * create at 2018/11/04 0004
	 * params description:
	 * return description:
	 */
	abstract fun initThis(rootView:View)

	/**
	 * 绑定控件事件
	 * create by 97457
	 * create at 2018/11/04 0004
	 * params description:
	 * return description:
	 */
	abstract fun bindViewListener(rootView:View)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initThis(view)
		bindViewListener(view)
	}
}