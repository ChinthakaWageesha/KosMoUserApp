package sl.com.eightdigitz.authentication.data.datasource

import sl.com.eightdigitz.authentication.datasource.model.Success
import sl.com.eightdigitz.core.model.domain.DUser
import io.reactivex.Single
import sl.com.eightdigitz.core.model.domain.DAuth0

interface AuthDataSource {

    fun getOTP(phoneNumber: String): Single<DAuth0>

    fun createAccount(dUser: DUser): Single<DUser>

}
