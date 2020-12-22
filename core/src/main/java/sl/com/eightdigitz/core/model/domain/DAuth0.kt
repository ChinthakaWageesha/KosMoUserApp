package sl.com.eightdigitz.core.model.domain

import com.google.gson.annotations.SerializedName

data class DAuth0(
    @SerializedName("_id") val id: String? = null,
    @SerializedName("phone_number") val phoneNumber: String? = null,
    @SerializedName("phone_verified") val phoneVerified: Boolean? = null,
    @SerializedName("request_language") val requestLanguage: String? = null,
    @SerializedName("error") val error: String? = null,
    @SerializedName("error_description") val errorDescription: String? = null
)
