package sl.com.eightdigitz.authentication.datasource.remote

import android.content.SharedPreferences
import sl.com.eightdigitz.authentication.data.datasource.AuthDataSource
import sl.com.eightdigitz.client.apis.AuthApi
import sl.com.eightdigitz.client.models.User
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.mapToDomain
import sl.com.eightdigitz.network.SupportInterceptor
import sl.com.eightdigitz.presentation.Constant
import io.reactivex.Single
import sl.com.eightdigitz.core.model.domain.DOTP
import sl.com.eightdigitz.core.model.domain.DOTPToken
import sl.com.eightdigitz.presentation.extensions.*

class AuthDataSourceImpl constructor(
    private val authApi: AuthApi,
    private val mSharedPreferences: SharedPreferences
) : AuthDataSource {

    override fun getOTP(phoneNumber: String): Single<DOTP> =
        authApi.getOTP(
            clientId = Constant.CLIENT_ID,
            connection = "sms",
            send = "code",
            phoneNumber = phoneNumber
        ).map {
            it.mapToDomain()
        }

    override fun getOTPToken(phoneNumber: String, otp: String): Single<DOTPToken> =
        authApi.getOTPToken(
            clientId = Constant.CLIENT_ID,
            grantType = "http://auth0.com/oauth/grant-type/passwordless/otp",
            phoneNumber = phoneNumber,
            otp = otp,
            realm = "sms"
        ).map {
            saveTokens(it.mapToDomain())
            it.mapToDomain()
        }

    override fun getUserByRefToken(idToken: String): Single<DUser> =
        authApi.getUserByFefToken(idToken).map {
            it.payload?.mapToDomain()
        }

    override fun createAccount(dUser: DUser): Single<DUser> =
        authApi.authPostRegister(
            mSharedPreferences.getIdToken()!!,
            dUser.mobileNo!!,
            dUser.fullName!!,
            dUser.defaultLanguage!!,
            dUser.email!!,
            dUser.dob!!,
            dUser.role!!,
            dUser.profilePicture!!,
            dUser.profileVideo!!,
            dUser.profileBanner
        ).map {
            saveUser(it.data)
            it.data?.mapToDomain()
        }

    private fun saveTokens(otpToken: DOTPToken){
        mSharedPreferences.setAccessToken(otpToken.accessToken)
        mSharedPreferences.setIdToken(otpToken.idToken)
    }

    private fun saveUser(mUser: User?) {
        mSharedPreferences.setValue(Constant.PREF_USER, mUser?.mapToDomain()?.toJsonString())
    }
}
