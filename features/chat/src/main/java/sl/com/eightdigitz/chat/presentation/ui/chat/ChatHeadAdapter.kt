package sl.com.eightdigitz.chat.presentation.ui.chat

import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.TYPE_DOCUMENT
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.TYPE_IMAGE
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.TYPE_TEXT
import sl.com.eightdigitz.chat.domain.model.CUser
import sl.com.eightdigitz.chat.util.getTime
import sl.com.eightdigitz.presentation.extensions.inflate
import sl.com.eightdigitz.presentation.extensions.loadImageRound
import sl.com.eightdigitz.chat.R
import kotlinx.android.synthetic.main.item_chat_header.view.*

class ChatHeadAdapter(var userRecentList: MutableList<CUser>) :
    RecyclerView.Adapter<ChatHeadAdapter.ViewHolder>() {

    private val handler = Handler()
    private var isClicked: Boolean = false
    var callback: AdapterCallback? = null
    private var userFilteredRecentList: MutableList<CUser> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_chat_header, false))
    }

    override fun getItemCount(): Int = userFilteredRecentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    /**
     * Filter user list using first name and last name
     * */
    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val search = constraint.toString()
                userFilteredRecentList = if (search.isEmpty()) {
                    userRecentList
                } else {
                    val resultList: MutableList<CUser> = mutableListOf()
                    for (user in userRecentList) {
                        if (user.first_name?.toLowerCase()?.contains(search.toLowerCase())!! ||
                            user.last_name?.toLowerCase()?.contains(search.toLowerCase())!! ||
                            (user.first_name + " " + user.last_name)?.toLowerCase()
                                ?.contains(search.toLowerCase())!!
                        )
                            resultList.add(user)
                    }
                    resultList
                }
                val filteredResults = FilterResults()
                filteredResults.values = userFilteredRecentList
                return filteredResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                userFilteredRecentList = results?.values as MutableList<CUser>
                notifyDataSetChanged()
            }
        }
    }

    fun addFriendList(list: MutableList<CUser>) {
        userFilteredRecentList.clear()
        userFilteredRecentList.addAll(list)
        userRecentList.clear()
        userRecentList.addAll(list)
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        userFilteredRecentList.removeAt(position)
        userRecentList.removeAt(position)
        notifyDataSetChanged()
    }

    fun clearList() {
        userRecentList.clear()
        userFilteredRecentList.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(position: Int) {
            val user = userFilteredRecentList[position]
            user.avatar_url?.let { itemView.iv_user_image.loadImageRound(it) }
                ?: run {
                    itemView.iv_user_image.setImageResource(R.drawable.placeholder_chat)
                }
            itemView.tv_user_name.text = user.getFullName()

            if (user.chatMessage?.getMsgCount() == 0 || user.chatMessage?.getMsgCount() == null) {
                itemView.tv_recent_count.visibility = View.GONE
            } else {
                itemView.tv_recent_count.visibility = View.VISIBLE
                itemView.tv_recent_count.text = user.chatMessage?.getMsgCount().toString()
            }

            user.chatMessage?.message_type?.let { type ->
                when (type) {
                    TYPE_TEXT -> {
                        itemView.tv_message.text = user.chatMessage?.message
                    }
                    TYPE_IMAGE -> {
                        itemView.tv_message.text = "Photo"
                    }
                    TYPE_DOCUMENT -> {
                        itemView.tv_message.text = "Document"
                    }
                }
            }

            user.chatMessage?.created_at?.let { time ->

                val createAt =
                    when (time) {
                        is Double -> {
                            time.toLong()
                        }
                        is Long -> {
                            time
                        }
                        else -> {
                            0L
                        }
                    }
                val milis = createAt
                itemView.tv_time.text = milis.getTime()
            }

            if (user.chatMessage != null) {
                itemView.tv_message.visibility = View.VISIBLE
                itemView.tv_time.visibility = View.VISIBLE
            } else {
                itemView.tv_message.visibility = View.INVISIBLE
                itemView.tv_time.visibility = View.INVISIBLE
            }

            itemView.setOnClickListener{
                if (!isClicked) {
                    callback?.onClickChatHead(userFilteredRecentList[adapterPosition])
                }
                isClicked = true
                handler.postDelayed({
                    isClicked = false
                }, 1000)
            }
        }
    }

    interface AdapterCallback {
        fun onClickChatHead(cUser: CUser)
    }
}