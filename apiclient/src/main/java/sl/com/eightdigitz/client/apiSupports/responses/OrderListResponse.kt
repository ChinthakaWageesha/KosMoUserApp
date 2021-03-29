package sl.com.eightdigitz.client.apiSupports.responses

import com.squareup.moshi.Json
import sl.com.eightdigitz.client.apiSupports.models.Message
import sl.com.eightdigitz.client.apiSupports.models.Order

data class OrderListResponse(
    @Json(name = "message") @field:Json(name = "message") var message: Message? = null,
    @Json(name = "status") @field:Json(name = "status") var status: Boolean? = null,
    @Json(name = "data") @field:Json(name = "data") var data: List<Order>? = null
)