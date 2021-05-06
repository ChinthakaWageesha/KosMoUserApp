package sl.com.eightdigitz.client.apiSupports.models

import com.squareup.moshi.Json

data class PromoCode(
    @Json(name = "DiscountAmount") @field:Json(name = "DiscountAmount") var discountAmount: Double? = null,
    @Json(name = "DiscountPer") @field:Json(name = "DiscountPer") var discountPercentage: Double? = null
)
