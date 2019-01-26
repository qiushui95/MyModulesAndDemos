package me.yangcx.demos.ui.aspect

import android.content.Context
import android.content.Intent
import android.view.View
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.activity_asepc_check.*
import me.yangcx.common.annotation.BindLayoutRes
import me.yangcx.demos.R
import me.yangcx.demos.base.BaseActivity
import org.jetbrains.anko.toast

/**
 *
 * create by 9745
 * create at 2019/01/25 0025
 */
@BindLayoutRes(R.layout.activity_asepc_check)
class AspectCheckActivity : BaseActivity() {

	companion object {
		fun launch(context: Context) {
			context.startActivity(Intent(context, AspectCheckActivity::class.java))
		}
	}

	@LoginCheck
	override fun initAfterUi() {

	}

	override fun onBindViewListener() {
		tvDone.setOnClickListener(this::doWithToken)
		tvClearToken.setOnClickListener(this::clearToken)
	}

	@LoginCheck
	private fun doWithToken(view: View) {
		toast("执行需要登录状态的函数成功")
	}

	private fun clearToken(view: View) {
		MMKV.defaultMMKV().removeValueForKey(AspectHomeActivity.KEY_TOKEN)
	}
}