package sl.com.eightdigitz.navigation.features

import androidx.fragment.app.Fragment
import sl.com.eightdigitz.navigation.loadFragmentOrNull

object NotificationsNavigation : DynamicFeature<Fragment> {

    private const val NOTIFICATIONS =
        "sl.com.eightdigitz.notifications.presentation.notification.NotificationsFragment"
    override val dynamicStart: Fragment?
        get() = NOTIFICATIONS.loadFragmentOrNull()
}
