package sl.com.eightdigitz.authentication.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_get_started.*
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.core.base.BaseFragment
import sl.com.eightdigitz.presentation.extensions.hideKeyboard
import sl.com.eightdigitz.presentation.extensions.setActionBar


class GetStarted : BaseFragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_get_started, container, false)
    }

    override fun onViewCreated() {
        setToolbar()
        btn_get_started.setOnClickListener(this)
    }

    private fun setToolbar() {
        (requireActivity() as AuthActivity).supportActionBar?.setActionBar(
            context!!,
            "",
            isHomeUpEnables = false
        )
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_get_started -> {
                (requireActivity() as AuthActivity).setRegister()
            }
        }
    }

    override fun onResume() {
        view?.hideKeyboard()
        setBackground(sl.com.eightdigitz.presentation.R.drawable.ic_sample_sanga)
        super.onResume()
    }

    companion object {
        const val TAG = "get_started"

        fun newInstance(): Fragment {
            return GetStarted()
        }
    }
}