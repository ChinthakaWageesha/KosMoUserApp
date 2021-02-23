package sl.com.eightdigitz.presentation.extensions

import android.widget.Button
import android.widget.ImageButton
import androidx.core.content.ContextCompat

/**
 * Button enabling/disabling modifiers
 */
fun Button.setDisable() {
    this.isEnabled = false
    this.isClickable = false
    this.isFocusable = false
    background = ContextCompat.getDrawable(
        context!!,
        sl.com.eightdigitz.presentation.R.drawable.bg_transparent_white_stroke_5dp
    )
}

fun Button.setEnable() {
    this.isEnabled = true
    this.isClickable = true
    this.isFocusable = true
    background = ContextCompat.getDrawable(
        context!!,
        sl.com.eightdigitz.presentation.R.drawable.bg_pink_btn_corner_round_5dp
    )
}

fun ImageButton.setDisable() {
    this.isEnabled = false
    this.isClickable = false
    this.isFocusable = false
    alpha = 0.3f
}

fun ImageButton.setEnable() {
    this.isEnabled = true
    this.isClickable = true
    this.isFocusable = true
    alpha = 1.0f
}

