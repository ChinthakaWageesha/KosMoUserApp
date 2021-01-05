package sl.com.eightdigitz.presentation.utile

import android.app.AlertDialog
import android.content.Context
import sl.com.eightdigitz.presentation.R

object AppDialog {

    fun showChooiceDialog(
        context: Context,
        event: DialogEvent?,
        title: String,
        array: Array<CharSequence>
    ) {
        val builder = AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle)
        builder.setCancelable(true)
        builder.setTitle(title)
            .setItems(array) { dialog, which ->
                dialog.dismiss()
                event?.onItemClick(which)
            }.show()
    }

    interface DialogEvent {
        fun onPositiveClicked() {}
        fun onNegativeClicked() {}
        fun onItemClick(which: Int) {}
    }
}