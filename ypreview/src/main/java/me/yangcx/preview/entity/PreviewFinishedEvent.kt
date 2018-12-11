package me.yangcx.preview.entity

/**
 * 预览结束
 * create by 97457
 * create at 2018/12/06 0006
 */
data class PreviewFinishedEvent(val postingTag: String, val targetId: Int) {

	fun checkTag(tag: String) = postingTag == tag
}