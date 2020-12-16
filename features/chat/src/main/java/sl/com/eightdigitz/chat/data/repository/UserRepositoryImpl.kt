package sl.com.eightdigitz.chat.data.repository

import sl.com.eightdigitz.chat.data.datasource.UserDataSource
import sl.com.eightdigitz.chat.domain.model.UserResponse
import sl.com.eightdigitz.chat.domain.repository.UserRepository
import io.reactivex.Single

class UserRepositoryImpl constructor(private var userDataSource: UserDataSource) : UserRepository {
    override fun getUserList(query: String?, page: Int?, perPage: Int?): Single<UserResponse> =
        userDataSource.getUserList(query,page,perPage)
}