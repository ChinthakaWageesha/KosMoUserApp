package sl.com.eightdigitz.app.presentation.preferences

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_preference_edit.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.client.apiSupports.requests.AddUserPreferenceRequest
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
import sl.com.eightdigitz.presentation.extensions.*

class PreferenceEdit : BaseActivity(), View.OnClickListener {

    private val vmPreference by viewModel<PreferencesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference_edit)
        setToolbar()
        init()
    }

    private fun setToolbar() {
        supportActionBar?.setActionBar(
            context = this,
            title = "",
            isHomeUpEnables = true
        )
    }

    private fun init() {
        setData()
        vmPreference.getPreferences()
        vmPreference.liveDataPreferences.observe(this, Observer { observerGetPreferences(it) })
        vmPreference.liveDataSetPreferences.observe(this, Observer { observerSetPreferences(it) })
        btn_update_preferences.setOnClickListener(this)
    }

    private fun setData() {
        tv_set_preferences_user_name.text = currentLoggedUser?.fullName
        if (!currentLoggedUser?.profilePicture.isNullOrEmpty()) {
            iv_set_preference_user_image.loadImage(
                url = currentLoggedUser?.profilePicture,
                placeholder = sl.com.eightdigitz.presentation.R.drawable.ic_ph_profile_profile_picture
            )
        } else {
            iv_set_preference_user_image.loadImage(
                url = Constant.KID_IMAGE_URL
            )
        }
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

    private fun observerSetPreferences(resource: Resource<DUser>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    "Preferences updated successfully".showToast(this)
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.error.toString().showToast(this)
                }
            }
        }
    }

    private fun setUpPreferenceAdapter(preferenceList: MutableList<DPreference>) {
        rv_set_preferences.adapter = PreferencesAdapter(
            preferenceList,
            currentLoggedUser?.preferences?.toMutableList()
        )
        rv_set_preferences.layoutManager = GridLayoutManager(this, 3)
    }

    override fun onResume() {
        setBackground(sl.com.eightdigitz.presentation.R.drawable.bg_get_otp)
        super.onResume()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_update_preferences -> {

                if (!(rv_set_preferences.adapter as PreferencesAdapter).getSelectedIds()
                        .isNullOrEmpty()
                ) {
                    val selectedIds =
                        (rv_set_preferences.adapter as PreferencesAdapter).getSelectedIds()

                    val request = AddUserPreferenceRequest()
                    request.preferenceIDs = selectedIds.toTypedArray()
                    vmPreference.setPreferences(request)
                }
            }
        }
    }
}