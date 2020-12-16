package sl.com.eightdigitz.authentication.domain.usecase

import sl.com.eightdigitz.authentication.datasource.model.Success
import sl.com.eightdigitz.authentication.domain.repository.AuthRepository
import sl.com.eightdigitz.core.domain.model.DUser
import io.reactivex.Single

class AuthUseCase constructor(private val authRepository: AuthRepository) {

    fun get(
        u: DUser
    ): Single<DUser> = authRepository.get(u)

    fun resetPassword(email: String): Single<Success> = authRepository.resetPassword(email)

    fun createAccount(u: DUser): Single<DUser> = authRepository.createAccount(u)

    fun logoutUser(): Single<Success> = authRepository.logoutUser()

    fun passwordChange(oldPassword: String, password: String): Single<Success> =
        authRepository.passwordChange(oldPassword, password)

    fun resetPassword(
        email: String,
        token: String,
        password: String,
        passwordConfirmation: String
    ) = authRepository.resetPassword(
        email, token, password, passwordConfirmation
    )
}
