package sl.com.eightdigitz.core.ui

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_help_center.*
import sl.com.eightdigitz.core.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.presentation.extensions.hideKeyboard
import sl.com.eightdigitz.presentation.extensions.setHelpCenterActionBar
import sl.com.eightdigitz.presentation.extensions.showToast
import sl.com.eightdigitz.presentation.extensions.startActivity

class HelpCenter : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help_center)
        setToolbar()
        init()
    }

    private fun setToolbar() {
        supportActionBar?.setHelpCenterActionBar(
            this,
            getString(sl.com.eightdigitz.presentation.R.string.title_help),
            isHomeUpEnables = true
        )
    }

    private fun init() {
        cl_account_services_base.setOnClickListener(this)
        cl_copyright_base.setOnClickListener(this)
        cl_terms_use_base.setOnClickListener(this)
        cl_privacy_policy_base.setOnClickListener(this)
        cl_software_license_base.setOnClickListener(this)
        cl_pricing_payments_base.setOnClickListener(this)
        cl_faq_base.setOnClickListener(this)
    }

    private fun showTempToast(){
        "To be implemented".showToast(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        hideKeyboard()
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cl_account_services_base -> showTempToast()
            R.id.cl_copyright_base -> showTempToast()
            R.id.cl_terms_use_base -> startActivity<TermsOfUse>()
            R.id.cl_privacy_policy_base -> startActivity<PrivacyPolicy>()
            R.id.cl_software_license_base -> showTempToast()
            R.id.cl_pricing_payments_base -> showTempToast()
            R.id.cl_faq_base -> showTempToast()
        }
    }
}