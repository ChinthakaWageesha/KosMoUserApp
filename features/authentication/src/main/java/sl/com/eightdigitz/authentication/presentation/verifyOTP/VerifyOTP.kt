package sl.com.eightdigitz.authentication.presentation.verifyOTP

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_verify_otp.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.presentation.AuthActivity
import sl.com.eightdigitz.authentication.presentation.GetStarted
import sl.com.eightdigitz.core.base.BaseFragment
import sl.com.eightdigitz.core.model.domain.DOTP
import sl.com.eightdigitz.core.model.domain.DOTPToken
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.navigation.features.ResultCode
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
import sl.com.eightdigitz.presentation.extensions.*


class VerifyOTP : BaseFragment(), View.OnClickListener {

    private val vmOTPToken by viewModel<OTPViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_verify_otp, container, false)
    }

    override fun onViewCreated() {
        init()
        showKeyboard(et_verify_code)
    }

    private fun init() {
        countDownTimer()
        vmOTPToken.liveDataOTP.observe(this, Observer { observerGetOTP(it) })
        vmOTPToken.liveDataOTPToken.observe(this, Observer { observerOTPToken(it) })
        vmOTPToken.liveDataUser.observe(this, Observer { observerRefUser(it) })

        et_verify_code.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (text?.length!! > 6) {
                    et_verify_code.validate(isCheckValidateIcon = true) { s -> s.length == 6 }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        btn_verify.setOnClickListener(this)
        iv_back_verify_otp.setOnClickListener(this)
        tv_message_not_receive_code.setOnClickListener(this)
    }

    private fun onVerify() {
        val isOTPCode =
            et_verify_code.validateOnTextChange(
                "",
                isCheckValidateIcon = true
            ) { s -> s.length > 4 }

        if (!isOTPCode) {
            showAlert(message = getString(sl.com.eightdigitz.presentation.R.string.error_invalid_verify_code))
            return
        }
        verifyCode()
    }

    @SuppressLint("SetTextI18n")
    private fun countDownTimer() {
        tv_message_not_receive_code.text =
            getString(sl.com.eightdigitz.presentation.R.string.message_did_not_receive_code)
        tv_timer.text = "60.00s"
        tv_timer.visibility = View.VISIBLE

        object : CountDownTimer(60000, 1000) {


            override fun onTick(millisUntilFinished: Long) {
                if (tv_timer != null) {
                    tv_timer.text = "${millisUntilFinished / 1000}s"
                }
            }

            override fun onFinish() {
                if (tv_message_not_receive_code != null && tv_timer != null){
                    tv_message_not_receive_code.text = "Resend Code"
                    tv_timer.visibility = View.GONE
                }
            }

        }.start()
    }

    @SuppressLint("MissingPermission")
    private fun resendCode() {
        et_verify_code.requestFocus()
        activity?.withNetwork({
            vmOTPToken.getOTP((requireActivity() as AuthActivity).mobileNumber!!)
        }, {
            showAlert(Msg.TITLE_ERROR, Msg.INTERNET_ISSUE)
        })
    }

    @SuppressLint("MissingPermission")
    private fun verifyCode() {
        activity?.withNetwork({
            vmOTPToken.getOTPToken(
                (requireActivity() as AuthActivity).mobileNumber!!,
                et_verify_code.getStringTrim()
            )
        }, {
            showAlert(Msg.TITLE_ERROR, Msg.INTERNET_ISSUE)
        })
    }

    private fun observerOTPToken(resource: Resource<DOTPToken>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    vmOTPToken.getUserByRefToken(it.data?.idToken!!)
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    showAlert(Msg.TITLE_ERROR, it.message.toString())
                }
            }
        }
    }

    private fun observerGetOTP(resource: Resource<DOTP>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    countDownTimer()
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    showAlert(Msg.TITLE_ERROR, it.message.toString())
                }
            }
        }
    }

    private fun observerRefUser(resource: Resource<DUser>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    (requireActivity() as AuthActivity).authSuccess()
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    if (it.message.toString() == "{\"error\":\"No records found\"}") {
                        (requireActivity() as AuthActivity).setRegister()
                    } else {
                        showAlert(Msg.TITLE_ERROR, it.message.toString())
                    }
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_verify -> onVerify()
            R.id.iv_back_verify_otp -> activity?.onBackPressed()
            R.id.tv_message_not_receive_code -> {
                if (tv_message_not_receive_code.text == "Resend Code") {
                    et_verify_code.clearText()
                    resendCode()
                }
            }
        }
    }

    override fun onResume() {
        setBackground(sl.com.eightdigitz.presentation.R.drawable.bg_gradient_verify_otp)
        super.onResume()
    }

    companion object {
        const val TAG = "verify_otp"

        fun newInstance(): Fragment {
            return VerifyOTP()
        }
    }
}