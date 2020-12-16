package sl.com.eightdigitz.chat.presentation.ui.user

import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import sl.com.eightdigitz.chat.domain.model.CUser
import sl.com.eightdigitz.presentation.extensions.inflate
import sl.com.eightdigitz.presentation.extensions.loadImageRound
import sl.com.eightdigitz.chat.R
import kotlinx.android.synthetic.main.item_chat_header.view.*

class UserAdapter(var userList: MutableList<CUser>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val handler = Handler()
    private var isClicked: Boolean = false
    var callback: UserAdapter.AdapterCallback? = null
    var userFilteredList: MutableList<CUser> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_chat_header, false))
    }

    override fun getItemCount(): Int = userFilteredList.size

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(position: Int) {
            val user = userFilteredList[position]
            user.avatar_url?.let { itemView.iv_user_image.loadImageRound(it) }
                ?: run {
                    itemView.iv_user_image.setImageResource(R.drawable.placeholder_chat)
                }
            itemView.tv_user_name.text = user.getFullName()

            itemView.setOnClickListener {
                if (!isClicked) {
                    callback?.onClickChatHead(userFilteredList[adapterPosition])
                }
                isClicked = true
                handler.postDelayed({
                    isClicked = false
                }, 1000)
            }
        }
    }

    /**
     * Filter user list using first name and last name
     * */
    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val search = constraint.toString()
                userFilteredList = if (search.isEmpty()) {
                    userList
                } else {
                    val resultList: MutableList<CUser> = mutableListOf()
                    for (user in userList) {
                        try {
                            if (user.first_name?.toLowerCase()?.contains(search.toLowerCase())!! ||
                                user.last_name?.toLowerCase()?.contains(search.toLowerCase())!! ||
                                (user.first_name + " " + user.last_name)?.toLowerCase()?.contains(search.toLowerCase())!!
                            )resultList.add(user)

                        }catch (e: Exception){
                            e.printStackTrace()
                        }

                    }
                    resultList
                }
                val filteredResults = FilterResults()
                filteredResults.values = userFilteredList
                return filteredResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                userFilteredList = results?.values as MutableList<CUser>
                notifyDataSetChanged()
            }
        }
    }

    fun addFriendList(list: MutableList<CUser>) {
        userFilteredList.clear()
        userFilteredList.addAll(list)
        userList.clear()
        userList.addAll(list)
        notifyDataSetChanged()
    }

    interface AdapterCallback {
        fun onClickChatHead(cUser: CUser)
    }
}