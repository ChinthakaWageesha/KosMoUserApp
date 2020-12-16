package sl.com.eightdigitz.authentication.domain.repository

import sl.com.eightdigitz.authentication.datasource.model.Success
import sl.com.eightdigitz.core.domain.model.DUser
import io.reactivex.Single

interface AuthRepository {

    fun get(user: DUser): Single<DUser>

    fun getByToken(token: String?)

    fun resetPassword(email: String): Single<Success>

    fun createAccount(user: DUser): Single<DUser>

    fun logoutUser(): Single<Success>

    fun passwordChange(oldPassword: String, password: String): Single<Success>

    fun resetPassword(
        email: String,
        token: String,
        password: String,
        passwordConfirmation: String
    ): Single<Success>
}
