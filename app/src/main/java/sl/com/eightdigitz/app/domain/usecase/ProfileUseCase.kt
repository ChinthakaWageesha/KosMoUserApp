package sl.com.eightdigitz.app.domain.usecase

import sl.com.eightdigitz.app.domain.repository.ProfileRepository
import sl.com.eightdigitz.core.domain.model.DUser
import io.reactivex.Single
import okhttp3.MultipartBody

class ProfileUseCase(private val profileRepository: ProfileRepository) {

    fun getProfile(): Single<DUser> = profileRepository.getProfile()

    fun updateProfile(dUSer: DUser): Single<DUser> = profileRepository.updateProfile(dUSer)

    fun updateProfilePic(image: MultipartBody.Part): Single<DUser> =
        profileRepository.updateProfilePic(image)

    fun getUser(): DUser? = profileRepository.getUser()
}
