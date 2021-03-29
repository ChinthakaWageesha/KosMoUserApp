package sl.com.eightdigitz.app.presentation.search.viewProfile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_view_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.order.addNewOrder.OrderDetails
import sl.com.eightdigitz.app.presentation.search.LogSearchViewModel
import sl.com.eightdigitz.client.apiSupports.models.User
import sl.com.eightdigitz.client.apiSupports.requests.LogSearchRequest
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.domain.DUserSearch
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.extensions.hideKeyboard
import sl.com.eightdigitz.presentation.extensions.setRoundedImage
import sl.com.eightdigitz.presentation.extensions.showAlert
import sl.com.eightdigitz.presentation.extensions.startActivity

class ViewProfile : BaseActivity(), View.OnClickListener {

    private val vmLogSearch by viewModel<LogSearchViewModel>()
    private var talent: DUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_profile)
        init()
    }

    private fun init() {
        setBackground(sl.com.eightdigitz.presentation.R.drawable.ic_sample)
        if (intent.hasExtra(IntentParsableConstants.EXTRA_USER) &&
            intent.getParcelableExtra<DUser>(IntentParsableConstants.EXTRA_USER) != null
        ) {
            talent = intent.getParcelableExtra<DUser>(IntentParsableConstants.EXTRA_USER)
            logProfileSearch(talent?.id!!)
            tv_profile_name.text = talent?.fullName
            tv_profile_field.text = talent?.role

            if (!talent?.profilePicture.isNullOrEmpty()) {
                iv_profile_image.setRoundedImage(
                    url = talent?.profilePicture!!,
                    radius = 10
                )
            } else {
                iv_profile_image.setRoundedImage(
                    url = Constant.KID_IMAGE_URL,
                    radius = 10
                )
            }

        } else {
            tv_profile_name.text = "Norman Munoz"
            tv_profile_field.text = "Athlete"

            iv_profile_image.setRoundedImage(
                url = Constant.USER_IMAGE_PAUL_WALKER,
                radius = 10
            )
        }
        vmLogSearch.liveDataPreferenceSearch.observe(this, Observer { observerLogProfileSearch(it) })
        iv_close_profile_view.setOnClickListener(this)
        btn_request_now.setOnClickListener(this)
    }

    private fun logProfileSearch(profileOwnerId: String){
        val logSearch = LogSearchRequest()
        logSearch.userID = currentLoggedUser?.id
        logSearch.searchType = SearchType.PROFILE_SEARCH
        logSearch.profileOwnerID = profileOwnerId
        vmLogSearch.logSearch(logSearch)
    }

    private fun observerLogProfileSearch(resource: Resource<DUserSearch>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> ""
                ResourceState.SUCCESS -> {
                    Log.d("PROFILE_SEARCH_LOG", it.data?.id!!)
                }
                ResourceState.ERROR -> {
                    showAlert(title = Msg.TITLE_ALERT, message = it.message?.error!!)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_close_profile_view -> {
                hideKeyboard()
                onBackPressed()
            }
            R.id.btn_request_now -> {
                if (talent != null){
                    val intent = Intent(this, OrderDetails::class.java)
                    intent.putExtra(IntentParsableConstants.EXTRA_USER, talent)
                    startActivity(intent)
                }
            }
        }
    }
}