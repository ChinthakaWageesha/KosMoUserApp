package sl.com.eightdigitz.authentication.presentation.verifyOTP

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.auth0.android.Auth0
import com.hbb20.CountryCodePicker
import kotlinx.android.synthetic.main.activity_get_otp.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.presentation.contact.SendRequest
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DOTP
import sl.com.eightdigitz.core.model.domain.DUserRegister
import sl.com.eightdigitz.country_picker.presentation.country_picker.CountryPickerBuilder
import sl.com.eightdigitz.country_picker.presentation.models.PCountry
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.extensions.*
import java.util.*

class GetOTP : BaseActivity(), View.OnClickListener {

    private lateinit var ccpPicker: CountryCodePicker
    private var auth0: Auth0? = null
    private var userRequest: DUserRegister? = null
    private val vmOTP by viewModel<OTPViewModel>()
    private var isNextClickable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_otp)
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
        if (intent.hasExtra(IntentParsableConstants.EXTRA_REGISTER_USER)){
            userRequest = intent.getParcelableExtra(IntentParsableConstants.EXTRA_REGISTER_USER)
        }
    }

    private fun init() {
        auth0 = Auth0(this)
        auth0?.isOIDCConformant = true
        vmOTP.liveDataOTP.observe(this, Observer { observerGetOTP(it) })
        ccpPicker = CountryCodePicker(this)
        ccpPicker.setAutoDetectedCountry(true)
        ccpPicker.setDefaultCountryUsingNameCode("lk")
        ccpPicker.registerCarrierNumberEditText(et_mobile)

        et_mobile.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                iv_error_no_number.visibility = View.GONE
                cl_get_otp_default.makeGone()
                cl_get_otp_error_container.makeGone()
                cl_get_otp_button_container.makeVisible()


                if (text?.length!! > 10) {
                    et_mobile.validateOnTextChange(isCheckValidateIcon = true) { s -> ccpPicker.isValidFullNumber }
                }

                if (ccpPicker.isValidFullNumber) {
                    makeNextEnable()
                } else {
                    makeNextDisable()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        et_mobile_code.setOnClickListener(this)
        cl_next.setOnClickListener(this)
        tv_send_request.setOnClickListener(this)
    }

    private fun makeNextEnable() {
        ibtn_goto_verify.setEnable()
        tv_next.setTextColor(
            ContextCompat.getColor(
                this,
                sl.com.eightdigitz.presentation.R.color.colorWhite
            )
        )
        isNextClickable = true
    }

    private fun makeNextDisable() {
        ibtn_goto_verify.setDisable()
        tv_next.setTextColor(
            ContextCompat.getColor(
                this,
                sl.com.eightdigitz.presentation.R.color.colorPinkAlpha30
            )
        )
        isNextClickable = false
    }

    @SuppressLint("MissingPermission")
    private fun getOtp() {
        if (isNextClickable) {
            withNetwork({
                userRequest?.mobileNo94 = ccpPicker.fullNumberWithPlus
                vmOTP.getOTP(ccpPicker.fullNumberWithPlus)
            }, {
                showAlert(Msg.TITLE_ERROR, Msg.INTERNET_ISSUE)
            })
        }
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
                    "Invalid phone number. Check and try again.".showToast(this)
                }
            }
        }
    }

    private fun navigateToVerify() {
        //userRequest?.mobileNo0 = ccpPicker.fullNumberWithPlus.replace("+94", "0")
        val intent = Intent(this, VerifyOTP::class.java)
        intent.putExtra(IntentParsableConstants.EXTRA_REGISTER_USER, userRequest)
        startActivityForResult(intent, RequestCodes.CREATE_USER_REQUEST_CODE)
        et_mobile.clearText()
    }

    private fun startCountryPicker() {
        CountryPickerBuilder.instance
            .setTitleTextColor(sl.com.eightdigitz.presentation.R.color.colorBlack)
            .setActivityResultKey(IntentParsableConstants.COUNTRY_SELECTION)
            .setActivityTitle(sl.com.eightdigitz.presentation.R.string.title_country_picker)
            .start(this, RequestCodes.COUNTRY_CODE_REQUEST)
    }

    private fun getResId(drawableName: String): Int {

        try {
            val res = sl.com.eightdigitz.country_picker.R.drawable::class.java
            val field = res.getField(drawableName)
            return field.getInt(null)
        } catch (e: Exception) {
            Log.e("CountryCodePicker", "Failure to get drawable id.", e)
        }

        return -1
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RequestCodes.COUNTRY_CODE_REQUEST -> {
                if (resultCode == Activity.RESULT_OK) {
                    val mCountry =
                        data?.extras?.getParcelable<PCountry>(IntentParsableConstants.COUNTRY_SELECTION)
                    val flagCode = mCountry?.code?.toLowerCase(Locale.ENGLISH)
                    val drawableName = "flag_$flagCode"
                    iv_flag.setImageDrawable(
                        ContextCompat.getDrawable(this, getResId(drawableName))
                    )
                    et_mobile_code.setText(mCountry?.dialCode.toString().trim())
                    ccpPicker.setCountryForNameCode(mCountry?.code)
                }
            }
            RequestCodes.CREATE_USER_REQUEST_CODE -> {
                if (resultCode == ResultCodes.CREATE_USER_RESULT_CODE){
                    setResult(ResultCodes.CREATE_USER_RESULT_CODE).also {
                        finish()
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cl_next -> getOtp()
            R.id.et_mobile_code -> startCountryPicker()
            R.id.tv_send_request -> startActivity<SendRequest>()
        }
    }

    override fun onResume() {
        setBackground(sl.com.eightdigitz.presentation.R.drawable.bg_get_otp)
        super.onResume()
    }
}