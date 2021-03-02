package sl.com.eightdigitz.client.apiSupports.requests

import com.squareup.moshi.Json

data class LogSearchRequest(
    @Json(name = "UserID") @field:Json(name = "UserID") var userID: String? = null,
    @Json(name = "SearchType") @field:Json(name = "SearchType") var searchType: String? = null,
    @Json(name = "SearchText") @field:Json(name = "SearchText") var searchText: String? = null,
    @Json(name = "ProfileOwnerID") @field:Json(name = "ProfileOwnerID") var profileOwnerID: String? = null,
    @Json(name = "PreferenceID") @field:Json(name = "PreferenceID") var preferenceID: String? = null
)
