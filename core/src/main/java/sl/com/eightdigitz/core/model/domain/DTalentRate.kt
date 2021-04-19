package sl.com.eightdigitz.core.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DTalentRate(
    var id: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var deletedAt: String? = null,
    var talentID: String? = null,
    var categoryID: String? = null,
    var typeID: String? = null,
    var rate: Int? = null,
    var companyPercentage: Int? = null,
    var talentPercentage: Int? = null,
    var talentName: String? = null,
    var orderType: String? = null,
    var orderCategory: String? = null,
    var VATPercentage: Int? = null
): Parcelable
