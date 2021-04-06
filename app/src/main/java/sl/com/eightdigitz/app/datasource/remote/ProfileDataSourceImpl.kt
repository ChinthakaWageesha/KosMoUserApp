package sl.com.eightdigitz.app.datasource.remote

import android.content.SharedPreferences
import io.reactivex.Single
import okhttp3.MultipartBody
import sl.com.eightdigitz.app.data.datasource.ProfileDataSource
import sl.com.eightdigitz.client.apiSupports.models.User
import sl.com.eightdigitz.client.apiSupports.requests.AddUserPreferenceRequest
import sl.com.eightdigitz.client.apiSupports.requests.UpdateUserRequest
import sl.com.eightdigitz.client.apis.AuthApi
import sl.com.eightdigitz.client.apis.MultimediaApi
import sl.com.eightdigitz.client.apis.PreferencesApi
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.*
import sl.com.eightdigitz.core.model.mapToDomain
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.extensions.getIdToken
import sl.com.eightdigitz.presentation.extensions.setAuthReference
import sl.com.eightdigitz.presentation.extensions.setValue
import sl.com.eightdigitz.presentation.extensions.toJsonString

class ProfileDataSourceImpl(
    private val mSharedPreferences: SharedPreferences,
    private val authApi: AuthApi,
    private val multimediaApi: MultimediaApi,
    private val preferencesApi: PreferencesApi
) : ProfileDataSource {

    override fun uploadAvatar(image: MultipartBody.Part): Single<String> =
        multimediaApi.uploadAvatar(
            bigData = image
        ).map {
            it.data
        }

    override fun updateUser(request: UpdateUserRequest): Single<DUser> =
        authApi.updateUser(
            idToken = mSharedPreferences.getIdToken()!!,
            updateUserRequest = request
        ).map {
            saveUser(it.data!!)
            it.data?.mapToDomain()
        }

    override fun getPreferences(): Single<ListResponse<DPreference>> =
        preferencesApi.getPreferences(
            language = "en"
        ).map {
            it.mapToDomain()
        }


    override fun setPreference(addUserPreferenceRequest: AddUserPreferenceRequest): Single<DUser> =
        preferencesApi.addUserPreference(
            idToken = mSharedPreferences.getIdToken()!!,
            addUserPreferenceRequest = addUserPreferenceRequest
        ).map {
            saveUser(it.data!!)
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
            it.mapToDomain()
        }

    private fun saveUser(user: User){
        mSharedPreferences.setAuthReference(user.authReference)
        mSharedPreferences.setValue(Constant.PREF_USER, user.mapToDomain().toJsonString())
    }
}