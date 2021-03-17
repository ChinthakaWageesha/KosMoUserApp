package sl.com.eightdigitz.app.data.datasource

import io.reactivex.Single
import okhttp3.MultipartBody
import sl.com.eightdigitz.client.apiSupports.requests.UpdateUserRequest
import sl.com.eightdigitz.core.model.domain.DUser

interface ProfileDataSource {

    fun uploadAvatar(image: MultipartBody.Part): Single<String>

    fun updateUser(request: UpdateUserRequest): Single<DUser>

}