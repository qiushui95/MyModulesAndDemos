package me.yangcx.demos.koin

import me.yangcx.demos.ui.home.ViewModelOfHome
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module

/**
 * ViewModel注入提供类
 * create by 9745
 * create at 2018/12/09 0009
 */
object ModuleOfViewModel {

	val modules = module {
		viewModel<ViewModelOfHome>()
	}
}