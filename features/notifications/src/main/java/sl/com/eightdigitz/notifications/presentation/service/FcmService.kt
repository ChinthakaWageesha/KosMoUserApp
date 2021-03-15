package sl.com.eightdigitz.notifications.presentation.service

import android.app.ActivityManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import sl.com.eightdigitz.core.ui.CoreActivity
import sl.com.eightdigitz.presentation.Constant.PREF_FCM_TOKEN
import sl.com.eightdigitz.presentation.IntentActionConstants.ACTION_VIEW_NOTIFICATIONS
import sl.com.eightdigitz.presentation.IntentParsableConstants.EXTRA_NOTIFICATION
import sl.com.eightdigitz.presentation.IntentParsableConstants.EXTRA_NOTIFICATION_DATA
import sl.com.eightdigitz.presentation.extensions.setValue
import sl.com.eightdigitz.presentation.extensions.toJsonString
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.reactivex.subjects.PublishSubject
import org.koin.android.ext.android.inject
import java.util.*

/**
 * IF the Notification has a custom data format use the PCustomNotificationModel to map the response ,if not use PNotification model
 */
class FcmService : FirebaseMessagingService() {

    private val mSharedPreferences by inject<SharedPreferences>()
    private lateinit var mNotificationManager: NotificationManager
    private val channelId = "hello_user"
    private var mContent: String? = null

    companion object {
        private var tokenData: PublishSubject<String> = PublishSubject.create()


        //todo call this method in Logout
        fun getObservable(): PublishSubject<String> {
            return tokenData
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        tokenData.onNext(token)
        mSharedPreferences.setValue(PREF_FCM_TOKEN, token)
        println("FCM Token $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        remoteMessage.let { message ->

            mNotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                setupNotificationChannels()
            }
            val notificationId = Random().nextInt(60000)

            mContent = remoteMessage.data.toJsonString()

            var pendingIntent: PendingIntent? = null

            var intentActivity: Intent? = null

            if (applicationInForeground()) {
                // clear the top and launch the notification tab
                intentActivity = Intent(this, CoreActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    action = ACTION_VIEW_NOTIFICATIONS
                    putExtra(EXTRA_NOTIFICATION, true)
                    putExtra(EXTRA_NOTIFICATION_DATA, mContent)
                }
            } else {
                // Create a Navigation To launch the notification
                intentActivity = Intent(this, CoreActivity::class.java).apply {
                    action = ACTION_VIEW_NOTIFICATIONS
                    putExtra(EXTRA_NOTIFICATION, true)
                    putExtra(EXTRA_NOTIFICATION_DATA, mContent)
                }
            }
            pendingIntent = PendingIntent.getActivity(
                this,
                112,
                intentActivity,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(sl.com.eightdigitz.core.R.drawable.ic_launcher_background)
                .setContentTitle(message.data["title"])
                .setContentText(message.data["message"])
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setSound(defaultSoundUri)

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.notify(
                notificationId,
                notificationBuilder.build()
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupNotificationChannels() {
        val adminChannelName = "Test"
        val adminChannelDescription = "Description"

        val adminChannel = NotificationChannel(
            channelId,
            adminChannelName,
            NotificationManager.IMPORTANCE_LOW
        )
        adminChannel.description = adminChannelDescription
        adminChannel.enableLights(true)
        adminChannel.lightColor = Color.RED
        adminChannel.enableVibration(true)
        mNotificationManager.createNotificationChannel(adminChannel)
    }

    private fun applicationInForeground(): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val services = activityManager.runningAppProcesses
        var isActivityFound = false

        if (services[0].processName
                .equals(
                    packageName,
                    ignoreCase = true
                ) && services[0].importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
        ) {
            isActivityFound = true
        }

        return isActivityFound
    }
}
