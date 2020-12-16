package sl.com.eightdigitz.chat.media

import com.google.android.exoplayer2.ui.PlayerView

abstract class MediaPlayerListener(

    protected var progressUpdateListener: ProgressUpdateListener?,

    protected var mMediaPlayerListener: PremoMediaPlayerListener?
) {

    abstract val isStreaming: Boolean

    abstract val state: Int

    abstract val currentPosition: Long

    abstract val duration: Long

    abstract val bufferedPosition: Long

    open fun release() {
        mMediaPlayerListener = null
        progressUpdateListener = null
    }

    abstract fun setPlayer(playerview: PlayerView)

    abstract fun loadContent(mediaContent: MediaContent)

    abstract fun startPlayback(playImmediately: Boolean)

    abstract fun resumePlayback()

    abstract fun pausePlayback()

    abstract fun stopPlayback()

    abstract fun seekTo(position: Long)

    abstract fun setPlaybackSpeed(speed: Float)

    /**
     * Used to listen for media player state changes
     * Created by evanhalley on 11/18/15.
     */
    interface PremoMediaPlayerListener {

        fun onStateChanged(state: Int)

    }
}