package me.yangcx.recycler.manager

import android.content.Context
import androidx.recyclerview.widget.LinearSmoothScroller

/**
 * RecyclerView快速滑动器
 * create by 97457
 * create at 2018/12/06
 */
class FastLinearSmoothScroller(context: Context) : LinearSmoothScroller(context) {
    override fun calculateTimeForScrolling(dx: Int): Int {
        val realDx = if (dx > 3000) {
            3000
        } else {
            dx
        }
        return super.calculateTimeForScrolling(realDx)
    }
}