package me.yangcx.preview.entity

/**
 * 切换预览图片位置
 * create by 97457
 * create at 2018/12/06 0006
 */
data class PreviewChangeEvent(val postingTag: String, val targetId: Int, val position: Int) {

	fun checkTag(tag: String) = postingTag == tag
}