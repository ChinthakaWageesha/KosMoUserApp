package sl.com.eightdigitz.app.data.repository

import android.content.SharedPreferences
import sl.com.eightdigitz.app.data.datasource.AuthDataSource
import sl.com.eightdigitz.app.domain.repository.AuthRepository
import sl.com.eightdigitz.models.Success
import sl.com.eightdigitz.presentation.extensions.cleaAll
import io.reactivex.Single

class AuthRepositoryImpl constructor(
    private val authDataSource: AuthDataSource,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {

    override fun logoutUser(): Single<Success> =
        authDataSource.logoutUser().doOnSuccess {
            sharedPreferences.cleaAll()
        }

    override fun passwordChange(oldPassworrd: String, password: String): Single<Success> =
        authDataSource.passwordChange(oldPassworrd, password)
}