package sl.com.eightdigitz.presentation.extensions

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.TypedValue
import android.view.Gravity
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import sl.com.eightdigitz.presentation.R

fun ActionBar.setActionBar(
    context: Context,
    title: String,
    isHomeUpEnables: Boolean = false,
    isCenterTitle: Boolean = false
) {
    val tv = TextView(context.applicationContext)

    val lp = RelativeLayout.LayoutParams(
        RelativeLayout.LayoutParams.WRAP_CONTENT,
        RelativeLayout.LayoutParams.WRAP_CONTENT
    )

    tv.layoutParams = lp

    tv.text = title

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        tv.setTextAppearance(R.style.ToolbarTextWhiteStyle)
    } else {
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18f)
        ResourcesCompat.getFont(tv.context, R.font.sf_pro_display_bold)
            ?.also { mTypeFace ->
                tv.typeface = mTypeFace
            }
    }

    if (isCenterTitle) {
        tv.setPadding(24, 0, 0, 0)
    }

    this.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
    this.customView = tv
    this.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.bg_support_actionbar))

    this.elevation = 0.dpToPx()
    if (isHomeUpEnables) {
        this.setDisplayHomeAsUpEnabled(true)
        this.setDisplayShowHomeEnabled(true)
        this.setHomeAsUpIndicator(R.drawable.ic_back_white)
    }
}

fun ActionBar.setAppActionBar(
    context: Context,
    title: String,
    isHomeUpEnables: Boolean = false,
    isCenterTitle: Boolean = false
) {
    val tv = TextView(context.applicationContext)

    val lp = RelativeLayout.LayoutParams(
        RelativeLayout.LayoutParams.WRAP_CONTENT,
        RelativeLayout.LayoutParams.WRAP_CONTENT
    )

    tv.layoutParams = lp

    tv.text = title

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        tv.setTextAppearance(R.style.ToolbarBlackTitleStyle)
    } else {
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24f)
        ResourcesCompat.getFont(tv.context, R.font.sf_pro_display_regular)
            ?.also { mTypeFace ->
                tv.typeface = mTypeFace
            }
    }

    if (isCenterTitle) {
        tv.setPadding(24, 0, 0, 0)
    }

    this.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
    this.customView = tv
    this.setBackgroundDrawable(
        ContextCompat.getDrawable(
            context,
            R.drawable.bg_white_corner_round_3dp
        )
    )
    this.elevation = 1.dpToPx()
    if (isHomeUpEnables) {
        this.setDisplayHomeAsUpEnabled(true)
        this.setDisplayShowHomeEnabled(true)
        this.setHomeAsUpIndicator(R.drawable.ic_back_black)
    }
}
