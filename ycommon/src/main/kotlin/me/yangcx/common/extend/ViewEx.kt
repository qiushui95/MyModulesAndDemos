package me.yangcx.common.extend

import android.view.View
import androidx.annotation.CheckResult
import io.reactivex.Observable
import me.yangcx.common.utils.ClickUtils
import java.util.concurrent.TimeUnit

/**
 * 绑定多个View的单击事件
 * create by 9745
 * create at 2018/11/06
 */
@CheckResult
fun View.click(vararg views: View, interval: Long = 1000, unit: TimeUnit = TimeUnit.MILLISECONDS): Observable<View> {
	val viewArray = Array(views.size + 1) {
		if (it >= views.size) {
			this
		} else {
			views[it]
		}
	}
	return ClickUtils.createMultipleViewSingleClickObservable(viewArray, interval, unit)
}

/**
 * 绑定多个View的连续点击事件
 * create by 9745
 * create at 2018/11/06
 */
@CheckResult
fun View.clickContinuous(vararg views: View, interval: Long = 1000, unit: TimeUnit = TimeUnit.MILLISECONDS): Observable<Pair<Int, View>> {
	val viewArray = Array(views.size + 1) {
		if (it >= views.size) {
			this
		} else {
			views[it]
		}
	}
	return ClickUtils.createMultipleViewContinuousClickObservable(viewArray, interval, unit)
}
