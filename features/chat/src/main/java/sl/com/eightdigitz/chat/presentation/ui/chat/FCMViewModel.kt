package sl.com.eightdigitz.chat.presentation.ui.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sl.com.eightdigitz.chat.datasource.fcm.FirebaseRemote
import sl.com.eightdigitz.chat.domain.model.Conversation

/**
 * Firebase Cloud Messaging View model
 * This viewmodel retrieve messages and user details
 *
 * */
class FCMViewModel : ViewModel(){

    private var firebaseRemote: FirebaseRemote =
        FirebaseRemote()
    var conversationLiveData : MutableLiveData<MutableList<Conversation>> = MutableLiveData()

    fun getRecentChat(userID: String): MutableLiveData<MutableList<Conversation>> {
        firebaseRemote.getRecentChats(userID){
            conversationLiveData.value = it.value
        }
        return conversationLiveData
    }
}