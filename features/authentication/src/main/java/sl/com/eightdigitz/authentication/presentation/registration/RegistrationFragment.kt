package sl.com.eightdigitz.authentication.presentation.registration

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
import sl.com.eightdigitz.core.base.BaseFragment
import sl.com.eightdigitz.core.model.presentation.PUser
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
import sl.com.eightdigitz.presentation.extensions.*
import kotlinx.android.synthetic.main.activity_authentication_registration.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : BaseFragment(), View.OnClickListener {

    private val vm by viewModel<RegistrationViewModel>()

    companion object {
        const val TAG = "registration"
        fun newInstance() = RegistrationFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.liveData.observe(this, Observer { updateUserRegistration(it) })
        (activity as AppCompatActivity).supportActionBar?.setActionBar(
            requireContext(),
            "Sign Up",
            true
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_authentication_registration, container, false)
    }

    override fun onViewCreated() {
        et_password.transformationMethod = PasswordTransformationMethod()
        et_email.validate(Msg.INVALID_EMAIL, true) { s -> s.isValidEmail() }

        btn_create_account.setOnClickListener(this)

        tv_terms_condition.setOnClickListener {
            //startDetailActivity(AppInformation.TAG_CONDITIONS)
        }
        tv_privacy_policy.setOnClickListener {
            //startDetailActivity(AppInformation.TAG_PRIVACY)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_create_account -> {
                if (validateForm()) {
                    val pUser = PUser(
                        name = et_full_name.getStringTrim(),
                        email = et_email.getStringTrim()
                    ).also {
                        it.userType = "si"
                        it.deviceId = context?.getDeviceId()
                        it.deviceType = getDeviceType()
                        it.password = et_password.getString()
                        it.devicePushToken = ""
                        //getBaseActivity()?.appLifecycle?.firebaseDeviceToken?.value
                    }
                    activity?.withNetwork({
                        vm.createAccount(pUser)
                    }, {
                        Msg.INTERNET_ISSUE.showToast(requireContext())
                    })
                }
            }
        }
    }

    private fun validateForm(): Boolean {
        val isFirstName =
            et_full_name.validate { s -> s.isNotEmpty() }
        if (!isFirstName) {
            "Name is required.".showToast(requireContext()); return false
        }
        val isEmail = et_email.validate(isCheckValidateIcon = true) { s -> s.isValidEmail() }
        if (!isEmail) {
            Msg.INVALID_EMAIL.showToast(requireContext()); return false
        }
        val isPassword =
            et_password.validate { s -> s.length >= 8 }
        if (!isPassword) {
            Msg.MIN_REQUIREMENT_PASSWORD.showToast(requireContext()); return false
        }
        return true
    }

    private fun updateUserRegistration(resource: Resource<PUser>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> {
                   showProgress()
                }
                ResourceState.SUCCESS -> {
                    hideProgress()
                    val accessToken = it.data?.accessToken
                    if (!accessToken.isNullOrEmpty()) {
                        (requireActivity() as AuthActivity).authSuccess()
                    } else {
                        Msg.ERROR_COMMON.showToast(requireContext())
                    }
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.showToast(requireContext())
                }
            }
        }
    }
}
