package sl.com.eightdigitz.chat.domain.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import kotlinx.android.parcel.IgnoredOnParcel

/**
 * Created by Chathura Wijesinghe on 8/4/19.
 * Elegantmedia
 * chaturaw.emedia@gmail.com
 */


/**
 * Chat module User model
 * */
data class CUser(
    var id: String? = null,
    var uuid: String? = null,
    var first_name: String? = null,
    var last_name: String? = null,
    var phone: String? = null,
    var email: String? = null,
    var avatar_url: String? = null,
    var address: String? = null,
    var accessToken: String? = null,
    var deviceId: String? = null,
    var deviceType: String? = null,
    var devicePushToken: String? = null,
    var password: String? = null,
    var username: String? = null,
    var timezone: String? = null,
    val firebase_id: String? = null
) : Parcelable {

    @IgnoredOnParcel
    var idSM: String? = null
    @IgnoredOnParcel
    var token: String? = null
    @IgnoredOnParcel
    var chatMessage: ChatMessage? = null
    @IgnoredOnParcel
    var message: String? = null

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(
        parcel: Parcel,
        flags: Int
    ) {
        parcel.writeString(id)
        parcel.writeString(uuid)
        parcel.writeString(first_name)
        parcel.writeString(last_name)
        parcel.writeString(phone)
        parcel.writeString(email)
        parcel.writeString(avatar_url)
        parcel.writeString(address)
        parcel.writeString(accessToken)
        parcel.writeString(deviceId)
        parcel.writeString(deviceType)
        parcel.writeString(devicePushToken)
        parcel.writeString(password)
        parcel.writeString(username)
        parcel.writeString(timezone)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "CUser(id=$id, uuid=$uuid, first_name=$first_name, last_name=$last_name, phone=$phone, email=$email, avatar_url=$avatar_url, address=$address, accessToken=$accessToken, deviceId=$deviceId, deviceType=$deviceType, devicePushToken=$devicePushToken, password=$password, username=$username, timezone=$timezone, firebase_id=$firebase_id, idSM=$idSM, token=$token, chatMessage=$chatMessage, message=$message)"
    }

    companion object CREATOR : Creator<CUser> {
        override fun createFromParcel(parcel: Parcel): CUser {
            return CUser(parcel)
        }

        override fun newArray(size: Int): Array<CUser?> {
            return arrayOfNulls(size)
        }
    }

    fun getFullName() : String{
        if(first_name != null && last_name != null)
            return "$first_name $last_name"
        if (first_name !=null && last_name == null)
            return first_name as String
        if (last_name !=null && first_name == null)
            return last_name as String

        return ""
    }
}
