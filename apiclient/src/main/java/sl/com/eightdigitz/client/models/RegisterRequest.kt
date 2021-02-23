package sl.com.eightdigitz.client.models

import com.squareup.moshi.Json

data class RegisterRequest(
    @Json(name = "MobileNo") @field:Json(name = "MobileNo") var mobileNo: String? = null,
    @Json(name = "FullName") @field:Json(name = "FullName") var fullName: String? = null,
    @Json(name = "DefaultLanguage") @field:Json(name = "DefaultLanguage") var defaultLanguage: String? = null,
    @Json(name = "Email") @field:Json(name = "Email") var email: String? = null,
    @Json(name = "Role") @field:Json(name = "Role") var role: String? = null,
    @Json(name = "ProfilePicture") @field:Json(name = "ProfilePicture") var profilePicture: String? = null,
    @Json(name = "ProfileDescription") @field:Json(name = "ProfileDescription") var profileDescription: String? = null,
    @Json(name = "Preferences") @field:Json(name = "Preferences") var preferences: List<Preference>? = null
)
