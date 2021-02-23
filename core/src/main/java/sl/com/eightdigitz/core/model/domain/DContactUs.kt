package sl.com.eightdigitz.core.model.domain

data class DContactUs(
    var id: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var deletedAt: String? = null,
    var name: String? = null,
    var email: String? = null,
    var phoneNo: String? = null,
    var subject: String? = null,
    var description: String? = null,
    var screenshotURL: String? = null,
    var attended: Boolean? = null,
    var attendedBy: String? = null,
    var attendedDate: String? = null,
    var adminComment: String? = null,
    var requestSource: String? = null
)
