package sl.com.eightdigitz.notifications.presentation.notification

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sl.com.eightdigitz.core.base.BaseFragment
import sl.com.eightdigitz.notifications.R
import sl.com.eightdigitz.notifications.presentation.models.PNotification
import com.google.android.material.snackbar.Snackbar

class NotificationsFragment : BaseFragment() {

    private lateinit var mAdapter: NotificationsAdapter
    private lateinit var mLayoutManager: LinearLayoutManager
    private var rvNotifications: RecyclerView? = null
    private lateinit var tempNotificationList: MutableList<PNotification>
    private val p = Paint()
    override fun onViewCreated() {
        val itemTouchHelper =
            ItemTouchHelper(SwipeToDelete(context, object : SwipeToDelete.Callback {
                override fun onSwipeRight(position: Int) {
                }

                override fun onSwipeLeft(position: Int) {
                    val deletedModel = tempNotificationList!![position]
                    mAdapter!!.removeItem(position)

                    val snackbar = Snackbar.make(
                        activity!!.window.decorView.rootView,
                        getString(R.string.item_removed),
                        Snackbar.LENGTH_LONG
                    )
                    snackbar.setAction("UNDO") {
                        // undo is selected, restore the deleted item
                        mAdapter!!.restoreItem(deletedModel, position)
                    }
                    snackbar.setActionTextColor(Color.YELLOW)
                    snackbar.show()
                }
            }))

        itemTouchHelper.attachToRecyclerView(rvNotifications)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notifications, container, false)
        rvNotifications = view.findViewById(R.id.rv_notifications)
        tempNotificationList = mutableListOf()
        setUpNotificationsAdapter()
        return view
    }

    private fun setUpNotificationsAdapter() {
        mLayoutManager = LinearLayoutManager(context)
        rvNotifications!!.layoutManager = mLayoutManager

        for (i in 0..10) {
            tempNotificationList.add(PNotification("Test $i", "Notification Body $i"))
        }
        mAdapter = NotificationsAdapter(tempNotificationList)
        rvNotifications!!.adapter = mAdapter
    }
}
