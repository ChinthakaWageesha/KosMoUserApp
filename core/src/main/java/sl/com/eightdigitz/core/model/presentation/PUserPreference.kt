package sl.com.eightdigitz.core.model.presentation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PUserPreference(
    var id: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var deletedAt: String? = null,
    var userAuthRef: String? = null,
    var user: PUser? = null,
    var preferenceID: String? = null,
    var preference: PPreference? = null
): Parcelable
