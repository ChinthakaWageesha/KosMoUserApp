package sl.com.eightdigitz.authentication.presentation.verifyOTP

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_verify_otp.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.presentation.GetStarted
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DOTP
import sl.com.eightdigitz.core.model.domain.DOTPToken
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.domain.DUserRegister
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.extensions.*

class VerifyOTP : BaseActivity(), View.OnClickListener {

    private var userRequest: DUserRegister? = null
    private val vmOTPToken by viewModel<OTPViewModel>()
    private var isVerifyEnable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_otp)
        setToolbar()
        getData()
        init()
    }

    private fun setToolbar() {
        supportActionBar?.setActionBar(
            this,
            "",
            isHomeUpEnables = true
        )
    }

    private fun getData() {
        if (intent.hasExtra(IntentParsableConstants.EXTRA_REGISTER_USER)) {
            userRequest = intent.getParcelableExtra(IntentParsableConstants.EXTRA_REGISTER_USER)
        }
    }

    private fun init() {
        vmOTPToken.liveDataOTP.observe(this, Observer { observerGetOTP(it) })
        vmOTPToken.liveDataOTPToken.observe(this, Observer { observerOTPToken(it) })
        vmOTPToken.liveDataUser.observe(this, Observer { observerRefUser(it) })

        et_verify_code.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (text?.length!! > 6) {
                    et_verify_code.validateOnTextChange(isCheckValidateIcon = true) { s -> s.length == 6 }
                }

                if (text.length == 6) {
                    makeVerifyEnable()
                } else {
                    makeVerifyDisable()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        cl_verify.setOnClickListener(this)
        tv_message_not_receive_code.setOnClickListener(this)
    }

    private fun makeVerifyEnable() {
        ibtn_verify.setEnable()
        tv_verify.setTextColor(
            ContextCompat.getColor(
                this,
                sl.com.eightdigitz.presentation.R.color.colorWhite
            )
        )
        isVerifyEnable = true
    }

    private fun makeVerifyDisable() {
        ibtn_verify.setDisable()
        tv_verify.setTextColor(
            ContextCompat.getColor(
                this,
                sl.com.eightdigitz.presentation.R.color.colorPinkAlpha30
            )
        )
        isVerifyEnable = false
    }

    private fun onVerify() {
        if (isVerifyEnable) {
            verifyCode()
        }
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
                if (tv_message_not_receive_code != null && tv_timer != null) {
                    tv_message_not_receive_code.text = "Resend Code"
                    tv_timer.visibility = View.GONE
                }
            }

        }.start()
    }

    @SuppressLint("MissingPermission")
    private fun resendCode() {
        et_verify_code.requestFocus()
        withNetwork({
            vmOTPToken.getOTP(userRequest?.mobileNo94!!)
        }, {
            showAlert(Msg.TITLE_ERROR, Msg.INTERNET_ISSUE)
        })
    }

    @SuppressLint("MissingPermission")
    private fun verifyCode() {
        withNetwork({
            vmOTPToken.getOTPToken(userRequest?.mobileNo94!!, et_verify_code.getStringTrim())
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
                    vmOTPToken.getUserByIDToken(it.data?.idToken!!)
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.error.toString().showToast(this)
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
                    it.message?.error.toString().showToast(this)
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
                    if (it.data?.fullName.isNullOrEmpty() && it.data?.mobileNo.isNullOrEmpty() && it.data?.email.isNullOrEmpty()) {
                        navigateToGetStart()
                    } else {
                        navigateToSuccess()
                    }
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    if (it.message?.error == "No records found") {
                        navigateToGetStart()
                    } else {
                        it.message?.error.toString().showToast(this)
                    }
                }
            }
        }
    }

    private fun navigateToGetStart() {
        val intent = Intent(this, GetStarted::class.java)
        intent.putExtra(IntentParsableConstants.EXTRA_REGISTER_USER, userRequest)
        startActivityForResult(intent, RequestCodes.CREATE_USER_REQUEST_CODE)
    }

    private fun navigateToSuccess() {
        setResult(ResultCodes.CREATE_USER_RESULT_CODE).also {
            finish()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cl_verify -> onVerify()
            R.id.tv_message_not_receive_code -> {
                if (tv_message_not_receive_code.text == "Resend Code") {
                    et_verify_code.clearText()
                    resendCode()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RequestCodes.CREATE_USER_REQUEST_CODE && resultCode == ResultCodes.CREATE_USER_RESULT_CODE){
            setResult(ResultCodes.CREATE_USER_RESULT_CODE).also {
                finish()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onResume() {
        countDownTimer()
        setBackground(sl.com.eightdigitz.presentation.R.drawable.bg_gradient_brown_pink_purple)
        super.onResume()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}