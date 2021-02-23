package sl.com.eightdigitz.authentication.presentation.registration

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_post_register.*
import kotlinx.android.synthetic.main.fragment_register_user.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.presentation.AuthActivity
import sl.com.eightdigitz.client.models.Preference
import sl.com.eightdigitz.client.models.RegisterRequest
import sl.com.eightdigitz.core.base.BaseFragment
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.mapToApiModel
import sl.com.eightdigitz.core.model.presentation.PUser
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.extensions.*

class PostRegister : BaseFragment(), View.OnClickListener, (Preference, Boolean) -> Unit {

    private val viewModel by viewModel<RegistrationViewModel>()
    private var selectedPreferenceList: ArrayList<Preference>? = null
    private var dUser: DUser? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_register, container, false)
    }

    override fun onViewCreated() {
        init()
        setToolbar()
    }

    private fun setToolbar() {
        (requireActivity() as AuthActivity).supportActionBar?.setActionBar(
            context!!,
            "",
            isHomeUpEnables = false
        )
        setHasOptionsMenu(true)
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        selectedPreferenceList = arrayListOf()
        dUser = arguments?.getParcelable(IntentParsableConstants.EXTRA_USER)
        iv_post_register.loadImage((requireActivity() as AuthActivity).avatarUrl)
        tv_post_register_name.text = dUser?.fullName
        viewModel.getPreferences()
        viewModel.liveDataSaveUser.observe(this, Observer { observerSaveUser(it) })
        viewModel.liveDataCategories.observe(this, Observer { observerGetPreferences(it) })
        btn_lets_do_it.setOnClickListener(this)
    }

    private fun observerGetPreferences(resource: Resource<List<DPreference>>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    setUpPreferenceAdapter(it.data!!.toMutableList())
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    showAlert(Msg.TITLE_ERROR, it.message.toString())
                }
            }
        }
    }

    private fun setUpPreferenceAdapter(preferenceList: MutableList<DPreference>) {
        rv_preferences.adapter = PreferenceAdapter(preferenceList, this)
        rv_preferences.layoutManager = GridLayoutManager(context!!, 3)
    }

    @SuppressLint("MissingPermission")
    private fun createAccount() {

        val registerRequest = RegisterRequest()
        registerRequest.mobileNo = dUser?.mobileNo
        registerRequest.fullName = dUser?.fullName
        registerRequest.defaultLanguage = dUser?.defaultLanguage
        registerRequest.email = dUser?.email
        registerRequest.role = "user"
        registerRequest.profilePicture = dUser?.profilePicture
        registerRequest.profileDescription = dUser?.profileDescription

        if (!selectedPreferenceList.isNullOrEmpty()) {
            registerRequest.preferences = selectedPreferenceList
        }

        activity?.withNetwork({
            viewModel.createAccount(registerRequest)
        }, {
            Msg.INTERNET_ISSUE.showToast(context!!)
        })
    }

    private fun observerSaveUser(resource: Resource<PUser>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> {
                    showProgress()
                }
                ResourceState.SUCCESS -> {
                    hideProgress()
                    if (!it.data?.authReference.isNullOrEmpty()) {
                        (requireActivity() as AuthActivity).authSuccess()
                    } else {
                        Msg.ERROR_COMMON.showToast(context!!)
                    }
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    showAlert(Msg.TITLE_ERROR, it.message.toString())
                }
            }
        }
    }

    override fun onResume() {
        setBackground(sl.com.eightdigitz.presentation.R.drawable.bg_get_otp)
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(sl.com.eightdigitz.presentation.R.menu.menu_skip, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            sl.com.eightdigitz.presentation.R.id.skip -> {
                createAccount()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("MissingPermission")
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_lets_do_it -> {
                createAccount()
            }
        }
    }

    companion object {
        const val TAG = "post_register"

        fun newInstance(user: DUser): Fragment {
            val bundle = Bundle()
            val fragment = PostRegister()
            bundle.putParcelable(IntentParsableConstants.EXTRA_USER, user)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun invoke(preference: Preference, isChecked: Boolean) {
        if (isChecked) {
            selectedPreferenceList?.add(preference)
        } else {
            selectedPreferenceList?.remove(preference)
        }
    }
}