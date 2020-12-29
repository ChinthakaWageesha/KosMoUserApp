package sl.com.eightdigitz.authentication.data.repository

import sl.com.eightdigitz.authentication.data.datasource.AuthDataSource
import sl.com.eightdigitz.authentication.domain.repository.AuthRepository
import sl.com.eightdigitz.core.model.domain.DUser
import io.reactivex.Single
import sl.com.eightdigitz.core.model.domain.DOTP
import sl.com.eightdigitz.core.model.domain.DOTPToken

class AuthRepositoryImpl constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {

    override fun getOTP(phoneNumber: String): Single<DOTP> =
        authDataSource.getOTP(phoneNumber)

    override fun getOTPToken(phoneNumber: String, otp: String): Single<DOTPToken> =
        authDataSource.getOTPToken(phoneNumber, otp)

    override fun getUserByRefToken(idToken: String): Single<DUser> =
        authDataSource.getUserByRefToken(idToken)

    override fun createAccount(dUser: DUser): Single<DUser> =
        authDataSource.createAccount(dUser)

}
