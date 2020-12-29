package sl.com.eightdigitz.app.domain.repository

import sl.com.eightdigitz.models.Success
import io.reactivex.Single

interface AuthRepository {

    fun logoutUser(): Single<Success>
}