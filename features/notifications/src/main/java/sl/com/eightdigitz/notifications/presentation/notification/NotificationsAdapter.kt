package sl.com.eightdigitz.notifications.presentation.notification

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sl.com.eightdigitz.notifications.R
import sl.com.eightdigitz.core.model.presentation.PNotification
import sl.com.eightdigitz.presentation.extensions.inflate
import kotlinx.android.synthetic.main.item_notification.view.*
import sl.com.eightdigitz.core.model.domain.DPushNotification
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.extensions.setRoundedImage

class NotificationsAdapter(
    private val notificationsList: MutableList<DPushNotification>,
    val onClickNotification: (DPushNotification) -> Unit
) : RecyclerView.Adapter<NotificationsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_notification,parent,false))
    }

    override fun getItemCount(): Int = notificationsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    fun addNotifications(list: MutableList<DPushNotification>){
        notificationsList.addAll(list)
        notifyDataSetChanged()
    }

    fun clear(){
        notificationsList.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(position: Int) {

            val notification = notificationsList[position]

            itemView.tv_notification_title.text = notification.notificationType
            itemView.tv_notification_date.text = notification.firebaseID

            itemView.iv_notification_image.setRoundedImage(
                url = Constant.MOVIE_IMAGE_URL_1,
                radius = 12
            )
        }
    }
}
