package me.yangcx.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * 基础Fragment
 * create by 97457
 * create at 2018/11/04 0004
 */
abstract class FoundationFragment(@LayoutRes private val layoutRes: Int) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutRes, container, false)
    }


    /**
     * 在初始化
     * create by 97457
     * create at 2018/11/04 0004
     * params description:
     * return description:
     */
    abstract fun initThis()

    /**
     * 绑定控件事件
     * create by 97457
     * create at 2018/11/04 0004
     * params description:
     * return description:
     */
    abstract fun bindViewListener()


    override fun onResume() {
        super.onResume()
        initThis()
        bindViewListener()
    }
}