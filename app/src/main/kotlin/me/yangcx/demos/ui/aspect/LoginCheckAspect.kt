package me.yangcx.demos.ui.aspect

import android.util.Log
import com.tencent.mmkv.MMKV
import me.yangcx.demos.varia.UsedApplication
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.jetbrains.anko.toast

/**
 *
 * create by 9745
 * create at 2019/01/26 0026
 */
@Aspect
class LoginCheckAspect {

	@Pointcut("execution(@me.yangcx.demos.ui.aspect.LoginCheck * *(..))&&@annotation(loginCheck)")
	fun pointcutLoginCheck(loginCheck: LoginCheck) {

	}

	@Around("pointcutLoginCheck(loginCheck)")
	fun loginCheck(joinPoint: ProceedingJoinPoint, loginCheck: LoginCheck): Any? {
		return if (!loginCheck.needCheck || MMKV.defaultMMKV().getBoolean(AspectHomeActivity.KEY_TOKEN, false)) {
			joinPoint.proceed(joinPoint.args)
		} else {
			UsedApplication.instance.toast("未登录，请登录")
			null
		}
	}
}