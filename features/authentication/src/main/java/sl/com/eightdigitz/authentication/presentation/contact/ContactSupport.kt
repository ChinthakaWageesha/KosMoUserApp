package sl.com.eightdigitz.authentication.presentation.contact

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.lifecycle.Observer
import com.github.dhaval2404.imagepicker.ImagePicker
import com.github.dhaval2404.imagepicker.constant.ImageProvider
import kotlinx.android.synthetic.main.activity_contact_support.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.client.apiSupports.requests.ContactUsRequest
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DContactUs
import sl.com.eightdigitz.core.ui.HelpCenter
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
import sl.com.eightdigitz.presentation.cropper.CropImageActivity
import sl.com.eightdigitz.presentation.extensions.*
import sl.com.eightdigitz.presentation.utile.AppDialog
import java.io.File


class ContactSupport : BaseActivity(), View.OnClickListener {

    private val vm by viewModel<JoinContactViewModel>()
    private val AVATAR_IMAGE_REQ_CODE = 1124
    private val arrayOptions: Array<CharSequence> = arrayOf("Take photo", "Choose from gallery")
    private var file: File? = null
    private var uploadedImageURL: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_support)
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

    private fun init(){
        showKeyboard(et_name)
        setSpannableContent()
        vm.liveDataContact.observe(this, Observer { observerContactSupport(it) })
        vm.liveDataContactImage.observe(this, Observer { observerUploadContactImage(it) })
        iv_upload_contact_us_photo.setOnClickListener(this)
        btn_submit_contact_us.setOnClickListener(this)
    }

    private fun setSpannableContent(){
        val spannable = SpannableString("Please note that we value and respect your privacy and will only use your data for " +
                "services purposes and will not share it with third parties. Please read the our Privacy Statement for more information.")
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                startActivity<HelpCenter>()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.WHITE
                ds.isUnderlineText = true
            }
        }

        spannable.setSpan(
            clickableSpan,
            163,
            180,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tv_desc_contact_us.text = spannable
        tv_desc_contact_us.movementMethod = LinkMovementMethod.getInstance()
        tv_desc_contact_us.highlightColor = Color.TRANSPARENT
    }

    private fun cameraOptionsDialog(
        arrayOption: Array<CharSequence>,
        requestCode: Int
    ) {
        AppDialog.showChooiceDialog(this, object : AppDialog.DialogEvent {
            override fun onItemClick(which: Int) {
                when (which) {
                    0 -> {
                        ImagePicker.with(this@ContactSupport)
                            .provider(ImageProvider.CAMERA)
                            //.compress(1024)
                            .compress(100)
                            .start(requestCode)
                    }
                    1 -> {
                        ImagePicker.with(this@ContactSupport)
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
            et_name.validateOnTextChange(isCheckValidateIcon = true) { s -> s.length > 3 }

        val isEmail =
            et_email.validateOnTextChange(isCheckValidateIcon = true) { s -> s.isValidEmail() }

        val isPhone =
            et_phone_number.validateOnTextChange(isCheckValidateIcon = true) { s -> s.isValidPhone() }

        val isSubject =
            et_subject.validateOnTextChange(isCheckValidateIcon = true) { s -> s.length > 2 }

        if (!isName || !isEmail || !isPhone || !isSubject) {
            showAlert(Msg.TITLE_REQUIRED, "Please fill out the required fields.")
            return false
        }

        return true
    }

    @SuppressLint("MissingPermission")
    private fun contactRequest(){
        if (validateForm()){
            val contactTalentRequest = ContactUsRequest(
                name = et_name.getStringTrim(),
                email = et_email.getStringTrim(),
                phoneNo = et_phone_number.getStringTrim(),
                subject = et_subject.getStringTrim(),
                requestSource = "talent"
            )

            if (et_feedback.getStringTrim().isNotEmpty()){
                contactTalentRequest.description = et_feedback.getStringTrim()
            }

            if (!uploadedImageURL.isNullOrEmpty()){
                contactTalentRequest.screenshotURL = file?.absolutePath
            }

            withNetwork({
                vm.contactSupport(contactTalentRequest)
            }, {
                showAlert(Msg.TITLE_ERROR, Msg.INTERNET_ISSUE)
            })
        }
    }

    private fun observerUploadContactImage(resource: Resource<String>){
        resource.let {
            when(it.state){
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    uploadedImageURL = it.data.toString()
                    et_upload_image.setText(uploadedImageURL)
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.error.toString().showToast(this)
                }
            }
        }
    }

    private fun observerContactSupport(resource: Resource<DContactUs>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    showAlert(
                        "Thank You!",
                        "Your inquiry has been sent. We will contact you soon.",
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
        when(v?.id){
            R.id.iv_upload_contact_us_photo -> cameraOptionsDialog(
                arrayOptions,
                AVATAR_IMAGE_REQ_CODE
            )
            R.id.btn_submit_contact_us -> contactRequest()
        }
    }
}