package sl.com.eightdigitz.app.presentation.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.hbb20.CountryCodePicker
import kotlinx.android.synthetic.main.activity_update_mobile.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.country_picker.presentation.country_picker.CountryPickerBuilder
import sl.com.eightdigitz.country_picker.presentation.models.PCountry
import sl.com.eightdigitz.presentation.IntentParsableConstants
import sl.com.eightdigitz.presentation.RequestCodes
import sl.com.eightdigitz.presentation.ResultCodes
import sl.com.eightdigitz.presentation.extensions.*
import java.util.*

class UpdateMobile : BaseActivity(), View.OnClickListener {

    private lateinit var ccpPicker: CountryCodePicker
    private var isUpdateClickable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_mobile)
        setToolbar()
        init()
    }

    private fun setToolbar() {
        supportActionBar?.setActionBar(
            this,
            title = "",
            isHomeUpEnables = true
        )
    }

    private fun init() {
        ccpPicker = CountryCodePicker(this)
        ccpPicker.setAutoDetectedCountry(true)
        ccpPicker.setDefaultCountryUsingNameCode("lk")
        ccpPicker.registerCarrierNumberEditText(et_mobile)

        et_mobile.setText(currentLoggedUser?.mobileNo!!.split("0")[1])

        et_mobile.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                iv_error_no_number.visibility = View.GONE

                if (text?.length!! > 10) {
                    et_mobile.validateOnTextChange(isCheckValidateIcon = true) { s -> ccpPicker.isValidFullNumber }
                }

                if (ccpPicker.isValidFullNumber) {
                    makeUpdateEnable()
                } else {
                    makeUpdateDisable()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
        et_mobile_code.setOnClickListener(this)
        cl_update.setOnClickListener(this)
    }

    private fun makeUpdateEnable() {
        ibtn_update.setEnable()
        tv_update.setTextColor(
            ContextCompat.getColor(
                this,
                sl.com.eightdigitz.presentation.R.color.colorWhite
            )
        )
        isUpdateClickable = true
    }

    private fun makeUpdateDisable() {
        ibtn_update.setDisable()
        tv_update.setTextColor(
            ContextCompat.getColor(
                this,
                sl.com.eightdigitz.presentation.R.color.colorPinkAlpha30
            )
        )
        isUpdateClickable = false
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

    private fun setMobileNumber() {
        if (isUpdateClickable) {
            val intent = Intent(this, VerifyMobileOTP::class.java)
            intent.putExtra(
                IntentParsableConstants.EXTRA_MOBILE_NUMBER,
                ccpPicker.fullNumberWithPlus
            )
            startActivityForResult(intent, RequestCodes.OTP_REQUEST_CODE)
        }
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
            RequestCodes.OTP_REQUEST_CODE -> {
                if (resultCode == ResultCodes.OTP_RESULT_CODE) {
                    val intent = intent
                    intent.putExtra(
                        IntentParsableConstants.EXTRA_MOBILE_NUMBER,
                        ccpPicker.fullNumberWithPlus
                    )
                    setResult(ResultCodes.MOBILE_RESULT_CODE, intent).also {
                        finish()
                    }
                }
            }
        }
    }

    override fun onResume() {
        setBackground(sl.com.eightdigitz.presentation.R.drawable.bg_gradient_brown_pink_purple)
        super.onResume()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.et_mobile_code -> startCountryPicker()
            R.id.cl_update -> setMobileNumber()
        }
    }
}