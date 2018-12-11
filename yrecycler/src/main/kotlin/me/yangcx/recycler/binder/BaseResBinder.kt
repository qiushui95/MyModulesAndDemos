package me.yangcx.recycler.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes

abstract class BaseResBinder<T : Any>(@LayoutRes private val layoutRes: Int) : BaseBinder<T>() {
    final override fun createView(inflater: LayoutInflater, parent: ViewGroup) =
        inflater.inflate(layoutRes, parent, false)!!
}