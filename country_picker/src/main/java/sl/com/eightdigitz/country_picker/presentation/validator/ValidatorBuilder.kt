package sl.com.eightdigitz.country_picker.presentation.validator

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import sl.com.eightdigitz.country_picker.presentation.country_picker.PickerManager

class ValidatorBuilder {


    fun setValidatingNumber(mPhoneNumber: String?): ValidatorBuilder {
        NumberValidatorManager.mPhoneNumber = mPhoneNumber
        return this
    }

    fun setNumberValidationResultKey(mResultKey: String): ValidatorBuilder {
        PickerManager.numberValidationResultKey = mResultKey
        return this
    }


    fun setActivityResultKey(mResultKey: String): ValidatorBuilder {
        PickerManager.activityResultKey = mResultKey
        return this
    }


    // pass the country code of the  user's current selection
    fun setCurrentDialCode(mResultKey: String): ValidatorBuilder {
        NumberValidatorManager.mCurrentDialCode = mResultKey
        return this
    }


    fun start(mContext: Activity, mRequestCode: Int) {
        val intent = Intent(mContext, NumberValidator::class.java)
        mContext.startActivityForResult(intent, mRequestCode)

    }

    fun start(mFragment: Fragment, mRequestCode: Int) {
        val intent = Intent(mFragment.activity, NumberValidator::class.java)
        mFragment.startActivityForResult(intent, mRequestCode)

    }


    companion object {
        @JvmStatic
        val instance: ValidatorBuilder
            get() = ValidatorBuilder()
    }
}