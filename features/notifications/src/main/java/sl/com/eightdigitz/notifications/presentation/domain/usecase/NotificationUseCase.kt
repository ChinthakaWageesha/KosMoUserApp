package sl.com.eightdigitz.notifications.presentation.domain.usecase

import sl.com.eightdigitz.notifications.presentation.domain.repository.NotificationRepository

class NotificationUseCase(private val notificationRepository: NotificationRepository) {

    fun getNotifications(userId: String) = notificationRepository.getNotificationsList(userId)
}
