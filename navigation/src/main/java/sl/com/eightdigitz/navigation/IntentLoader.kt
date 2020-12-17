package sl.com.eightdigitz.navigation

import android.content.Intent

// TODO Change the Package Name respectively
private const val PACKAGE_NAME = "sl.com.eightdigitz.userapp"

private fun intentTo(className: String): Intent =
    Intent(Intent.ACTION_VIEW).setClassName(PACKAGE_NAME, className)

internal fun String.loadIntentOrNull(): Intent? =
    try {
        Class.forName(this).run { intentTo(this@loadIntentOrNull) }
    } catch (e: ClassNotFoundException) {
        null
    }
