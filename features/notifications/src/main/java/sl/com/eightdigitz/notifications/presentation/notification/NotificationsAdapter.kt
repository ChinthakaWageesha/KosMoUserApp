package sl.com.eightdigitz.notifications.presentation.notification

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sl.com.eightdigitz.notifications.R
import sl.com.eightdigitz.core.model.presentation.PNotification
import sl.com.eightdigitz.presentation.extensions.inflate
import kotlinx.android.synthetic.main.item_notification.view.*

class NotificationsAdapter(
    var notificationsList: MutableList<PNotification>
) : RecyclerView.Adapter<NotificationsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_notification)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.run {
        onBind(position)
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

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(position: Int) {
        }
    }
}
