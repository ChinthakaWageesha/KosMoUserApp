package sl.com.eightdigitz.authentication.domain.repository

import sl.com.eightdigitz.core.model.domain.DUser
import io.reactivex.Single
import sl.com.eightdigitz.core.model.domain.DAuth0

interface AuthRepository {

    fun getOTP(phoneNumber: String): Single<DAuth0>

    fun createAccount(dUser: DUser): Single<DUser>
}
