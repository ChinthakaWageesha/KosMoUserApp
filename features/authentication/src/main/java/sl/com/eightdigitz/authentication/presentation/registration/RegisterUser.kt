package sl.com.eightdigitz.authentication.presentation.registration

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.dhaval2404.imagepicker.ImagePicker
import com.github.dhaval2404.imagepicker.constant.ImageProvider
import com.hbb20.CountryCodePicker
import kotlinx.android.synthetic.main.fragment_get_otp.*
import kotlinx.android.synthetic.main.fragment_register_user.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.presentation.AuthActivity
import sl.com.eightdigitz.authentication.presentation.contact.JoinContactViewModel
import sl.com.eightdigitz.core.base.BaseFragment
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.presentation.PUser
import sl.com.eightdigitz.country_picker.presentation.country_picker.CountryPickerBuilder
import sl.com.eightdigitz.country_picker.presentation.models.PCountry
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.cropper.CropImageActivity
import sl.com.eightdigitz.presentation.extensions.*
import sl.com.eightdigitz.presentation.utile.AppDialog
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class RegisterUser : BaseFragment(), View.OnClickListener {

    private var calender = Calendar.getInstance()
    private lateinit var ccpPicker: CountryCodePicker
    private val vmAvatar by viewModel<JoinContactViewModel>()
    private val arrayOptions: Array<CharSequence> = arrayOf("Take photo", "Choose from gallery")
    private var file: File? = null
    private var dobFormat: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_user, container, false)
    }

    override fun onViewCreated() {
        setToolbar()
        init()
        showKeyboard(et_name_sign_up)
    }

    private fun setToolbar() {
        (requireActivity() as AuthActivity).supportActionBar?.setActionBar(
            context!!,
            "",
            isHomeUpEnables = true
        )
    }

    private fun init() {
        vmAvatar.liveDataContactImage.observe(this, Observer { observerUploadAvatar(it) })
        ccpPicker = CountryCodePicker(context)
        ccpPicker.setAutoDetectedCountry(true)
        ccpPicker.setDefaultCountryUsingNameCode("lk")
        ccpPicker.registerCarrierNumberEditText(et_country)

        if (!(requireActivity() as AuthActivity).mobileNumber94.isNullOrEmpty()) {
            et_sign_up_phone_number.setText((requireActivity() as AuthActivity).mobileNumber94)
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

    private fun startCountryPicker() {
        CountryPickerBuilder.instance
            .setTitleTextColor(sl.com.eightdigitz.presentation.R.color.colorBlack)
            .setActivityResultKey(IntentParsableConstants.COUNTRY_SELECTION)
            .setActivityTitle(sl.com.eightdigitz.presentation.R.string.title_country_picker)
            .start(this, RequestCodes.COUNTRY_CODE_REQUEST)
    }

    private fun getResId(drawableName: String): Int {

        try {
            val res = sl.com.eightdigitz.country_picker.R.drawable::class.java
            val field = res.getField(drawableName)
            return field.getInt(null)
        } catch (e: Exception) {
            Log.e("CountryCodePicker", "Failure to get drawable id.", e)
        }

        return -1
    }

    @SuppressLint("MissingPermission")
    private fun onContinue() {
        if (validateForm() && validateProPic()) {
            val dUser = DUser(
                fullName = et_name_sign_up.getStringTrim(),
                email = et_sign_up_email.getStringTrim(),
                mobileNo = (requireActivity() as AuthActivity).mobileNumber0,
                //dob = et_birthday.getStringTrim(),
                defaultLanguage = (requireActivity() as AuthActivity).language,
                role = "User",
                profilePicture = (requireActivity() as AuthActivity).avatarUrl
            )
            if (et_sign_up_description.getStringTrim().isNotEmpty()) {
                dUser.profileDescription = et_sign_up_description.getStringTrim()
            }

            hideKeyboard()
            (requireActivity() as AuthActivity).setPostRegister(dUser)
        }
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
        if ((requireActivity() as AuthActivity).avatarUrl.isNullOrEmpty()) {
            showAlert(Msg.TITLE_REQUIRED, "Please upload profile picture")
            return false
        }
        return true
    }

    private fun observerUploadAvatar(resource: Resource<String>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> {
                    showProgress()
                }
                ResourceState.SUCCESS -> {
                    hideProgress()
                    (requireActivity() as AuthActivity).avatarUrl = it.data.toString()
                    iv_sign_up_pro_pic.loadImageRound(file?.absolutePath!!)
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.showToast(context!!)
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
            context!!,
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
        AppDialog.showChooiceDialog(context!!, object : AppDialog.DialogEvent {
            override fun onItemClick(which: Int) {
                when (which) {
                    0 -> {
                        ImagePicker.with(this@RegisterUser)
                            .provider(ImageProvider.CAMERA) // Default will be ImageProvider.BOTH
                            //.compress(1024)           //   Final image size will be less than 100KB (Optional)
                            .compress(100)
                            .start(requestCode)
                    }
                    1 -> {
                        ImagePicker.with(this@RegisterUser)
                            .galleryOnly() // User can only select image from Gallery(Optional)
                            .compress(100) //   Final image size will be less than 100KB (Optional)
                            .start(requestCode)
                    }
                }
            }
        }, "", arrayOption)
    }

    @SuppressLint("MissingPermission")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AVATAR_IMAGE_REQ_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                file = ImagePicker.getFile(data)
                file?.absolutePath?.let { CropImageActivity.startCropActivity(this, it) }
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                ImagePicker.getError(data).showToast(context!!)
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
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_calendar -> setDatePicker()
            R.id.btn_submit_sign_up -> onContinue()
            R.id.et_country -> startCountryPicker()
            R.id.iv_sign_up_pro_pic -> cameraOptionsDialog(arrayOptions, AVATAR_IMAGE_REQ_CODE)
        }
    }

    override fun onResume() {
        setBackground(sl.com.eightdigitz.presentation.R.drawable.bg_get_otp)
        super.onResume()
    }


    companion object {
        const val TAG = "register_user"
        private const val AVATAR_IMAGE_REQ_CODE = 1124

        fun newInstance(): Fragment {
            return RegisterUser()
        }
    }
}