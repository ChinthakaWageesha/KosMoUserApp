package sl.com.eightdigitz.authentication.presentation.registration

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_post_register.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.client.apiSupports.requests.AddUserPreferenceRequest
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.domain.DUserRegister
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.extensions.*

class PostRegister : BaseActivity(), View.OnClickListener, (DPreference, Boolean) -> Unit {

    private val viewModel by viewModel<RegistrationViewModel>()
    private var userRequest: DUserRegister? = null
    private var selectedPreferenceIds: ArrayList<String>? = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_register)
        setToolbar()
        getData()
        init()
    }

    private fun setToolbar(){
        supportActionBar?.setActionBar(
            this,
            "",
            isHomeUpEnables = false
        )
    }

    private fun getData(){
        if (intent.hasExtra(IntentParsableConstants.EXTRA_REGISTER_USER)) {
            userRequest = intent.getParcelableExtra(IntentParsableConstants.EXTRA_REGISTER_USER)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        viewModel.getPreferences()
        iv_post_register.loadImage(userRequest?.profilePicture)
        tv_post_register_name.text = userRequest?.fullName

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
                    it.message?.error.toString().showToast(this)
                }
            }
        }
    }

    private fun observerSetPreferences(resource: Resource<DUser>){
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
        rv_preferences.layoutManager = GridLayoutManager(this, 3)
    }

    private fun navigateToMain(){
        setResult(ResultCodes.CREATE_USER_RESULT_CODE).also {
            finish()
        }
    }

    private fun setPreferences(){
        val request = AddUserPreferenceRequest()
        request.preferenceIDs = selectedPreferenceIds?.toTypedArray()
        viewModel.addUserPreferences(request)
    }


    override fun onResume() {
        setBackground(sl.com.eightdigitz.presentation.R.drawable.bg_get_otp)
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(sl.com.eightdigitz.presentation.R.menu.menu_skip, menu)
        return true
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
                if (!selectedPreferenceIds.isNullOrEmpty()){
                    setPreferences()
                } else {
                    navigateToMain()
                }
            }
        }
    }

    override fun invoke(preference: DPreference, isChecked: Boolean) {
        /*preferenceId = if (isChecked){
            preference.id
        } else {
            null
        }*/
        if (isChecked) {
            selectedPreferenceIds?.add(preference.id!!)
        } else {
            selectedPreferenceIds?.remove(preference.id)
        }
    }
}