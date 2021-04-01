package sl.com.eightdigitz.authentication.presentation.registration

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.lifecycle.Observer
import com.github.dhaval2404.imagepicker.ImagePicker
import com.github.dhaval2404.imagepicker.constant.ImageProvider
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.hbb20.CountryCodePicker
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.presentation.contact.JoinContactViewModel
import sl.com.eightdigitz.client.apiSupports.requests.RegisterRequest
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DUserRegister
import sl.com.eightdigitz.core.model.presentation.PUser
import sl.com.eightdigitz.core.ui.HelpCenter
import sl.com.eightdigitz.country_picker.presentation.country_picker.CountryPickerBuilder
import sl.com.eightdigitz.country_picker.presentation.models.PCountry
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.cropper.CropImageActivity
import sl.com.eightdigitz.presentation.extensions.*
import sl.com.eightdigitz.presentation.utile.AppDialog
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class RegisterUser : BaseActivity(), View.OnClickListener {

    private val AVATAR_IMAGE_REQ_CODE = 1124
    private val vmAvatar by viewModel<JoinContactViewModel>()
    private val vmRegister by viewModel<RegistrationViewModel>()
    private var calender = Calendar.getInstance()
    private var userRequest: DUserRegister? = null
    private lateinit var ccpPicker: CountryCodePicker
    private val arrayOptions: Array<CharSequence> = arrayOf("Take photo", "Choose from gallery")
    private var file: File? = null
    private var dobFormat: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setToolbar()
        getData()
        init()
    }

    private fun setToolbar() {
        supportActionBar?.setActionBar(
            this,
            "",
            isHomeUpEnables = true
        )
    }

    private fun getData(){
        if (intent.hasExtra(IntentParsableConstants.EXTRA_REGISTER_USER)) {
            userRequest = intent.getParcelableExtra(IntentParsableConstants.EXTRA_REGISTER_USER)
        }
    }

    private fun init() {
        vmAvatar.liveDataContactImage.observe(this, Observer { observerUploadAvatar(it) })
        vmRegister.liveDataSaveUser.observe(this, Observer { observerSaveUser(it) })
        setSpannableContent()
        ccpPicker = CountryCodePicker(this)
        ccpPicker.setAutoDetectedCountry(true)
        ccpPicker.setDefaultCountryUsingNameCode("lk")
        ccpPicker.registerCarrierNumberEditText(et_country)

        if (!userRequest?.mobileNo94.isNullOrEmpty()) {
            et_sign_up_phone_number.setText(userRequest?.mobileNo94)
        }

        et_sign_up_email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (text!!.endsWith(".com")) {
                    et_sign_up_email.validateOnTextChange(isCheckValidateIcon = true) { s -> s.isValidEmail() }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        iv_calendar.setOnClickListener(this)
        btn_submit_sign_up.setOnClickListener(this)
        iv_sign_up_pro_pic.setOnClickListener(this)
        et_country.setOnClickListener(this)
    }

    private fun setSpannableContent() {
        val spannable =
            SpannableString("Please note that we value and respect your privacy and will only use your data for services purposes. Read the our Privacy Statement for more information. Learn all you need to know, visit our help centre.")

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
            183,
            204,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tv_message_sign_up_desc.text = spannable
        tv_message_sign_up_desc.movementMethod = LinkMovementMethod.getInstance()
        tv_message_sign_up_desc.highlightColor = Color.TRANSPARENT
    }

    private fun startCountryPicker() {
        CountryPickerBuilder.instance
            .setTitleTextColor(sl.com.eightdigitz.presentation.R.color.colorBlack)
            .setActivityResultKey(IntentParsableConstants.COUNTRY_SELECTION)
            .setActivityTitle(sl.com.eightdigitz.presentation.R.string.title_country_picker)
            .start(this, RequestCodes.COUNTRY_CODE_REQUEST)
    }

    @SuppressLint("MissingPermission")
    private fun processSignUp() {
        if (validateForm() && validateProPic()) {
            withNetwork({
                getFcmTokenObserver()
            }, {
                showAlert(message = Msg.INTERNET_ISSUE)
            })
        }
    }

    private fun getFcmTokenObserver() {
        showProgress()
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                val token = task.result?.token
                authWithBackend(fmcToken = token)
            })
    }

    @SuppressLint("MissingPermission")
    private fun authWithBackend(fmcToken: String?) {
        val registerRequest = RegisterRequest()
        registerRequest.firebaseID = fmcToken
        registerRequest.mobileNo = userRequest?.mobileNo0
        registerRequest.fullName = et_name_sign_up.getStringTrim()
        registerRequest.email = et_sign_up_email.getStringTrim()
        registerRequest.defaultLanguage = userRequest?.defaultLanguage
        registerRequest.role = "user"
        registerRequest.profilePicture = userRequest?.profilePicture

        if (et_sign_up_description.getStringTrim().isNotEmpty()) {
            registerRequest.profileDescription = et_sign_up_description.getStringTrim()
            registerRequest.profileBiography = et_sign_up_description.getStringTrim()
        }

        withNetwork({
            vmRegister.createAccount(registerRequest)
        }, {
            showAlert(message = Msg.INTERNET_ISSUE)
        })
    }

    private fun validateForm(): Boolean {
        val isName = et_name_sign_up.validate(isCheckValidateIcon = true) { s -> s.length > 3 }

        val isEmail =
            et_sign_up_email.validateOnTextChange(isCheckValidateIcon = true) { s -> s.isValidEmail() }

        val isPhone =
            et_sign_up_phone_number.validateOnTextChange(isCheckValidateIcon = true) { s ->
                s.replace(
                    "+94",
                    "0"
                ).isValidMobile()
            }

        val isDateOfBirth = et_birthday.validate(isCheckValidateIcon = true) { s -> s.isNotEmpty() }

        val isCountry = et_country.validate(isCheckValidateIcon = true) { s -> s.isNotEmpty() }

        if (!isName || !isEmail || !isPhone || !isDateOfBirth || !isCountry) {
            showAlert(Msg.TITLE_REQUIRED, "Please fill out the required fields.")
            return false
        }

        return true
    }

    private fun validateProPic(): Boolean {
        if (userRequest?.profilePicture.isNullOrEmpty()) {
            showAlert(Msg.TITLE_REQUIRED, "Please upload profile picture")
            return false
        }
        return true
    }


    private fun observerSaveUser(resource: Resource<PUser>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> {
                    showProgress()
                }
                ResourceState.SUCCESS -> {
                    hideProgress()
                    if (!it.data?.authReference.isNullOrEmpty() && !it.data?.fullName.isNullOrEmpty()) {
                        userRequest?.fullName = it.data?.fullName
                        navigateToPostRegister()
                    } else {
                        Msg.ERROR_COMMON.showToast(this)
                    }
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.error.toString().showToast(this)
                }
            }
        }
    }

    private fun observerUploadAvatar(resource: Resource<String>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> {
                    showProgress()
                }
                ResourceState.SUCCESS -> {
                    hideProgress()
                    userRequest?.profilePicture = it.data.toString()
                    iv_sign_up_pro_pic.loadImageRound(file?.absolutePath!!)
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.error?.showToast(this)
                }
            }
        }
    }

    private fun setDatePicker() {
        val date =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                calender.set(Calendar.YEAR, year)
                calender.set(Calendar.MONTH, monthOfYear)
                calender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateLabel()
            }

        val mDatePickerDialog = DatePickerDialog(
            this,
            date,
            calender.get(Calendar.YEAR),
            calender.get(Calendar.MONTH),
            calender.get(Calendar.DAY_OF_MONTH)
        )

        mDatePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        mDatePickerDialog.show()
    }

    private fun updateLabel() {
        val myFormat = "yyyy-MM-dd"
        val stdFormat = "dd MMMM yyyy"
        val sdfForView = SimpleDateFormat(myFormat, Locale.US)
        val sdfForDB = SimpleDateFormat(stdFormat, Locale.US)
        dobFormat = sdfForDB.format(calender.time)
        et_birthday.setText(sdfForView.format(calender.time))
    }

    private fun cameraOptionsDialog(
        arrayOption: Array<CharSequence>,
        requestCode: Int
    ) {
        AppDialog.showChooiceDialog(this, object : AppDialog.DialogEvent {
            override fun onItemClick(which: Int) {
                when (which) {
                    0 -> {
                        ImagePicker.with(this@RegisterUser)
                            .provider(ImageProvider.CAMERA)
                            .compress(100)
                            .start(requestCode)
                    }
                    1 -> {
                        ImagePicker.with(this@RegisterUser)
                            .galleryOnly()
                            .compress(100)
                            .start(requestCode)
                    }
                }
            }
        }, "", arrayOption)
    }

    private fun navigateToPostRegister(){
        val intent = Intent(this, PostRegister::class.java)
        intent.putExtra(IntentParsableConstants.EXTRA_REGISTER_USER, userRequest)
        startActivityForResult(intent, RequestCodes.CREATE_USER_REQUEST_CODE)
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
                vmAvatar.uploadAvatar(file!!)
            }
        } else if (requestCode == RequestCodes.COUNTRY_CODE_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                val mCountry =
                    data?.extras?.getParcelable<PCountry>(IntentParsableConstants.COUNTRY_SELECTION)
                et_country.setText(mCountry?.name)
            }
        } else if (requestCode == RequestCodes.CREATE_USER_REQUEST_CODE){
            if (resultCode == ResultCodes.CREATE_USER_RESULT_CODE){
                setResult(ResultCodes.CREATE_USER_RESULT_CODE).also {
                    finish()
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_calendar -> setDatePicker()
            R.id.btn_submit_sign_up -> processSignUp()
            R.id.et_country -> startCountryPicker()
            R.id.iv_sign_up_pro_pic -> cameraOptionsDialog(arrayOptions, AVATAR_IMAGE_REQ_CODE)
        }
    }

    override fun onResume() {
        setBackground(sl.com.eightdigitz.presentation.R.drawable.bg_get_otp)
        super.onResume()
    }
}