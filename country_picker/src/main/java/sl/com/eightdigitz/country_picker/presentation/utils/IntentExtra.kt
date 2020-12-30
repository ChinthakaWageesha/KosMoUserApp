package sl.com.eightdigitz.country_picker.presentation.utils


/**
 * User Can Pass the Result code using the EXTRA_RESULT_KEY key .if Empty key is passed then the component will provide the result
 * using the  'EXTRA_RESULT_KEY_DEFAULT ' key with result code of 'Activity.RESULT_OK'
 */
object IntentExtra {
    const val EXTRA_NUMBER_VALIDATION_RESULT_KEY_DEFAULT =
        "EXTRA_NUMBER_VALIDATION_RESULT_KEY_DEFAULT"
    const val EXTRA_RESULT_KEY_DEFAULT = "EXTRA_RESULT_KEY_DEFAULT"
    const val EXTRA_RESULT_KEY = "EXTRA_RESULT_KEY"
}

