package sl.com.eightdigitz.app.data.datasource

import sl.com.eightdigitz.models.Success
import io.reactivex.Single

interface AuthDataSource {

    fun logoutUser(): Single<Success>

    fun passwordChange(oldPassworrd: String, password: String): Single<Success>
}