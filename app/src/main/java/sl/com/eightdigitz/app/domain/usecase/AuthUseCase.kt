package sl.com.eightdigitz.app.domain.usecase

import sl.com.eightdigitz.app.domain.repository.AuthRepository
import sl.com.eightdigitz.models.Success
import io.reactivex.Single

class AuthUseCase(private val authRepository: AuthRepository) {

    fun logoutUser(): Single<Success> = authRepository.logoutUser()

    fun passwordChange(oldPassworrd: String, password: String): Single<Success> =
        authRepository.passwordChange(oldPassworrd, password)
}