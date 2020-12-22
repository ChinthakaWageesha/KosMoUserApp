package sl.com.eightdigitz.core.base

import android.app.Dialog
import android.content.Context
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

abstract class BaseDialogView : DialogFragment() {

    private var progress: Dialog? = null
    private var parentActivity: BaseActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.parentActivity = activity
            // activity?.onFragmentAttached()
        }
    }

    override fun show(
        fragmentManager: FragmentManager,
        tag: String?
    ) {
        val transaction = fragmentManager.beginTransaction()

        val prevFragment = fragmentManager.findFragmentByTag(tag)
        if (prevFragment != null) {
            transaction.remove(prevFragment)
        }
        transaction.addToBackStack(null)
        show(transaction, tag)
    }

    /*override fun showProgress() {
      hideProgress()
      progress?.let {
        if (!it.isShowing) {
          it.show()
        }
      }
    }

    override fun hideProgress() {
      progress?.let {
        if (it.isShowing) {
          progress?.dismiss()
        }
      }
    }

    override fun showMessage(message: String?) {
      message?.let { message.showToast(parentActivity?.applicationContext!!) }
    }

    override fun showErrorMessage(message: String?) {
      message?.let { message.showToast(parentActivity?.applicationContext!!) }
    }

    override fun showRequiredMessage(message: String?) {
      message?.let { message.showToast(parentActivity?.applicationContext!!) }
    }

    override fun showAlertMessage(
      message: String?,
      title: String,
      event: AppDialog.DialogEvent?
    ) {
      message?.let { AppDialog.showMsgDialog(this.context!!, event, it, title, "ok") }
    }*/

    fun dismissDialog(tag: String) {
        dismiss()
        // getBaseActivity()?.onFragmentDetached(tag)
    }

    private fun getBaseActivity(): BaseActivity? {
        return parentActivity
    }
}
