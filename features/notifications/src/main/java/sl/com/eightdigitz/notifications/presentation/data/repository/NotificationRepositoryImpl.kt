package sl.com.eightdigitz.notifications.presentation.data.repository

import io.reactivex.Single
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DPushNotification
import sl.com.eightdigitz.notifications.presentation.data.datasource.NotificationDataSource
import sl.com.eightdigitz.notifications.presentation.domain.repository.NotificationRepository

class NotificationRepositoryImpl constructor(
    private val notificationDataSource: NotificationDataSource
) : NotificationRepository{

    override fun getNotificationsList(userId: String): Single<ListResponse<DPushNotification>> =
        notificationDataSource.getNotificationsList(userId)
}