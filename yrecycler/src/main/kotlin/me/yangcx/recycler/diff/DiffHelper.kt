package me.yangcx.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import me.yangcx.recycler.diff.Diffable

/**
 * 内容比较实现
 * create by 97457
 * create at 2018/11/29 0029
 */
class DiffHelper : DiffUtil.Callback() {
    private var oldList: List<Any?>? = null
    private var newList: List<Any?>? = null

    override fun getOldListSize() = oldList?.size ?: 0
    override fun getNewListSize() = newList?.size ?: 0

    private fun getOldItem(position: Int) = oldList?.get(position)
    private fun getNewItem(position: Int) = newList?.get(position)

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = getOldItem(oldItemPosition)
        val newItem = getNewItem(newItemPosition)
        return if (oldItem is Diffable && newItem is Diffable) {
            oldItem.isItemSame(newItem)
        } else {
            oldItem == newItem
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = getOldItem(oldItemPosition)
        val newItem = getNewItem(newItemPosition)
        return if (oldItem is Diffable && newItem is Diffable) {
            oldItem.isContentSame(newItem)
        } else {
            oldItem == newItem
        }
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return getChangeList(oldItemPosition, newItemPosition)
    }

    private fun getChangeList(oldItemPosition: Int, newItemPosition: Int): List<String> {
        val changeList = mutableListOf<String>()
        val oldItem = getOldItem(oldItemPosition)
        val newItem = getNewItem(newItemPosition)
        if (oldItem is Diffable && newItem is Diffable) {
            oldItem.getChangeList(newItem, changeList)
        }
        return changeList
    }

    fun diff(oldList: List<Any?>, newList: List<Any?>, detectMoves: Boolean = true): DiffUtil.DiffResult {
        this.oldList = oldList
        this.newList = newList
        return DiffUtil.calculateDiff(this, detectMoves)
    }
}