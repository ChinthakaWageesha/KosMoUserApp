package sl.com.eightdigitz.presentation

object Constant {

    const val PERPAGE = 30
    const val PREF_NAME = "user_app"

    const val CLIENT_ID = "k4PQJsn9sjtYNw6YGQyy5WnU7JmMvYQs"

    const val PREF_USER = "user_data"
    const val PREF_ID_TOKEN = "id_token"
    const val PREF_AUTH_REFERENCE = "auth_reference"
    const val ACTION_UNAUTH = "ACTION_UNAUTH"
    const val PREF_FCM_TOKEN = "fcm_push_token"
    const val PREF_USER_ID = "user_id"
    const val USER_IMAGE_AQUAMAN = "https://storage.googleapis.com/hello-app-cont/2021-02-24-hello-app-sms|6035259e3824a2f357dd0b85-aquaman.jpeg"
    const val USER_IMAGE_PAUL_WALKER = "https://storage.googleapis.com/hello-app-cont/2021-02-24-hello-app-sms|6035259e3824a2f357dd0b85-paul-walker.jpg"
    const val USER_IMAGE_TOM_HARDY = "https://storage.googleapis.com/hello-app-cont/2021-02-24-hello-app-sms|6035259e3824a2f357dd0b85-Tom_Hardy.jpg"
    const val KID_IMAGE_URL = "https://storage.googleapis.com/hello-app-cont/2021-03-08-hello-app-sms|5fe19563be03d24dc9df5020-kid.jpg"
    const val MOVIE_IMAGE_URL = "https://storage.googleapis.com/hello-app-cont/2021-02-18-hello-app-sms|5fe19563be03d24dc9df5020-download.jfif"
    const val MOVIE_IMAGE_URL_1 = "https://storage.googleapis.com/hello-app-cont/2021-02-18-hello-app-sms|5fe19563be03d24dc9df5020-download (1).jfif"
    const val POSTER_IMAGE_URL = "https://storage.googleapis.com/hello-app-cont/2021-02-19-hello-app-sms|5fe19563be03d24dc9df5020-poster.jpg"
}

object LanguageType {
    const val ENGLISH = "en"
    const val SINHALA = "si"
    const val TAMIL = "ta"
    const val HINDI = "hi"
}

object NavigationTypes {
    const val NAVIGATE_TO_ORDER_SUMMARY = "navigate_Order_summary"
    const val NAVIGATE_TO_REVIEW_ORDER = "navigate_Review_Order"
}

object SearchType {
    const val PROFILE_SEARCH = "profile-view"
    const val PREFERENCE_SEARCH = "preference"
}

object IntentActionConstants {
    const val ACTION_VIEW_NOTIFICATIONS = "ACTION_VIEW_NOTIFICATIONS"
}

object ApiResponseCodes {
    const val UNAUTHORIZED = 401
    const val SERVER_ERROR = 500
}

object RequestCodes {
    const val COUNTRY_CODE_REQUEST = 1001
    const val MOBILE_REQUEST_CODE = 1002
    const val OTP_REQUEST_CODE = 1003
    const val CREATE_USER_REQUEST_CODE = 1004
    const val UPDATE_USER_REQUEST_CODE = 1005
    const val NEW_ORDER_REQUEST_CODE = 1006
    const val DECLINE_ORDER_REQUEST_CODE = 1007
    const val SETTINGS_REQUEST_CODE = 1008
}

object ResultCodes {
    const val MOBILE_RESULT_CODE = 2001
    const val OTP_RESULT_CODE = 2002
    const val UPDATE_USER_RESULT_CODE = 2003
    const val CREATE_USER_RESULT_CODE = 2004
    const val NEW_ORDER_RESULT_CODE = 2005
    const val DECLINE_ORDER_RESULT_CODE = 2006
    const val SETTINGS_RESULT_CODE = 2007
}

object IntentParsableConstants {
    const val EXTRA_NOTIFICATION = "extra_notification"
    const val EXTRA_NOTIFICATION_DATA = "extra_notification_data"
    const val EXTRA_REGISTER_USER = "EXTRA_REGISTER_USER"
    const val EXTRA_USER = "EXTRA_USER"
    const val COUNTRY_SELECTION = "COUNTRY_SELECTION"
    const val EXTRA_PREFERENCE = "extra_preference"
    const val EXTRA_CATEGORY_ITEM_HOME = "home_category_item"
    const val EXTRA_NEW_ORDER = "new_order"
    const val EXTRA_MOBILE_NUMBER = "mobile number"
    const val EXTRA_TALENT_RATE = "talent_rate"
}

object ViewTypes {
    const val RECENT_TALENTS = 1
    const val TALENTS = 2
}
