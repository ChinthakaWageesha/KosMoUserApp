package sl.com.eightdigitz.chat.presentation.ui.video

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import sl.com.eightdigitz.chat.R
import sl.com.eightdigitz.chat.media.*
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.extensions.gone
import sl.com.eightdigitz.presentation.extensions.setActionBar
import sl.com.eightdigitz.presentation.extensions.showToast
import sl.com.eightdigitz.presentation.extensions.withNetwork
import kotlinx.android.synthetic.main.activity_video_preview.*
import kotlinx.android.synthetic.main.activity_video_preview.view.*
import kotlinx.android.synthetic.main.custom_exo_playback_control_view.view.*

class VideoPreviewActivity : BaseActivity(), MediaPlayerListener.PremoMediaPlayerListener,
    ProgressUpdateListener {

    private var isRecentlyPlay = false
    private var mMediaPlayerFullscreen: Boolean = false
    private var mFullScreenIcon: ImageView? = null
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var fullScreenDialog: Dialog

    companion object {
        const val EXTRA_URL = "extra_url"
        fun startVideoActivity(context: Context, url: String) {
            val intent = Intent(context, VideoPreviewActivity::class.java).apply {
                putExtra(EXTRA_URL, url)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_preview)
        supportActionBar?.setActionBar(this, "Video", true)

        val url = intent?.getStringExtra(EXTRA_URL)
        setData(url)
    }

    override fun onResume() {
        pv_video_player.onResume()
        super.onResume()
    }

    override fun onPause() {
        mediaPlayer.pausePlayback()
        pv_video_player.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mediaPlayer.release()
        super.onDestroy()
    }

    private fun initFullScreenDialog() {
        fullScreenDialog = Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        fullScreenDialog.setOnCancelListener {
            closeFullscreenDialog()
        }
    }

    private fun openFullscreenDialog() {
        pv_video_player.useController = true
        (pv_video_player.parent as ViewGroup).removeView(pv_video_player)
        fullScreenDialog.addContentView(
            pv_video_player,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        mFullScreenIcon?.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_fullscreen_skrink
            )
        )
        mMediaPlayerFullscreen = true
        fullScreenDialog.show()
    }

    private fun initFullscreenButton() {
//        val controlView = pv_video_player.exo_controller
        mFullScreenIcon = pv_video_player.exo_fullscreen_icon
        mFullScreenIcon?.setOnClickListener {
            if (!mMediaPlayerFullscreen)
                openFullscreenDialog()
            else
                closeFullscreenDialog()
        }
    }

    private fun closeFullscreenDialog() {
        pv_video_player.useController = true
        (pv_video_player.parent as ViewGroup).removeView(pv_video_player)
        fl_parent.addView(pv_video_player)
        mFullScreenIcon?.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_fullscreen_expand
            )
        )
        mMediaPlayerFullscreen = false
        fullScreenDialog.dismiss()
    }

    @SuppressLint("MissingPermission")
    private fun setData(url: String?) {

        val mMediaContent = MediaContent(video = url)

        initFullScreenDialog()
        initFullscreenButton()
        mediaPlayer = MediaPlayer(this, this, this)
        mediaPlayer.setPlayer(pv_video_player)
        mediaPlayer.setPlayMethod(true)

        if (url == null) {
            fl_parent.gone()
            return
        }
        withNetwork(
            {
                mediaPlayer.loadContent(mMediaContent)
                mediaPlayer.startPlayback(true)
            },
            {
                Msg.INTERNET_ISSUE.showToast(this)
                onBackPressed()
            })
    }

    override fun onProgressUpdate(progress: Long, bufferedProgress: Long, duration: Long) {

    }

    override fun onStateChanged(state: Int) {
        when (state) {
            MediaPlayerState.STATE_PLAYING -> {
                if (isRecentlyPlay) {
                    isRecentlyPlay = false
                }
                pb_video.gone()
            }
            MediaPlayerState.STATE_IDLE -> {
                pb_video.gone()
            }
            MediaPlayerState.STATE_PAUSED -> {
                pb_video.gone()
            }
            MediaPlayerState.STATE_CONNECTING -> {
            }
            MediaPlayerState.STATE_ENDED -> {
                mediaPlayer.pausePlayback()
            }
        }
    }
}