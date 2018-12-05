package me.yangcx.common.extend

import java.io.File

fun File.getChild(childName: String): File {
    return File(this, childName)
}

fun File.newChild(childName: String): File {
    val child = getChild(childName)
    if (!child.exists()) {
        child.mkdirs()
    }
    return child
}