package sl.com.eightdigitz.core.base

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.presentation.extensions.getUserString
import sl.com.eightdigitz.presentation.extensions.jsonStringMapTo

abstract class BaseFragment : Fragment() {

    private var parentActivity: BaseActivity? = null
    private val sharedPreferences by inject<SharedPreferences>()
    var currentLoggedUser: DUser? = null

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
        if (!sharedPreferences.getUserString().isNullOrEmpty()){
            currentLoggedUser = sharedPreferences.getUserString()?.jsonStringMapTo()
        }
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
