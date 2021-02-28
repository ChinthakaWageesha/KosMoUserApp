package sl.com.eightdigitz.client.models

import com.squareup.moshi.Json

data class AddUserPreferenceRequest(
    @Json(name = "PreferenceID") @field:Json(name = "PreferenceID") var preferenceID: String? = null
)
