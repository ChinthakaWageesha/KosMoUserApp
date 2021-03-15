package sl.com.eightdigitz.core.model.domain

data class DPushNotification(
    var id: Int? = null,
    var uuid: String? = null,
    var title: String? = null,
    var message: String? = null,
    var badgeCount: Int? = null,
    var data: DNotification? = null,
    var sentAt: String? = null,
    var sentTimeLabel: String? = null
)