package sl.com.eightdigitz.authentication.domain.repository

import io.reactivex.Single
import okhttp3.MultipartBody
import sl.com.eightdigitz.client.apiSupports.models.FirebaseToken
import sl.com.eightdigitz.client.apiSupports.requests.AddUserPreferenceRequest
import sl.com.eightdigitz.client.apiSupports.requests.ContactUsRequest
import sl.com.eightdigitz.client.apiSupports.requests.JoinUsRequest
import sl.com.eightdigitz.client.apiSupports.requests.RegisterRequest
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.*

interface AuthRepository {

    fun uploadAvatar(image: MultipartBody.Part): Single<String>

    fun contactSupport(contactUsRequest: ContactUsRequest): Single<DContactUs>

    fun joinUs(joinUsRequest: JoinUsRequest): Single<DJoinUs>

    fun getOTP(phoneNumber: String): Single<DOTP>

    fun getOTPToken(phoneNumber: String, otp: String): Single<DOTPToken>

    fun getUserByIDToken(idToken: String): Single<DUser>

    fun getPreferences(): Single<ListResponse<DPreference>>

    fun registerFirebaseToken(request: FirebaseToken): Single<DFirebaseToken>

    fun createAccount(registerRequest: RegisterRequest): Single<DUser>

    fun addUserPreference(addUserPreferenceRequest: AddUserPreferenceRequest): Single<DUser>
}
