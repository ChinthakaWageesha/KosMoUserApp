package sl.com.eightdigitz.presentation

object Constant {

    const val PERPAGE = 30
    const val PREF_NAME = "user_app"

    const val CLIENT_ID = "k4PQJsn9sjtYNw6YGQyy5WnU7JmMvYQs"
    var SAMPLE_X_ID_TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IjhoVUNjenZpcGtuWlNOT" +
            "2VjS0NQdCJ9.eyJuaWNrbmFtZSI6IiIsIm5hbWUiOiIiLCJwaWN0dXJlIjoiaHR0cHM6Ly9jZG4uYXV0aDAuY" +
            "29tL2F2YXRhcnMvZGVmYXVsdC5wbmciLCJ1cGRhdGVkX2F0IjoiMjAyMS0wMi0xOVQxNzoyMTo0NC45OTZaIiw" +
            "icGhvbmVfbnVtYmVyIjoiKzk0NzE1NzgxOTg5IiwiaXNzIjoiaHR0cHM6Ly9kZXYtYWhhbWFkLnVzLmF1dGgwL" +
            "mNvbS8iLCJzdWIiOiJzbXN8NWZlMTk1NjNiZTAzZDI0ZGM5ZGY1MDIwIiwiYXVkIjoiazRQUUpzbjlzanRZTn" +
            "c2WUdReXk1V25VN0ptTXZZUXMiLCJpYXQiOjE2MTM3NTUzMDUsImV4cCI6MTYxMzc5MTMwNX0.XsCu8QM-qKi" +
            "pxtZeqfffOWctuns-JqkKIOHM2feFCcout7pWwjbEYurcfiJDQtL1qmuG3z0BQE_BSevRERulH6NSXyPiOFK2s8" +
            "GL4fJAT-6htY8ORKcvfrnElhPJNMohlQeM_mlbTFXWrL8Jkea2692XtqnP7GHj2CYVZn1a7m035ejR2At4Vyb5s" +
            "cq0r5pCMISGm_wE-8g8LRVXfex0Ryt1L0vvj3XUKKzAOWZ-iuaa2frFE4TnVRnWBjiqxj2UmrYchwoWIkVDI0U" +
            "US1qL_I8O5ZUp8L-XhU7rC0n1XFiZDLDbRy6VHbxwPb4IshiW9CTV2fNbsN3Vu0mU-p6v9A"

    const val PREF_USER = "user_data"
    const val PREF_ACCESS_TOKEN = "access_token"
    const val PREF_ID_TOKEN = "id_token"
    const val PREF_AUTH_REFERENCE = "auth_reference"
    const val ACTION_UNAUTH = "ACTION_UNAUTH"
    const val PREF_FCM_TOKEN = "fcm_push_token"
    const val USER_IMAGE_AQUAMAN = "https://storage.googleapis.com/hello-app-cont/2021-02-24-hello-app-sms|6035259e3824a2f357dd0b85-aquaman.jpeg"
    const val USER_IMAGE_PAUL_WALKER = "https://storage.googleapis.com/hello-app-cont/2021-02-24-hello-app-sms|6035259e3824a2f357dd0b85-paul-walker.jpg"
    const val USER_IMAGE_TOM_HARDY = "https://storage.googleapis.com/hello-app-cont/2021-02-24-hello-app-sms|6035259e3824a2f357dd0b85-Tom_Hardy.jpg"
    const val KID_IMAGE_URL = "https://storage.googleapis.com/hello-app-cont/2021-02-24-hello-app-sms|6035259e3824a2f357dd0b85-kid.jfif"
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
    const val RECENT_SEARCH_REQUEST_CODE = 1002
}

object ResultCodes {
    const val RECENT_SEARCH_RESULT_CODE = 2002
}

object IntentParsableConstants {
    const val EXTRA_NOTIFICATION = "extra_notification"
    const val EXTRA_NOTIFICATION_DATA = "extra_notification_data"
    const val EXTRA_USER = "EXTRA_USER"
    const val COUNTRY_SELECTION = "COUNTRY_SELECTION"
    const val EXTRA_PREFERENCE = "extra_preference"
    const val EXTRA_CATEGORY_ITEM_HOME = "home_category_item"
}
