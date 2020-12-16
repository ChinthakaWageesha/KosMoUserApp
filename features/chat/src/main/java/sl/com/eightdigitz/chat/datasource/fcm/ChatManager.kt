package sl.com.eightdigitz.chat.datasource.fcm

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.CONVERSATION
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.KEY_RECEIVER_ID
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.KEY_STATE
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.REFERENCE_MESSAGES
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.STATE_DELIVER
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.STATE_NOT_READ
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.STATE_READ
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.TYPE_RECEIVED
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.TYPE_SENT
import sl.com.eightdigitz.chat.R
import sl.com.eightdigitz.chat.domain.model.*
import sl.com.eightdigitz.chat.presentation.ui.chat.ChatAdapterCallback
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*

const val REFERENCE_MESSAGE = "message"

class ChatManager constructor(
    private val context: Context,
    private val reference: String = REFERENCE_MESSAGES,
    private val userId: String,
    private val friendId: String
) {

    var callback: ChatAdapterCallback? = null
    private var chatFirebaseAdapter: ChatFirebaseAdapter? = null
    private var chatRecyclerAdapter: ChatRecyclerAdapter? = null
    private var dbRef: DatabaseReference
    private var path: String

    @LayoutRes
    var sendLayout: Int = R.layout.item_chat_message_send
    @LayoutRes
    var recieveLayout: Int = R.layout.item_chat_message_recieved
    var recyclerView: RecyclerView? = null

    fun setRecyclerItems(
        recyclerView: RecyclerView,
        @LayoutRes sendLayout: Int = R.layout.item_chat_message_send,
        @LayoutRes recieveLayout: Int = R.layout.item_chat_message_recieved
    ): ChatManager {
        this.sendLayout = sendLayout
        this.recieveLayout = recieveLayout
        this.recyclerView = recyclerView
        return this
    }

    init {
        val database = FirebaseDatabase.getInstance().reference
        dbRef = FirebaseDatabase.getInstance().getReference(reference)
        dbRef.keepSynced(true)
        path =
            getPath(
                userId,
                friendId,
                false
            )
    }

    companion object {
        fun getPath(
            userId: String,
            friendId: String,
            withSepareter: Boolean
        ): String {
            if (withSepareter) {
                return "${userId}/$friendId"
            } else {
                return if (userId < friendId) {
                    "${userId}_$friendId"
                } else {
                    "${friendId}_$userId"
                }
            }
        }

        fun getDatabaseRef(reference: String): DatabaseReference =
            FirebaseDatabase.getInstance().getReference(reference)

        fun deleteMessages(path: String) {
            getDatabaseRef(
                REFERENCE_MESSAGE
            ).child(path).removeValue()
        }
    }

    fun startListening() {
        chatFirebaseAdapter?.startListening()
        chatRecyclerAdapter?.startListener()
    }

    fun stopListening() {
        chatFirebaseAdapter?.stopListening()
        chatRecyclerAdapter?.stopListener()
    }

    fun readMessageNormal() {
        val query = FirebaseDatabase.getInstance()
            .getReference(reference)
            .child(path)

        chatRecyclerAdapter =
            ChatRecyclerAdapter(
                userId,
                query,
                mutableListOf(),
                sendLayout,
                recieveLayout
            )

        chatRecyclerAdapter?.callbackAdapter = callback

        chatRecyclerAdapter?.registerAdapterDataObserver(object :
            RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                recyclerView?.recycledViewPool?.clear()
                recyclerView?.smoothScrollToPosition(chatRecyclerAdapter?.itemCount!! - 1)
            }
        })

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        linearLayoutManager.stackFromEnd = true
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView?.adapter = chatRecyclerAdapter
    }

    fun readMessageList(readMessageList: (MutableList<ChatMessage>) -> Unit) {
        val query = FirebaseDatabase.getInstance()
            .getReference(reference)
            .child(path)
            .orderByChild(KEY_RECEIVER_ID)
            .equalTo(userId)

        val listMessage = mutableListOf<ChatMessage>()

        query.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val value = p0.getValue(ChatMessage::class.java)
                if (value?.status != STATE_READ) {
                    value.let { valueMessage -> listMessage.add(valueMessage!!) }
                }
                readMessageList.invoke(listMessage)
            }

            override fun onChildRemoved(p0: DataSnapshot) {
            }
        })
    }

    fun readMessage() {
        val query = FirebaseDatabase.getInstance()
            .getReference(reference)
            .child(path)

        val options = FirebaseRecyclerOptions.Builder<ChatMessage>()
            .setQuery(query) { snapshot ->
                val value = snapshot.getValue(ChatMessage::class.java)

                if (value?.sender_id == userId && value?.status == STATE_NOT_READ) {
                    value.status = STATE_DELIVER
                } else {
                    value?.status = STATE_READ
                }

                value!!
            }.build()

//        chatFirebaseAdapter =
//            ChatFirebaseAdapter(
//                options,
//                sendLayout,
//                recieveLayout
//            )
//
//        chatFirebaseAdapter?.registerAdapterDataObserver(object :
//            RecyclerView.AdapterDataObserver() {
//            override fun onItemRangeInserted(
//                positionStart: Int,
//                itemCount: Int
//            ) {
//                super.onItemRangeInserted(positionStart, itemCount)
//                recyclerView?.smoothScrollToPosition(chatFirebaseAdapter?.itemCount!! - 1)
//            }
//        })
//
//        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
//        linearLayoutManager.stackFromEnd = true
//        recyclerView?.setHasFixedSize(true)
//        recyclerView?.layoutManager = linearLayoutManager
//        recyclerView?.adapter = chatFirebaseAdapter
    }

    fun writeMessage(
        chat: ChatMessage,
        sender: String,
        type: Int,
        onSuccess: (message: String, receiverId: String) -> Unit
    ) {
        val child = dbRef.child(path).push()
//        val chat = ChatMessage()
//        chat.message = message
        chat.message_id = child.key
        chat.sender_id = sender
        chat.receiver_id = friendId
        chat.created_at = ServerValue.TIMESTAMP
        chat.message_type = type
        chat.status = STATE_NOT_READ
        child.setValue(chat).onSuccessTask {
            child.updateChildren(mapOf(KEY_STATE to STATE_DELIVER))
        }.addOnSuccessListener {
            onSuccess(chat.message!!, friendId)
        }
        updateConversationMessage(chat)
    }

    /**
     * Update recent conversation
    **/
    private fun updateConversationMessage(chat: ChatMessage) {
        val recent = FirebaseDatabase.getInstance().getReference(CONVERSATION)
        recent.updateChildren(mapOf(
            getPath(
                userId,
                friendId,
                true
            ) to chat))
        recent.updateChildren(mapOf(
            getPath(
                friendId,
                userId,
                true
            ) to chat))
    }
}