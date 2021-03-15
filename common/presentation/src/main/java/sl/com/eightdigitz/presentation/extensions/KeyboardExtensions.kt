package sl.com.eightdigitz.presentation.extensions

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment


/**
 * Use only from Activities, don't use from Fragment (with getActivity) or from Dialog/DialogFragment
 */
fun Activity.showKeyboard(editText: EditText) {
    val imgr: InputMethodManager =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    editText.requestFocus()
}

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}

fun Fragment.showKeyboard(editText: EditText) {
    val imgr: InputMethodManager =
        activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    editText.requestFocus()
}

fun Fragment.hideKeyboard(){
    activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
}

/**
 * Use everywhere except from Activity (Custom View, Fragment, Dialogs, DialogFragments).
 */
fun View.hideKeyboardView() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
