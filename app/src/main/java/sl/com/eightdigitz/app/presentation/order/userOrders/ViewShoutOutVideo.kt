package sl.com.eightdigitz.app.presentation.order.userOrders

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_view_shoutout_video.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DOrder
import sl.com.eightdigitz.presentation.IntentParsableConstants
import sl.com.eightdigitz.presentation.extensions.makeInvisible
import sl.com.eightdigitz.presentation.extensions.makeVisible
import sl.com.eightdigitz.presentation.extensions.showToast
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ViewShoutOutVideo : BaseActivity(), View.OnClickListener {

    private val CHECK_PERMISSION_REQUEST = 0
    private var order: DOrder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_shoutout_video)
        init()
    }

    private fun init() {
        if (intent.hasExtra(IntentParsableConstants.EXTRA_NEW_ORDER)) {
            order = intent.getParcelableExtra(IntentParsableConstants.EXTRA_NEW_ORDER)
            shoutOutVideo.setSource(order?.shoutOutURL)
        }

        iv_download_shoutout.setOnClickListener(this)
        iv_close_shoutout.setOnClickListener(this)
        iv_share_shoutout.setOnClickListener(this)
    }


    inner class Download : AsyncTask<String?, Int?, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            downloading_progress.makeVisible()
        }

        override fun doInBackground(vararg url: String?): String {
            val mydir = File(Environment.getExternalStorageDirectory().toString() + "/Hello")
            if (!mydir.exists()) {
                mydir.mkdirs()
            }
            val manager: DownloadManager =
                getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val downloadUri: Uri = Uri.parse(order?.shoutOutURL)
            val request: DownloadManager.Request = DownloadManager.Request(downloadUri)
            val dateFormat = SimpleDateFormat("mmddyyyyhhmmss")
            val date: String = dateFormat.format(Date())
            request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE
            )
                .setAllowedOverRoaming(false)
                .setTitle("Downloading")
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "$date.mp4")
            manager.enqueue(request)
            return mydir.absolutePath + File.separator.toString() + date + ".mp4"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            downloading_progress.makeInvisible()
            if (result!!.endsWith(".mp4")){
                "ShoutOut video already downloaded".showToast(this@ViewShoutOutVideo)
            } else {
                result.showToast(this@ViewShoutOutVideo)
            }
        }
    }

    private fun checkPermission(): Boolean {
        val READ_EXTERNAL_PERMISSION: Int =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (READ_EXTERNAL_PERMISSION != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                CHECK_PERMISSION_REQUEST
            )
            return false
        }
        return true
    }

    private fun shareVideo() {
        val videoUri = Uri.parse(order?.shoutOutURL)
        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, videoUri)
            type = "image/png"
        }
        startActivity(Intent.createChooser(shareIntent, "Share Using"))
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CHECK_PERMISSION_REQUEST && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //do somethings
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_close_shoutout -> onBackPressed()
            R.id.iv_share_shoutout -> shareVideo()
            R.id.iv_download_shoutout -> {
                if (checkPermission()) {
                    val db = Download()
                    db.execute()
                }
            }
        }
    }
}