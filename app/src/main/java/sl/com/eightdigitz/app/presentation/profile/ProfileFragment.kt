package sl.com.eightdigitz.app.presentation.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_profile.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.favourite.Favourites
import sl.com.eightdigitz.app.presentation.order.userOrders.UserOrders
import sl.com.eightdigitz.app.presentation.preferences.PreferenceEdit
import sl.com.eightdigitz.app.presentation.reminder.Reminders
import sl.com.eightdigitz.app.presentation.settings.Settings
import sl.com.eightdigitz.core.base.BaseFragment
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.ui.HelpCenter
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.IntentParsableConstants
import sl.com.eightdigitz.presentation.RequestCodes
import sl.com.eightdigitz.presentation.ResultCodes
import sl.com.eightdigitz.presentation.extensions.loadImage
import sl.com.eightdigitz.presentation.extensions.showToast
import sl.com.eightdigitz.presentation.extensions.startActivity

class ProfileFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated() {
        init()
        setData(currentLoggedUser)
    }

    private fun init() {
        tv_goto_edit_profile.setOnClickListener(this)
        cl_your_favorites.setOnClickListener(this)
        cl_orders.setOnClickListener(this)
        cl_reminders.setOnClickListener(this)
        cl_promotions.setOnClickListener(this)
        cl_settings.setOnClickListener(this)
        cl_preferences.setOnClickListener(this)
        cl_help_centre.setOnClickListener(this)
    }

    private fun setData(user: DUser?) {
        tv_profile_user_name.text = user?.fullName
        if (!user?.profilePicture.isNullOrEmpty()) {
            iv_profile_user_image.loadImage(
                url = user?.profilePicture,
                placeholder = sl.com.eightdigitz.presentation.R.drawable.ic_ph_profile_profile_picture
            )
        } else {
            iv_profile_user_image.loadImage(
                url = Constant.KID_IMAGE_URL
            )
        }
    }

    companion object {
        const val TAG = "ProfileFragment"
        fun newInstance() = ProfileFragment()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RequestCodes.UPDATE_USER_REQUEST_CODE && resultCode == ResultCodes.UPDATE_USER_RESULT_CODE){
            setData(data?.getParcelableExtra(IntentParsableConstants.EXTRA_USER))
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_goto_edit_profile -> {
                val intent = Intent(context!!, EditProfile::class.java)
                startActivityForResult(intent, RequestCodes.UPDATE_USER_REQUEST_CODE)
            }
            R.id.cl_your_favorites -> context?.startActivity<Favourites>()
            R.id.cl_orders -> context?.startActivity<UserOrders>()
            R.id.cl_reminders -> context?.startActivity<Reminders>()
            R.id.cl_promotions -> "promotions".showToast(context!!)
            R.id.cl_settings -> context?.startActivity<Settings>()
            R.id.cl_preferences -> context?.startActivity<PreferenceEdit>()
            R.id.cl_help_centre -> context?.startActivity<HelpCenter>()
        }
    }
}
