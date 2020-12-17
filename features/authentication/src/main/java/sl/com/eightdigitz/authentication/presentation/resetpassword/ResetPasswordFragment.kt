package sl.com.eightdigitz.authentication.presentation.resetpassword

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.datasource.model.Success
import sl.com.eightdigitz.authentication.presentation.AuthActivity
import sl.com.eightdigitz.core.presentation.base.BaseFragment
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
import sl.com.eightdigitz.presentation.extensions.*
import kotlinx.android.synthetic.main.activity_authentication_registration.*
import kotlinx.android.synthetic.main.reset_password_fragment.*
import org.koin.android.ext.android.inject

class ResetPasswordFragment : BaseFragment(), View.OnClickListener {

    companion object {
        const val TAG = "reset_password"
        fun newInstance() = ResetPasswordFragment()
    }

    private val viewModel by inject<ResetPasswordViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.liveData.observe(this, Observer { updateCreatePassword(it) })
        (activity as AppCompatActivity).supportActionBar?.setActionBar(
            requireContext(),
            "Create New Password",
            true
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.reset_password_fragment, container, false)
    }

    override fun onViewCreated() {
        et_password.transformationMethod = PasswordTransformationMethod()
        et_confirm_password.transformationMethod = PasswordTransformationMethod()
        et_email.validate(Msg.INVALID_EMAIL, true) { s -> s.isValidEmail() }

        btn_reset_password.setOnClickListener(this)
    }

    private fun updateCreatePassword(resource: Resource<Success>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> {
                    showProgress()
                }
                ResourceState.SUCCESS -> {
                    hideProgress()
                    (requireActivity() as AuthActivity).setChooseLanguage()
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.showToast(requireContext())
                }
            }
        }
    }

    override fun onClick(v: View?) {

    }
}