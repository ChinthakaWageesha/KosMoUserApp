package sl.com.eightdigitz.notifications.presentation.datasource.remote

import io.reactivex.Single
import sl.com.eightdigitz.client.apis.NotificationApi
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DPushNotification
import sl.com.eightdigitz.core.model.mapToDomain
import sl.com.eightdigitz.notifications.presentation.data.datasource.NotificationDataSource

class NotificationDataSourceImpl(
    private val notificationApi: NotificationApi
) : NotificationDataSource {

    override fun getNotificationsList(): Single<ListResponse<DPushNotification>> =
        notificationApi.notificationsGet().map {
            it.mapToDomain()
        }

}