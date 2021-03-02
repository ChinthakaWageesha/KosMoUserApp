package sl.com.eightdigitz.client.apiSupports.responses

import com.squareup.moshi.Json

data class OTPResponse(
    @Json(name = "_id") @field:Json(name = "_id") var id: String? = null,
    @Json(name = "phone_number") @field:Json(name = "phone_number") var phoneNumber: String? = null,
    @Json(name = "phone_verified") @field:Json(name = "phone_verified") var phone_verified: Boolean? = null,
    @Json(name = "request_language") @field:Json(name = "request_language") var requestLanguage: String? = null,
    @Json(name = "error") @field:Json(name = "error") var error: String? = null,
    @Json(name = "error_description") @field:Json(name = "error_description") var errorDescription: String? = null
)
