package sl.com.eightdigitz.client.apiSupports.requests

import com.squareup.moshi.Json
import sl.com.eightdigitz.client.apiSupports.models.Preference

data class RegisterRequest(
    @Json(name = "FirebaseID") @field:Json(name = "FirebaseID") var firebaseID: String? = null,
    @Json(name = "Auth0RefGoogle") @field:Json(name = "Auth0RefGoogle") var auth0RefGoogle: String? = null,
    @Json(name = "MobileNo") @field:Json(name = "MobileNo") var mobileNo: String? = null,
    @Json(name = "FullName") @field:Json(name = "FullName") var fullName: String? = null,
    @Json(name = "DefaultLanguage") @field:Json(name = "DefaultLanguage") var defaultLanguage: String? = null,
    @Json(name = "Email") @field:Json(name = "Email") var email: String? = null,
    @Json(name = "Role") @field:Json(name = "Role") var role: String? = null,
    @Json(name = "ProfilePicture") @field:Json(name = "ProfilePicture") var profilePicture: String? = null,
    @Json(name = "ProfileDescription") @field:Json(name = "ProfileDescription") var profileDescription: String? = null,
    @Json(name = "ProfileBiography") @field:Json(name = "ProfileBiography") var profileBiography: String? = null,
    @Json(name = "Preferences") @field:Json(name = "Preferences") var preferences: List<Preference>? = null
)
