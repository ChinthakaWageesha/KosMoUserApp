package sl.com.eightdigitz.client.apiSupports.requests

import com.squareup.moshi.Json

data class UpdateUserRequest (
    @Json(name = "ID") @field:Json(name = "ID") var id: String? = null,
    @Json(name = "MobileNo") @field:Json(name = "MobileNo") var mobileNo: String? = null,
    @Json(name = "FullName") @field:Json(name = "FullName") var fullName: String? = null,
    @Json(name = "Email") @field:Json(name = "Email") var email: String? = null,
    @Json(name = "ProfilePicture") @field:Json(name = "ProfilePicture") var profilePicture: String? = null,
    @Json(name = "ProfileBiography") @field:Json(name = "ProfileBiography") var profileBiography: String? = null
)