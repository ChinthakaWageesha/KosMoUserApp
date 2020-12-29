package sl.com.eightdigitz.presentation.extensions

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun String.showToast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun Activity.showAlert(title: String = "Alert", message: String) {
    this.alert(title, message) {
        positiveButton("Ok") {
        }
    }.show()
}

fun Activity.showAlert(title: String = "Alert", message: String, callback: Callback) {
    this.alert(title, message) {
        positiveButton("Ok") {
            callback.onPositiveClicked()
        }
    }.show()
}

fun Fragment.showAlert(title: String = "Alert", message: String) {
    this.alert(title, message) {
        positiveButton("Ok") {
        }
    }.show()
}

fun Activity.showConfirm(
    title: String,
    message: String,
    negativeText: String,
    positiveText: String,
    callback: Callback
) {
    this.alert(title, message) {
        positiveButton(positiveText){
            callback.onPositiveClicked()
        }
        negativeButton(negativeText){
            callback.onNegativeClicked()
        }
    }.show()
}

interface Callback{
    fun onPositiveClicked()
    fun onNegativeClicked()
}
