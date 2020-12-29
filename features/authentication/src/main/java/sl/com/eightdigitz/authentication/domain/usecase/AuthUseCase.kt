package sl.com.eightdigitz.authentication.domain.usecase

import sl.com.eightdigitz.authentication.domain.repository.AuthRepository
import sl.com.eightdigitz.core.model.domain.DUser
import io.reactivex.Single
import sl.com.eightdigitz.core.model.domain.DOTP

class AuthUseCase constructor(private val authRepository: AuthRepository) {

    fun getOTP(phoneNumber: String): Single<DOTP> = authRepository.getOTP(phoneNumber)

    fun getOTPToken(phoneNumber: String, otp: String) = authRepository.getOTPToken(phoneNumber, otp)

    fun getUserByRefToken(idToken: String) = authRepository.getUserByRefToken(idToken)

    fun createAccount(u: DUser): Single<DUser> = authRepository.createAccount(u)
}
