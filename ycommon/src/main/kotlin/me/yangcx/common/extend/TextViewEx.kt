package me.yangcx.common.extend

import android.widget.TextView
import androidx.annotation.StringRes

fun TextView.setText(@StringRes resid: Int, vararg args: Any) {
    text = context.getString(resid, *args)
}