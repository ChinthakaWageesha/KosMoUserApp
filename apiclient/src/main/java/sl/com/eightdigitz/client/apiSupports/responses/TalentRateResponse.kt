package sl.com.eightdigitz.client.apiSupports.responses

import com.squareup.moshi.Json
import sl.com.eightdigitz.client.apiSupports.models.Message
import sl.com.eightdigitz.client.apiSupports.models.TalentRate

data class TalentRateResponse(
    @Json(name = "message") @field:Json(name = "message") var message: Message? = null,
    @Json(name = "status") @field:Json(name = "status") var result: Boolean? = null,
    @Json(name = "data") @field:Json(name = "data") var data: TalentRate? = null
)
