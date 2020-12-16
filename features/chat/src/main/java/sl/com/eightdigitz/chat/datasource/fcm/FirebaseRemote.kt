package sl.com.eightdigitz.chat.datasource.fcm

import androidx.lifecycle.MutableLiveData
import sl.com.eightdigitz.presentation.extensions.toJsonString
import sl.com.eightdigitz.chat.domain.model.CUser
import sl.com.eightdigitz.chat.domain.model.ChatMessage
import sl.com.eightdigitz.chat.domain.model.Conversation
import com.google.firebase.database.*
import com.google.gson.Gson


class FirebaseRemote {

    private var userRef: DatabaseReference = FirebaseDatabase.getInstance()
        .getReference(FCMReference.USERS)
    private var idCountRef: DatabaseReference = FirebaseDatabase.getInstance()
        .getReference(FCMReference.ID_COUNT)
    private var conversationRef: DatabaseReference = FirebaseDatabase.getInstance()
        .getReference(FCMReference.CONVERSATION)
    private lateinit var eventListener: ValueEventListener
    private lateinit var eventRecentListener: ValueEventListener
    private lateinit var eventConversationListener: ValueEventListener

    init {
        userRef.keepSynced(true)
        conversationRef.keepSynced(true)
    }

//    fun writeUser(
//        user: FirebaseUser,
//        idCount: Int
//    ) {
//        val databaseReference = userRef.child(user.uid)
//        databaseReference.setValue(User(id = null, firebase_id = user.uid, first_name = user.displayName, email =  user.email))
//        setIDCount(idCount)
//    }

    fun getIDCount(liveData: MutableLiveData<Int>) {
        idCountRef.child(FCMReference.ID_COUNT_VAL)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    liveData.value = dataSnapshot.getValue(Int::class.java)
                }
            })
    }

    fun readUser(liveData: MutableLiveData<List<CUser>>) {
        eventListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val usersList = arrayListOf<CUser>()

                dataSnapshot.value?.let { mValue ->
                    val users = mValue as HashMap<*, *>
                    users.values.forEach {
                        try {
                            val user = Gson().fromJson(it.toString(), CUser::class.java)
                            usersList.add(user)
                        } catch (e: Exception) {
                            println(e.stackTrace)
                        }

                    }
                }
                liveData.value = usersList
            }
        }
        userRef.addValueEventListener(eventListener)
    }

    fun setIDCount(id: Int) {
        val map = mapOf(FCMReference.ID_COUNT_VAL to id)
        idCountRef.updateChildren(map)
    }

    fun getRecentIds(userId: String, getRecentIds: (MutableList<String>) -> Unit) {
        eventListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.value?.let { value ->
                    val users = (value as HashMap<*, *>).keys.toTypedArray()
                    getRecentIds.invoke(users.map { (it as String) }.toMutableList())
                } ?: kotlin.run {
                    getRecentIds.invoke(mutableListOf())
                }
            }
        }
        conversationRef.child(userId).addValueEventListener(eventListener)
    }

    /**
     * This function will be deprecated
     * @see getRecentChats()
     * */
    @Deprecated("use getRecentChats instead this function")
    fun getRecent(
        path: String,
        getRecent: (ChatMessage) -> Unit
    ) {
        eventRecentListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val message = dataSnapshot.getValue(ChatMessage::class.java)
                message?.let { getRecent.invoke(it) }
            }
        }
        conversationRef.child(path).addValueEventListener(eventRecentListener)
    }

    fun deleteRecent(path: String) {
        conversationRef.child(path).removeValue()
    }

    fun destroy() {
        try {
            userRef.removeEventListener(eventListener)
            conversationRef.removeEventListener(eventRecentListener)
            conversationRef.removeEventListener(eventConversationListener)
        } catch (e: Exception) {
        }
    }

    /**
     * Get recent conversation and the User's details from FCM conversation and user data structures
     * @see Conversation
     * */
    fun getRecentChats(
        userId: String,
        getRecentChats: (MutableLiveData<MutableList<Conversation>>) -> Unit
    ) {
        var recentList = mutableListOf<Conversation>()
        eventConversationListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val chatList = MutableLiveData<MutableList<Conversation>>()

                // get recent conversation from "conversation" and associate it with the sender from "user"
                dataSnapshot.value.let { mRecent ->
                    val recentChat = mRecent as? HashMap<*, *>
                    recentChat?.values?.forEach {

                        try {
                            val chat =
                               Gson().fromJson(it.toJsonString(), ChatMessage::class.java)

                            var friendId = if(chat?.sender_id!! == userId) chat?.receiver_id!! else chat?.sender_id!!

                            userRef.child(friendId)
                                .addValueEventListener(object : ValueEventListener {
                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                                        try {
                                            val user = dataSnapshot.getValue(CUser::class.java)
                                            val chats = Conversation(user!!, chat)
                                            recentList.add(chats)

                                            chatList.value = recentList
                                            getRecentChats.invoke(chatList)

                                        } catch (e: Exception) {
                                            e.printStackTrace()
                                        }
                                    }

                                    override fun onCancelled(p0: DatabaseError) {
                                    }
                                })
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                    if (recentList.size > 0) {
                        chatList.value = recentList
                        getRecentChats.invoke(chatList)
                        recentList = mutableListOf()
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        // added recent value change listener
        conversationRef.child(userId).addValueEventListener(eventConversationListener)
    }
}