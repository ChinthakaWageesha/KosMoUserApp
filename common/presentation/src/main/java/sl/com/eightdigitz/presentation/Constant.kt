package sl.com.eightdigitz.presentation

object Constant {

    const val PERPAGE = 30
    const val CATEGORY_PERPAGE = 20
    const val PREF_NAME = "user_app"

    const val CLIENT_ID = "k4PQJsn9sjtYNw6YGQyy5WnU7JmMvYQs"

    const val PREF_USER = "user_data"
    const val PREF_TOKEN = "access_token"
    const val ACTION_UNAUTH = "ACTION_UNAUTH"
    const val PREF_FCM_TOKEN = "fcm_push_token"
}

object LanguageType {
    const val SINHALA = "si"
    const val TAMIL = "ta"
    const val ENGLISH = "en"
}

object IntentActionConstants {
    const val ACTION_VIEW_NOTIFICATIONS = "ACTION_VIEW_NOTIFICATIONS"
}

object ApiResponseCodes {
    const val UNAUTHORIZED = 401
    const val SERVER_ERROR = 500
}

object IntentParsableConstants {
    const val EXTRA_NOTIFICATION = "extra_notification"
    const val EXTRA_NOTIFICATION_DATA = "extra_notification_data"
    const val EXTRA_IS_FROM_LOGIN = "EXTRA_IS_FROM_LOGIN"
}
