package sl.com.eightdigitz.client.models

import com.squareup.moshi.Json

data class UploadBigDataResponse(
    @Json(name = "data") @field:Json(name = "data") var data: String? = null,
    @Json(name = "message") @field:Json(name = "message") var message: String? = null,
    @Json(name = "status") @field:Json(name = "status") var result: Boolean? = null
)
