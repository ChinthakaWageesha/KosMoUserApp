package sl.com.eightdigitz.core.model.domain

data class DPushNotification(
    var id: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var deletedAt: String? = null,
    var userID: String? = null,
    var notificationType: String? = null,
    var status: String? = null,
    var isRead: Boolean? = null,
    var title: String? = null,
    var description: String? = null,
    var orderID: String? = null,
    var firebaseID: String? = null,
    var talentID: String? = null
)