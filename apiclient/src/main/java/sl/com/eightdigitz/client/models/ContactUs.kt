package sl.com.eightdigitz.client.models

import com.squareup.moshi.Json

data class ContactUs(
    @Json(name = "ID") @field:Json(name = "ID") var id: String? = null,
    @Json(name = "CreatedAt") @field:Json(name = "CreatedAt") var createdAt: String? = null,
    @Json(name = "UpdatedAt") @field:Json(name = "UpdatedAt") var updatedAt: String? = null,
    @Json(name = "DeletedAt") @field:Json(name = "DeletedAt") var deletedAt: String? = null,
    @Json(name = "Name") @field:Json(name = "Name") var name: String? = null,
    @Json(name = "Email") @field:Json(name = "Email") var email: String? = null,
    @Json(name = "PhoneNo") @field:Json(name = "PhoneNo") var phoneNo: String? = null,
    @Json(name = "Subject") @field:Json(name = "Subject") var subject: String? = null,
    @Json(name = "Description") @field:Json(name = "Description") var description: String? = null,
    @Json(name = "ScreenshotURL") @field:Json(name = "ScreenshotURL") var screenshotURL: String? = null,
    @Json(name = "Attended") @field:Json(name = "Attended") var attended: Boolean? = null,
    @Json(name = "AttendedBy") @field:Json(name = "AttendedBy") var attendedBy: String? = null,
    @Json(name = "AttendedDate") @field:Json(name = "AttendedDate") var attendedDate: String? = null,
    @Json(name = "AdminComment") @field:Json(name = "AdminComment") var adminComment: String? = null,
    @Json(name = "RequestSource") @field:Json(name = "RequestSource") var requestSource: String? = null
)
