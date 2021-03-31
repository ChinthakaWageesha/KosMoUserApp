package sl.com.eightdigitz.app.presentation.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.ext.android.get
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.navigation.features.ResultCode
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.extensions.*
import java.util.ArrayList

class Settings : BaseActivity(), View.OnClickListener {

    private var mSharedPreferences : SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        init()
    }

    private fun init() {
        mSharedPreferences = get()
        setData(currentLoggedUser!!)
        setEventTypeSpinner()
        cl_logout.setOnClickListener(this)
    }

    private fun setData(user: DUser){
        tv_settings_user_name.text = user.fullName
        if (!user.profilePicture.isNullOrEmpty()) {
            iv_settings_user_image.loadImage(
                url = user.profilePicture,
                placeholder = sl.com.eightdigitz.presentation.R.drawable.ic_ph_profile_profile_picture
            )
        } else {
            iv_settings_user_image.loadImage(
                url = Constant.KID_IMAGE_URL
            )
        }
    }

    private fun setEventTypeSpinner() {
        val eventTypes: ArrayList<String> = ArrayList()

        eventTypes.add("English")
        eventTypes.add("Sinhala")
        eventTypes.add("Tamil")
        eventTypes.add("Hindi")

        if (sp_language_settings != null) {
            val adapter = ArrayAdapter(
                this,
                sl.com.eightdigitz.presentation.R.layout.item_spinner,
                sl.com.eightdigitz.presentation.R.id.tv_spinner_category_name,
                eventTypes
            )

            sp_language_settings.adapter = adapter

            sp_language_settings.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?, position: Int,
                    id: Long
                ) {
                    eventTypes[position].showToast(this@Settings)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
        }
    }

    private fun logout(){
        showConfirm(
            title = Msg.TITLE_CONFIRMATION,
            message = Msg.LOGOUT,
            negativeText = "No",
            positiveText = "Yes",
            callback = object : Callback {
                override fun onPositiveClicked() {
                    redirectToSignIn()
                }

                override fun onNegativeClicked() {

                }

            }
        )
    }

    private fun redirectToSignIn(){
        mSharedPreferences?.cleaAll()
        setResult(ResultCode.RESULT_NAV_LOGOUT)
            .also { finish() }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cl_logout -> logout()
        }
    }
}