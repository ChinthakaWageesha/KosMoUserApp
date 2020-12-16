package sl.com.eightdigitz.chat.presentation.ui.video

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import sl.com.eightdigitz.chat.ChatModuleConfig
import sl.com.eightdigitz.chat.R
import kotlinx.android.synthetic.main.activity_trim_video.*
import life.knowledge4.videotrimmer.interfaces.OnTrimVideoListener


class TrimVideoActivity : AppCompatActivity(), OnTrimVideoListener {

    companion object {
        const val URI_EXTRA = "uri"
        const val TRIM_REQUEST = 1343
        fun startTrimmer(activity: Activity, uri: String) {
            val intent = Intent(activity, TrimVideoActivity::class.java).apply {
                putExtra(URI_EXTRA, uri)
            }
            (activity).startActivityForResult(intent, TRIM_REQUEST)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trim_video)

        val uri = intent.getStringExtra(URI_EXTRA)

        if (mK4LVideoTrimmer != null) {
            mK4LVideoTrimmer.setMaxDuration(ChatModuleConfig.maxVideoTrimDuration)
            mK4LVideoTrimmer.setDestinationPath(ChatModuleConfig.destinationPath)
            mK4LVideoTrimmer.setOnTrimVideoListener(this)
            mK4LVideoTrimmer.setVideoURI(Uri.parse(uri))
//            mK4LVideoTrimmer.setVideoInformationVisibility(true)
        }
    }

    override fun getResult(uri: Uri?) {
        val intent = Intent()
        intent.data = uri
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun cancelAction() {
        mK4LVideoTrimmer.destroy()
        finish()
    }
}