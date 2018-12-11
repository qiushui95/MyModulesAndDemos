package me.yangcx.common.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class FoundationActivity(@LayoutRes private val layoutRes: Int) : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		if (layoutRes != NO_UI_RES_ID) {
			setContentView(layoutRes)
		}
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