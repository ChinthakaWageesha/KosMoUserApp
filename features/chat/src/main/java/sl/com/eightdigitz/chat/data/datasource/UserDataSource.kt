package sl.com.eightdigitz.chat.data.datasource


import sl.com.eightdigitz.chat.domain.model.UserResponse
import io.reactivex.Single

interface UserDataSource {
    fun getUserList(query: String?, page: Int?, perPage: Int?): Single<UserResponse>
}