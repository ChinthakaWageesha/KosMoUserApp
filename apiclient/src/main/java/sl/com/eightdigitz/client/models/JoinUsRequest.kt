package sl.com.eightdigitz.client.models

import com.squareup.moshi.Json

data class JoinUsRequest(
    @Json(name = "Name") @field:Json(name = "Name") var name: String? = null,
    @Json(name = "Email") @field:Json(name = "Email") var email: String? = null,
    @Json(name = "PhoneNo") @field:Json(name = "PhoneNo") var phoneNo: String? = null,
    @Json(name = "TopSocialMediaPlatform") @field:Json(name = "TopSocialMediaPlatform") var topSocialMediaPlatform: String? = null,
    @Json(name = "SocialMediaProfileLink") @field:Json(name = "SocialMediaProfileLink") var socialMediaProfileLink: String? = null,
    @Json(name = "SelfDescription") @field:Json(name = "SelfDescription") var selfDescription: String? = null,
    @Json(name = "ProfilePicURL") @field:Json(name = "ProfilePicURL") var profilePicURL: String? = null
)
