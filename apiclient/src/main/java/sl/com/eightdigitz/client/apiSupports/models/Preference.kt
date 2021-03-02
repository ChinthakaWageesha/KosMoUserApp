package sl.com.eightdigitz.client.apiSupports.models

import com.squareup.moshi.Json

data class Preference(
    @Json(name = "ID") @field:Json(name = "ID") var id: String? = null,
    @Json(name = "CreatedAt") @field:Json(name = "CreatedAt") var createdAt: String? = null,
    @Json(name = "UpdatedAt") @field:Json(name = "UpdatedAt") var updatedAt: String? = null,
    @Json(name = "DeletedAt") @field:Json(name = "DeletedAt") var deletedAt: String? = null,
    @Json(name = "Code") @field:Json(name = "Code") var code: String? = null,
    @Json(name = "Name") @field:Json(name = "Name") var name: String? = null,
    @Json(name = "Language") @field:Json(name = "Language") var language: String? = null
)
