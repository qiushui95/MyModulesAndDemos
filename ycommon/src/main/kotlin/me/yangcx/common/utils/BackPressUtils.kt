package me.yangcx.common.utils

import android.app.Activity
import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.ObservableSubscribeProxy
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import io.reactivex.subjects.PublishSubject
import me.yangcx.common.R
import me.yangcx.common.extend.toastNormal
import java.util.concurrent.TimeUnit

/**
 * 返回键帮助类
 * create by 9745
 * create at 2018/11/10 0010
 */
object BackPressUtils {
    /**
     * 创建返回时间发布者
     * create by 9745
     * create at 2018/11/10
     */
    fun createDoublePublish(): PublishSubject<Int> {
        return PublishSubject.create<Int>()
    }

    /**
     * 双击返回功能初始化被观察者
     * create by 9745
     * create at 2018/11/10
     * @param lifecycleOwner 生命周期绑定对象
     */
    fun initDoubleCheck(publisher: PublishSubject<Int>, lifecycleOwner: LifecycleOwner): ObservableSubscribeProxy<Int> {
        return publisher.mergeWith(
                publisher.debounce(1000, TimeUnit.MILLISECONDS)
                        .map {
                            0
                        }
        ).scan { preview: Int, current: Int ->
            if (current == 0) {
                0
            } else {
                preview + 1
            }
        }.filter {
            it > 0
        }.`as`(AutoDispose.autoDisposable(
                AndroidLifecycleScopeProvider.from(lifecycleOwner, Lifecycle.Event.ON_DESTROY))
        )
    }

    /**
     *双击返回Toast提示
     * create by 9745
     * create at 2018/11/10
     * @param sure 确认返回后执行的函数
     */
    fun doubleToast(observable: ObservableSubscribeProxy<Int>, activity: Activity, sure: () -> Unit) {
        observable.subscribe {
            if (it != 0) {
                sure()
            } else {
                activity.toastNormal(R.string.stringExitTips)
            }
        }
    }

    /**
     * 发布返回事件
     * create by 9745
     * create at 2018/11/10
     */
    fun publishOnPressed(publisher: PublishSubject<Int>) {
        publisher.onNext(1)
    }
}