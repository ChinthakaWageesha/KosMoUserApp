package sl.com.eightdigitz.client.apiSupports.requests

import com.squareup.moshi.Json

data class AddUserPreferenceRequest(
    @Json(name = "PreferenceIDs") @field:Json(name = "PreferenceIDs") var preferenceIDs: Array<String>? = null
)
