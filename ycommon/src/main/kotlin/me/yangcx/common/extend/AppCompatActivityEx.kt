package me.yangcx.common.extend

import android.view.View
import androidx.annotation.CheckResult
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.ObservableSubscribeProxy
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import me.yangcx.common.utils.ClickUtils
import java.util.concurrent.TimeUnit

/**
 * Activity的扩展
 * create by 9745
 * create at 2018/11/06 0006
 */

/**
 * 绑定多个View的单击事件
 * create by 9745
 * create at 2018/11/06
 */
@CheckResult
fun AppCompatActivity.click(vararg views: View, interval: Long = 1000, unit: TimeUnit = TimeUnit.MILLISECONDS): ObservableSubscribeProxy<View> {
    return ClickUtils.createMultipleViewSingleClickObservable(views, interval, unit)
            .`as`(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
}

/**
 * 绑定多个View的单击事件
 * create by 9745
 * create at 2018/11/06
 * @param disposeEvent 解绑生命周期
 */
@CheckResult
fun AppCompatActivity.click(vararg views: View, disposeEvent: Lifecycle.Event, interval: Long = 1000, unit: TimeUnit = TimeUnit.MILLISECONDS): ObservableSubscribeProxy<View> {
    return ClickUtils.createMultipleViewSingleClickObservable(views, interval, unit)
            .`as`(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this, disposeEvent)))
}

/**
 * 绑定多个View的连续点击事件
 * create by 9745
 * create at 2018/11/06
 */
@CheckResult
fun AppCompatActivity.clickContinuous(vararg views: View, interval: Long = 1000, unit: TimeUnit = TimeUnit.MILLISECONDS): ObservableSubscribeProxy<Pair<Int, View>> {
    return ClickUtils.createMultipleViewContinuousClickObservable(views, interval, unit)
            .`as`(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
}

/**
 * 绑定多个View的连续点击事件
 * create by 9745
 * create at 2018/11/06
 * @param disposeEvent 解绑生命周期
 */
@CheckResult
fun AppCompatActivity.clickContinuous(vararg views: View, disposeEvent: Lifecycle.Event, interval: Long = 1000, unit: TimeUnit = TimeUnit.MILLISECONDS): ObservableSubscribeProxy<Pair<Int, View>> {
    return ClickUtils.createMultipleViewContinuousClickObservable(views, interval, unit)
            .`as`(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this, disposeEvent)))
}