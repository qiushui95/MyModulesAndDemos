package me.yangcx.demos.ui.aspect

import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.activity_asepc_home.*
import me.yangcx.common.annotation.BindLayoutRes
import me.yangcx.demos.R
import me.yangcx.demos.base.BaseActivity

/**
 *
 * create by 9745
 * create at 2019/01/25 0025
 */
@BindLayoutRes(R.layout.activity_asepc_home)
class AspectHomeActivity : BaseActivity() {

	companion object {
		const val KEY_TOKEN = "token"
	}

	override fun initAfterUi() {
		MMKV.initialize(this)
	}

	private fun updateTokenStatus() {
		tvTokenStatus.text = if (MMKV.defaultMMKV().containsKey(KEY_TOKEN)) {
			"已设置token"
		} else {
			"未设置token"
		}
	}

	override fun onResume() {
		super.onResume()
		updateTokenStatus()
	}

	override fun onBindViewListener() {
		tvJumpNext.setOnClickListener {
			AspectCheckActivity.launch(this)
		}
		tvUpdateToken.setOnClickListener {
			MMKV.defaultMMKV().encode(KEY_TOKEN, true)
			updateTokenStatus()
		}
		tvClearToken.setOnClickListener {
			MMKV.defaultMMKV().removeValueForKey(KEY_TOKEN)
			updateTokenStatus()
		}
	}

}