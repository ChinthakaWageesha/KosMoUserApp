/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: iQuote API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package sl.com.eightdigitz.client.apiSupports.models

import com.squareup.moshi.Json

data class User(
    @Json(name = "ID") @field:Json(name = "ID") var id: String? = null,
    @Json(name = "CreatedAt") @field:Json(name = "CreatedAt") var createdAt: String? = null,
    @Json(name = "UpdatedAt") @field:Json(name = "UpdatedAt") var updatedAt: String? = null,
    @Json(name = "DeletedAt") @field:Json(name = "DeletedAt") var deletedAt: String? = null,
    @Json(name = "MobileNo") @field:Json(name = "MobileNo") var mobileNo: String? = null,
    @Json(name = "DefaultLanguage") @field:Json(name = "DefaultLanguage") var defaultLanguage: String? = null,
    @Json(name = "Email") @field:Json(name = "Email") var email: String? = null,
    @Json(name = "FullName") @field:Json(name = "FullName") var fullName: String? = null,
    @Json(name = "DOB") @field:Json(name = "DOB") var dob: String? = null,
    @Json(name = "Role") @field:Json(name = "Role") var role: String? = null,
    @Json(name = "ProfilePicture") @field:Json(name = "ProfilePicture") var profilePicture: String? = null,
    @Json(name = "ProfileVideo") @field:Json(name = "ProfileVideo") var profileVideo: String? = null,
    @Json(name = "ProfileBanner") @field:Json(name = "ProfileBanner") var profileBanner: String? = null,
    @Json(name = "Preferences") @field:Json(name = "Preferences") var preferences: List<UserPreference>? = null,
    @Json(name = "Auth0Ref") @field:Json(name = "Auth0Ref") var authReference: String? = null,
    @Json(name = "Verified") @field:Json(name = "Verified") var verified: Boolean? = null,
    @Json(name = "ProfileBiography") @field:Json(name = "ProfileBiography") var profileBiography: String? = null,
    @Json(name = "ProfileDescription") @field:Json(name = "ProfileDescription") var profileDescription: String? = null,
    @Json(name = "TopSocialMediaPlatform") @field:Json(name = "TopSocialMediaPlatform") var topSocialMediaPlatform: String? = null,
    @Json(name = "SocialMediaProfileLink") @field:Json(name = "SocialMediaProfileLink") var socialMediaProfileLink: String? = null,
    @Json(name = "SubscribeMail") @field:Json(name = "SubscribeMail") var subscribeMail: Boolean? = null,
    @Json(name = "UserName") @field:Json(name = "UserName") var userName: String? = null,
    @Json(name = "FirebaseID") @field:Json(name = "FirebaseID") var firebaseID: String? = null,
    @Json(name = "Auth0RefGoogle") @field:Json(name = "Auth0RefGoogle") var auth0RefGoogle: String? = null
)

