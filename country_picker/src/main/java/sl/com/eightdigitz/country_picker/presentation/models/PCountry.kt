package sl.com.eightdigitz.country_picker.presentation.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PCountry(
    var code: String? = null,
    var name: String? = null,
    var dialCode: String? = null
) : Parcelable