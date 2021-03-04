package sl.com.eightdigitz.client.apiSupports.models

import com.squareup.moshi.Json

data class AppInfo(
    @Json(name = "ID") @field:Json(name = "ID") var id: String? = null,
    @Json(name = "CreatedAt") @field:Json(name = "CreatedAt") var createdAt: String? = null,
    @Json(name = "UpdatedAt") @field:Json(name = "UpdatedAt") var updatedAt: String? = null,
    @Json(name = "DeletedAt") @field:Json(name = "DeletedAt") var deletedAt: String? = null,
    @Json(name = "CountryCode") @field:Json(name = "CountryCode") var countryCode: String? = null,
    @Json(name = "LanguageCode") @field:Json(name = "LanguageCode") var languageCode: String? = null,
    @Json(name = "Description") @field:Json(name = "Description") var description: String? = null,
    @Json(name = "CreatedBy") @field:Json(name = "CreatedBy") var createdBy: String? = null
)
