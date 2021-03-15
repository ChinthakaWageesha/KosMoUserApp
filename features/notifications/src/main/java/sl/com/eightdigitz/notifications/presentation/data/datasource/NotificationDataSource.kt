package sl.com.eightdigitz.notifications.presentation.data.datasource

import io.reactivex.Single
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DPushNotification

interface NotificationDataSource {

    fun getNotificationsList(): Single<ListResponse<DPushNotification>>

}