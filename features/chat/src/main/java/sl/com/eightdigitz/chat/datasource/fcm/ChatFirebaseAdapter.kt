package sl.com.eightdigitz.chat.datasource.fcm

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.STATE_DELIVER
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.STATE_NOT_READ
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.STATE_READ
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.TYPE_DATE
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.TYPE_DOCUMENT
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.TYPE_IMAGE
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.TYPE_RECEIVED
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.TYPE_SENT
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.TYPE_TEXT
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.TYPE_VIDEO
import sl.com.eightdigitz.chat.domain.model.ChatMessage
import sl.com.eightdigitz.chat.presentation.ui.chat.ChatAdapterCallback
import sl.com.eightdigitz.chat.util.HH_MM
import sl.com.eightdigitz.chat.util.getDate
import sl.com.eightdigitz.chat.util.getScreenWidth
import sl.com.eightdigitz.chat.util.toCustomDate
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import sl.com.eightdigitz.chat.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import kotlinx.android.synthetic.main.item_chat_date.view.*
import java.util.*


class ChatFirebaseAdapter(
    option: FirebaseRecyclerOptions<ChatMessage>,
    @LayoutRes val sendLayout: Int,
    @LayoutRes val receiveLayout: Int
) : FirebaseRecyclerAdapter<ChatMessage, RecyclerView.ViewHolder>(option) {

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

            else -> throw IllegalStateException() as Throwable
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItem(position).getType()!!
    }

    override fun onBindViewHolder(
        viewHolder: RecyclerView.ViewHolder,
        position: Int,
        chatMessage: ChatMessage
    ) {
        when {
            getItemViewType(position) == TYPE_SENT -> (viewHolder as ViewHolderSent)
                .onBind(chatMessage)
            getItemViewType(position) == TYPE_RECEIVED -> (viewHolder as ViewHolderReceive)
                .onBind(chatMessage)
            getItemViewType(position) == TYPE_DATE -> (viewHolder as ViewHolderDate)
                .onBind(chatMessage)
        }
    }
}

class ViewHolderSent(view: View) : RecyclerView.ViewHolder(view) {

    var callback: ChatAdapterCallback? = null
    private val tvMessage: TextView? = itemView.findViewWithTag("send_message")
    private val ivImage: ImageView? = itemView.findViewWithTag("send_image")
    private val tvTime: TextView? = itemView.findViewWithTag("send_time")
    private val clAttachment: ConstraintLayout? = itemView.findViewWithTag("send_attachment")
    private val tvAttachmentName: TextView? = itemView.findViewWithTag("send_attachment_name")
    private val ivIndicat: ImageView? = itemView.findViewWithTag("indicator")

    fun onBind(
        chatMessage: ChatMessage
    ) {
        ivImage?.tag = null
        ivImage?.visibility = View.GONE
        clAttachment?.visibility = View.GONE
        tvMessage?.visibility = View.GONE
        ivImage?.let { Glide.with(itemView).clear(it) }
        val width = itemView.context.getScreenWidth() / 2

        chatMessage.created_at?.let { time ->
            val date = Date()
            date.time = (time as Long)
            tvTime?.text = date.toCustomDate(HH_MM)
        }

        when (chatMessage.status) {
            STATE_READ -> {
                ivIndicat?.setImageResource(R.drawable.ic_chat_read)
                ivIndicat?.visibility = View.VISIBLE
            }
            STATE_DELIVER -> {
                ivIndicat?.setImageResource(R.drawable.ic_chat_deliver)
                ivIndicat?.visibility = View.VISIBLE
            }
            STATE_NOT_READ -> {
                ivIndicat?.visibility = View.GONE
            }
        }

        when (chatMessage.message_type) {
            TYPE_TEXT -> {
                tvMessage?.visibility = View.VISIBLE
                tvMessage?.text = chatMessage.message
                itemView.setOnClickListener(null)
            }
            TYPE_IMAGE -> {
                ivImage?.visibility = View.VISIBLE
                ivImage?.let {
                    Glide.with(it)
                        .load(chatMessage.files?.get(0)?.thumbnail_path)
                        .thumbnail(
                            Glide.with(it).load(R.drawable.placeholder_chat).override(
                                width,
                                width
                            ).centerCrop()
                        )
                        .apply(RequestOptions.overrideOf(width, width).centerCrop())
                        .into(it)
                }
                ivImage?.setOnClickListener {
                    callback?.onClickImage(adapterPosition)
                }
            }
            TYPE_DOCUMENT -> {
                clAttachment?.visibility = View.VISIBLE
                chatMessage.message?.let {
                    tvAttachmentName?.text = Uri.parse(it).lastPathSegment
                }
                clAttachment?.setOnClickListener {
                    callback?.onClickDocument(adapterPosition)
                }
            }
            TYPE_VIDEO -> {
                ivImage?.visibility = View.VISIBLE
                ivImage?.let {
                    Glide.with(it)
                        .load(chatMessage.files?.get(0)?.file_path)
                        .thumbnail(
                            Glide.with(it).load(R.drawable.placeholder_chat).override(
                                width,
                                width
                            ).centerCrop()
                        )
                        .apply(RequestOptions.overrideOf(width, width).centerCrop())
                        .into(it)
                }
                ivImage?.setOnClickListener {
                    callback?.onClickVideo(adapterPosition)
                }
            }
        }
    }
}

