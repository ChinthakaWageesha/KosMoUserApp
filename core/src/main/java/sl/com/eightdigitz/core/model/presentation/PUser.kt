package sl.com.eightdigitz.core.model.presentation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PUser(
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
    var preferences: List<PPreference>? = null,
    var authReference: String? = null,
    var verified: Boolean? = null
): Parcelable

