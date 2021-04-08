package sl.com.eightdigitz.core.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DOrder(
    var id: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var deletedAt: String? = null,
    var userID: String? = null,
    var talentID: String? = null,
    var orderFor: String? = null,
    var orderType: String? = null,
    var toPronoun: String? = null,
    var fromPronoun: String? = null,
    var orderInstructions: String? = null,
    var uploadInstructions: String? = null,
    var requestedDeliveryDate: String? = null,
    var publicVideo: Boolean? = null,
    var userReviewRate: Int? = null,
    var userComment: String? = null,
    var shoutOutValidUpTo: String? = null,
    var stage: String? = null,
    var owner: String? = null,
    var talentName: String? = null,
    var shoutOutURL: String? = null,
    var ownerProfileURL: String? = null
) : Parcelable
