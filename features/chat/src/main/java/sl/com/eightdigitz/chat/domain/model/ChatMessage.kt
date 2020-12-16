package sl.com.eightdigitz.chat.domain.model

import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.KEY_FILES
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.KEY_MESSAGE
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.KEY_MESSAGE_ID
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.KEY_RECEIVER_ID
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.KEY_SENDER_ID
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.KEY_STATE
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.KEY_CREATED_AT
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.KEY_TYPE_MESSAGE
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.STATE_NOT_READ
import com.google.firebase.database.Exclude
import com.google.gson.annotations.SerializedName



class ChatMessage : Comparable<ChatMessage> {

    @SerializedName(KEY_MESSAGE_ID)
    var message_id: String? = null
    @SerializedName(KEY_MESSAGE)
    var message: String? = null
    @SerializedName(KEY_SENDER_ID)
    var sender_id: String? = null
    @SerializedName(KEY_CREATED_AT)
    var created_at: Any? = null
    @SerializedName(KEY_RECEIVER_ID)
    var receiver_id: String? = null
    @SerializedName(KEY_TYPE_MESSAGE)
    var message_type: Int? = null
    @SerializedName(KEY_STATE)
    var status: Int = STATE_NOT_READ
    @SerializedName(KEY_FILES)
    var files: ArrayList<CFile>? = null

    private var messageType: Int = 0
    private var msgCount: Int = 0

    @Exclude
    fun getType(): Int {
        return messageType
    }

    fun setType(messageType: Int) {
        this.messageType = messageType
    }

    @Exclude
    fun getMsgCount(): Int {
        return msgCount
    }

    fun setMsgCount(count: Int) {
        this.msgCount = count
    }

    override operator fun compareTo(o: ChatMessage): Int {
        return (this.created_at as Long).compareTo(o.created_at as Long)
    }
}












