package sl.com.eightdigitz.authentication.presentation.registration

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_post_register.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.presentation.AuthActivity
import sl.com.eightdigitz.client.apiSupports.requests.AddUserPreferenceRequest
import sl.com.eightdigitz.client.apiSupports.models.Preference
import sl.com.eightdigitz.core.base.BaseFragment
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.domain.DUserPreference
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.extensions.*

class PostRegister : BaseFragment(), View.OnClickListener, (Preference, Boolean) -> Unit {

    private val viewModel by viewModel<RegistrationViewModel>()
   // private var selectedPreferenceList: ArrayList<Preference>? = null
    private var preferenceId: String? = null

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
       // selectedPreferenceList = arrayListOf()
        viewModel.getPreferences()
        iv_post_register.loadImage((requireActivity() as AuthActivity).avatarUrl)
        tv_post_register_name.text = (requireActivity() as AuthActivity).fullName

        viewModel.liveDataCategories.observe(this, Observer { observerGetPreferences(it) })
        viewModel.liveDataPreference.observe(this, Observer { observerSetPreferences(it)})
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

    private fun observerSetPreferences(resource: Resource<DUserPreference>){
        resource.let {
            when(it.state){
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    navigateToMain()
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    showConfirm(
                        title = Msg.TITLE_ALERT,
                        message = "Sorry, Error occurred!, but you can still set preferences in your profile ",
                        positiveText = "Navigate",
                        negativeText = "Cancel",
                        callback = object : Callback {
                            override fun onPositiveClicked() {
                                navigateToMain()
                            }

                            override fun onNegativeClicked() {

                            }
                        }
                    )
                }
            }
        }
    }

    private fun setUpPreferenceAdapter(preferenceList: MutableList<DPreference>) {
        rv_preferences.adapter = PreferenceAdapter(preferenceList, this)
        rv_preferences.layoutManager = GridLayoutManager(context!!, 3)
    }

    private fun navigateToMain(){
        (requireActivity() as AuthActivity).authSuccess()
    }

    private fun setPreferences(){
        val request = AddUserPreferenceRequest()
        request.preferenceID = preferenceId
        viewModel.addUserPreferences(request)
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
                navigateToMain()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("MissingPermission")
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_lets_do_it -> {
                if (!preferenceId.isNullOrEmpty()){
                    setPreferences()
                } else {
                    navigateToMain()
                }
            }
        }
    }

    companion object {
        const val TAG = "post_register"

        fun newInstance(): Fragment {
            return PostRegister()
        }
    }

    override fun invoke(preference: Preference, isChecked: Boolean) {
        preferenceId = if (isChecked){
            preference.id
        } else {
            null
        }
        /*if (isChecked) {
            selectedPreferenceList?.add(preference)
        } else {
            selectedPreferenceList?.remove(preference)
        }*/
    }
}