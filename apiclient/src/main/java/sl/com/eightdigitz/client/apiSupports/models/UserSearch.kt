package sl.com.eightdigitz.client.apiSupports.models

import com.squareup.moshi.Json

data class UserSearch(
    @Json(name = "ID") @field:Json(name = "ID") var id: String? = null,
    @Json(name = "CreatedAt") @field:Json(name = "CreatedAt") var createdAt: String? = null,
    @Json(name = "UpdatedAt") @field:Json(name = "UpdatedAt") var updatedAt: String? = null,
    @Json(name = "DeletedAt") @field:Json(name = "DeletedAt") var deletedAt: String? = null,
    @Json(name = "UserID") @field:Json(name = "UserID") var userID: String? = null,
    @Json(name = "SearchType") @field:Json(name = "SearchType") var searchType: String? = null,
    @Json(name = "SearchText") @field:Json(name = "SearchText") var searchText: String? = null,
    @Json(name = "ProfileOwnerID") @field:Json(name = "ProfileOwnerID") var profileOwnerID: String? = null,
    @Json(name = "PreferenceID") @field:Json(name = "PreferenceID") var preferenceID: String? = null
)
