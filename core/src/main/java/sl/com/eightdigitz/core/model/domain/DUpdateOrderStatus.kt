package sl.com.eightdigitz.core.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DUpdateOrderStatus(
    var id: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var deletedAt: String? = null,
    var orderId: String? = null,
    var stage: String? = null,
    var updatedBy: String? = null,
    var updatedDate: String? = null,
    var userComment: String? = null
) : Parcelable
