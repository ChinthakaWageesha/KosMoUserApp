package sl.com.eightdigitz.core.model.presentation

data class PNotification(
    var entity_id: String? = null,
    var entity_title: String? = null,
    var entity_message: String? = null,
    var thumbnail_url: String? = null,
    var end_date: String? = null
)
