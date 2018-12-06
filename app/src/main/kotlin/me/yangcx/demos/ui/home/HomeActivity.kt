package me.yangcx.demos.ui.home

import kotlinx.android.synthetic.main.activity_home.*
import me.yangcx.common.extend.click
import me.yangcx.demos.R
import me.yangcx.demos.base.BaseActivity
import me.yangcx.demos.ui.preview.ActivityPreview

class HomeActivity : BaseActivity(R.layout.activity_home) {
    override fun initAfterUi() {

    }

    override fun onBindViewListener() {
        click(tvPreview)
            .subscribe {
                when (it) {
                    tvPreview -> {
                    ActivityPreview.launch(this)
                    }
                    else -> {
                    }
                }
            }
    }

}
