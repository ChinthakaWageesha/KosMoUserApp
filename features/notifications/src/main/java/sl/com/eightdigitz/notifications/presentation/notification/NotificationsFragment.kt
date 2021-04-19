package sl.com.eightdigitz.notifications.presentation.notification

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import sl.com.eightdigitz.core.base.BaseFragment
import sl.com.eightdigitz.notifications.R
import kotlinx.android.synthetic.main.fragment_notifications.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.core.model.domain.DPushNotification
import sl.com.eightdigitz.notifications.presentation.di.injectFeature
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
import sl.com.eightdigitz.presentation.extensions.setEmptyView
import sl.com.eightdigitz.presentation.extensions.showAlert
import sl.com.eightdigitz.presentation.extensions.showToast
import sl.com.eightdigitz.presentation.extensions.withNetwork

class NotificationsFragment : BaseFragment(), (DPushNotification) -> Unit {

    private val vmNotifications by viewModel<NotificationViewModel>()

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
        setUpNotificationsAdapter()
        getNotifications()
        vmNotifications.notificationLiveData.observe(this, Observer { observerGetNotifications(it) })
    }

    @SuppressLint("MissingPermission")
    private fun getNotifications() {
        activity?.withNetwork({
            vmNotifications.getNotifications()
        }, {
            showAlert(message = Msg.INTERNET_ISSUE)
        })
    }

    private fun setUpNotificationsAdapter() {
        rv_notifications.adapter = NotificationsAdapter(mutableListOf(), this)
        rv_notifications.layoutManager =
            LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
    }

    private fun observerGetNotifications(resource: Resource<List<DPushNotification>>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    (rv_notifications.adapter as NotificationsAdapter).clear()
                    rv_notifications.setEmptyView(tv_no_notifications, it.data?.size!!)
                    (rv_notifications.adapter as NotificationsAdapter).addNotifications(it.data!!.toMutableList())
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.error.toString().showToast(context!!)
                }

            }
        }
    }

    companion object {
        fun newInstance(): Fragment {
            return NotificationsFragment()
        }

        const val TAG = "Notifications"
    }

    override fun invoke(notification: DPushNotification) {
        notification.firebaseID
    }
}
