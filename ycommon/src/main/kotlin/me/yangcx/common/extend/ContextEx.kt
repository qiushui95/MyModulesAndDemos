package me.yangcx.common.extend

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.toastNormal(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT, vararg args: Any?): Toast {
    val message = getString(resId, args)
    return toastNormal(message, duration)
}

fun Context.toastNormal(message: CharSequence, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(this, message, duration).apply {
        show()
    }
}

fun Context.toastSuccess(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT, vararg args: Any?): Toast {
    val message = getString(resId, args)
    return toastNormal(message, duration)
}

fun Context.toastSuccess(message: CharSequence, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(this, message, duration).apply {
        show()
    }
}

fun Context.toastFailed(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT, vararg args: Any?): Toast {
    val message = getString(resId, args)
    return toastNormal(message, duration)
}

fun Context.toastFailed(message: CharSequence, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(this, message, duration).apply {
        show()
    }
}

fun Context.toastWarning(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT, vararg args: Any?): Toast {
    val message = getString(resId, args)
    return toastNormal(message, duration)
}

fun Context.toastWarning(message: CharSequence, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(this, message, duration).apply {
        show()
    }
}