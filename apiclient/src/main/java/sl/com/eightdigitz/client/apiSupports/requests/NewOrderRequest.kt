package sl.com.eightdigitz.client.apiSupports.requests

import com.squareup.moshi.Json

data class NewOrderRequest(
    @Json(name = "ToPronoun") @field:Json(name = "ToPronoun") var toPronoun: String? = null,
    @Json(name = "OrderFor") @field:Json(name = "OrderFor") var orderFor: String? = null,
    @Json(name = "FromPronoun") @field:Json(name = "FromPronoun") var fromPronoun: String? = null,
    @Json(name = "OrderInstructions") @field:Json(name = "OrderInstructions") var orderInstructions: String? = null,
    @Json(name = "RequestedDeliveryDate") @field:Json(name = "RequestedDeliveryDate") var deliveryDate: String? = null,
    @Json(name = "Stage") @field:Json(name = "Stage") var stage: String? = null,
    @Json(name = "UserID") @field:Json(name = "UserID") var userID: String? = null,
    @Json(name = "TalentID") @field:Json(name = "TalentID") var talentID: String? = null,
    @Json(name = "OrderType") @field:Json(name = "OrderType") var orderType: String? = null
)
