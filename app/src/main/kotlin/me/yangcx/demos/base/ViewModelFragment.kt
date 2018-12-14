package me.yangcx.demos.base

import android.os.Bundle
import android.view.View

/**
 * 绑定ViewModel的Fragment
 * create by 97457
 * create at 2018/12/14 0014
 */
abstract class ViewModelFragment : BaseFragment() {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		onBindViewModel(view)
	}

	protected abstract fun onBindViewModel(rootView: View)
}