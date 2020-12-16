package sl.com.eightdigitz.chat.presentation.ui.user

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import sl.com.eightdigitz.chat.di.injectChatModule
import sl.com.eightdigitz.chat.domain.model.CUser
import sl.com.eightdigitz.chat.presentation.ui.chat.*
import sl.com.eightdigitz.core.base.BaseFragment
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
import sl.com.eightdigitz.presentation.extensions.setActionBar
import sl.com.eightdigitz.presentation.extensions.showAlert
import sl.com.eightdigitz.presentation.extensions.startActivityForResult
import sl.com.eightdigitz.presentation.utile.PaginationScrollListener
import sl.com.eightdigitz.chat.R
import kotlinx.android.synthetic.main.fragment_chat_head.*
import org.koin.android.ext.android.inject

class UsersFragment(var userId: String): BaseFragment(), UserAdapter.AdapterCallback{

    private lateinit var userAdapter: UserAdapter
    private val userViewModel by inject<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectChatModule()
        (activity as AppCompatActivity).supportActionBar?.setActionBar(
            context!!,
            "Users",
            isHomeUpEnables = false,
            isCenterTitle = true
        )

        userViewModel.liveUserListData.observe(this, Observer {
            updateList(it!!)
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
        userAdapter = UserAdapter(mutableListOf())
        userAdapter.callback = this

        rv_chat_head.apply {
            adapter = userAdapter

            addOnScrollListener(object :
                PaginationScrollListener(rv_chat_head.layoutManager as LinearLayoutManager) {

                override fun loadMoreItems() {
                    userViewModel.currentPage++
                    userViewModel.getUserList()
                }

                override fun isLastPage(): Boolean = userViewModel.isLastPage()

                override fun isLoading(): Boolean = userViewModel.isLoading
            })
        }

        showProgress()
        userViewModel.getUserList()

        et_search.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(search: CharSequence?, p1: Int, p2: Int, p3: Int) {
                userAdapter.getFilter().filter(search?.trim())
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    override fun onViewCreated() {
    }

    override fun onClickChatHead(cUser: CUser) {
        startActivityForResult<ChatActivity>(REQ_CODE_CHAT) {
            putExtra(EXTRA_USER_ID, userId)
            putExtra(EXTRA_FRIEND_ID, cUser.firebase_id)
            putExtra(EXTRA_FRIEND, cUser)
        }
    }

    private fun updateList(resource: Resource<MutableList<CUser>>) {
        resource.let {
            when(it.state){
                ResourceState.LOADING ->{
                    showProgress()
                }
                ResourceState.SUCCESS ->{
                    it.data.let {userList ->
                        hideProgress()
                        userAdapter.addFriendList(userList!!)
                    }
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    showAlert(it.message.toString())

                }
            }
        }

    }

    companion object {
        const val TAG = "User List"
        fun newInstance(userId: String) = UsersFragment(userId)
    }
}