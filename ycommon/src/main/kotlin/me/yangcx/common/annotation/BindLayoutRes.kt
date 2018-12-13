package me.yangcx.common.annotation

import androidx.annotation.LayoutRes
import java.lang.annotation.Inherited

/**
 * 绑定布局文件注解
 * create by 97457
 * create at 2018/12/13 0013
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
annotation class BindLayoutRes(@LayoutRes val layoutRes: Int)