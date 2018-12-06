package me.yangcx.recycler.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleOwner

abstract class BaseResBinder<T>(@LayoutRes private val layoutRes: Int, lifecycleOwner: LifecycleOwner) :
    BaseBinder<T>(lifecycleOwner) {
    final override fun createView(inflater: LayoutInflater, parent: ViewGroup) =
        inflater.inflate(layoutRes, parent, false)!!
}