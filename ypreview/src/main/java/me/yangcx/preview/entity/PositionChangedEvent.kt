package me.yangcx.preview.entity

import com.alexvasilkov.gestures.animation.ViewPosition

/**
 * 图片位置变化
 * create by 97457
 * create at 2018/12/06
 */
data class PositionChangedEvent(
        val viewBitmapPath: String,
        val viewPosition: ViewPosition
)