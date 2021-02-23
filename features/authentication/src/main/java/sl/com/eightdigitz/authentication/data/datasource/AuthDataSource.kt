package sl.com.eightdigitz.authentication.data.datasource

import io.reactivex.Single
import okhttp3.MultipartBody
import sl.com.eightdigitz.client.models.ContactUsRequest
import sl.com.eightdigitz.client.models.JoinUsRequest
import sl.com.eightdigitz.client.models.RegisterRequest
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.*

interface AuthDataSource {

    fun uploadAvatar(image: MultipartBody.Part) : Single<String>

    fun contactSupport(contactUsRequest: ContactUsRequest): Single<DContactUs>

    fun joinUs(joinUsRequest: JoinUsRequest): Single<DJoinUs>

    fun getOTP(phoneNumber: String): Single<DOTP>

    fun getOTPToken(phoneNumber: String, otp: String): Single<DOTPToken>

    fun getUserByIDToken(idToken: String) : Single<DUser>

    fun getPreferences() : Single<ListResponse<DPreference>>

    fun createAccount(registerRequest: RegisterRequest): Single<DUser>

}
