package sl.com.eightdigitz.client.apiSupports.models

import com.squareup.moshi.Json

data class Message(
    @Json(name = "error") @field:Json(name = "error") var error: String? = null
)
