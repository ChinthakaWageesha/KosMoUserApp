package sl.com.eightdigitz.authentication.presentation.registration

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.presentation.PUser
import sl.com.eightdigitz.navigation.features.ResultCode
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
import sl.com.eightdigitz.presentation.extensions.*
import java.text.SimpleDateFormat
import java.util.*


class RegisterActivity : BaseActivity(), View.OnClickListener {

    private var calender = Calendar.getInstance()
    private val vmRegister by viewModel<RegistrationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        init()
    }

    private fun init() {
        setToolbar()
        et_email.validateOnTextChange(isCheckValidateIcon = true) { s -> s.isValidEmail() }
        vmRegister.liveDataSaveUser.observe(this, Observer { observerSaveUser(it) })
        et_birthday.setOnClickListener(this)
        et_birthday.setOnClickListener(this)
        btn_continue.setOnClickListener(this)
    }

    private fun setToolbar() {
        supportActionBar?.setActionBar(
            this,
            "",
            isHomeUpEnables = true
        )
    }

    @SuppressLint("MissingPermission")
    private fun onContinue() {
        if (validateForm()) {
            val dUser = DUser(
                mobileNo = "+94715781989",
                defaultLanguage = "English",
                email= et_email.getStringTrim(),
                dob = et_birthday.getStringTrim(),
                role = "User",
                profilePicture = "",
                profileBanner = "",
                profileVideo = ""
            )
            withNetwork({
                vmRegister.createAccount(dUser)
            }, {
                Msg.INTERNET_ISSUE.showToast(this)
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
                        setResult(ResultCode.RESULT_NAV_MAIN).also {
                            finish()
                        }
                    } else {
                        Msg.ERROR_COMMON.showToast(this)
                    }
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.showToast(this)
                }
            }
        }
    }

    private fun setDatePicker() {
        val date =
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                calender.set(Calendar.YEAR, year)
                calender.set(Calendar.MONTH, monthOfYear)
                calender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateLabel()
            }

        DatePickerDialog(
            this,
            date,
            calender.get(Calendar.YEAR),
            calender.get(Calendar.MONTH),
            calender.get(Calendar.DAY_OF_MONTH)
        ).show()

    }

    private fun updateLabel() {
        val myFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        et_birthday.setText(sdf.format(calender.time))
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.et_birthday -> setDatePicker()
            R.id.btn_continue -> onContinue()
        }
    }
}