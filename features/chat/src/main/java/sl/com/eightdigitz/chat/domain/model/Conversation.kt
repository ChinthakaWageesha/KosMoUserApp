package sl.com.eightdigitz.chat.domain.model

/**
 * Conversation data model
 * @see CUser , and also
 * @see ChatMessage
 *
 * */
data class Conversation(var user: CUser, var chatMessage: ChatMessage)