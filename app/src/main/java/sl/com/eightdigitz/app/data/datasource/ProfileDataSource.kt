package sl.com.eightdigitz.app.data.datasource

import io.reactivex.Single
import okhttp3.MultipartBody
import sl.com.eightdigitz.client.apiSupports.requests.AddUserPreferenceRequest
import sl.com.eightdigitz.client.apiSupports.requests.UpdateUserRequest
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.domain.DUserPreference

interface ProfileDataSource {

    fun uploadAvatar(image: MultipartBody.Part): Single<String>

    fun updateUser(request: UpdateUserRequest): Single<DUser>

    fun getPreferences(): Single<ListResponse<DPreference>>

    fun setPreference(addUserPreferenceRequest: AddUserPreferenceRequest): Single<DUserPreference>

}