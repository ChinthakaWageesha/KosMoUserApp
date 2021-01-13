package sl.com.eightdigitz.core.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    private var parentActivity: BaseActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.parentActivity = activity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated()
    }

    fun showProgress() {
        getBaseActivity()?.showProgress()
    }

    fun hideProgress() {
        getBaseActivity()?.hideProgress()
    }

    private fun getBaseActivity() = parentActivity

    fun showKeyboard(editText: EditText) {
        val imgr: InputMethodManager =
            activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        editText.requestFocus()
    }


    fun hideKeyboard() {
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }

    fun setBackground(drawableId: Int) {
        activity!!.window.setBackgroundDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                drawableId
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        hideProgress()
    }

    abstract fun onViewCreated()
}
