package sl.com.eightdigitz.notifications.presentation.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Model to use in the notification list view
 *
 */

@Parcelize
data class PNotification(
    @SerializedName("title") val title: String? = null,
    @SerializedName("body") val body: String? = null
) : Parcelable
