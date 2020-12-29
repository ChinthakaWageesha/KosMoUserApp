package sl.com.eightdigitz.app.datasource.remote

import sl.com.eightdigitz.app.data.datasource.AuthDataSource
import sl.com.eightdigitz.client.apis.AuthApi
import sl.com.eightdigitz.models.Success
import io.reactivex.Single
import sl.com.eightdigitz.core.model.mapToDataSource

class AuthRemoteDataSourceImpl(
    private val authApi: AuthApi
) : AuthDataSource {

    override fun logoutUser(): Single<Success> =
        authApi.authGetLogout().map { it.mapToDataSource() }

}