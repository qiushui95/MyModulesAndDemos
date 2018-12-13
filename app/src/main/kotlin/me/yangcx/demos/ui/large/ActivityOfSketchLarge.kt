package me.yangcx.demos.ui.large

import com.bumptech.glide.Glide
import com.davemorrissey.labs.subscaleview.ImageSource
import kotlinx.android.synthetic.main.activity_large_image.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.yangcx.common.annotation.BindLayoutRes
import me.yangcx.demos.R
import me.yangcx.demos.base.BaseActivity
import me.yangcx.demos.varia.Constants

/**
 * 加载大图界面
 * create by 97457
 * create at 2018/12/13 0013
 */
@BindLayoutRes(R.layout.activity_large_image)
class ActivityOfSketchLarge : BaseActivity() {

	override fun initAfterUi() {
		GlobalScope.launch(Dispatchers.IO) {
			val downloadResult = Glide.with(this@ActivityOfSketchLarge)
				.downloadOnly()
				.load(Constants.LARGE_IMAGE_URL)
				.submit()
			val downFile = downloadResult.get()
			launch(Dispatchers.Main) {
				ssivLarge.setImage(ImageSource.uri(downFile.absolutePath))
			}
		}
	}

	override fun onBindViewListener() {

	}

	override fun onDestroy() {
		GlobalScope.launch(Dispatchers.IO) {
			Glide.get(applicationContext).clearDiskCache()
		}
		super.onDestroy()
	}
}