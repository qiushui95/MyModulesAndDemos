package me.yangcx.demos.entity

import android.app.Activity
import kotlin.reflect.KClass

/**
 * Home界面按钮信息
 * create by 9745
 * create at 2018/12/08 0008
 */
class HomeButtonInfo<T : Activity>(
		val title: CharSequence,
		val clickClass: KClass<T>
								  )