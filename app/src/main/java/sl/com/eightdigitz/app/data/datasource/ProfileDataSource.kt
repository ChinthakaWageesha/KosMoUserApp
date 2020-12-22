package sl.com.eightdigitz.app.data.datasource

import sl.com.eightdigitz.core.model.domain.DUser
import io.reactivex.Single
import okhttp3.MultipartBody

interface ProfileDataSource {

    fun updateProfile(dUser: DUser): Single<DUser>

    fun getProfile(): Single<DUser>

    fun updateProfilePic(image: MultipartBody.Part): Single<DUser>
}
