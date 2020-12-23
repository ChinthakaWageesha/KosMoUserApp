package sl.com.eightdigitz.core.model.domain


data class DOTP(
    val id: String? = null,
    val phoneNumber: String? = null,
    val phoneVerified: Boolean? = null,
    val requestLanguage: String? = null,
    val error: String? = null,
    val errorDescription: String? = null
)
