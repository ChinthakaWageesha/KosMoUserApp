package sl.com.eightdigitz.app.presentation.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import androidx.lifecycle.Observer
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util.getUserAgent
import kotlinx.android.synthetic.main.activity_view_profile.*
import okhttp3.internal.Util
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.order.addNewOrder.OrderDetails
import sl.com.eightdigitz.app.presentation.search.LogSearchViewModel
import sl.com.eightdigitz.client.apiSupports.requests.LogSearchRequest
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.domain.DUserSearch
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.extensions.getAppFilePath
import sl.com.eightdigitz.presentation.extensions.hideKeyboard
import sl.com.eightdigitz.presentation.extensions.setRoundedImage
import sl.com.eightdigitz.presentation.extensions.showToast

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
            talent = intent.getParcelableExtra(IntentParsableConstants.EXTRA_USER)
            logProfileSearch(talent?.id!!)
            tv_profile_name.text = talent?.fullName
            tv_profile_field.text = talent?.role

            if (!talent?.profilePicture.isNullOrEmpty()) {
                iv_profile_image.setRoundedImage(
                    url = talent?.profilePicture!!,
                    placeHolder = sl.com.eightdigitz.presentation.R.drawable.ic_ph_profile_profile_picture,
                    radius = 10
                )
            }
        }

        if (!talent?.profileVideo.isNullOrEmpty()) {
            talent_video.setSource(talent?.profileVideo)
        }

        vmLogSearch.liveDataPreferenceSearch.observe(
            this,
            Observer { observerLogProfileSearch(it) })

        chk_volume.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                talent_video.player.volume = 0f
            } else {
                talent_video.player.volume = 0.8f
            }
        }

        iv_close_profile_view.setOnClickListener(this)
        btn_request_now.setOnClickListener(this)
    }

    private fun logProfileSearch(profileOwnerId: String) {
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
                    it.message?.error!!.showToast(this)
                }
            }
        }
    }

    override fun onResume() {
        talent_video.setPlayWhenReady(true)
        super.onResume()
    }

    override fun onPause() {
        talent_video.pausePlayer()
        super.onPause()
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
                if (talent != null) {
                    val intent = Intent(this, OrderDetails::class.java)
                    intent.putExtra(IntentParsableConstants.EXTRA_USER, talent)
                    startActivity(intent)
                }
            }
        }
    }
}