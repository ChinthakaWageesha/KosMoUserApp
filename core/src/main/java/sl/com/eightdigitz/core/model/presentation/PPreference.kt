package sl.com.eightdigitz.core.model.presentation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PPreference(
    var id: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var deletedAt: String? = null,
    var code: String? = null,
    var name: String? = null,
    var language: String? = null
): Parcelable
