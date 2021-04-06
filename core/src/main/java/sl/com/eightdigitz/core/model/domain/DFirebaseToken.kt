package sl.com.eightdigitz.core.model.domain


data class DFirebaseToken(
    var userID: String? = null,
    var firebaseToken: String? = null,
    var deviceType: String? = null
)
