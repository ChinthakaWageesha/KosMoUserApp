package sl.com.eightdigitz.chat.media

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager

object BroadcastHelper {

    const val INTENT_PLAYER_STATE_CHANGE = "com.soundlover.playerStateChange"
    const val INTENT_PROGRESS_UPDATE = "com.soundlover.progressUpdate"

    const val EXTRA_MEDIA = "media"
    const val EXTRA_PLAYER_STATE = "playerStateId"
    const val EXTRA_PROGRESS = "progress"
    const val EXTRA_BUFFERED_PROGRESS = "bufferedProgress"
    const val EXTRA_DURATION = "duration"

    fun broadcastPlayerStateChange(
        context: Context, playerStateId: Int,
        media: MediaContent?
    ) {
        val intent = Intent(INTENT_PLAYER_STATE_CHANGE).apply {
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_MEDIA, media)
            putExtra(EXTRA_PLAYER_STATE, playerStateId)
            putExtras(bundle)
        }
        sendBroadcast(context, intent)
    }

    fun broadcastProgressUpdate(
        context: Context, progress: Long, bufferedProgress: Long,
        duration: Long, media: MediaContent?
    ) {
        val intent = Intent(INTENT_PROGRESS_UPDATE).apply {
            putExtra(EXTRA_PROGRESS, progress)
            putExtra(EXTRA_BUFFERED_PROGRESS, bufferedProgress)
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_MEDIA, media)
            putExtras(bundle)
            putExtra(EXTRA_DURATION, duration)
        }
        sendBroadcast(context, intent)
    }

    private fun sendBroadcast(context: Context, intent: Intent?) {
        intent?.let {
            LocalBroadcastManager.getInstance(context).sendBroadcast(it)
        }
    }

    fun registerReceiver(context: Context, receiver: BroadcastReceiver, vararg actions: String) {
        for (i in actions.indices) {
            LocalBroadcastManager.getInstance(context)
                .registerReceiver(receiver, IntentFilter(actions[i]))
        }
    }

    fun unregisterReceiver(context: Context, vararg receivers: BroadcastReceiver) {
        for (i in receivers.indices) {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(receivers[i])
        }
    }
}
