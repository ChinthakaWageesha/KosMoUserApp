package sl.com.eightdigitz.presentation

object Constant {

    const val PERPAGE = 30
    const val PREF_NAME = "user_app"

    const val CLIENT_ID = "k4PQJsn9sjtYNw6YGQyy5WnU7JmMvYQs"

    const val PREF_USER = "user_data"
    const val PREF_ACCESS_TOKEN = "access_token"
    const val PREF_ID_TOKEN = "id_token"
    const val PREF_AUTH_REFERENCE = "auth_reference"
    const val ACTION_UNAUTH = "ACTION_UNAUTH"
    const val PREF_FCM_TOKEN = "fcm_push_token"
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

object SearchType {
    const val PROFILE_SEARCH = "profile-view"
    const val PREFERENCE_SEARCH = "preference"
}

object AppInformationType{
    const val PRIVACY_POLICY = "Privacy Policy"
    const val TNC = "Terms & Conditions"
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
    const val UPDATE_USER_REQUEST_CODE = 1003
}

object ResultCodes {
    const val MOBILE_RESULT_CODE = 2001
    const val UPDATE_USER_RESULT_CODE = 2002
}

object IntentParsableConstants {
    const val EXTRA_NOTIFICATION = "extra_notification"
    const val EXTRA_NOTIFICATION_DATA = "extra_notification_data"
    const val EXTRA_USER = "EXTRA_USER"
    const val COUNTRY_SELECTION = "COUNTRY_SELECTION"
    const val EXTRA_PREFERENCE = "extra_preference"
    const val EXTRA_CATEGORY_ITEM_HOME = "home_category_item"
    const val EXTRA_ORDER_STAGE_ITEM = "order_stage_item"
    const val EXTRA_NEW_ORDER = "new_order"
    const val EXTRA_MOBILE_NUMBER = "mobile number"
}
