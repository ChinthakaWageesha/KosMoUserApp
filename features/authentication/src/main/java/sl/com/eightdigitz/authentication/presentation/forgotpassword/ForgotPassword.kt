package sl.com.eightdigitz.authentication.presentation.forgotpassword

import android.annotation.SuppressLint
import android.os.Bundle
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
import kotlinx.android.synthetic.main.activity_authentication_forgot_password.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgotPassword : BaseFragment() {

    companion object {
        const val TAG = "forgot_password"
        fun newInstance() = ForgotPassword()
    }

    private val vm by viewModel<ForgotPasswordViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.liveData.observe(this, Observer { updateData(it) })
        (activity as AppCompatActivity).supportActionBar?.setActionBar(
            requireContext(),
            "Reset Password",
            isHomeUpEnables = true
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_authentication_forgot_password, container, false)
    }

    override fun onViewCreated() {
        et_auth_email.validate(isCheckValidateIcon = true) { s -> s.isValidEmail() }

        btn_auth_reset_password.setOnClickListener {
            click(it)
        }
    }

    @SuppressLint("MissingPermission")
    private fun click(v: View) {
        when (v.id) {
            R.id.btn_auth_reset_password -> {
                et_auth_email.validate { s -> s.isValidEmail() }
                    .let {
                        if (it) {
                            activity?.withNetwork(
                                {
                                    vm.resetPassword(et_auth_email.getString())
                                },
                                {
                                    Msg.INTERNET_ISSUE.showToast(requireContext())
                                })
                        } else {
                            (Msg.INVALID_EMAIL).showToast(requireContext())
                        }
                    }
            }
        }
    }

    private fun updateData(it: Resource<Success>) {
        when (it.state) {
            ResourceState.LOADING -> {
                btn_auth_reset_password.disableButton()
                showProgress()
            }
            ResourceState.SUCCESS -> {
                btn_auth_reset_password.enableButton()
                hideProgress()
                (requireActivity() as AuthActivity).setResetPassword()
                /* this.alert("Link Sent", it.message) {
                     positiveButton("Ok") {

                     }
                 }*/
            }
            ResourceState.ERROR -> {
                btn_auth_reset_password.enableButton()
                hideProgress()
                showAlert(it.message.toString())
            }
        }
    }
}
