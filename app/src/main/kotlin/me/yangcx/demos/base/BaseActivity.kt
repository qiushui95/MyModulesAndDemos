package me.yangcx.demos.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.noober.background.BackgroundLibrary
import me.yangcx.common.ui.FoundationActivity

/**
 * 基础Activity
 * create by 97457
 * create at 2018/12/06 0006
 */
abstract class BaseActivity(@LayoutRes layoutRes: Int) : FoundationActivity(layoutRes) {
    override fun onCreate(savedInstanceState: Bundle?) {
        BackgroundLibrary.inject(this)
        super.onCreate(savedInstanceState)
    }
}