package sl.com.eightdigitz.app.presentation.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.github.dhaval2404.imagepicker.ImagePicker
import com.github.dhaval2404.imagepicker.constant.ImageProvider
import com.hbb20.CountryCodePicker
import kotlinx.android.synthetic.main.activity_edit_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.client.apiSupports.requests.UpdateUserRequest
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.country_picker.presentation.country_picker.CountryPickerBuilder
import sl.com.eightdigitz.country_picker.presentation.models.PCountry
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.cropper.CropImageActivity
import sl.com.eightdigitz.presentation.extensions.*
import sl.com.eightdigitz.presentation.utile.AppDialog
import java.io.File

class EditProfile : BaseActivity(), View.OnClickListener {

    private val vmProfile by viewModel<ProfileViewModel>()
    private val arrayOptions: Array<CharSequence> = arrayOf("Take photo", "Choose from gallery")
    private lateinit var ccpPicker: CountryCodePicker
    private val AVATAR_IMAGE_REQ_CODE = 1100
    private var profileImageUrl: String? = null
    private var file: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        init()
        setData(currentLoggedUser)
    }

    private fun init() {
        ccpPicker = CountryCodePicker(this)
        ccpPicker.setAutoDetectedCountry(true)
        ccpPicker.setDefaultCountryUsingNameCode("lk")
        ccpPicker.registerCarrierNumberEditText(et_edit_profile_country)
        vmProfile.liveDataUploadAvatar.observe(this, Observer { observerUploadAvatar(it) })
        vmProfile.liveDataUpdateUser.observe(this, Observer { observerUpdateUser(it) })
        et_edit_profile_country.setText("Sri Lanka")
        et_edit_profile_country.setOnClickListener(this)
        et_edit_profile_mobile.setOnClickListener(this)
        iv_edit_pro_pic.setOnClickListener(this)
        btn_edit_profile.setOnClickListener(this)
        iv_edit_profile_back.setOnClickListener(this)
    }

    private fun setData(user: DUser?) {
        tv_edit_user_name.text = user?.fullName
        if (!user?.profilePicture.isNullOrEmpty()) {
            profileImageUrl = user?.profilePicture
            iv_edit_profile_user_image.loadImage(
                url = profileImageUrl,
                placeholder = sl.com.eightdigitz.presentation.R.drawable.ic_ph_profile_profile_picture
            )
        } else {
            iv_edit_profile_user_image.loadImage(
                url = Constant.KID_IMAGE_URL
            )
        }

        et_edit_profile_name.setText(user?.fullName)
        et_edit_profile_email.setText(user?.email)
        et_edit_profile_mobile.setText(user?.mobileNo)

        if (!user?.profileBiography.isNullOrEmpty()) {
            et_edit_profile_bio.setText(user?.profileBiography)
        }
    }

    private fun startCountryPicker() {
        CountryPickerBuilder.instance
            .setTitleTextColor(sl.com.eightdigitz.presentation.R.color.colorBlack)
            .setActivityResultKey(IntentParsableConstants.COUNTRY_SELECTION)
            .setActivityTitle(sl.com.eightdigitz.presentation.R.string.title_country_picker)
            .start(this, RequestCodes.COUNTRY_CODE_REQUEST)
    }

    private fun cameraOptionsDialog(
        arrayOption: Array<CharSequence>,
        requestCode: Int
    ) {
        AppDialog.showChooiceDialog(this, object : AppDialog.DialogEvent {
            override fun onItemClick(which: Int) {
                when (which) {
                    0 -> {
                        ImagePicker.with(this@EditProfile)
                            .provider(ImageProvider.CAMERA)
                            .compress(100)
                            .start(requestCode)
                    }
                    1 -> {
                        ImagePicker.with(this@EditProfile)
                            .galleryOnly()
                            .compress(100)
                            .start(requestCode)
                    }
                }
            }
        }, "", arrayOption)
    }

    private fun startUpdateMobile() {
        val intent = Intent(this, UpdateMobile::class.java)
        startActivityForResult(intent, RequestCodes.MOBILE_REQUEST_CODE)
    }

    private fun updateUser() {
        val request = UpdateUserRequest()
        request.id = currentLoggedUser?.id
        request.auth0Ref = currentLoggedUser?.authReference
        request.fullName = et_edit_profile_name.getStringTrim()
        request.email = et_edit_profile_email.getStringTrim()
        request.mobileNo = et_edit_profile_mobile.getStringTrim()
        request.profileBiography = et_edit_profile_bio.getStringTrim()
        request.profilePicture = profileImageUrl

        withNetwork({
            vmProfile.updateUser(request)
        }, {
            showAlert(message = Msg.INTERNET_ISSUE)
        })
    }

    private fun observerUploadAvatar(resource: Resource<String>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> {
                    showProgress()
                }
                ResourceState.SUCCESS -> {
                    hideProgress()
                    profileImageUrl = it.data.toString()
                    iv_edit_profile_user_image.loadImageRound(file?.absolutePath!!)
                    updateUser()
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.error?.showToast(this)
                }
            }
        }
    }

    private fun observerUpdateUser(resource: Resource<DUser>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    tv_edit_user_name.text = it.data?.fullName
                    val intent = intent
                    intent.putExtra(IntentParsableConstants.EXTRA_USER, it.data)
                    setResult(ResultCodes.UPDATE_USER_RESULT_CODE, intent)
                    "Profile Data saved Successfully".showToast(this)
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.error?.showToast(this)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AVATAR_IMAGE_REQ_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                file = ImagePicker.getFile(data)
                file?.absolutePath?.let { CropImageActivity.startCropActivity(this, it) }
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                ImagePicker.getError(data).showToast(this)
            }
        } else if (requestCode == CropImageActivity.REQUEST_CROP_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                val result = data?.data
                file = File(result?.path!!)
                vmProfile.uploadAvatar(file!!)
            }
        }

        if (requestCode == RequestCodes.COUNTRY_CODE_REQUEST && resultCode == Activity.RESULT_OK) {
            val mCountry =
                data?.extras?.getParcelable<PCountry>(IntentParsableConstants.COUNTRY_SELECTION)
            et_edit_profile_country.setText(mCountry?.name)
        }

        if (requestCode == RequestCodes.MOBILE_REQUEST_CODE && resultCode == ResultCodes.MOBILE_RESULT_CODE) {
            et_edit_profile_mobile.setText(
                data?.extras?.getString(IntentParsableConstants.EXTRA_MOBILE_NUMBER)
                    ?.replace("+94", "0")
            )
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.et_edit_profile_country -> startCountryPicker()
            R.id.et_edit_profile_mobile -> startUpdateMobile()
            R.id.iv_edit_pro_pic -> cameraOptionsDialog(arrayOptions, AVATAR_IMAGE_REQ_CODE)
            R.id.btn_edit_profile -> updateUser()
            R.id.iv_edit_profile_back -> onBackPressed()
        }
    }
}
