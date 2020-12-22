package sl.com.eightdigitz.app.domain.repository

import sl.com.eightdigitz.core.model.domain.DUser
import io.reactivex.Single
import okhttp3.MultipartBody

interface ProfileRepository {

    fun getByToken(token: String?)

    fun updateProfile(dUser: DUser): Single<DUser>

    fun getProfile(): Single<DUser>

    fun updateProfilePic(image: MultipartBody.Part): Single<DUser>

    fun getUser(): DUser?
}
