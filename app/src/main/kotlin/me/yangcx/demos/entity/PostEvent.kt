package me.yangcx.demos.entity

/**
 * 发送事件实体类
 * create by 97457
 * create at 2018/12/06 0006
 */
data class PostEvent<T : Any>(val tag: String, val data: T) {

	fun checkTag(tag: String) = tag == this.tag
}