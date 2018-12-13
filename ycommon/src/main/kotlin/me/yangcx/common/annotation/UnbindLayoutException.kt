package me.yangcx.common.annotation

/**
 * 未绑定布局文件错误
 * create by 97457
 * create at 2018/12/13 0013
 */
class UnbindLayoutException : RuntimeException("未通过@BindLayoutRes绑定布局文件,若布局不是布局文件或者不需要布局，请重写setContentView方法")