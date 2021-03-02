package sl.com.eightdigitz.client.apiSupports.models

import com.squareup.moshi.Json

data class JoinUs(
    @Json(name = "ID") @field:Json(name = "ID") var id: String? = null,
    @Json(name = "CreatedAt") @field:Json(name = "CreatedAt") var createdAt: String? = null,
    @Json(name = "UpdatedAt") @field:Json(name = "UpdatedAt") var updatedAt: String? = null,
    @Json(name = "DeletedAt") @field:Json(name = "DeletedAt") var deletedAt: String? = null,
    @Json(name = "Name") @field:Json(name = "Name") var name: String? = null,
    @Json(name = "Email") @field:Json(name = "Email") var email: String? = null,
    @Json(name = "PhoneNo") @field:Json(name = "PhoneNo") var phoneNo: String? = null,
    @Json(name = "TopSocialMediaPlatform") @field:Json(name = "TopSocialMediaPlatform") var topSocialMediaPlatform: String? = null,
    @Json(name = "SocialMediaProfileLink") @field:Json(name = "SocialMediaProfileLink") var socialMediaProfileLink: String? = null,
    @Json(name = "SelfDescription") @field:Json(name = "SelfDescription") var selfDescription: String? = null,
    @Json(name = "ProfilePicURL") @field:Json(name = "ProfilePicURL") var profilePicURL: String? = null,
    @Json(name = "Attended") @field:Json(name = "Attended") var attended: Boolean? = null,
    @Json(name = "AttendedBy") @field:Json(name = "AttendedBy") var attendedBy: String? = null,
    @Json(name = "AttendedDate") @field:Json(name = "AttendedDate") var attendedDate: String? = null,
    @Json(name = "AdminComment") @field:Json(name = "AdminComment") var adminComment: String? = null
)