class ViewHolderReceive(view: View) : RecyclerView.ViewHolder(view) {

    var callback: ChatAdapterCallback? = null
    private val tvMessage: TextView? = itemView.findViewWithTag("receive_message")
    private val ivImage: ImageView? = itemView.findViewWithTag("receive_image")
    private val tvTime: TextView? = itemView.findViewWithTag("receive_time")
    private val clAttachment: ConstraintLayout? = itemView.findViewWithTag("receive_attachment")
    private val tvAttachmentName: TextView? = itemView.findViewWithTag("receive_attachment_name")

    fun onBind(
        chatMessage: ChatMessage
    ) {
        ivImage?.tag = null
        ivImage?.visibility = View.GONE
        clAttachment?.visibility = View.GONE
        tvMessage?.visibility = View.GONE
        ivImage?.let { Glide.with(itemView).clear(it) }
        val width = itemView.context.getScreenWidth() / 2

        chatMessage.created_at?.let { time ->
            val date = Date()
            date.time = (time as Long)
            tvTime?.text = date.toCustomDate(HH_MM)
        }

        when (chatMessage.message_type) {
            TYPE_TEXT -> {
                tvMessage?.visibility = View.VISIBLE
                tvMessage?.text = chatMessage.message
                itemView.setOnClickListener(null)
            }
            TYPE_IMAGE -> {
                ivImage?.visibility = View.VISIBLE
                ivImage?.let {
                    Glide.with(it)
                        .load(chatMessage.files?.get(0)?.thumbnail_path)
                        .thumbnail(
                            Glide.with(it).load(R.drawable.placeholder_chat).override(
                                width,
                                width
                            ).centerCrop()
                        )
                        .apply(RequestOptions.overrideOf(width, width).centerCrop())
                        .into(it)
                }
                ivImage?.setOnClickListener {
                    callback?.onClickImage(adapterPosition)
                }
            }
            TYPE_DOCUMENT -> {
                clAttachment?.visibility = View.VISIBLE
                chatMessage.message?.let {
                    tvAttachmentName?.text = Uri.parse(it).lastPathSegment
                }
                clAttachment?.setOnClickListener {
                    callback?.onClickDocument(adapterPosition)
                }
            }
            TYPE_VIDEO -> {
                ivImage?.visibility = View.VISIBLE
                ivImage?.let {
                    Glide.with(it)
                        .load(chatMessage.files?.get(0)?.file_path)
                        .thumbnail(
                            Glide.with(it).load(R.drawable.placeholder_chat).override(
                                width,
                                width
                            ).centerCrop()
                        )
                        .apply(RequestOptions.overrideOf(width, width).centerCrop())
                        .into(it)
                }
                ivImage?.setOnClickListener {
                    callback?.onClickVideo(adapterPosition)
                }
            }
        }
    }
}

class ViewHolderDate(view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(chatMessage: ChatMessage) {
        val date = (chatMessage.created_at as Long).getDate()
        itemView.tv_date.text = date
    }
}
