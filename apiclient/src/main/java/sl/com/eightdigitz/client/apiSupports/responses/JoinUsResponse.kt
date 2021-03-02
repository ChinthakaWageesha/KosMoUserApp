package sl.com.eightdigitz.client.apiSupports.responses

import com.squareup.moshi.Json
import sl.com.eightdigitz.client.apiSupports.models.JoinUs
import sl.com.eightdigitz.client.apiSupports.models.Message

data class JoinUsResponse(
    @Json(name = "message") @field:Json(name = "message") var message: Message? = null,
    @Json(name = "status") @field:Json(name = "status") var result: Boolean? = null,
    @Json(name = "data") @field:Json(name = "data") var data: JoinUs? = null
)
