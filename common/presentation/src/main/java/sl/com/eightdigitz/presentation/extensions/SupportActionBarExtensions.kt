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

    // Create a LayoutParams for TextView
    val lp = RelativeLayout.LayoutParams(
        RelativeLayout.LayoutParams.WRAP_CONTENT, // Width of TextView
        RelativeLayout.LayoutParams.WRAP_CONTENT
    ) // Height of TextView

    // Apply the layout parameters to TextView widget
    tv.layoutParams = lp

    // Set text to display in TextView
    tv.text = title // ActionBar title text

    // Set the text color of TextView to black
    // This line change the ActionBar title text color
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

    // Set the ActionBar display option
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
