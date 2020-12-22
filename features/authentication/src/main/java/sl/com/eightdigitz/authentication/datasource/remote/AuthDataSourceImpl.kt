package sl.com.eightdigitz.authentication.datasource.remote

import android.content.SharedPreferences
import sl.com.eightdigitz.authentication.data.datasource.AuthDataSource
import sl.com.eightdigitz.authentication.datasource.model.Success
import sl.com.eightdigitz.authentication.datasource.model.mapToDataSource
import sl.com.eightdigitz.client.apis.AuthApi
import sl.com.eightdigitz.client.apis.ForgotPasswordApi
import sl.com.eightdigitz.client.apis.ResetPasswordApi
import sl.com.eightdigitz.client.models.User
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.mapToDomain
import sl.com.eightdigitz.network.SupportInterceptor
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.extensions.setToken
import sl.com.eightdigitz.presentation.extensions.setValue
import sl.com.eightdigitz.presentation.extensions.toJsonString
import io.reactivex.Single
import sl.com.eightdigitz.core.model.domain.DAuth0

class AuthDataSourceImpl constructor(
    private val authApi: AuthApi,
    private val mSharedPreferences: SharedPreferences,
    private val mSupportInterceptor: SupportInterceptor
) : AuthDataSource {

    override fun getOTP(phoneNumber: String): Single<DAuth0> =
        authApi.getOTP(
            clientId = Constant.CLIENT_ID,
            connection = "sms",
            send = "code",
            phoneNumber = phoneNumber
        ).map {
            it.mapToDomain()
        }

    override fun createAccount(dUser: DUser): Single<DUser> =
        authApi.authPostRegister(
            dUser.deviceId!!,
            dUser.deviceType!!,
            dUser.name!!,
            dUser.email!!,
            dUser.password!!,
            dUser.password!!,
            dUser.userType!!,
            dUser.devicePushToken
        ).map {
            saveData(it.payload)
            it.payload?.mapToDomain()
        }

    private fun saveData(mUser: User?) {
        mSupportInterceptor.accessToken = mUser?.accessToken
        mSharedPreferences.setToken(mUser?.accessToken)
        mSharedPreferences.setValue(Constant.PREF_USER, mUser?.mapToDomain()?.toJsonString())
    }
}
