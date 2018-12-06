package me.yangcx.recycler.diff

/**
 * 实体类差异比较接口
 * create by 97457
 * create at 2018/11/29 0029
 */
interface Diffable {
    /**
     * 两个元素是否代表同一对象
     * create by 97457
     * create at 2018/11/29
     * @param other 另一元素
     * @return true->代表用一个对象
     */
    fun isItemSame(other: Any): Boolean

    /**
     * 经过isSame()检查为同一对象之后，调用此方法检查对象内容是否相同
     * create by 97457
     * create at 2018/11/29
     * @param other 另一元素
     * @return true->俩元素内容完全相同
     * @return false->俩元素内容有差异
     */
    fun isContentSame(other: Any): Boolean

    /**
     * 经过isContentSame()检查对象内容有差异之后,调用获取新旧元素具体差异内容
     * create by 97457
     * create at 2018/11/29
     * @param other 另一元素
     * @param changeList 差异内容列表
     */
    fun getChangeList(other: Any, changeList: MutableList<String>)
}