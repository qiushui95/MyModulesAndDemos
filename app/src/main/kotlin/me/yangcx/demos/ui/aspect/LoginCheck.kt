package me.yangcx.demos.ui.aspect

/**
 *
 * create by 9745
 * create at 2019/01/26 0026
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class LoginCheck(val needCheck: Boolean = true) {
}