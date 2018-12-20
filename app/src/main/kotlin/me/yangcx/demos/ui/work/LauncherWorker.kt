package me.yangcx.demos.ui.work

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.orhanobut.logger.Logger
import org.jetbrains.anko.startActivity

/**
 *
 * create by 9745
 * create at 2018/12/20 0020
 */
class LauncherWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

	override fun doWork(): Result {
		applicationContext.startActivity<ActicityOfWorkStart>()
		Log.e("======", "start  thread:${Thread.currentThread().name}")
		return Result.success(
				Data.Builder()
						.putString("key", "ycx")
						.build()

		)
	}
}