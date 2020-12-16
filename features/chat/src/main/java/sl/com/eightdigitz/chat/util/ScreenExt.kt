package sl.com.eightdigitz.chat.util

import android.content.Context
import android.util.TypedValue

fun Context.getScreenWidth(): Int {
    return this.resources.configuration.screenWidthDp.toFloat()
        .dpToPx(this)
}

fun Float.dpToPx(context: Context): Int {
    return Math.round(
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, this,
            context.resources.displayMetrics
        ) + 0.5f
    )
}