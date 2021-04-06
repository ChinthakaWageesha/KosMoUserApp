package sl.com.eightdigitz.app.domain.repository

import io.reactivex.Single
import okhttp3.MultipartBody
import sl.com.eightdigitz.client.apiSupports.requests.AddUserPreferenceRequest
import sl.com.eightdigitz.client.apiSupports.requests.UpdateUserRequest
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.*

interface ProfileRepository {

    fun uploadAvatar(image: MultipartBody.Part): Single<String>

    fun updateUser(request: UpdateUserRequest): Single<DUser>

    fun getPreferences(): Single<ListResponse<DPreference>>

    fun setPreference(addUserPreferenceRequest: AddUserPreferenceRequest): Single<DUser>

    fun getOTP(phoneNumber: String): Single<DOTP>

    fun getOTPToken(phoneNumber: String, otp: String): Single<DOTPToken>

}