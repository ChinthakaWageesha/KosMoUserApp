package sl.com.eightdigitz.app.data.repository

import android.content.SharedPreferences
import sl.com.eightdigitz.app.domain.repository.ProfileRepository
import sl.com.eightdigitz.app.data.datasource.ProfileDataSource
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.extensions.get
import sl.com.eightdigitz.presentation.extensions.jsonStringMapTo
import sl.com.eightdigitz.presentation.extensions.setValue
import sl.com.eightdigitz.presentation.extensions.toJsonString
import io.reactivex.Single
import okhttp3.MultipartBody

class ProfileRepositoryImpl constructor(
    private val profileDataSource: ProfileDataSource,
    private val sharedPreferences: SharedPreferences
) : ProfileRepository {

    override fun updateProfile(dUser: DUser): Single<DUser> =
        profileDataSource.updateProfile(dUser).doOnSuccess {
            sharedPreferences.setValue(Constant.PREF_USER, it.toJsonString())
        }

    override fun getByToken(token: String?) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun getProfile(): Single<DUser> =
        profileDataSource.getProfile().doOnSuccess {
            sharedPreferences.setValue(Constant.PREF_USER, it.toJsonString())
        }

    override fun updateProfilePic(image: MultipartBody.Part): Single<DUser> =
        profileDataSource.updateProfilePic(image).doOnSuccess {
            sharedPreferences.setValue(Constant.PREF_USER, it.toJsonString())
        }

    override fun getUser(): DUser? = sharedPreferences.get<String>(Constant.PREF_USER)?.jsonStringMapTo<DUser>()
}
