package me.yangcx.preview.entity

/**
 * 图片显示策略
 * create by 97457
 * create at 2018/12/06 0006
 */
enum class ImageShowType {
    ALL,//先显示缩略图，再显示原图
    JUST_THUMBNAIL,//只显示缩略图
    JUST_ORIGIN//只显示原图
}