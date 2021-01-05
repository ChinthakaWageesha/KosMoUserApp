package sl.com.eightdigitz.presentation.cropper

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.isseiaoki.simplecropview.CropImageView
import com.isseiaoki.simplecropview.callback.CropCallback
import com.isseiaoki.simplecropview.callback.LoadCallback
import com.isseiaoki.simplecropview.callback.SaveCallback
import kotlinx.android.synthetic.main.activity_crop_image.*
import sl.com.eightdigitz.presentation.R
import sl.com.eightdigitz.presentation.extensions.getAppFilePath
import java.io.File

class CropImageActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CROP_IMAGE = 11211
        const val PREF_KEY_IMAGE_URL = "image_url"
        const val PREF_CROP_MODE = "extra_crop_mode"
        const val PREF_KEY = "key"

        const val MODE_CIRCLE = 1
        const val MODE_SQUARE = 2

        fun startCropActivity(activty: Activity, imageUrl: String) {
            val intent = Intent(activty, CropImageActivity::class.java).apply {
                putExtra(PREF_KEY_IMAGE_URL, imageUrl)
                //putExtra(PREF_CROP_MODE, mode)
            }
            activty.startActivityForResult(intent, REQUEST_CROP_IMAGE)
        }

        fun startCropActivity(fragment: Fragment, imageUrl: String) {
            val intent = Intent(fragment.context, CropImageActivity::class.java).apply {
                putExtra(PREF_KEY_IMAGE_URL, imageUrl)
                //putExtra(PREF_CROP_MODE, mode)
            }
            fragment.startActivityForResult(intent, REQUEST_CROP_IMAGE)
        }
    }

    private var file: File? = null
    private val mCropCallback = object : CropCallback {
        override fun onSuccess(cropped: Bitmap?) {

        }

        override fun onError(e: Throwable?) {

        }

    }

    private val mSaveCallback = object : SaveCallback {

        override fun onSuccess(outputUri: Uri) {
            val intent = Intent().apply {
                data = outputUri
            }
            setResult(RESULT_OK, intent)
            finish()
        }

        override fun onError(e: Throwable?) {
            Log.e("sdsd", "${e.toString()}")
        }

    }

    private lateinit var outputFileUri: Uri
    private var mIntKey: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop_image)
        setUpCrop()
        this.getAppFilePath()
        btn_cancel_crop.setOnClickListener {
            this.finish()
        }
        btn_done_crop.setOnClickListener {
            ci_image.startCrop(Uri.fromFile(file), mCropCallback, mSaveCallback)
        }
    }

    private fun setUpCrop() {
        val data = intent?.extras?.getString(PREF_KEY_IMAGE_URL)
        val mode = intent?.extras?.getInt(PREF_CROP_MODE, MODE_SQUARE)

        outputFileUri = Uri.fromFile(File(data))
        file = File(this.applicationContext.getAppFilePath(), outputFileUri.lastPathSegment)
        file?.createNewFile()
        ci_image.setInitialFrameScale(0.75f)
        val cropMode = when (mode) {
            MODE_CIRCLE -> CropImageView.CropMode.CIRCLE
            MODE_SQUARE -> CropImageView.CropMode.SQUARE
            else -> CropImageView.CropMode.SQUARE
        }
        ci_image.setCropMode(cropMode)
        ci_image.load(outputFileUri).useThumbnail(true).execute(object : LoadCallback {
            override fun onSuccess() {

            }

            override fun onError(e: Throwable?) {

            }

        })
    }

    override fun onSaveInstanceState(oldInstanceState: Bundle) {
        super.onSaveInstanceState(oldInstanceState)
        oldInstanceState.clear()
    }
}