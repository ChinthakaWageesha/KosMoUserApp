package sl.com.eightdigitz.client.models

import com.squareup.moshi.Json

data class ContactUsResponse(
    @Json(name = "message") @field:Json(name = "message") var message: Message? = null,
    @Json(name = "status") @field:Json(name = "status") var result: Boolean? = null,
    @Json(name = "data") @field:Json(name = "data") var data: ContactUs? = null
)
