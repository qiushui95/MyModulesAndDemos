package me.yangcx.common.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.yangcx.common.annotation.BindLayoutRes
import me.yangcx.common.annotation.UnbindLayoutException

abstract class FoundationActivity : AppCompatActivity() {
	/**
	 * 设置布局View
	 * create by 97457
	 * create at 2018/12/13
	 */
	protected open fun setContentView() {
		val annotation = javaClass.getAnnotation(BindLayoutRes::class.java)
		if (annotation != null && annotation.layoutRes > 0) {
			setContentView(annotation.layoutRes)
		} else {
			throw UnbindLayoutException()
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView()
		initAfterUi()
		onBindViewListener()
	}


	/**
	 * 在UI创建之后初始化
	 * create by 97457
	 * create at 2018/11/04 0004
	 * params description:
	 * return description:
	 */
	protected abstract fun initAfterUi()

	/**
	 * 绑定控件事件
	 * create by 97457
	 * create at 2018/11/04 0004
	 * params description:
	 * return description:
	 */
	protected abstract fun onBindViewListener()


	companion object {
		const val NO_UI_RES_ID = 0
	}
}