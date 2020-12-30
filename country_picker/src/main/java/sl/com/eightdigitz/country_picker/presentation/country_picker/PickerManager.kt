package sl.com.eightdigitz.country_picker.presentation.country_picker

import sl.com.eightdigitz.country_picker.presentation.utils.IntentExtra
import sl.com.eightdigitz.country_picker.R

object PickerManager {

    var activityResultKey: String = IntentExtra.EXTRA_RESULT_KEY_DEFAULT
    var numberValidationResultKey: String = IntentExtra.EXTRA_NUMBER_VALIDATION_RESULT_KEY_DEFAULT
    var activityTitle: Int = R.string.app_name
    var activityTitleTextColor: Int = android.R.color.white
    var activitySearchIcon: Int = R.drawable.search_icon
    var activityUpIcon: Int = R.drawable.ic_arrow_back
    var activityStatusBarColor: Int = R.color.colorPrimaryDark
    var mCurrentDialCode: String? = null

}