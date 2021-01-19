package sl.com.eightdigitz.authentication.presentation.verifyOTP

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.auth0.android.Auth0
import com.auth0.android.provider.AuthenticationActivity
import com.hbb20.CountryCodePicker
import kotlinx.android.synthetic.main.fragment_get_otp.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.presentation.AuthActivity
import sl.com.eightdigitz.core.base.BaseFragment
import sl.com.eightdigitz.core.model.domain.DOTP
import sl.com.eightdigitz.country_picker.presentation.country_picker.CountryPickerBuilder
import sl.com.eightdigitz.country_picker.presentation.models.PCountry
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.extensions.*
import java.util.*


class GetOTP : BaseFragment(), View.OnClickListener {

    private lateinit var ccpPicker: CountryCodePicker
    private var auth0: Auth0? = null
    private val vmOTP by viewModel<OTPViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_get_otp, container, false)
    }

    override fun onViewCreated() {
        setToolbar()
        init()
        showKeyboard(et_mobile)
    }

    private fun init() {
        auth0 = Auth0(context!!)
        auth0?.isOIDCConformant = true
        vmOTP.liveDataOTP.observe(this, Observer { observerGetOTP(it) })
        ccpPicker = CountryCodePicker(context)
        ccpPicker.setAutoDetectedCountry(true)
        ccpPicker.setDefaultCountryUsingNameCode("lk")
        ccpPicker.registerCarrierNumberEditText(et_mobile)

        et_mobile.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (text?.length!! > 10) {

                    if (ccpPicker.isValidFullNumber) {
                        tv_get_otp_validation.visibility = View.GONE
                    } else {
                        tv_get_otp_validation.visibility = View.VISIBLE
                        tv_get_otp_validation.text =
                            getString(sl.com.eightdigitz.presentation.R.string.error_invalid_phone)
                    }

                    et_mobile.validateOnTextChange(isCheckValidateIcon = true) { s -> ccpPicker.isValidFullNumber }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        btn_send_code.setOnClickListener(this)
        et_mobile_code.setOnClickListener(this)
    }

    private fun setToolbar() {
        (requireActivity() as AuthActivity).supportActionBar?.setActionBar(
            context!!,
            "",
            isHomeUpEnables = true
        )
    }


    private fun onSendCode() {

        val isCountryCode = et_mobile_code.validateOnTextChange { s -> s.isNotEmpty() }

        val isPhone =
            et_mobile.validateOnTextChange(
                "",
                isCheckValidateIcon = true
            ) { s -> ccpPicker.isValidFullNumber }

        if (!isCountryCode) {
            tv_get_otp_validation.visibility = View.VISIBLE
            tv_get_otp_validation.text =
                getString(sl.com.eightdigitz.presentation.R.string.error_invalid_country_code)
            return
        }

        if (!isPhone) {
            tv_get_otp_validation.visibility = View.VISIBLE
            tv_get_otp_validation.text =
                getString(sl.com.eightdigitz.presentation.R.string.error_invalid_phone)
            return
        }

        //getOtp()
        navigateToVerify()
    }

    @SuppressLint("MissingPermission")
    private fun getOtp() {
        activity?.withNetwork({
            vmOTP.getOTP(ccpPicker.fullNumberWithPlus)
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
                    showAlert(Msg.TITLE_ERROR, "Invalid phone number. Check and try again.")
                }
            }
        }
    }

    private fun navigateToVerify() {
        (requireActivity() as AuthActivity).mobileNumber = ccpPicker.fullNumberWithPlus
        (requireActivity() as AuthActivity).setVerifyOTP()
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
                        ContextCompat.getDrawable(context!!, getResId(drawableName))
                    )
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

    override fun onResume() {
        setBackground(sl.com.eightdigitz.presentation.R.drawable.bg_gradient_purple_seablue_get_otp)
        super.onResume()
    }

    companion object {
        const val TAG = "get_otp"

        fun newInstance(): Fragment {
            return GetOTP()
        }
    }
}