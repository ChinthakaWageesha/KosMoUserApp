package sl.com.eightdigitz.notifications.presentation.notification

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sl.com.eightdigitz.notifications.R
import sl.com.eightdigitz.notifications.presentation.models.PNotification
import sl.com.eightdigitz.presentation.extensions.inflate
import kotlinx.android.synthetic.main.item_notification.view.*

class NotificationsAdapter(var notificationsList: MutableList<PNotification>) :
    RecyclerView.Adapter<NotificationsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_notification)
        return ViewHolder(view)
    }

    fun removeItem(position: Int) {
        notificationsList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, notificationsList.size)
    }

    fun restoreItem(notifications: PNotification, position: Int) {
        notificationsList.add(position, notifications)
        notifyItemInserted(position)
    }

    override fun getItemCount(): Int = notificationsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
        holder.itemView.tv_notification_title.text = notificationsList[position].title
        holder.itemView.tv_notification_body.text = notificationsList[position].body
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(position: Int) {
        }
    }
}
