package sl.com.eightdigitz.authentication.data.datasource

import sl.com.eightdigitz.authentication.datasource.model.Success
import sl.com.eightdigitz.core.domain.model.DUser
import io.reactivex.Single

interface AuthRemoteDataSource {

    fun get(dUser: DUser): Single<DUser>

    fun resetPassword(email: String): Single<Success>

    fun createAccount(dUser: DUser): Single<DUser>

    fun logoutUser(): Single<Success>

    fun passwordChange(oldPassword: String, password: String): Single<Success>

    fun resetPassword(
        email: String,
        token: String,
        password: String,
        passwordConfirmation: String
    ): Single<Success>
}
