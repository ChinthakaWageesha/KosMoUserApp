package sl.com.eightdigitz.country_picker.presentation.country_picker

import android.app.Activity
import android.content.Intent
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

class CountryPickerBuilder {


    fun setActivityResultKey(mResultKey: String): CountryPickerBuilder {
        PickerManager.activityResultKey = mResultKey
        return this
    }

    fun setActivityTitle(@StringRes mActivityTitle: Int): CountryPickerBuilder {
        PickerManager.activityTitle = mActivityTitle
        return this
    }

    fun setTitleTextColor(@ColorRes mActivityTitleTextColor: Int): CountryPickerBuilder {
        PickerManager.activityTitleTextColor = mActivityTitleTextColor
        return this
    }


    // pass the country code of the  user's current selection
    fun setCurrentDialCode(mResultKey: String): CountryPickerBuilder {
        PickerManager.mCurrentDialCode = mResultKey
        return this
    }


    fun setSearchDrawable(@DrawableRes mMenuSearchDrawable: Int): CountryPickerBuilder {
        PickerManager.activitySearchIcon = mMenuSearchDrawable
        return this
    }

    fun setUpNavigationDrawable(@DrawableRes mUpNavigationDrawable: Int): CountryPickerBuilder {
        PickerManager.activityUpIcon = mUpNavigationDrawable
        return this
    }

    fun setStatusBarColor(@ColorRes mActivityStatusBatColor: Int): CountryPickerBuilder {
        PickerManager.activityStatusBarColor = mActivityStatusBatColor
        return this
    }

    fun start(mContext: Activity, mRequestCode: Int) {
        val intent = Intent(mContext, CountryPicker::class.java)
        mContext.startActivityForResult(intent, mRequestCode)
    }

    fun start(mFragment: Fragment, mRequestCode: Int) {
        val intent = Intent(mFragment.activity, CountryPicker::class.java)
        mFragment.startActivityForResult(intent, mRequestCode)

    }


    companion object {
        @JvmStatic
        val instance: CountryPickerBuilder
            get() = CountryPickerBuilder()
    }
}