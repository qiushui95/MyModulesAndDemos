package me.yangcx.recycler.manager

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView快速滑动线性布局
 * create by 97457
 * create at 2018/12/06
 */
class FastScrollLinearManger(
    context: Context, @RecyclerView.Orientation orientation: Int,
    reverseLayout: Boolean
) : LinearLayoutManager(context, orientation, reverseLayout) {
    private val scroller by lazy { FastLinearSmoothScroller(context) }

    constructor(context: Context) : this(context, RecyclerView.VERTICAL, false)

    override fun smoothScrollToPosition(
        recyclerView: RecyclerView,
        state: RecyclerView.State,
        position: Int
    ) {
        scroller.targetPosition = position
        startSmoothScroll(scroller)
    }
}
