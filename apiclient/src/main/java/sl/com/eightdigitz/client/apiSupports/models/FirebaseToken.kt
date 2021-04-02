package sl.com.eightdigitz.client.apiSupports.models

import com.squareup.moshi.Json

data class FirebaseToken(
    @Json(name = "UserID") @field:Json(name = "UserID") var userID: String? = null,
    @Json(name = "FirebaseToken") @field:Json(name = "FirebaseToken") var firebaseToken: String? = null,
    @Json(name = "DeviceType") @field:Json(name = "DeviceType") var deviceType: String? = null
)
