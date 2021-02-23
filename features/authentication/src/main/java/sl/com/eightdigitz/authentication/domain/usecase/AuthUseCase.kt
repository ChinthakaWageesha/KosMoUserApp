package sl.com.eightdigitz.authentication.domain.usecase

import sl.com.eightdigitz.authentication.domain.repository.AuthRepository
import sl.com.eightdigitz.core.model.domain.DUser
import io.reactivex.Single
import okhttp3.MultipartBody
import sl.com.eightdigitz.client.models.ContactUsRequest
import sl.com.eightdigitz.client.models.JoinUsRequest
import sl.com.eightdigitz.client.models.RegisterRequest
import sl.com.eightdigitz.core.model.domain.DOTP

class AuthUseCase constructor(private val authRepository: AuthRepository) {

    fun uploadAvatar(image: MultipartBody.Part) = authRepository.uploadAvatar(image)

    fun contactUs(contactUsRequest: ContactUsRequest) =
        authRepository.contactSupport(contactUsRequest)

    fun joinUs(joinUsRequest: JoinUsRequest) = authRepository.joinUs(joinUsRequest)

    fun getOTP(phoneNumber: String): Single<DOTP> = authRepository.getOTP(phoneNumber)

    fun getOTPToken(phoneNumber: String, otp: String) = authRepository.getOTPToken(phoneNumber, otp)

    fun getUserByIDToken(idToken: String) = authRepository.getUserByIDToken(idToken)

    fun getPreferences() = authRepository.getPreferences()

    fun createAccount(request: RegisterRequest): Single<DUser> =
        authRepository.createAccount(request)
}
