package sl.com.eightdigitz.chat.presentation.ui.chat

import sl.com.eightdigitz.chat.domain.model.ChatMessage

interface ChatAdapterCallback {
    fun onClickDocument(position: Int) {}
    fun onClickImage(position: Int) {}
    fun onClickVideo(position: Int) {}
    fun onClickDocument(chatMessage: ChatMessage, type: Int) {}
}