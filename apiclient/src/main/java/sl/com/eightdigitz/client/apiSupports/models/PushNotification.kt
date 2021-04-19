package sl.com.eightdigitz.client.apiSupports.models

import com.squareup.moshi.Json

data class PushNotification(
    @Json(name = "ID") @field:Json(name = "ID") var id: String? = null,
    @Json(name = "CreatedAt") @field:Json(name = "CreatedAt") var createdAt: String? = null,
    @Json(name = "UpdatedAt") @field:Json(name = "UpdatedAt") var updatedAt: String? = null,
    @Json(name = "DeletedAt") @field:Json(name = "DeletedAt") var deletedAt: String? = null,
    @Json(name = "UserID") @field:Json(name = "UserID") var userID: String? = null,
    @Json(name = "NotificationType") @field:Json(name = "NotificationType") var notificationType: String? = null,
    @Json(name = "Status") @field:Json(name = "Status") var status: String? = null,
    @Json(name = "Read") @field:Json(name = "Read") var isRead: Boolean? = null,
    @Json(name = "Title") @field:Json(name = "Title") var title: String? = null,
    @Json(name = "Description") @field:Json(name = "Description") var description: String? = null,
    @Json(name = "OrderID") @field:Json(name = "OrderID") var orderID: String? = null,
    @Json(name = "FirebaseID") @field:Json(name = "FirebaseID") var firebaseID: String? = null,
    @Json(name = "TalentID") @field:Json(name = "TalentID") var talentID: String? = null
)