package sl.com.eightdigitz.authentication.presentation.contact

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody
import sl.com.eightdigitz.authentication.domain.usecase.AuthUseCase
import sl.com.eightdigitz.client.apiSupports.requests.ContactUsRequest
import sl.com.eightdigitz.client.apiSupports.requests.JoinUsRequest
import sl.com.eightdigitz.core.model.domain.DContactUs
import sl.com.eightdigitz.core.model.domain.DJoinUs
import sl.com.eightdigitz.network.ErrorHandler
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.setError
import sl.com.eightdigitz.presentation.setLoading
import sl.com.eightdigitz.presentation.setSuccess
import java.io.File

data class JoinContactViewModel constructor(private val authUseCase: AuthUseCase) : ViewModel() {

    val liveDataJoinUs = MutableLiveData<Resource<DJoinUs>>()
    val liveDataContact = MutableLiveData<Resource<DContactUs>>()
    val liveDataContactImage = MutableLiveData<Resource<String>>()
    private val compositeDisposable = CompositeDisposable()

    fun uploadAvatar(imageFile: File) {
        liveDataContactImage.setLoading()
        val requestFile = RequestBody.create((MultipartBody.FORM), imageFile)
        val requestBody = MultipartBody.Part.createFormData("file", imageFile.name, requestFile)

        compositeDisposable.add(
            authUseCase.uploadAvatar(requestBody)
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe(
                    {
                        liveDataContactImage.setSuccess(it)
                    },
                    {
                        liveDataContactImage.setError(ErrorHandler.getApiErrorMessage(it))
                    }
                )
        )
    }

    fun joinUs(joinUsRequest: JoinUsRequest) =
        compositeDisposable.add(
            authUseCase.joinUs(joinUsRequest)
                .doOnSubscribe {
                    liveDataJoinUs.setLoading()
                }
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe({
                    liveDataJoinUs.setSuccess(it, null)
                }, {
                    liveDataJoinUs.setError(it.message)
                })
        )

    fun contactSupport(contactUsRequest: ContactUsRequest) =
        compositeDisposable.add(
            authUseCase.contactUs(contactUsRequest)
                .doOnSubscribe {
                    liveDataContact.setLoading()
                }
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe({
                    liveDataContact.setSuccess(it, null)
                }, {
                    liveDataContact.setError(it.message)
                })
        )

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
