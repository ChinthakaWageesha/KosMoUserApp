package sl.com.eightdigitz.chat.domain.usecase

import sl.com.eightdigitz.chat.domain.model.UserResponse
import sl.com.eightdigitz.chat.domain.repository.UserRepository
import io.reactivex.Single

class UserUseCase(private val userRepository: UserRepository) {
    fun getUserList(query: String?, page: Int?, perPage: Int?): Single<UserResponse> =
        userRepository.getUserList(query, page, perPage)
}