package sl.com.eightdigitz.app.domain.usecase

import io.reactivex.Single
import okhttp3.MultipartBody
import sl.com.eightdigitz.app.domain.repository.ProfileRepository
import sl.com.eightdigitz.client.apiSupports.requests.AddUserPreferenceRequest
import sl.com.eightdigitz.client.apiSupports.requests.UpdateUserRequest
import sl.com.eightdigitz.core.model.domain.DOTP

class ProfileUseCase(private val profileRepository: ProfileRepository) {

    fun uploadAvatar(image: MultipartBody.Part) = profileRepository.uploadAvatar(image)

    fun updateUser(request: UpdateUserRequest) = profileRepository.updateUser(request)

    fun getPreferences() = profileRepository.getPreferences()

    fun setPreferences(request: AddUserPreferenceRequest) = profileRepository.setPreference(request)

    fun getOTP(phoneNumber: String): Single<DOTP> = profileRepository.getOTP(phoneNumber)

    fun getOTPToken(phoneNumber: String, otp: String) = profileRepository.getOTPToken(phoneNumber, otp)

}