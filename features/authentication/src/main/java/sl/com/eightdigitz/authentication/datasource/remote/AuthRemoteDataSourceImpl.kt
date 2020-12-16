package sl.com.eightdigitz.authentication.datasource.remote

import android.content.SharedPreferences
import sl.com.eightdigitz.authentication.data.datasource.AuthRemoteDataSource
import sl.com.eightdigitz.authentication.datasource.model.Success
import sl.com.eightdigitz.authentication.datasource.model.mapToDataSource
import sl.com.eightdigitz.client.apis.AuthApi
import sl.com.eightdigitz.client.apis.ForgotPasswordApi
import sl.com.eightdigitz.client.apis.ResetPasswordApi
import sl.com.eightdigitz.client.models.User
import sl.com.eightdigitz.core.domain.model.DUser
import sl.com.eightdigitz.core.domain.model.mapToDomain
import sl.com.eightdigitz.network.SupportInterceptor
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.extensions.setToken
import sl.com.eightdigitz.presentation.extensions.setValue
import sl.com.eightdigitz.presentation.extensions.toJsonString
import io.reactivex.Single

class AuthRemoteDataSourceImpl constructor(
    private val authApi: AuthApi,
    private val forgotPasswordApi: ForgotPasswordApi,
    private val resetPasswordApi: ResetPasswordApi,
    private val mSharedPreferences: SharedPreferences,
    private val mSupportInteceptor: SupportInterceptor
) : AuthRemoteDataSource {

    override fun get(
        dUser: DUser
    ): Single<DUser> =
        authApi.authPostLogin(
            dUser.deviceId!!,
            dUser.deviceType!!,
            dUser.email!!,
            dUser.password!!,
            dUser.devicePushToken
        )
            .map {
                saveData(it.payload)
                it.payload?.mapToDomain()
            }

    override fun resetPassword(email: String): Single<Success> =
        forgotPasswordApi.forgotpasswordPostResetPassword(email)
            .map { it.mapToDataSource() }

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

    override fun logoutUser(): Single<Success> =
        authApi.authGetLogout().map { it.mapToDataSource() }

    override fun passwordChange(oldPassword: String, password: String): Single<Success> =
        authApi.authPostUpdatePassword(password, oldPassword, password)
            .map { it.mapToDataSource() }

    private fun saveData(mUser: User?) {
        mSupportInteceptor.accessToken = mUser?.accessToken
        mSharedPreferences.setToken(mUser?.accessToken)
        mSharedPreferences.setValue(Constant.PREF_USER, mUser?.mapToDomain()?.toJsonString())
    }

    override fun resetPassword(
        email: String,
        token: String,
        password: String,
        passwordConfirmation: String
    ): Single<Success> = resetPasswordApi.resetpasswordPostResetPassword(
        email,
        token,
        password,
        passwordConfirmation
    ).map { it.mapToDataSource() }
}
