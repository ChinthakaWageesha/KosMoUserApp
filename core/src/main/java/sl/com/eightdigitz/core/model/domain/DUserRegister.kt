package sl.com.eightdigitz.core.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DUserRegister(
    var mobileNo94: String? = null,
    var fullName: String? = null,
    var defaultLanguage: String? = null,
    var profilePicture: String? = null
    //var mobileNo0: String? = null
) : Parcelable
