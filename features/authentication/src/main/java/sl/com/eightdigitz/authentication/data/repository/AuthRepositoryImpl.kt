package sl.com.eightdigitz.authentication.data.repository

import sl.com.eightdigitz.authentication.data.datasource.AuthDataSource
import sl.com.eightdigitz.authentication.domain.repository.AuthRepository
import io.reactivex.Single
import okhttp3.MultipartBody
import sl.com.eightdigitz.client.apiSupports.models.FirebaseToken
import sl.com.eightdigitz.client.apiSupports.requests.AddUserPreferenceRequest
import sl.com.eightdigitz.client.apiSupports.requests.ContactUsRequest
import sl.com.eightdigitz.client.apiSupports.requests.JoinUsRequest
import sl.com.eightdigitz.client.apiSupports.requests.RegisterRequest
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.*
import sl.com.eightdigitz.models.Success

class AuthRepositoryImpl constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {

    override fun uploadAvatar(image: MultipartBody.Part): Single<String> =
        authDataSource.uploadAvatar(image)

    override fun contactSupport(contactUsRequest: ContactUsRequest): Single<DContactUs> =
        authDataSource.contactSupport(contactUsRequest)

    override fun joinUs(joinUsRequest: JoinUsRequest): Single<DJoinUs> =
        authDataSource.joinUs(joinUsRequest)

    override fun getOTP(phoneNumber: String): Single<DOTP> =
        authDataSource.getOTP(phoneNumber)

    override fun getOTPToken(phoneNumber: String, otp: String): Single<DOTPToken> =
        authDataSource.getOTPToken(phoneNumber, otp)

    override fun getUserByIDToken(idToken: String): Single<DUser> =
        authDataSource.getUserByIDToken(idToken)

    override fun getPreferences(): Single<ListResponse<DPreference>> =
        authDataSource.getPreferences()

    override fun registerFirebaseToken(request: FirebaseToken): Single<DFirebaseToken> =
        authDataSource.registerFirebaseToken(request)

    override fun createAccount(registerRequest: RegisterRequest): Single<DUser> =
        authDataSource.createAccount(registerRequest)

    override fun addUserPreference(addUserPreferenceRequest: AddUserPreferenceRequest): Single<DUser> =
        authDataSource.addUserPreference(addUserPreferenceRequest)

    override fun requestWelcomeNotification(userId: String): Single<Success> =
        authDataSource.requestWelcomeNotification(userId)

}
