package sl.com.eightdigitz.presentation.extensions

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.os.Parcelable
import android.provider.Settings
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresPermission
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.io.IOException
import java.nio.charset.Charset

/**
 * Checks network connectivity
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isNetworkStatusAvailable(): Boolean {
    val connectivityManager = this
        .getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager?.let {
        val netInfo = it.activeNetworkInfo
        netInfo?.let {
            if (netInfo.isConnected) return true
        }
    }
    return false
}

/**
 * Execute block of code if network is available
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.withNetwork(available: () -> Unit, error: () -> Unit) {
    if (isNetworkStatusAvailable()) {
        available()
    } else {
        error()
    }
}

/**
 * Loads content of file from assets as String using UTF-8 charset
 */
fun Context.loadFromAsset(jsonName: String): String? {
    var stream: String? = null
    try {
        val inputStream = this.assets.open(jsonName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        stream = String(buffer, Charset.forName("UTF-8"))
    } catch (e: IOException) {
    }
    return stream
}

/**
 * Computes status bar height
 */
fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = this.resources.getIdentifier("status_bar_height", "dimen",
        "android")
    if (resourceId > 0) {
        result = this.resources.getDimensionPixelSize(resourceId)
    }
    return result
}

/**
 * Computes screen height
 */
fun Context.getScreenHeight(): Int {
    var screenHeight = 0
    val wm = this.getSystemService(Context.WINDOW_SERVICE) as? WindowManager
    wm?.let {
        val metrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(metrics)
        screenHeight = metrics.heightPixels
    }
    return screenHeight
}

/**
 * Convert dp integer to pixel
 */
fun Context.dpToPx(dp: Int): Float {
    val displayMetrics = this.resources.displayMetrics
    return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).toFloat()
}

/**
 * Get color from resources
 */
fun Context.getCompatColor(@ColorRes colorInt: Int): Int =
    ContextCompat.getColor(this, colorInt)

/**
 * Get drawable from resources
 */
fun Context.getCompatDrawable(@DrawableRes drawableRes: Int): Drawable? =
    ContextCompat.getDrawable(this, drawableRes)

/**
 * Start Activity from context
 * */
inline fun <reified T : Activity> Context?.startActivity() = this?.startActivity(Intent(this, T::class.java))

inline fun <reified T : Activity> Activity?.startActivityForResult(result: Int) = this?.startActivityForResult(Intent(this, T::class.java), result)

inline fun <reified T : Activity> Fragment?.startActivityForResult(
    result: Int,
    func: Intent.() -> Unit
) {
    val intent = Intent(this?.context, T::class.java).apply {
        func()
    }
    this?.startActivityForResult(intent, result)
}

/**
 * Show Short Toast message from string resource
 * */
fun Context?.toastShort(@StringRes textId: Int, duration: Int = Toast.LENGTH_SHORT) = this?.let { Toast.makeText(it, textId, duration).show() }

/**
 * Show Long Toast message from string resource
 * */
fun Context?.toastLong(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) = this?.let { Toast.makeText(it, textId, duration).show() }

/**
 * Show Long Toast message from string
 */
fun Context?.toastLong(text: String, duration: Int = Toast.LENGTH_LONG) = this?.let { Toast.makeText(it, text, duration).show() }

/**
 * This function allows add Parcelable to intent which takes class name as Extra's name
 * */
fun Intent?.putParcelable(T: Parcelable) = this?.putExtra(T::class.simpleName, T)

@SuppressLint("HardwareIds")
fun Context.getDeviceId(): String {
    return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
}

fun getDeviceType(): String {
    return "Android"
}