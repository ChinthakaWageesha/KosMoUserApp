package sl.com.eightdigitz.chat.presentation.ui.chat

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
import sl.com.eightdigitz.presentation.extensions.*
import sl.com.eightdigitz.presentation.utile.PaginationScrollListener
import sl.com.eightdigitz.chat.R
import sl.com.eightdigitz.chat.datasource.fcm.ChatManager
import sl.com.eightdigitz.chat.di.injectChatModule
import sl.com.eightdigitz.chat.domain.model.ChatMessage
import sl.com.eightdigitz.chat.domain.model.CUser
import sl.com.eightdigitz.presentation.utile.OnSwipe
import sl.com.eightdigitz.presentation.utile.SwipeToDeleteCallback
import kotlinx.android.synthetic.main.fragment_chat_head.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ChatHeadFragment(var userId: String) : Fragment(), ChatHeadAdapter.AdapterCallback {


    private lateinit var chatHeadAdapter: ChatHeadAdapter
    private val viewModelProfile by inject<Any>()
    private val viewModel by inject<ChatViewModel>()
    private val fcmViewModel by viewModel<FCMViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        fcmViewModel.getRecentChat(userId!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectChatModule()
        (activity as AppCompatActivity).supportActionBar?.setActionBar(
            context!!,
            "Chat",
            isHomeUpEnables = false,
            isCenterTitle = true
        )

//        viewModel.liveUserListData.observe(this, Observer { updateList(it) })
//        viewModelProfile.liveData.observe(this, Observer { loadUserData(it) })
        //viewModelProfile.getUserData() //Todo
        /**
         * Observe FCM recent massages along with the User model
         * @see FCMViewModel
         * */
        fcmViewModel.getRecentChat(userId!!)

        fcmViewModel.conversationLiveData.observe(this, Observer { list ->
            var userList = mutableListOf<CUser>()
            list.forEach { recent ->
                var user = recent.user
                user.chatMessage = recent.chatMessage
                userList.add(user)
            }

            updateList(userList)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat_head, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        fetchChatHeads()

        chatHeadAdapter =
            ChatHeadAdapter(mutableListOf())
        chatHeadAdapter.callback = this
        rv_chat_head.apply {
            adapter = chatHeadAdapter
            val itemTouchHelper =
            ItemTouchHelper(SwipeToDeleteCallback(requireContext(), object : OnSwipe {
                override fun left(position: Int) {
                    removeChat(position)
                }
            }))
            itemTouchHelper.attachToRecyclerView(this)

//            addOnScrollListener(object :
//                PaginationScrollListener(rv_chat_head.layoutManager as LinearLayoutManager) {
//
//                override fun loadMoreItems() {
//                    viewModel.currentPage++
//                    viewModel.getFriendList()
//                }
//
//                override fun isLastPage(): Boolean = viewModel.isLastPage()
//
//                override fun isLoading(): Boolean = viewModel.isLoading
//            })
        }

//        et_search.setOnEditorActionListener { v, actionId, event ->
//            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                activity?.hideKeyboard()
//                clearList()
//                if (et_search.getStringTrim().isNotEmpty()) {
//                    viewModel.key = et_search.getStringTrim()
//                } else {
//                    viewModel.key = null
//                }
//                viewModel.getFriendList()
//                return@setOnEditorActionListener true
//            }
//            return@setOnEditorActionListener false
//        }

//      search recent chat
        et_search.addTextChangedListener(object : TextWatcher{
            override fun onTextChanged(search: CharSequence?, p1: Int, p2: Int, p3: Int) {
                chatHeadAdapter.getFilter().filter(search?.trim())
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun updateList(users: MutableList<CUser>){
        chatHeadAdapter.addFriendList(users)
        users.forEach {user->
            setRecentMessage(user.chatMessage!!)
        }
    }

    private fun removeChat(position: Int) {
        alert(getString(R.string.title_delete_chat), getString(R.string.message_delete_chat)) {
            cancelable = false
            positiveButton(R.string.action_ok) {
                val friend = chatHeadAdapter.userRecentList[position]
                //"${friend.firstName}'s conversation messages are deleted".showSuccess(context!!)
                viewModel.removeRecent(userId, friend.firebase_id!!)
                chatHeadAdapter.deleteItem(position)
                fcmViewModel.getRecentChat(userId!!)

                friend.firebase_id?.let { id ->
//                    TODO : Unfriend user
//                    userUnfriend(id)

                }
            }
            negativeButton(R.string.action_cancel) {
                chatHeadAdapter.notifyDataSetChanged()
            }
        }.show()
    }

    private fun updateList(resource: Resource<MutableList<CUser>>?) {
        resource?.let {
            when (it.state) {
                ResourceState.LOADING -> {

                }
                ResourceState.SUCCESS -> {
                    it.data?.let { it1 -> chatHeadAdapter.addFriendList(it1) }
                    it.data?.forEach { user ->
                        viewModel.getRecent(
                            ChatManager.getPath(
                            userId.toString(),
                            user.id.toString(), true))
                            .observe(this@ChatHeadFragment, Observer { message ->
                                setRecentMessage(message)
                            })
                    }
                }
                ResourceState.ERROR -> {
                }
            }
        }
    }

    private fun loadUserData(resource: Resource<CUser>?) {
        resource?.let {
            when (it.state) {
                ResourceState.LOADING -> {
                }
                ResourceState.SUCCESS -> {
                    userId = it.data?.id!!
                }
                ResourceState.ERROR -> {
                }
            }
        }
    }

    private fun setRecentMessage(message: ChatMessage) {
        if (chatHeadAdapter.itemCount != 0) {
            val user = chatHeadAdapter.userRecentList.firstOrNull {
                message.sender_id == it.firebase_id.toString() || message.receiver_id == it.firebase_id.toString()
            }
            user?.let { userFilter ->
                ChatManager(
                    context!!,
                    userId = userId.toString(),
                    friendId = userFilter.firebase_id.toString()
                ).readMessageList {
                    message.setMsgCount(it.size)
                    chatHeadAdapter.userRecentList.sortByDescending { userSort -> userSort.chatMessage?.created_at as? Long }
                    chatHeadAdapter.notifyDataSetChanged()
                }
                userFilter.chatMessage = message
                chatHeadAdapter.notifyDataSetChanged()
            }
        }
    }


//    private fun fetchChatHeads() {
//        activity?.withNetwork(
//            {
//                viewModel.getFriendList()
//            },
//            {
//               //Todo error message
//            })
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CODE_CHAT) {
            if (resultCode == Activity.RESULT_OK) {
                chatHeadAdapter.clearList()
//                fetchChatHeads()
            }
        }
    }

    override fun onClickChatHead(cUser: CUser) {
        startActivityForResult<ChatActivity>(REQ_CODE_CHAT) {
            putExtra(EXTRA_USER_ID, userId)
            putExtra(EXTRA_FRIEND_ID, cUser.firebase_id)
            putExtra(EXTRA_FRIEND, cUser)
        }
    }

    private fun clearList() {
        viewModel.currentPage = 1
        (rv_chat_head.adapter as ChatHeadAdapter).clearList()
    }

    companion object {
        const val TAG = "chat"
        fun newInstance(userId: String) = ChatHeadFragment(userId)
    }
}