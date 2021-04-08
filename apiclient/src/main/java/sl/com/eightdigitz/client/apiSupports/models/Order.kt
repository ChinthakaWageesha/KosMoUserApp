package sl.com.eightdigitz.client.apiSupports.models

import com.squareup.moshi.Json

data class Order(
    @Json(name = "ID") @field:Json(name = "ID") var id: String? = null,
    @Json(name = "CreatedAt") @field:Json(name = "CreatedAt") var createdAt: String? = null,
    @Json(name = "UpdatedAt") @field:Json(name = "UpdatedAt") var updatedAt: String? = null,
    @Json(name = "DeletedAt") @field:Json(name = "DeletedAt") var deletedAt: String? = null,
    @Json(name = "UserID") @field:Json(name = "UserID") var userID: String? = null,
    @Json(name = "TalentID") @field:Json(name = "TalentID") var talentID: String? = null,
    @Json(name = "OrderFor") @field:Json(name = "OrderFor") var orderFor: String? = null,
    @Json(name = "OrderType") @field:Json(name = "OrderType") var orderType: String? = null,
    @Json(name = "ToPronoun") @field:Json(name = "ToPronoun") var toPronoun: String? = null,
    @Json(name = "FromPronoun") @field:Json(name = "FromPronoun") var fromPronoun: String? = null,
    @Json(name = "OrderInstructions") @field:Json(name = "OrderInstructions") var orderInstructions: String? = null,
    @Json(name = "UploadInstructions") @field:Json(name = "UploadInstructions") var uploadInstructions: String? = null,
    @Json(name = "RequestedDeliveryDate") @field:Json(name = "RequestedDeliveryDate") var requestedDeliveryDate: String? = null,
    @Json(name = "PublicVideo") @field:Json(name = "PublicVideo") var publicVideo: Boolean? = null,
    @Json(name = "UserReviewRate") @field:Json(name = "UserReviewRate") var userReviewRate: Int? = null,
    @Json(name = "UserComment") @field:Json(name = "UserComment") var userComment: String? = null,
    @Json(name = "ShoutoutValidUpto") @field:Json(name = "ShoutoutValidUpto") var shoutOutValidUpTo: String? = null,
    @Json(name = "Stage") @field:Json(name = "Stage") var stage: String? = null,
    @Json(name = "Owner") @field:Json(name = "Owner") var owner: String? = null,
    @Json(name = "TalentName") @field:Json(name = "TalentName") var talentName: String? = null,
    @Json(name = "ShoutOutURL") @field:Json(name = "ShoutOutURL") var shoutOutURL: String? = null,
    @Json(name = "OwnerProfileURL") @field:Json(name = "OwnerProfileURL") var ownerProfileURL: String? = null
)
