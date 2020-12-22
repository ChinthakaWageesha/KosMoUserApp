package sl.com.eightdigitz.authentication.domain.usecase

import sl.com.eightdigitz.authentication.domain.repository.AuthRepository
import sl.com.eightdigitz.core.model.domain.DUser
import io.reactivex.Single
import sl.com.eightdigitz.core.model.domain.DAuth0

class AuthUseCase constructor(private val authRepository: AuthRepository) {

    fun getOTP(phoneNumber: String): Single<DAuth0> = authRepository.getOTP(phoneNumber)

    fun createAccount(u: DUser): Single<DUser> = authRepository.createAccount(u)
}
