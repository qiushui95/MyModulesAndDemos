package me.yangcx.demos.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import me.yangcx.demos.R
import me.yangcx.demos.entity.HomeButtonInfo
import me.yangcx.demos.ui.large.ActivityOfSketchLarge
import me.yangcx.demos.ui.preview.ActivityOfPreview
import me.yangcx.demos.ui.work.ActivityOfWork

/**
 * create by 9745
 * create at 2018/12/08 0008
 */
class ViewModelOfHome(application: Application) : AndroidViewModel(application) {

	val buttonList by lazy {
		listOf(
				HomeButtonInfo(getApplication<Application>().getString(R.string.stringHomeButton1), ActivityOfPreview::class),
				HomeButtonInfo(getApplication<Application>().getString(R.string.stringHomeButton2), ActivityOfSketchLarge::class),
				HomeButtonInfo(getApplication<Application>().getString(R.string.stringHomeButton3), ActivityOfWork::class)
			  )
	}
}