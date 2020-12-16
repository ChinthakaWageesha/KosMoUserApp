package sl.com.eightdigitz.chat.media

import android.os.Parcel
import android.os.Parcelable

data class MediaContent(
    var name: String? = null,
    var status: Boolean? = null,
    var audio: String? = null,
    var video: String? = null,
    var contentType: String? = null
) : Parcelable {

    var progress: Long = 0
    var statusContent: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
        progress = parcel.readLong()
        statusContent = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeValue(status)
        parcel.writeString(audio)
        parcel.writeString(video)
        parcel.writeString(contentType)
        parcel.writeLong(progress)
        parcel.writeInt(statusContent)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MediaContent> {
        override fun createFromParcel(parcel: Parcel): MediaContent {
            return MediaContent(parcel)
        }

        override fun newArray(size: Int): Array<MediaContent?> {
            return arrayOfNulls(size)
        }
    }


}