package sl.com.eightdigitz.authentication.data.datasource

import sl.com.eightdigitz.core.model.domain.DUser
import io.reactivex.Single
import sl.com.eightdigitz.core.model.domain.DOTP
import sl.com.eightdigitz.core.model.domain.DOTPToken

interface AuthDataSource {

    fun getOTP(phoneNumber: String): Single<DOTP>

    fun getOTPToken(phoneNumber: String, otp: String): Single<DOTPToken>

    fun getUserByRefToken(idToken: String) : Single<DUser>

    fun createAccount(dUser: DUser): Single<DUser>

}
