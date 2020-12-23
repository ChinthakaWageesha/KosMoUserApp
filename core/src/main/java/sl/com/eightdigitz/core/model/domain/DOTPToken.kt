package sl.com.eightdigitz.core.model.domain

data class DOTPToken(
    val accessToken: String? = null,
    val idToken: String? = null,
    val scope: String? = null,
    val expiresIn: Int? = null,
    val tokenType: String? = null,
    val error: String? = null,
    val errorDescription: String? = null
)
