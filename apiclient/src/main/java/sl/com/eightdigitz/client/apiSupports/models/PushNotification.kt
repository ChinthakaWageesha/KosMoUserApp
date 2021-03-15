package sl.com.eightdigitz.client.apiSupports.models

import com.squareup.moshi.Json

data class PushNotification(
    @Json(name = "id") @field:Json(name = "id") var id: Int? = null,
    @Json(name = "uuid") @field:Json(name = "uuid") var uuid: String? = null,
    @Json(name = "title") @field:Json(name = "title") var title: String? = null,
    @Json(name = "message") @field:Json(name = "message") var message: String? = null,
    @Json(name = "badge_count") @field:Json(name = "badge_count") var badgeCount: Int? = null,
    @Json(name = "data") @field:Json(name = "data") var data: Notification? = null,
    @Json(name = "sent_at") @field:Json(name = "sent_at") var sentAt: String? = null,
    @Json(name = "sent_time_label") @field:Json(name = "sent_time_label") var sentTimeLabel: String? = null
)