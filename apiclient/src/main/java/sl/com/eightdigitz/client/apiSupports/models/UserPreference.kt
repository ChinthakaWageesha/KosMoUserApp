package sl.com.eightdigitz.client.apiSupports.models

import com.squareup.moshi.Json

data class UserPreference(
    @Json(name = "ID") @field:Json(name = "ID") var id: String? = null,
    @Json(name = "CreatedAt") @field:Json(name = "CreatedAt") var createdAt: String? = null,
    @Json(name = "UpdatedAt") @field:Json(name = "UpdatedAt") var updatedAt: String? = null,
    @Json(name = "DeletedAt") @field:Json(name = "DeletedAt") var deletedAt: String? = null,
    @Json(name = "UserAuth0Ref") @field:Json(name = "UserAuth0Ref") var userAuthRef: String? = null,
    @Json(name = "User") @field:Json(name = "User") var user: User? = null,
    @Json(name = "PreferenceID") @field:Json(name = "PreferenceID") var preferenceID: String? = null,
    @Json(name = "Preference") @field:Json(name = "Preference") var preference: Preference? = null
)
