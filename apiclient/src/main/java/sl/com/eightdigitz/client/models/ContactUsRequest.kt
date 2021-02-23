package sl.com.eightdigitz.client.models

import com.squareup.moshi.Json

data class ContactUsRequest(
    @Json(name = "Name") @field:Json(name = "Name") var name: String? = null,
    @Json(name = "Email") @field:Json(name = "Email") var email: String? = null,
    @Json(name = "PhoneNo") @field:Json(name = "PhoneNo") var phoneNo: String? = null,
    @Json(name = "Subject") @field:Json(name = "Subject") var subject: String? = null,
    @Json(name = "RequestSource") @field:Json(name = "RequestSource") var requestSource: String? = null,
    @Json(name = "Description") @field:Json(name = "Description") var description: String? = null,
    @Json(name = "ScreenshotURL") @field:Json(name = "ScreenshotURL") var screenshotURL: String? = null
)
