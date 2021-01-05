package sl.com.eightdigitz.authentication.presentation

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.fragment_get_started.*
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.core.base.BaseFragment
import sl.com.eightdigitz.core.ui.AppInformation
import sl.com.eightdigitz.presentation.AppInformationType


class GetStarted : BaseFragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_get_started, container, false)
    }

    override fun onViewCreated() {
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
        AppInformation.startActivity(context!!, title)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_get_started -> {
                (requireActivity() as AuthActivity).setGetOTP()
            }
        }
    }

    companion object{
        const val TAG = "get_started"

        fun newInstance(): Fragment {
            return GetStarted()
        }
    }
}