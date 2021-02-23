package sl.com.eightdigitz.core.model.domain

data class DJoinUs(
    var id: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var deletedAt: String? = null,
    var name: String? = null,
    var email: String? = null,
    var phoneNo: String? = null,
    var topSocialMediaPlatform: String? = null,
    var socialMediaProfileLink: String? = null,
    var selfDescription: String? = null,
    var profilePicURL: String? = null,
    var attended: Boolean? = null,
    var attendedBy: String? = null,
    var attendedDate: String? = null,
    var adminComment: String? = null
)
