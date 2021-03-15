package sl.com.eightdigitz.client.apiSupports.models

import com.squareup.moshi.Json

data class Notification(
    @Json(name = "entity_id") @field:Json(name = "entity_id") var entityId: String? = null,
    @Json(name = "entity_title") @field:Json(name = "entity_title") var entityTitle: String? = null,
    @Json(name = "entity_message") @field:Json(name = "entity_message") var entityMessage: String? = null,
    @Json(name = "thumbnail_url") @field:Json(name = "thumbnail_url") var thumbnailUrl: String? = null,
    @Json(name = "end_date") @field:Json(name = "end_date") var endDate: String? = null
)