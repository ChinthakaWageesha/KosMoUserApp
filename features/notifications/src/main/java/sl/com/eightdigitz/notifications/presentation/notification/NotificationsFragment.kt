package sl.com.eightdigitz.notifications.presentation.notification

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import sl.com.eightdigitz.core.base.BaseFragment
import sl.com.eightdigitz.notifications.R
import sl.com.eightdigitz.core.model.presentation.PNotification
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_notifications.*
import sl.com.eightdigitz.notifications.presentation.di.injectFeature

class NotificationsFragment : BaseFragment() {

    private lateinit var tempNotificationList: MutableList<PNotification>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated() {
        init()
    }

    private fun init() {
        injectFeature()
        tempNotificationList = mutableListOf()
        setUpNotificationsAdapter()
        setSwipeToDelete()
    }

    private fun setUpNotificationsAdapter() {
        rv_notifications.adapter = NotificationsAdapter(tempNotificationList)
        rv_notifications.layoutManager =
            LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
    }

    private fun setSwipeToDelete(){
        val itemTouchHelper =
            ItemTouchHelper(SwipeToDelete(context, object : SwipeToDelete.Callback {
                override fun onSwipeRight(position: Int) {
                }

                override fun onSwipeLeft(position: Int) {
                    val deletedModel = tempNotificationList[position]
                    (rv_notifications.adapter as NotificationsAdapter).removeItem(position)

                    val snackbar = Snackbar.make(
                        activity!!.window.decorView.rootView,
                        getString(R.string.item_removed),
                        Snackbar.LENGTH_LONG
                    )
                    snackbar.setAction("UNDO") {
                        (rv_notifications.adapter as NotificationsAdapter).restoreItem(deletedModel, position)
                    }
                    snackbar.setActionTextColor(Color.YELLOW)
                    snackbar.show()
                }
            }))

        itemTouchHelper.attachToRecyclerView(rv_notifications)
    }

    companion object {
        fun newInstance(): Fragment {
            return NotificationsFragment()
        }

        const val TAG = "Notifications"
    }
}
