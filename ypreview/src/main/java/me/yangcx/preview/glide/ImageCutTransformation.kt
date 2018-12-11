package me.yangcx.preview.glide

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import com.alexvasilkov.gestures.animation.ViewPosition
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest

/**
 * 切除图片未显示的部分图片转换器
 * create by 97457
 * create at 2018/12/06
 */
class ImageCutTransformation(private val viewPosition: ViewPosition) : BitmapTransformation() {

	private val src by lazy {
		Rect()
	}
	private val dst by lazy {
		Rect(0, 0, viewPosition.visible.width(), viewPosition.visible.height())
	}

	private val paint by lazy {
		Paint().apply {
			color = Color.RED
			isAntiAlias = true
		}
	}

	override fun updateDiskCacheKey(messageDigest: MessageDigest) {

	}

	override fun transform(pool: BitmapPool, resource: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
		val visible = viewPosition.visible
		val view = viewPosition.view
		src.left = visible.left - view.left
		src.top = visible.top - view.top
		src.right = src.left + visible.width()
		src.bottom = src.top + visible.height()
		val result = Bitmap.createBitmap(dst.width(), dst.height(), Bitmap.Config.ARGB_8888)
		Canvas(result).apply {
			drawBitmap(resource, src, dst, paint)
		}
		return result
	}
}