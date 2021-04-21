package sl.com.eightdigitz.notifications.presentation.domain.repository

import io.reactivex.Single
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DPushNotification

interface NotificationRepository {

    fun getNotificationsList(userId: String): Single<ListResponse<DPushNotification>>

}