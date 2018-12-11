package me.yangcx.common.utils

import android.content.Context
import android.os.Environment
import java.io.File

object DirectoryUtils {
	fun getRootCache(context: Context): File {
		return if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState() || !Environment.isExternalStorageRemovable()) {
			context.externalCacheDir ?: context.cacheDir
		} else {
			context.cacheDir
		}
	}
}