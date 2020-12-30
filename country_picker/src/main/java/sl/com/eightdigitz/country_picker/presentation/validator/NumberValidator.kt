package sl.com.eightdigitz.country_picker.presentation.validator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import sl.com.eightdigitz.country_picker.presentation.country_picker.PickerManager
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil


class NumberValidator : AppCompatActivity() {


    @SuppressWarnings
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NumberValidatorManager.mCurrentDialCode?.let {
            isValidNumber(it)
        }
    }


    private fun isValidNumber(code: String?) {
        NumberValidatorManager.mPhoneNumber?.let { mNumber ->
            val mPhoneNumberUtil = PhoneNumberUtil.createInstance(this)
            val mPhoneNumber = mPhoneNumberUtil.parse(mNumber, code)

            val mResultIntent = Intent()
            mResultIntent.putExtra(
                PickerManager.numberValidationResultKey,
                mPhoneNumberUtil.isValidNumber(mPhoneNumber)
            )
            setResult(Activity.RESULT_OK, mResultIntent)
            finish()
        }

    }


    private fun showMessage(mMessage: String) =
        Toast.makeText(this, mMessage, Toast.LENGTH_SHORT).show()
}