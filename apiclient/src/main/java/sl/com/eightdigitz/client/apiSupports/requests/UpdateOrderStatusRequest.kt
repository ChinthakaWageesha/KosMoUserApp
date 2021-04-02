package sl.com.eightdigitz.client.apiSupports.requests

import com.squareup.moshi.Json

data class UpdateOrderStatusRequest(
    @Json(name = "orderID") @field:Json(name = "orderID") var orderId: String? = null,
    @Json(name = "stage") @field:Json(name = "stage") var stage: String? = null,
    @Json(name = "updatedBy") @field:Json(name = "updatedBy") var updatedBy: String? = null
)
