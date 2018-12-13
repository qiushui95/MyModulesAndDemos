package me.yangcx.demos.base

import android.os.Bundle
import androidx.annotation.LayoutRes

/**
 * 绑定ViewModel的activity基础类
 * create by 97457
 * create at 2018/11/29 0029
 */
abstract class ViewModelActivity : BaseActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		onBindViewModel()
	}

	/**
	 * 绑定ViewModel
	 * create by 97457
	 * create at 2018/11/29
	 */
	protected abstract fun onBindViewModel()
}