package sl.com.eightdigitz.app.data.repository

import io.reactivex.Single
import okhttp3.MultipartBody
import sl.com.eightdigitz.app.data.datasource.ProfileDataSource
import sl.com.eightdigitz.app.domain.repository.ProfileRepository
import sl.com.eightdigitz.client.apiSupports.requests.AddUserPreferenceRequest
import sl.com.eightdigitz.client.apiSupports.requests.UpdateUserRequest
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.domain.DUserPreference
import sl.com.eightdigitz.country_picker.domain.model.DCountry

class ProfileRepositoryImpl constructor(
    private val profileDataSource: ProfileDataSource
) : ProfileRepository {

    override fun uploadAvatar(image: MultipartBody.Part): Single<String> =
        profileDataSource.uploadAvatar(image)

    override fun updateUser(request: UpdateUserRequest): Single<DUser> =
        profileDataSource.updateUser(request)

    override fun getPreferences(): Single<ListResponse<DPreference>> =
        profileDataSource.getPreferences()

    override fun setPreference(addUserPreferenceRequest: AddUserPreferenceRequest): Single<DUser> =
        profileDataSource.setPreference(addUserPreferenceRequest)

}