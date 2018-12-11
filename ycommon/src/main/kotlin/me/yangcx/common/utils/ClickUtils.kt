package me.yangcx.common.utils

import android.view.View
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 * 点击事件帮助类
 * create by 9745
 * create at 2018/11/06 0006
 */
internal object ClickUtils {

	/**
	 * 创建多个View单次点击事件的Observable对象
	 * create by 9745
	 * create at 2018/11/06
	 */
	fun createMultipleViewSingleClickObservable(views: Array<out View>, interval: Long, unit: TimeUnit): Observable<View> {
		return Observable.fromArray(*views)
				.flatMap {
					createSingleViewSingleClickObservable(it, interval, unit)
				}
	}

	/**
	 * 创建单个View单次点击事件的Observable对象
	 * create by 9745
	 * create at 2018/11/06
	 * @param interval 单位时间内只会触发一次点击事件
	 * @param unit 时间单位
	 */
	fun createSingleViewSingleClickObservable(view: View, interval: Long, unit: TimeUnit): Observable<View> {
		return view.clicks()
				.map {
					view
				}
				.throttleFirst(interval, unit, AndroidSchedulers.mainThread())
	}

	/**
	 *创建多个View连续点击事件的Observable对象
	 * create by 97457
	 * create at 2018/11/07
	 */
	fun createMultipleViewContinuousClickObservable(views: Array<out View>, interval: Long, unit: TimeUnit): Observable<Pair<Int, View>> {
		return Observable.fromArray(*views)
				.flatMap {
					createSingleViewContinuousClickObservable(it, interval, unit)
				}
	}

	/**
	 *创建单个View连续点击事件的Observable对象
	 * create by 97457
	 * create at 2018/11/07
	 * @param interval 事件在单位事件内才是连续点击,超过重新计数
	 * @param unit 时间单位
	 */
	fun createSingleViewContinuousClickObservable(view: View, interval: Long, unit: TimeUnit): Observable<Pair<Int, View>> {
		val share = view.clicks()
				.map {
					1
				}.share()
		return share.mergeWith(
				share.debounce(interval, unit, AndroidSchedulers.mainThread())
						.map {
							0
						}
							  ).scan { preview, current ->
			if (current == 0) {
				0
			} else {
				preview + 1
			}
		}.filter {
			it > 0
		}.map {
			Pair(it - 1, view)
		}
	}
}