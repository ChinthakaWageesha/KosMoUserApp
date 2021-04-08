package sl.com.eightdigitz.core.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DUser(
    var id: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var deletedAt: String? = null,
    var mobileNo: String? = null,
    var defaultLanguage: String? = null,
    var email: String? = null,
    var fullName: String? = null,
    var dob: String? = null,
    var role: String? = null,
    var profilePicture: String? = null,
    var profileVideo: String? = null,
    var profileBanner: String? = null,
    var preferences: List<DUserPreference>? = null,
    var authReference: String? = null,
    var verified: Boolean? = null,
    var profileBiography: String? = null,
    var profileDescription: String? = null,
    var topSocialMediaPlatform: String? = null,
    var socialMediaProfileLink: String? = null,
    var subscribeMail: Boolean? = null,
    var userName: String? = null,
    var firebaseId: String? = null,
    var auth0RefGoogle: String? = null,
    var talentAvailability: String? = null
): Parcelable