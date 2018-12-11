package me.yangcx.common.utils

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import android.view.View
import me.yangcx.common.R

/**
 * 系统UI帮助类
 * create by 9745
 * create at 2018/11/11 0011
 */
object SystemUiUtils {

	/**
	 * 是否可以开启沉浸状态栏
	 * create by 9745
	 * create at 2018/11/11
	 * params description:
	 * return description:
	 */
	@SuppressLint("ObsoleteSdkInt")
	private fun isCanHaveTransparentDecor() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

	/**
	 * 获取系统dimen值
	 * create by 9745
	 * create at 2018/11/11
	 * @param key 系统key
	 */
	private fun getSystemDimen(key: String): Int {
		var result = 0
		val resources = Resources.getSystem()
		val resourceId = resources.getIdentifier(key, "dimen", "android")
		if (resourceId > 0) {
			result = resources.getDimensionPixelSize(resourceId)
		}
		return result
	}

	/**
	 * 获取状态栏高度
	 * create by 9745
	 * create at 2018/11/11
	 */
	fun getStatusHeight() = getSystemDimen("status_bar_height")

	/**
	 *给view添加或删除大小为statusbar高度的上方padding
	 * create by 9745
	 * create at 2018/11/11 0011
	 */
	fun fitStatus(
			view: View, isFits: Boolean = !(view.getTag(R.id.idTagFitStatus)?.toString()
											?: "false").toBoolean()
				 ) {
		val hasFit = (view.getTag(R.id.idTagFitStatus)?.toString()
					  ?: "false").toBoolean()
		if (isCanHaveTransparentDecor() && hasFit != isFits) {
			val statusHeight = getStatusHeight()
			val paddingTop = view.paddingTop
			if (!isFits) {
				view.setPadding(view.paddingStart, paddingTop - statusHeight, view.paddingEnd, view.paddingBottom)
				view.layoutParams.height -= statusHeight
			} else {
				view.setPadding(view.paddingStart, paddingTop + statusHeight, view.paddingEnd, view.paddingBottom)
				view.layoutParams.height += statusHeight
			}
			view.setTag(R.id.idTagFitStatus, isFits)
		}
	}

	/**
	 * 获取导航键高度
	 * create by 9745
	 * create at 2018/11/11
	 */
	fun getNavigationHeight() = getSystemDimen("navigation_bar_height")

	/**
	 *给view添加或删除大小为Navigation高度的下方padding
	 * create by 9745
	 * create at 2018/11/11 0011
	 */
	fun fitNavigation(
			view: View, isFits: Boolean = !(view.getTag(R.id.idTagFitNavigation)?.toString()
											?: "false").toBoolean()
					 ) {
		val hasFit = (view.getTag(R.id.idTagFitNavigation)?.toString()
					  ?: "false").toBoolean()
		if (isCanHaveTransparentDecor() && hasFit != isFits) {
			val navigationHeight = getNavigationHeight()
			val paddingBottom = view.paddingBottom
			if (!isFits) {
				view.setPadding(view.paddingStart, view.paddingTop, view.paddingEnd, paddingBottom - navigationHeight)
				view.layoutParams.height -= navigationHeight
			} else {
				view.setPadding(view.paddingStart, view.paddingTop, view.paddingEnd, paddingBottom + navigationHeight)
				view.layoutParams.height += navigationHeight
			}
			view.setTag(R.id.idTagFitNavigation, isFits)
		}
	}
}