package sl.com.eightdigitz.client.apiSupports.models

import com.squareup.moshi.Json

data class TalentRate (
    @Json(name = "ID") @field:Json(name = "ID") var id: String? = null,
    @Json(name = "CreatedAt") @field:Json(name = "CreatedAt") var createdAt: String? = null,
    @Json(name = "UpdatedAt") @field:Json(name = "UpdatedAt") var updatedAt: String? = null,
    @Json(name = "DeletedAt") @field:Json(name = "DeletedAt") var deletedAt: String? = null,
    @Json(name = "TalentID") @field:Json(name = "TalentID") var talentID: String? = null,
    @Json(name = "CategoryID") @field:Json(name = "CategoryID") var categoryID: String? = null,
    @Json(name = "TypeID") @field:Json(name = "TypeID") var typeID: String? = null,
    @Json(name = "Rate") @field:Json(name = "Rate") var rate: Int? = null,
    @Json(name = "CompanyPer") @field:Json(name = "CompanyPer") var companyPercentage: Int? = null,
    @Json(name = "TalentPer") @field:Json(name = "TalentPer") var talentPercentage: Int? = null,
    @Json(name = "TalentName") @field:Json(name = "TalentName") var talentName: String? = null,
    @Json(name = "OrderType") @field:Json(name = "OrderType") var orderType: String? = null,
    @Json(name = "OrderCategory") @field:Json(name = "OrderCategory") var orderCategory: String? = null,
    @Json(name = "VATPer") @field:Json(name = "VATPer") var VATPercentage: Int? = null
)