package me.yangcx.preview.glide

import android.graphics.*
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
    private val dst by lazy {
        Rect()
    }
    private val src by lazy {
        Rect(0,0,viewPosition.visible.width(),viewPosition.visible.height())
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
        dst.left = visible.left - view.left
        dst.top = visible.top - view.top
        dst.right = dst.left + visible.width()
        dst.bottom = dst.top + visible.height()
        val result = Bitmap.createBitmap(src.width(), src.height(), Bitmap.Config.ARGB_8888)
        Canvas(result).apply {
            drawBitmap(resource, src, dst, paint)
        }
        return result
    }
}