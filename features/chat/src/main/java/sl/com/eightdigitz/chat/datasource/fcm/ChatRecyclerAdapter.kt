package sl.com.eightdigitz.chat.datasource.fcm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.KEY_READ
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.KEY_STATE
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.STATE_READ
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.TYPE_DATE
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.TYPE_DOCUMENT
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.TYPE_IMAGE
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.TYPE_RECEIVED
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.TYPE_SENT
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.TYPE_VIDEO
import sl.com.eightdigitz.chat.R
import sl.com.eightdigitz.chat.domain.model.*
import sl.com.eightdigitz.chat.presentation.ui.chat.ChatAdapterCallback
import sl.com.eightdigitz.chat.util.DD_MMM_EEEE
import sl.com.eightdigitz.chat.util.toCustomDate
import sl.com.eightdigitz.chat.util.toDate
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference

class ChatRecyclerAdapter(
    val userId: String,
    val query: DatabaseReference,
    val chatList: MutableList<ChatMessage>,
    @LayoutRes val sendLayout: Int,
    @LayoutRes val receiveLayout: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    ChatAdapterCallback {

    var callbackAdapter: ChatAdapterCallback? = null
    private var preDate: String? = null

    private val firebaseDbListener = object : ChildEventListener {
        override fun onCancelled(p0: DatabaseError) {
        }

        override fun onChildMoved(p0: DataSnapshot, p1: String?) {
        }

        override fun onChildChanged(p0: DataSnapshot, p1: String?) {
        }

        override fun onChildAdded(p0: DataSnapshot, p1: String?) {
            val value = p0.getValue(ChatMessage::class.java)

            if (value?.sender_id == userId) {
                value.setType(TYPE_SENT)
            } else {
                value?.setType(TYPE_RECEIVED)
            }

            val date = (value?.created_at as Long).toDate().toCustomDate(DD_MMM_EEEE)
            if (date != preDate) {
                val dateMessage = ChatMessage().apply {
                    created_at = value.created_at
                    setType(TYPE_DATE)
                }
                chatList.add(dateMessage)
            }

            preDate = date

            value.let { chatList.add(it) }
            notifyDataSetChanged()

            if (value.receiver_id == userId) {
                query.child(value.message_id!!).updateChildren(mapOf(KEY_STATE to STATE_READ))
            }
        }

        override fun onChildRemoved(p0: DataSnapshot) {
        }
    }

    fun startListener() {
        query.addChildEventListener(firebaseDbListener)
    }

    fun stopListener() {
        chatList.clear()
        query.removeEventListener(firebaseDbListener)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_SENT -> ViewHolderSent(
                inflater.inflate(sendLayout, parent, false)
            )

            TYPE_RECEIVED -> ViewHolderReceive(
                inflater.inflate(receiveLayout, parent, false)
            )

            TYPE_DATE -> ViewHolderDate(
                inflater.inflate(R.layout.item_chat_date, parent, false)
            )

            else -> throw IllegalStateException()
        }
    }

    override fun getItemCount(): Int = chatList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chatMessage = chatList[position]
        when {
            getItemViewType(position) == TYPE_SENT -> {
                (holder as ViewHolderSent).onBind(chatMessage)
                holder.callback = this
            }
            getItemViewType(position) == TYPE_RECEIVED -> {
                (holder as ViewHolderReceive).onBind(chatMessage)
                holder.callback = this
            }
            getItemViewType(position) == TYPE_DATE -> (holder as ViewHolderDate)
                .onBind(chatMessage)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return chatList[position].getType()!!.toInt()
    }

    override fun onClickVideo(position: Int) {
        callbackAdapter?.onClickDocument(chatList[position], TYPE_VIDEO)
    }

    override fun onClickImage(position: Int) {
        callbackAdapter?.onClickDocument(chatList[position], TYPE_IMAGE)
    }

    override fun onClickDocument(position: Int) {
        callbackAdapter?.onClickDocument(chatList[position], TYPE_DOCUMENT)
    }
}