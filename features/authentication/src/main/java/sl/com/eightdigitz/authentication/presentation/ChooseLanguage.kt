package sl.com.eightdigitz.authentication.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_choose_language.*
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.di.injectFeature
import sl.com.eightdigitz.authentication.presentation.contact.ContactSupport
import sl.com.eightdigitz.authentication.presentation.verifyOTP.GetOTP
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DUserRegister
import sl.com.eightdigitz.navigation.features.ResultCode
import sl.com.eightdigitz.presentation.IntentParsableConstants
import sl.com.eightdigitz.presentation.LanguageType
import sl.com.eightdigitz.presentation.RequestCodes
import sl.com.eightdigitz.presentation.ResultCodes
import sl.com.eightdigitz.presentation.extensions.setEnable
import sl.com.eightdigitz.presentation.extensions.showToast
import sl.com.eightdigitz.presentation.extensions.startActivity

class ChooseLanguage : BaseActivity(), View.OnClickListener {

    private var language: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_language)
        injectFeature()
        init()
    }

    private fun init() {
        rg_select_language_1.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId != -1) {
                fun2();
            }
        }

        rg_select_language_2.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId != -1) {
                fun1();
            }
        }

        btn_continue.setOnClickListener(this)
        tv_contact_us.setOnClickListener(this)
    }

    private fun fun1() {
        rg_select_language_1.setOnCheckedChangeListener(null)
        rg_select_language_1.clearCheck()
        rg_select_language_1.setOnCheckedChangeListener { _, _ -> fun2() }
        btn_continue.setEnable()
    }

    private fun fun2() {
        rg_select_language_2.setOnCheckedChangeListener(null)
        rg_select_language_2.clearCheck()
        rg_select_language_2.setOnCheckedChangeListener { _, _ -> fun1() }
        btn_continue.setEnable()
    }

    private fun navigateToGetOTP(request: DUserRegister){
        if (request.defaultLanguage == LanguageType.ENGLISH){
            val intent = Intent(this, GetOTP::class.java)
            intent.putExtra(IntentParsableConstants.EXTRA_REGISTER_USER, request)
            startActivityForResult(intent, RequestCodes.CREATE_USER_REQUEST_CODE)
        } else {
          "${request.defaultLanguage} is available for this stage".showToast(this)
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_contact_us -> startActivity<ContactSupport>()
            R.id.btn_continue -> {
                val request = DUserRegister()
                if (btn_english.isChecked) {
                    language = LanguageType.ENGLISH
                }
                if (btn_sinhala.isChecked) {
                    language = LanguageType.SINHALA
                }
                if (btn_tamil.isChecked) {
                    language = LanguageType.TAMIL
                }
                if (btn_hindi.isChecked) {
                    language = LanguageType.HINDI
                }
                request.defaultLanguage = language
                navigateToGetOTP(request)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RequestCodes.CREATE_USER_REQUEST_CODE && resultCode == ResultCodes.CREATE_USER_RESULT_CODE){
            setResult(ResultCode.RESULT_NAV_MAIN).also {
                finish()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onResume() {
        setBackground(sl.com.eightdigitz.presentation.R.drawable.bg_choose_language)
        super.onResume()
    }
}