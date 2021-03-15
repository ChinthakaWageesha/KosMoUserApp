package sl.com.eightdigitz.core.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DNotification(
    var entityId: String? = null,
    var entityTitle: String? = null,
    var entityMessage: String? = null,
    var thumbnailUrl: String? = null,
    var endDate: String? = null
): Parcelable
