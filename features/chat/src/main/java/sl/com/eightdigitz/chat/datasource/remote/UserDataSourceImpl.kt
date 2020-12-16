package sl.com.eightdigitz.chat.datasource.remote

import sl.com.eightdigitz.chat.data.datasource.UserDataSource
import sl.com.eightdigitz.chat.domain.model.UserResponse
import sl.com.eightdigitz.chat.domain.model.mapToDomain
import sl.com.eightdigitz.client.apis.UserApi
import io.reactivex.Single

class UserDataSourceImpl constructor(private var userApi: UserApi) : UserDataSource {
    override fun getUserList(query: String?, page: Int?, perPage: Int?): Single<UserResponse> =
        userApi.usersGet(query,page, perPage).map {data ->
            val userList = data.payload?.map { it.mapToDomain() }
            val response = UserResponse(userList!!, data.paginator?.mapToDomain())

            response
        }
}