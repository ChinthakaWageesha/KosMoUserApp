package sl.com.eightdigitz.app.presentation.search.viewProfile

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_view_profile.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.order.OrderDetails
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.IntentParsableConstants
import sl.com.eightdigitz.presentation.extensions.setRoundedImage
import sl.com.eightdigitz.presentation.extensions.startActivity

class ViewProfile : BaseActivity(), View.OnClickListener {

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
            val talent = intent.getParcelableExtra<DUser>(IntentParsableConstants.EXTRA_USER)
            tv_profile_name.text = talent?.fullName
            tv_profile_field.text = talent?.role

            if (!talent?.profilePicture.isNullOrEmpty()) {
                iv_profile_image.setRoundedImage(
                    url = talent?.profilePicture!!,
                    radius = 10
                )
            } else {
                iv_profile_image.setRoundedImage(
                    url = Constant.USER_IMAGE_PAUL_WALKER,
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

        iv_close_profile_view.setOnClickListener(this)
        btn_request_now.setOnClickListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        goBack()
        return super.onSupportNavigateUp()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_close_profile_view -> goBack()
            R.id.btn_request_now -> startActivity<OrderDetails>()
        }
    }
}