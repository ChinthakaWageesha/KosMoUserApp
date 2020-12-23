package sl.com.eightdigitz.client.models

import com.squareup.moshi.Json

data class Message(
    @Json(name = "error") @field:Json(name = "error") var error: String? = null
)
