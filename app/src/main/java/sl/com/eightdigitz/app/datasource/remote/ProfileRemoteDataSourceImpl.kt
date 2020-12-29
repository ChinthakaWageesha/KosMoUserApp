package sl.com.eightdigitz.app.datasource.remote

import sl.com.eightdigitz.app.data.datasource.ProfileDataSource
import sl.com.eightdigitz.client.apis.ProfileApi
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.mapToDomain
import io.reactivex.Single
import okhttp3.MultipartBody

class ProfileRemoteDataSourceImpl(
    private val profileApi: ProfileApi
) : ProfileDataSource {

    override fun updateProfile(dUser: DUser): Single<DUser> =
        profileApi.profilePutUpdate(dUser.mobileNo!!, dUser.email!!).map { it.payload?.mapToDomain() }

    override fun getProfile(): Single<DUser> =
        profileApi.profileGetMyProfile().map { it.payload?.mapToDomain() }

    override fun updateProfilePic(image: MultipartBody.Part): Single<DUser> =
        profileApi.profilePostUpdateMyAvatar(image).map {
            it.payload?.mapToDomain()
        }
}
