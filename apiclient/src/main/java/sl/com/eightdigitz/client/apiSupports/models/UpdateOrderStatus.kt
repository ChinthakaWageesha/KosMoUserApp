package sl.com.eightdigitz.client.apiSupports.models

import com.squareup.moshi.Json

data class UpdateOrderStatus(
    @Json(name = "ID") @field:Json(name = "ID") var id: String? = null,
    @Json(name = "CreatedAt") @field:Json(name = "CreatedAt") var createdAt: String? = null,
    @Json(name = "UpdatedAt") @field:Json(name = "UpdatedAt") var updatedAt: String? = null,
    @Json(name = "DeletedAt") @field:Json(name = "DeletedAt") var deletedAt: String? = null,
    @Json(name = "OrderID") @field:Json(name = "OrderID") var orderId: String? = null,
    @Json(name = "Stage") @field:Json(name = "Stage") var stage: String? = null,
    @Json(name = "UpdatedBy") @field:Json(name = "UpdatedBy") var updatedBy: String? = null,
    @Json(name = "UpdatedDate") @field:Json(name = "UpdatedDate") var updatedDate: String? = null,
    @Json(name = "UserComment") @field:Json(name = "UserComment") var userComment: String? = null
)
