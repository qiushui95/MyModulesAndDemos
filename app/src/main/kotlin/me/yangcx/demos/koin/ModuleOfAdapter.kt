package me.yangcx.demos.koin

import me.drakeet.multitype.MultiTypeAdapter
import org.koin.dsl.module.module

/**
 * 适配器注入提供类
 * create by 9745
 * create at 2018/12/09 0009
 */
object ModuleOfAdapter {
    val modules = module {
        factory {
            MultiTypeAdapter()
        }
    }
}