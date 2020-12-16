package sl.com.eightdigitz.chat.presentation.ui.image

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle

import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.presentation.extensions.loadImage
import sl.com.eightdigitz.presentation.extensions.setActionBar
import sl.com.eightdigitz.chat.R
import kotlinx.android.synthetic.main.activity_preview.*

class PreviewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        supportActionBar?.setActionBar(
            this,
            "Send picture",
            isHomeUpEnables = true
        )

        val urlImage = intent.data
        iv_preview.loadImage(urlImage?.path, R.drawable.placeholder)

        btn_cancel.setOnClickListener {
            finish()
        }
        btn_send.setOnClickListener {
            val result = Intent().apply {
                data = urlImage
                //putExtra(EXTRA_URL, urlImage)
            }
            setResult(Activity.RESULT_OK, result)
            finish()
        }
    }

    companion object {
        const val REQUEST_PREVIEW = 19876
        const val EXTRA_URL = "extra_url"
        fun startActivity(activity: Activity, url: Uri) {
            val intent = Intent(activity, PreviewActivity::class.java).apply {
                data = url
            }
            activity.startActivityForResult(intent,
                REQUEST_PREVIEW
            )
        }
    }
}
