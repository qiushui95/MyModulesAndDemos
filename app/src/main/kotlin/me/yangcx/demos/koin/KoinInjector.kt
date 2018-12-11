package me.yangcx.demos.koin

import android.app.Application
import org.koin.android.ext.android.startKoin

/**
 * koin注入器
 * create by 9745
 * create at 2018/12/09 0009
 */
object KoinInjector {

	fun inject(application: Application) {
		application.startKoin(
			application,
			listOf(
				ModuleOfAdapter.modules,
				ModuleOfViewModel.modules
			)
		)
	}
}