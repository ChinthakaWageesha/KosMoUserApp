package sl.com.eightdigitz.authentication.presentation.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.presentation.AuthActivity
import sl.com.eightdigitz.core.presentation.base.BaseFragment
import sl.com.eightdigitz.core.presentation.model.PUser
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
import sl.com.eightdigitz.presentation.extensions.*
import kotlinx.android.synthetic.main.activity_authentication_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment() {

    private val vm by viewModel<LoginViewModel>()

    companion object {
        const val GOOGLE_SIGN_IN = 1555
        const val TAG = "login"
        fun newInstance() = LoginFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.liveData.observe(this, Observer { updateUserLogin(it) })
        (activity as AppCompatActivity).supportActionBar?.setActionBar(
            requireContext(),
            "Sign In",
            true
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_authentication_login, container, false)
    }

    override fun onViewCreated() {
        et_password.transformationMethod = PasswordTransformationMethod()
        et_email.validate(isCheckValidateIcon = true) { s -> s.isValidEmail() }

        btn_forgotpassoword?.setOnClickListener {
            (activity as AuthActivity).setForgotPassword()
        }

        btn_signin.setOnClickListener {
            it.hideKeyboard()
            clickLogin()
        }
    }

    @SuppressLint("MissingPermission")
    private fun clickLogin() {

        val email: String = et_email.getStringTrim()
        val password: String = et_password.getStringTrim()

        if (validateForm()) {
            val puser = PUser(email = email).also {
                it.deviceId = context?.getDeviceId()
                it.deviceType = getDeviceType()
                it.password = password
                it.devicePushToken = ""//getBaseActivity()?.appLifecycle?.firebaseDeviceToken?.value
            }
            activity?.withNetwork({
                vm.get(puser)
            }, {
                Msg.INTERNET_ISSUE.showToast(requireContext())
            })
        }
    }

    private fun validateForm(): Boolean {
        val isEmail = et_email.validate(Msg.INVALID_EMAIL, true) { s -> s.isValidEmail() }
        if (!isEmail) {
            Msg.INVALID_EMAIL.showToast(requireContext()); return false
        }
        val isPassword = et_password.validate(Msg.REQUIRED_PASSWORD) { s -> s.isNotEmpty() }
        if (!isPassword) {
            Msg.REQUIRED_EMAIL.showToast(requireContext()); return false
        }
        return true
    }

    private fun updateUserLogin(resource: Resource<PUser>) {

        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    (requireActivity() as AuthActivity).authSuccess()
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.let { message ->
                        showAlert(message)
                    }
                }
            }
        }
    }

}
