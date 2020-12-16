package sl.com.eightdigitz.notifications.presentation.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * // use this model to map the fcm notification data to the app
 */

@Parcelize
class PCustomNotificationModel(
    var jobId: String? = null,
    var body: String? = null,
    var type: String? = null,
    var title: String? = null,
    var sound: String? = null
) : Parcelable
