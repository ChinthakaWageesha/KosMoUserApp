package sl.com.eightdigitz.authentication.presentation.contact

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.github.dhaval2404.imagepicker.ImagePicker
import com.github.dhaval2404.imagepicker.constant.ImageProvider
import kotlinx.android.synthetic.main.activity_contact_support.*
import kotlinx.android.synthetic.main.activity_send_request.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.client.apiSupports.requests.JoinUsRequest
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DJoinUs
import sl.com.eightdigitz.core.ui.HelpCenter
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
import sl.com.eightdigitz.presentation.cropper.CropImageActivity
import sl.com.eightdigitz.presentation.extensions.*
import sl.com.eightdigitz.presentation.utile.AppDialog
import java.io.File

class SendRequest : BaseActivity(), View.OnClickListener {

    private val vm by viewModel<JoinContactViewModel>()
    private val AVATAR_IMAGE_REQ_CODE = 1124
    private val arrayOptions: Array<CharSequence> = arrayOf("Take photo", "Choose from gallery")
    private var file: File? = null
    private var uploadedImageURL: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_request)
        setToolbar()
        init()
    }

    private fun setToolbar() {
        supportActionBar?.setActionBar(
            this,
            "",
            isHomeUpEnables = true
        )
    }

    private fun init() {
        vm.liveDataJoinUs.observe(this, Observer { observerJoinUs(it) })
        vm.liveDataContactImage.observe(this, Observer { observerUploadJoinUsImage(it)})
        showKeyboard(et_name_send_request)
        iv_talent_pro_pic.setOnClickListener(this)
        tv_desc_send_request_three.setOnClickListener(this)
        btn_submit_send_request.setOnClickListener(this)
    }

    private fun cameraOptionsDialog(
        arrayOption: Array<CharSequence>,
        requestCode: Int
    ) {
        AppDialog.showChooiceDialog(this, object : AppDialog.DialogEvent {
            override fun onItemClick(which: Int) {
                when (which) {
                    0 -> {
                        ImagePicker.with(this@SendRequest)
                            .provider(ImageProvider.CAMERA)
                            //.compress(1024)
                            .compress(100)
                            .start(requestCode)
                    }
                    1 -> {
                        ImagePicker.with(this@SendRequest)
                            .galleryOnly()
                            .compress(100)
                            .start(requestCode)
                    }
                }
            }
        }, "", arrayOption)
    }

    private fun validateForm(): Boolean {
        val isName =
            et_name_send_request.validateOnTextChange(isCheckValidateIcon = true) { s -> s.length > 3 }

        val isEmail =
            et_email_send_request.validateOnTextChange(isCheckValidateIcon = true) { s -> s.isValidEmail() }

        val isPhone =
            et_phone_number_send_request.validateOnTextChange(isCheckValidateIcon = true) { s -> s.isValidMobile() }

        val isSocialMediaPlatform =
            et_social_media_platform.validateOnTextChange(isCheckValidateIcon = true) { s -> s.length > 2 }

        val isSocialMediaLink =
            et_social_media_link.validateOnTextChange(isCheckValidateIcon = true) { s -> s.length > 4 }

        val isDescription =
            et_talent_description.validateOnTextChange(isCheckValidateIcon = true) { s -> s.length > 4 }

        if (!isName || !isEmail || !isPhone || !isSocialMediaPlatform || !isSocialMediaLink || !isDescription) {
            showAlert(Msg.TITLE_REQUIRED, "Please fill out the required fields.")
            return false
        }

        return true
    }

    private fun validateProPic(): Boolean {
        val isProfilePic = uploadedImageURL

        if (isProfilePic.isNullOrEmpty()) {
            showAlert(Msg.TITLE_REQUIRED, "Please upload profile picture")
            return false
        }

        return true
    }

    @SuppressLint("MissingPermission")
    private fun sendRequest() {
        if (validateForm() && validateProPic()) {
            val joinTalentRequest = JoinUsRequest(
                name = et_name_send_request.getStringTrim(),
                email = et_email_send_request.getStringTrim(),
                phoneNo = et_phone_number_send_request.getStringTrim(),
                topSocialMediaPlatform = et_social_media_platform.getStringTrim(),
                socialMediaProfileLink = et_social_media_link.getStringTrim(),
                selfDescription = et_talent_description.getStringTrim(),
                profilePicURL = uploadedImageURL
            )
            withNetwork({
                vm.joinUs(joinTalentRequest)
            }, {
                showAlert(Msg.TITLE_ERROR, Msg.INTERNET_ISSUE)
            })
        }
    }

    private fun observerUploadJoinUsImage(resource: Resource<String>){
        resource.let {
            when(it.state){
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    uploadedImageURL = it.data.toString()
                    iv_talent_pro_pic.loadImageRound(file?.absolutePath!!)
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.error.toString().showToast(this)
                }
            }
        }
    }

    private fun observerJoinUs(resource: Resource<DJoinUs>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    showAlert(
                        "Thank You!",
                        "Your request for the registration has been received. Stay tuned.",
                        callback = object : Callback {
                            override fun onPositiveClicked() {
                                onBackPressed()
                            }

                            override fun onNegativeClicked() {

                            }

                        })
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.error.toString().showToast(this)
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
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
                vm.uploadAvatar(file!!)
            }
        }
    }

    override fun onResume() {
        setBackground(sl.com.eightdigitz.presentation.R.drawable.bg_get_otp)
        super.onResume()
    }

    override fun onSupportNavigateUp(): Boolean {
        hideKeyboard()
        return super.onSupportNavigateUp()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_talent_pro_pic -> cameraOptionsDialog(arrayOptions, AVATAR_IMAGE_REQ_CODE)
            R.id.btn_submit_send_request -> sendRequest()
            R.id.tv_desc_send_request_three -> startActivity<HelpCenter>()
        }
    }
}