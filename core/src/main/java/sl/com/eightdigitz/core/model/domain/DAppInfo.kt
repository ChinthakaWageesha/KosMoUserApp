package sl.com.eightdigitz.core.model.domain

data class DAppInfo(
    var id: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var deletedAt: String? = null,
    var countryCode: String? = null,
    var languageCode: String? = null,
    var description: String? = null,
    var createdBy: String? = null
)
