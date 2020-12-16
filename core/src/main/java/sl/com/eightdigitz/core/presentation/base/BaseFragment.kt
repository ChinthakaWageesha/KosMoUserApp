package sl.com.eightdigitz.core.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import sl.com.eightdigitz.presentation.extensions.gone
import sl.com.eightdigitz.presentation.extensions.visible

abstract class BaseFragment : Fragment() {

    private var parentActivity: BaseActivity? = null
    var mProgressBar: View? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.parentActivity = activity
            // activity?.onFragmentAttached()
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
        mProgressBar = progressBar()
    }

    fun showProgress() { progressBar()?.visible() }

    fun hideProgress() { progressBar()?.gone() }

    override fun onDestroyView() {
        super.onDestroyView()
        hideProgress()
    }

    fun getBaseActivity() = parentActivity

    abstract fun onViewCreated()
    abstract fun progressBar(): View?
}
