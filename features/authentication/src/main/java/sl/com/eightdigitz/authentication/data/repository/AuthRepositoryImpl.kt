package sl.com.eightdigitz.authentication.data.repository

import android.content.SharedPreferences
import sl.com.eightdigitz.authentication.data.datasource.AuthRemoteDataSource
import sl.com.eightdigitz.authentication.datasource.model.Success
import sl.com.eightdigitz.authentication.domain.repository.AuthRepository
import sl.com.eightdigitz.core.domain.model.DUser
import sl.com.eightdigitz.presentation.extensions.cleaAll
import io.reactivex.Single

class AuthRepositoryImpl constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {

    override fun getByToken(token: String?) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    //    Here you may get cache liveData data rather accessing remote data
//    TODO change liveData model
    override fun get(user: DUser): Single<DUser> =
        authRemoteDataSource.get(user)

    override fun createAccount(user: DUser): Single<DUser> =
        authRemoteDataSource.createAccount(user)

    override fun resetPassword(email: String): Single<Success> =
        authRemoteDataSource.resetPassword(email)

    override fun resetPassword(
        email: String,
        token: String,
        password: String,
        passwordConfirmation: String
    ): Single<Success> =
        authRemoteDataSource.resetPassword(email, token, password, passwordConfirmation)

    override fun logoutUser(): Single<Success> =
        authRemoteDataSource.logoutUser().doOnSuccess {
            sharedPreferences.cleaAll()
        }

    override fun passwordChange(oldPassword: String, password: String): Single<Success> =
        authRemoteDataSource.passwordChange(oldPassword, password)
}
