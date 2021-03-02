package sl.com.eightdigitz.client.apiSupports.responses

import com.squareup.moshi.Json

data class OTPTokenResponse(
    @Json(name = "access_token") @field:Json(name = "access_token") var accessToken: String? = null,
    @Json(name = "id_token") @field:Json(name = "id_token") var idToken: String? = null,
    @Json(name = "scope") @field:Json(name = "scope") var scope: String? = null,
    @Json(name = "expires_in") @field:Json(name = "expires_in") var expiresIn: Int? = null,
    @Json(name = "token_type") @field:Json(name = "token_type") var tokenType: String? = null,
    @Json(name = "error") @field:Json(name = "error") var error: String? = null,
    @Json(name = "error_description") @field:Json(name = "error_description") var errorDescription: String? = null
)
