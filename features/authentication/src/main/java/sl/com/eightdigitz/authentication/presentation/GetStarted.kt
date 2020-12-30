package sl.com.eightdigitz.authentication.presentation

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.text.HtmlCompat
import kotlinx.android.synthetic.main.activity_get_started.*
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.presentation.registration.RegisterActivity
import sl.com.eightdigitz.authentication.presentation.verifyOTP.GetOTPActivity
import sl.com.eightdigitz.authentication.presentation.verifyOTP.VerifyOTPActivity
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.ui.AppInformation
import sl.com.eightdigitz.presentation.AppInformationType
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.extensions.showToast
import sl.com.eightdigitz.presentation.extensions.startActivity

class GetStarted : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)
        setContent()
        btn_get_started.setOnClickListener(this)
    }

    private fun setContent() {
        val span =
            SpannableString(getString(sl.com.eightdigitz.presentation.R.string.message_get_started_description))

        val clickSpan1: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textview: View) {
                navigateToAppInfo(AppInformationType.TNC)
            }
        }

        val clickSpan2: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textview: View) {
                navigateToAppInfo(AppInformationType.PRIVACY_POLICY)
            }
        }

        span.setSpan(clickSpan1, 32, 44, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        span.setSpan(ForegroundColorSpan(Color.WHITE), 32, 44, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(clickSpan2, 47, 61, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        span.setSpan(ForegroundColorSpan(Color.WHITE), 47, 61, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        tv_get_started_desc.text = span
        tv_get_started_desc.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun navigateToAppInfo(title: String) {
        tv_get_started_desc.highlightColor = Color.TRANSPARENT
        AppInformation.startActivity(this, title)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
           R.id.btn_get_started -> startActivity<GetOTPActivity>()
        }
    }

}
