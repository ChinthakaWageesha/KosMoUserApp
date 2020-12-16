package sl.com.eightdigitz.app.presentation.profile

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sl.com.eightdigitz.app.domain.usecase.ProfileUseCase
import sl.com.eightdigitz.core.domain.model.mapToDomain
import sl.com.eightdigitz.core.domain.model.mapToPresentation
import sl.com.eightdigitz.core.presentation.model.PUser
import sl.com.eightdigitz.network.ErrorHandler
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.setError
import sl.com.eightdigitz.presentation.setLoading
import sl.com.eightdigitz.presentation.setSuccess
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class ProfileViewModel constructor(
    private val profileUseCase: ProfileUseCase
) : ViewModel() {

    val liveData = MutableLiveData<Resource<PUser>>()

    private val compositeDisposable = CompositeDisposable()

    fun updateProfile(pUser: PUser) {
        liveData.setLoading()
        compositeDisposable.add(profileUseCase.updateProfile(pUser.mapToDomain())
            .subscribeOn(Schedulers.io())
            .map { it.mapToPresentation() }
            .subscribe(
                {
                    liveData.setSuccess(it, "Profile updated successfully.")
                },
                {
                    liveData.setError(ErrorHandler.getApiErrorMessage(it))
                })
        )
    }

    fun profilePictureUpdate(imageFile: File) {
        liveData.setLoading()
        val requestFile = RequestBody.create((MultipartBody.FORM), imageFile)
        val requestBody = MultipartBody.Part.createFormData("image", imageFile.name, requestFile)

        compositeDisposable.add(
            profileUseCase.updateProfilePic(requestBody)
                .subscribeOn(Schedulers.io())
                .map { it.mapToPresentation() }
                .subscribe(
                    {
                        liveData.setSuccess(it, "Profile picture updated successfully")
                    },
                    {
                        liveData.setError(ErrorHandler.getApiErrorMessage(it))
                    }
                )
        )
    }

    fun getUserData() {
        val user = profileUseCase.getUser()?.mapToPresentation()
        if (user != null) {
            liveData.setSuccess(user, null)
        }

        compositeDisposable.add(profileUseCase.getProfile()
            .subscribeOn(Schedulers.io())
            .map { it.mapToPresentation() }
            .subscribe(
                {
                    liveData.setSuccess(it, null)
                },
                {
                    liveData.setError(ErrorHandler.getApiErrorMessage(it))
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
