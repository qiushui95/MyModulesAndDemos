package me.yangcx.demos.varia

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.multidex.MultiDexApplication
import me.yangcx.demos.koin.KoinInjector
import org.greenrobot.eventbus.EventBus

/**
 * create by 97457
 * create at 2018/12/06 0006
 */
class UsedApplication : MultiDexApplication(), Application.ActivityLifecycleCallbacks {

	override fun onCreate() {
		super.onCreate()
		registerLifecycle()
		KoinInjector.inject(this)
	}

	private fun registerLifecycle() {
		registerActivityLifecycleCallbacks(this)
	}

	private fun registerEventBus(target: Any, register: Boolean) {
		if (target is IEventBus) {
			if (register) {
				EventBus.getDefault().register(target)
			} else {
				EventBus.getDefault().unregister(target)
			}
		}
	}

	override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
		registerEventBus(activity, true)
		registerFragmentLifecycle(activity)
	}

	override fun onActivityStarted(activity: Activity) {

	}

	override fun onActivityResumed(activity: Activity) {

	}

	override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {

	}

	override fun onActivityPaused(activity: Activity) {

	}

	override fun onActivityStopped(activity: Activity) {

	}

	override fun onActivityDestroyed(activity: Activity) {
		registerEventBus(activity, false)
	}

	private fun registerFragmentLifecycle(activity: Activity) {
		if (activity is FragmentActivity) {
			activity.supportFragmentManager.registerFragmentLifecycleCallbacks(object :
																					   FragmentManager.FragmentLifecycleCallbacks() {
				override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
					registerEventBus(f, true)
				}

				override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
					registerEventBus(f, false)
				}
			}, true)
		}
	}
}
