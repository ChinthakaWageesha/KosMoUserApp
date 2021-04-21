package sl.com.eightdigitz.notifications.presentation.notification

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import sl.com.eightdigitz.core.model.domain.DPushNotification
import sl.com.eightdigitz.core.model.mapToPresentation
import sl.com.eightdigitz.network.ErrorHandler
import sl.com.eightdigitz.notifications.presentation.domain.usecase.NotificationUseCase
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.setError
import sl.com.eightdigitz.presentation.setLoading
import sl.com.eightdigitz.presentation.setSuccess

class NotificationViewModel(
    private val notificationUseCase: NotificationUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val notificationLiveData = MutableLiveData<Resource<List<DPushNotification>>>()

    fun getNotifications(userId: String) {
        notificationLiveData.setLoading()
        compositeDisposable.add(
            notificationUseCase.getNotifications(userId)
                .subscribeOn(Schedulers.io())
                .map { it.data }
                .subscribe({
                    notificationLiveData.setSuccess(it!!, null)
                }, {
                    notificationLiveData.setError(
                        ErrorHandler.getApiErrorMessage(it).mapToPresentation()
                    )
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}