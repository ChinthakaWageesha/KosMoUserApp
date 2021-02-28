package sl.com.eightdigitz.core.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DUserPreference(
    var id: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var deletedAt: String? = null,
    var userAuthRef: String? = null,
    var user: DUser? = null,
    var preferenceID: String? = null,
    var preference: DPreference? = null
): Parcelable
