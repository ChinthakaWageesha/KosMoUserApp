package sl.com.eightdigitz.authentication.datasource.remote

import android.content.SharedPreferences
import sl.com.eightdigitz.authentication.data.datasource.AuthDataSource
import sl.com.eightdigitz.client.apis.AuthApi
import sl.com.eightdigitz.core.model.mapToDomain
import sl.com.eightdigitz.presentation.Constant
import io.reactivex.Single
import okhttp3.MultipartBody
import sl.com.eightdigitz.client.apiSupports.models.FirebaseToken
import sl.com.eightdigitz.client.apis.JoinContactApi
import sl.com.eightdigitz.client.apis.MultimediaApi
import sl.com.eightdigitz.client.apis.PreferencesApi
import sl.com.eightdigitz.client.apiSupports.models.User
import sl.com.eightdigitz.client.apiSupports.requests.AddUserPreferenceRequest
import sl.com.eightdigitz.client.apiSupports.requests.ContactUsRequest
import sl.com.eightdigitz.client.apiSupports.requests.JoinUsRequest
import sl.com.eightdigitz.client.apiSupports.requests.RegisterRequest
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.*
import sl.com.eightdigitz.presentation.extensions.*

class AuthDataSourceImpl constructor(
    private val mSharedPreferences: SharedPreferences,
    private val authApi: AuthApi,
    private val joinContactApi: JoinContactApi,
    private val multimediaApi: MultimediaApi,
    private val preferencesApi: PreferencesApi
) : AuthDataSource {

    override fun uploadAvatar(image: MultipartBody.Part): Single<String> =
        multimediaApi.uploadAvatar(
            bigData = image
        ).map {
            it.data
        }

    override fun contactSupport(contactUsRequest: ContactUsRequest): Single<DContactUs> =
        joinContactApi.contactSupport(contactUsRequest).map {
            it.data?.mapToDomain()
        }

    override fun joinUs(joinUsRequest: JoinUsRequest): Single<DJoinUs> =
        joinContactApi.joinUs(joinUsRequest).map {
            it.data?.mapToDomain()
        }

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

    override fun getUserByIDToken(idToken: String): Single<DUser> =
        authApi.getUserByIDToken(idToken).map {
            saveUser(it.data)
            it.data?.mapToDomain()
        }

    override fun getPreferences(): Single<ListResponse<DPreference>> =
        preferencesApi.getPreferences(
            language = "en"
        ).map {
            it.mapToDomain()
        }

    override fun registerFirebaseToken(request: FirebaseToken): Single<DFirebaseToken> =
        authApi.registerFirebaseToken(
            request = request
        ).map {
            it.data?.mapToDomain()
        }

    override fun createAccount(registerRequest: RegisterRequest): Single<DUser> =
        authApi.authPostRegister(
            idToken = mSharedPreferences.getIdToken()!!,
            registerRequest = registerRequest
        ).map {
            saveUser(it.data)
            it.data?.mapToDomain()
        }

    override fun addUserPreference(addUserPreferenceRequest: AddUserPreferenceRequest): Single<DUser> =
        preferencesApi.addUserPreference(
            idToken = mSharedPreferences.getIdToken()!!,
            addUserPreferenceRequest = addUserPreferenceRequest
        ).map {
            saveUser(it.data)
            it.data?.mapToDomain()
        }

    private fun saveTokens(otpToken: DOTPToken) {
        mSharedPreferences.setAccessToken(otpToken.accessToken)
        mSharedPreferences.setIdToken(otpToken.idToken)
    }

    private fun saveUser(mUser: User?) {
        mSharedPreferences.setAuthReference(mUser?.authReference)
        mSharedPreferences.setValue(Constant.PREF_USER, mUser?.mapToDomain()?.toJsonString())
    }
}
