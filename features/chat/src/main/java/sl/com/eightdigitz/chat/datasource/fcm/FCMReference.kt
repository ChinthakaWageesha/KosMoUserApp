package sl.com.eightdigitz.chat.datasource.fcm

object FCMReference {
    const val USERS = "users"

    const val ID_COUNT = "id_count"
    const val ID_COUNT_VAL = "count"


    const val CONVERSATION = "conversations"


//    Message References

    const val REFERENCE_MESSAGES = "messages"
    const val KEY_MESSAGE = "message"
    const val KEY_MESSAGE_ID = "message_id"
    const val KEY_SENDER_ID = "sender_id"
    const val KEY_RECEIVER_ID = "receiver_id"
    const val KEY_TYPE_MESSAGE = "message_type"
    const val KEY_CREATED_AT = "created_at"
    const val KEY_STATE = "status"
    const val KEY_READ = "read"
    const val KEY_FILES = "files"

    const val TYPE_SENT = 1
    const val TYPE_RECEIVED = 2
    const val TYPE_DATE = 3

    const val TYPE_TEXT = 1
    const val TYPE_ATTRIBUTE = 2
    const val TYPE_EMOJI = 3
    const val TYPE_IMAGE = 4
    const val TYPE_AUDIO = 5
    const val TYPE_VIDEO = 6
    const val TYPE_DOCUMENT = 7
    const val TYPE_LOCATION = 8
    const val TYPE_CONTACTS = 9


    const val STATE_NOT_READ = 0
    const val STATE_DELIVER = 1
    const val STATE_READ = 2



}