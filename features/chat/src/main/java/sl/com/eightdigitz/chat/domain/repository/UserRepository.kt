package sl.com.eightdigitz.chat.domain.repository


import sl.com.eightdigitz.chat.domain.model.UserResponse
import io.reactivex.Single

interface UserRepository{
    fun getUserList(query: String?, page: Int?, perPage: Int?): Single<UserResponse>
}