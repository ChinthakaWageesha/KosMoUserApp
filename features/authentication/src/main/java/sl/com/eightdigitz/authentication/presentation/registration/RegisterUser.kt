package sl.com.eightdigitz.authentication.presentation.registration

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.dhaval2404.imagepicker.ImagePicker
import com.github.dhaval2404.imagepicker.constant.ImageProvider
import kotlinx.android.synthetic.main.fragment_register_user.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.presentation.AuthActivity
import sl.com.eightdigitz.authentication.presentation.verifyOTP.GetOTP
import sl.com.eightdigitz.core.base.BaseFragment
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.presentation.PUser
import sl.com.eightdigitz.navigation.features.ResultCode
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
import sl.com.eightdigitz.presentation.cropper.CropImageActivity
import sl.com.eightdigitz.presentation.extensions.*
import sl.com.eightdigitz.presentation.utile.AppDialog
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class RegisterUser : BaseFragment(), View.OnClickListener {

    private var calender = Calendar.getInstance()
    private val vmRegister by viewModel<RegistrationViewModel>()
    private val arrayOptions: Array<CharSequence> = arrayOf("Take photo", "Choose from gallery")
    private var file: File? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_user, container, false)
    }

    override fun onViewCreated() {
        init()
    }

    private fun init() {
        vmRegister.liveDataSaveUser.observe(this, Observer { observerSaveUser(it) })
        et_birthday.setOnClickListener(this)
        btn_continue.setOnClickListener(this)
        iv_upload_photo.setOnClickListener(this)
        iv_back_register.setOnClickListener(this)
        et_email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (text!!.endsWith(".com")){
                    et_email.validateOnTextChange(isCheckValidateIcon = true) { s -> s.isValidEmail() }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    @SuppressLint("MissingPermission")
    private fun onContinue() {
        if (validateForm()) {
            val dUser = DUser(
                mobileNo = (requireActivity() as AuthActivity).mobileNumber,
                fullName = et_full_name.getStringTrim(),
                defaultLanguage = (requireActivity() as AuthActivity).language,
                email = et_email.getStringTrim(),
                dob = et_birthday.getStringTrim(),
                role = "User",
                profilePicture = "",
                profileBanner = "",
                profileVideo = ""
            )
            activity?.withNetwork({
                vmRegister.createAccount(dUser)
            }, {
                Msg.INTERNET_ISSUE.showToast(context!!)
            })
        }
    }

    private fun validateForm(): Boolean {
        val isFullName = et_full_name.validate(isCheckValidateIcon = true) { s -> s.length > 3 }
        val isEmail =
            et_email.validateOnTextChange(isCheckValidateIcon = true) { s -> s.isValidEmail() }
        val isDateOfBirth = et_birthday.validate { s -> s.isNotEmpty() }

        if (!isFullName) {
            showAlert(message = getString(sl.com.eightdigitz.presentation.R.string.error_full_name_required))
            return false
        }

        if (!isEmail) {
            showAlert(message = getString(sl.com.eightdigitz.presentation.R.string.error_email_required))
            return false
        }

        if (!isDateOfBirth) {
            showAlert(message = getString(sl.com.eightdigitz.presentation.R.string.error_birthday_required))
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
                    if (!it.data?.authReference.isNullOrEmpty()) {
                        (requireActivity() as AuthActivity).fullName = et_full_name.getStringTrim()
                        (requireActivity() as AuthActivity).setPostRegister()
                    } else {
                        Msg.ERROR_COMMON.showToast(context!!)
                    }
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
            calender.get(Calendar.DAY_OF_MONTH))

        mDatePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        mDatePickerDialog.show()
    }

    private fun updateLabel() {
        val myFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        et_birthday.setText(sdf.format(calender.time))
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
                iv_sign_up_user_photo.loadImageRound(file?.absolutePath!!)
                (requireActivity() as AuthActivity).avatarUrl = file?.absolutePath
                /*file?.let {
                    activity?.withNetwork({
                        iv_sign_up_user_photo.loadImageRound(file?.absolutePath!!)
                    }, {
                        Msg.INTERNET_ISSUE.showToast(context!!)
                    })
                }*/
            }
        }
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.et_birthday -> setDatePicker()
            R.id.btn_continue -> onContinue()
            R.id.iv_upload_photo -> cameraOptionsDialog(arrayOptions, AVATAR_IMAGE_REQ_CODE)
            R.id.iv_back_register -> activity?.onBackPressed()
        }
    }

    override fun onResume() {
        setBackground(sl.com.eightdigitz.presentation.R.drawable.bg_gradient_purple_seablue_register)
        super.onResume()
    }

    companion object{
        const val TAG = "register_user"
        private const val AVATAR_IMAGE_REQ_CODE = 1124

        fun newInstance(): Fragment {
            return RegisterUser()
        }
    }
}