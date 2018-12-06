package me.yangcx.preview.entity

/**
 * 预览开始
 * create by 97457
 * create at 2018/12/06
 */
data class PreviewStartEvent(val postingTag: String, val targetId: Int){
    fun checkTag(tag: String) = postingTag == tag
}