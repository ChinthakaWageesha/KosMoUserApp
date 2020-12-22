package sl.com.eightdigitz.core.model.presentation

import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import sl.com.eightdigitz.core.model.presentation.PBusiness

@Parcelize
data class PUser(
    var id: Int? = null,
    var uuid: String? = null,
    var name: String? = null,
    var phone: String? = null,
    var email: String? = null,
    var avatarUrl: String? = null,
    var timezone: String? = null,
    var type: Int? = null,
    var signupError: String? = null,
    var business: PBusiness? = null,
    var services: String? = null,
    var accessToken: String? = null
) : Parcelable {
    @IgnoredOnParcel
    var userType: String? = null

    @IgnoredOnParcel
    var idSM: String? = null

    @IgnoredOnParcel
    var token: String? = null

    @IgnoredOnParcel
    var provider: String? = null

    @IgnoredOnParcel
    var deviceId: String? = null

    @IgnoredOnParcel
    var deviceType: String? = null

    @IgnoredOnParcel
    var password: String? = null

    @IgnoredOnParcel
    var devicePushToken: String? = null

    @IgnoredOnParcel
    var message: String? = null
}
