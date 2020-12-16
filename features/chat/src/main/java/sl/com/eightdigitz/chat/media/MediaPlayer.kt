package sl.com.eightdigitz.chat.media

import android.content.Context
import android.net.Uri
import android.os.Handler
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Log
import com.google.android.exoplayer2.util.Util

const val MEDIA_CACHE_DIR = "media-cache"

class MediaPlayer(
    var context: Context,
    mediaPlayerListener: PremoMediaPlayerListener,
    progressUpdateListener: ProgressUpdateListener
) : MediaPlayerListener(progressUpdateListener, mediaPlayerListener),
    Player.EventListener {

    private var playerview: PlayerView? = null
    private var playVideo = false
    private var mMediaContent: MediaContent? = null
    private val TAG = MediaPlayer::class.java.simpleName
    private val ALLOCATION_SIZE = 65535
    private val BUFFER_SIZE = 10 * 1024 * 1024
    private var mMediaPlayer: ExoPlayer = ExoPlayerFactory.newSimpleInstance(
        context,
        DefaultRenderersFactory(context),
        DefaultTrackSelector(),
        DefaultLoadControl()
    ) as ExoPlayer
    private var mMediaPlayerState: Int = 0
    private var mIsStreaming: Boolean = false
    private var mProgressUpdater: Runnable? = null
    private var isUpdatingProgress: Boolean = false
    private val mHandler: Handler = Handler()

    init {
        mMediaPlayer.addListener(this)
        mMediaPlayerState = MediaPlayerState.STATE_IDLE
        mProgressUpdater = ProgressUpdater()
    }

    override val isStreaming: Boolean
        get() = mIsStreaming
    override val state: Int
        get() = mMediaPlayerState
    override val currentPosition: Long
        get() = mMediaPlayer.currentPosition
    override val duration: Long
        get() = mMediaPlayer.duration
    override val bufferedPosition: Long
        get() = mMediaPlayer.bufferedPosition

    fun setPlayMethod(isVideo: Boolean) {
        playVideo = isVideo
    }

    override fun loadContent(mediaContent: MediaContent) {
        mMediaContent = mediaContent
    }

    override fun setPlayer(playerview: PlayerView) {
        this.playerview = playerview
        playerview.player = mMediaPlayer
    }

    override fun startPlayback(playImmediately: Boolean) {
        mMediaContent?.let {
            if (it.progress > -1) {
                mMediaPlayer.seekTo(it.progress)
            } else {
                mMediaPlayer.seekTo(0)
            }
        }
        val mediaSourse = buildMediaSource()
        mMediaPlayer.prepare(mediaSourse)
        mMediaPlayer.playWhenReady = playImmediately
    }

    override fun resumePlayback() {
        mMediaPlayer.playWhenReady = true
    }

    override fun pausePlayback() {
        mMediaPlayer.playWhenReady = false
    }

    override fun stopPlayback() {
        mMediaPlayer.stop()
        mIsStreaming = false
        mMediaContent = null
    }

    override fun seekTo(position: Long) {
        mMediaPlayer.seekTo(position)
        if (state == MediaPlayerState.STATE_PAUSED) {
            ProgressUpdater().run()
        }
    }

    override fun setPlaybackSpeed(speed: Float) {
        //
    }

    override fun release() {
        super.release()
        mMediaPlayer.release()
        mMediaPlayer.removeListener(this)
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        val playbackStateStr: String

        when (playbackState) {
            Player.STATE_BUFFERING -> {
                mMediaPlayerState = MediaPlayerState.STATE_CONNECTING
                playerview?.keepScreenOn = true
                playbackStateStr = "Buffering"
            }
            Player.STATE_ENDED -> {
                mMediaPlayerState = MediaPlayerState.STATE_ENDED
                playerview?.keepScreenOn = false
                playbackStateStr = "Ended"
            }
            Player.STATE_IDLE -> {
                mMediaPlayerState = MediaPlayerState.STATE_IDLE
                playerview?.keepScreenOn = false
                playbackStateStr = "Idle"
            }
            Player.STATE_READY -> {
                playerview?.keepScreenOn = true
                mMediaPlayerState = if (playWhenReady)
                    MediaPlayerState.STATE_PLAYING
                else
                    MediaPlayerState.STATE_PAUSED
                playbackStateStr = "Ready"

                if (playWhenReady) {
                    startProgressUpdater()
                } else {
                    stopProgressUpdater()
                }
            }
            else -> {
                mMediaPlayerState = MediaPlayerState.STATE_IDLE
                playerview?.keepScreenOn = false
                playbackStateStr = "Unknown"
            }
        }
        mMediaPlayerListener?.onStateChanged(mMediaPlayerState)
        Log.d(
            TAG, String.format(
                "ExoPlayer state changed: %s, Play When Ready: %s",
                playbackStateStr,
                playWhenReady.toString()
            )
        )
    }

    fun startProgressUpdater() {
        if (!isUpdatingProgress) {
            mProgressUpdater?.run()
            isUpdatingProgress = true
        }
    }

    fun stopProgressUpdater() {
        if (isUpdatingProgress) {
            mHandler.removeCallbacks(mProgressUpdater)
            isUpdatingProgress = false
        }
    }

    override fun onPlayerError(error: ExoPlaybackException?) {
        stopPlayback()
    }

    private fun buildMediaSource(): MediaSource {
        val uri =
            if (playVideo) Uri.parse(mMediaContent?.video) else Uri.parse(mMediaContent?.audio)
        val dataSourceFactory = DefaultDataSourceFactory(
            context,
            Util.getUserAgent(context, context.getString(sl.com.eightdigitz.presentation.R.string.app_name))
        )
        uri?.let {
            return ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(it)
        }
        mIsStreaming = true
        throw IllegalStateException("Unable to build media source")
    }

    private inner class ProgressUpdater : Runnable {

        private val TIME_UPDATE_MS = 150

        override fun run() {
            progressUpdateListener?.onProgressUpdate(
                mMediaPlayer.currentPosition,
                if (isStreaming) mMediaPlayer.bufferedPosition else mMediaPlayer.duration,
                mMediaPlayer.duration
            )
            mHandler.postDelayed(mProgressUpdater, TIME_UPDATE_MS.toLong())
        }
    }
}