package me.yangcx.demos.ui.work

import android.util.Log
import androidx.lifecycle.Observer
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import me.yangcx.common.annotation.BindLayoutRes
import me.yangcx.demos.R
import me.yangcx.demos.base.BaseActivity
import java.util.concurrent.TimeUnit

/**
 *
 * create by 9745
 * create at 2018/12/20 0020
 */
@BindLayoutRes(R.layout.activity_work)
class ActivityOfWork : BaseActivity() {

	override fun initAfterUi() {
		val request = PeriodicWorkRequestBuilder<LauncherWorker>(15, TimeUnit.MINUTES)
				.addTag("tag")
				.build()
		WorkManager.getInstance().enqueueUniquePeriodicWork("unique", ExistingPeriodicWorkPolicy.KEEP, request)
		WorkManager.getInstance()
				.getWorkInfosByTagLiveData("tag")
				.observe(this, Observer {
					it.forEach {
						val key = it.outputData.getString("key")
						Log.e("======", "key:$key  thread:${Thread.currentThread().name}")
					}
				})
	}

	override fun onBindViewListener() {
	}

	override fun onDestroy() {
		super.onDestroy()
		WorkManager.getInstance()
				.cancelAllWorkByTag("tag")
	}
}