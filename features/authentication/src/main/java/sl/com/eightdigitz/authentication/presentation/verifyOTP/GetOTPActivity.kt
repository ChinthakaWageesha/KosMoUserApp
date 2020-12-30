package sl.com.eightdigitz.authentication.presentation.verifyOTP

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import sl.com.eightdigitz.country_picker.presentation.country_picker.CountryPickerBuilder
import sl.com.eightdigitz.country_picker.presentation.models.PCountry
import com.auth0.android.Auth0
import com.hbb20.CountryCodePicker
import kotlinx.android.synthetic.main.activity_get_otp.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DOTP
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.extensions.*

class GetOTPActivity : BaseActivity(), View.OnClickListener {

    private lateinit var ccpPicker: CountryCodePicker
    private var auth0: Auth0? = null
    private val vmOTP by viewModel<OTPViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_otp)
        init()
    }

    private fun init() {
        setToolbar()
        auth0 = Auth0(this)
        auth0?.isOIDCConformant = true
        vmOTP.liveDataOTP.observe(this, Observer { observerGetOTP(it) })
        et_mobile.validateOnTextChange(isCheckValidateIcon = true) { s -> s.isValidPhone() }
        ccpPicker = CountryCodePicker(this)
        ccpPicker.setAutoDetectedCountry(true)
        ccpPicker.registerCarrierNumberEditText(et_mobile)
        btn_send_code.setOnClickListener(this)
        et_mobile_code.setOnClickListener(this)
    }

    private fun setToolbar() {
        supportActionBar?.setActionBar(
            this,
            "",
            isHomeUpEnables = true
        )
    }

    private fun onSendCode() {
        val isPhone =
            et_mobile.validateOnTextChange("", isCheckValidateIcon = true) { s -> s.isValidPhone() }

        if (!isPhone) {
            showAlert(message = getString(sl.com.eightdigitz.presentation.R.string.error_invalid_phone))
            return
        }

        VerifyOTPActivity.startActivity(this@GetOTPActivity, "+94715781989")
        //getOtp()
    }

    @SuppressLint("MissingPermission")
    private fun getOtp() {
        withNetwork({
            vmOTP.getOTP("+94715781989")
        }, {
            showAlert(Msg.TITLE_ERROR, Msg.INTERNET_ISSUE)
        })
    }

    private fun observerGetOTP(resource: Resource<DOTP>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    navigateToVerify()
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    showAlert(Msg.TITLE_ERROR, it.message.toString())
                }
            }
        }
    }

    private fun navigateToVerify() {
        showAlert(
            Msg.TITLE_SUCCESS,
            "Verification code has been sent to +94715781989",
            object : Callback {
                override fun onPositiveClicked() {
                    VerifyOTPActivity.startActivity(this@GetOTPActivity, "+94715781989")
                }

                override fun onNegativeClicked() {
                    TODO("Not yet implemented")
                }

            })
    }

    private fun startCountryPicker() {
        CountryPickerBuilder.instance
            .setTitleTextColor(sl.com.eightdigitz.presentation.R.color.colorBlack)
            .setActivityResultKey(IntentParsableConstants.COUNTRY_SELECTION)
            .setActivityTitle(sl.com.eightdigitz.presentation.R.string.title_country_picker)
            //.setUpNavigationDrawable(au.com.fakebuzz.presentation.R.drawable.ic_ab_back)
            //.setSearchDrawable(au.com.fakebuzz.presentation.R.drawable.ic_ab_searchbar_search)
            .start(this, RequestCodes.COUNTRY_CODE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RequestCodes.COUNTRY_CODE_REQUEST -> {
                if (resultCode == Activity.RESULT_OK) {
                    val mCountry =
                        data?.extras?.getParcelable<PCountry>(IntentParsableConstants.COUNTRY_SELECTION)
                    mCountry?.name?.let { mCountryName ->
                    }
                    et_mobile_code.setText(mCountry?.dialCode.toString().trim())
                    ccpPicker.setCountryForNameCode(mCountry?.code)
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_send_code -> onSendCode()
            R.id.et_mobile_code -> startCountryPicker()
        }
    }
}