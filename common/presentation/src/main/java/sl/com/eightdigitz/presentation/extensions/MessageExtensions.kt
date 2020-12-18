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

fun Fragment.showAlert(title: String = "Alert", message: String) {
    this.alert(title, message) {
        positiveButton("Ok") {
        }
    }.show()
}
