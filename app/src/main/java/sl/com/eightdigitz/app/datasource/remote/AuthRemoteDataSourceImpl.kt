package sl.com.eightdigitz.app.datasource.remote

import sl.com.eightdigitz.app.data.datasource.AuthDataSource
import sl.com.eightdigitz.app.datasource.model.mapToDataSource
import sl.com.eightdigitz.client.apis.AuthApi
import sl.com.eightdigitz.models.Success
import io.reactivex.Single

class AuthRemoteDataSourceImpl(
    private val authApi: AuthApi
) : AuthDataSource {

    override fun logoutUser(): Single<Success> =
        authApi.authGetLogout().map { it.mapToDataSource() }

    override fun passwordChange(oldPassworrd: String, password: String): Single<Success> =
        authApi.authPostUpdatePassword(password, oldPassworrd, password)
            .map { it.mapToDataSource() }
}