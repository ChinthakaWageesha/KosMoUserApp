package sl.com.eightdigitz.app.presentation.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody
import sl.com.eightdigitz.app.domain.usecase.ProfileUseCase
import sl.com.eightdigitz.client.apiSupports.requests.UpdateUserRequest
import sl.com.eightdigitz.core.model.domain.DOTP
import sl.com.eightdigitz.core.model.domain.DOTPToken
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.mapToPresentation
import sl.com.eightdigitz.country_picker.domain.model.DCountry
import sl.com.eightdigitz.network.ErrorHandler
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.setError
import sl.com.eightdigitz.presentation.setLoading
import sl.com.eightdigitz.presentation.setSuccess
import java.io.File

class ProfileViewModel(
    private val profileUseCase: ProfileUseCase
) : ViewModel() {

    val liveDataUploadAvatar = MutableLiveData<Resource<String>>()
    val liveDataUpdateUser = MutableLiveData<Resource<DUser>>()
    val liveDataOTP = MutableLiveData<Resource<DOTP>>()
    val liveDataOTPToken = MutableLiveData<Resource<DOTPToken>>()

    private val compositeDisposable = CompositeDisposable()

    fun uploadAvatar(imageFile: File) {
        liveDataUploadAvatar.setLoading()
        val requestFile = RequestBody.create((MultipartBody.FORM), imageFile)
        val requestBody = MultipartBody.Part.createFormData("file", imageFile.name, requestFile)

        compositeDisposable.add(
            profileUseCase.uploadAvatar(requestBody)
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe(
                    {
                        liveDataUploadAvatar.setSuccess(it)
                    },
                    {
                        liveDataUploadAvatar.setError(
                            ErrorHandler.getApiErrorMessage(it).mapToPresentation()
                        )
                    }
                )
        )
    }

    fun updateUser(request: UpdateUserRequest) {
        liveDataUpdateUser.setLoading()
        compositeDisposable.add(
            profileUseCase.updateUser(request)
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe({
                    liveDataUpdateUser.setSuccess(it, null)
                }, {
                    liveDataUpdateUser.setError(
                        ErrorHandler.getApiErrorMessage(it).mapToPresentation()
                    )
                })
        )
    }

    fun getOTP(phoneNumber: String) {
        liveDataOTP.setLoading()
        compositeDisposable.add(
            profileUseCase.getOTP(phoneNumber)
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe({
                    liveDataOTP.setSuccess(it, null)
                }, {
                    liveDataOTP.setError(ErrorHandler.getApiErrorMessage(it).mapToPresentation())
                })
        )
    }

    fun getOTPToken(phoneNumber: String, otp: String) {
        liveDataOTPToken.setLoading()
        compositeDisposable.add(
            profileUseCase.getOTPToken(phoneNumber, otp)
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe({
                    liveDataOTPToken.setSuccess(it, null)
                }, {
                    liveDataOTPToken.setError(ErrorHandler.getApiErrorMessage(it).mapToPresentation())
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}