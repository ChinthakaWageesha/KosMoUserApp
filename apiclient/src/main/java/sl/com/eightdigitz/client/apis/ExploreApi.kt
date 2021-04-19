package sl.com.eightdigitz.client.apis

import io.reactivex.Single
import retrofit2.http.GET
import sl.com.eightdigitz.client.apiSupports.responses.UserListResponse

@JvmSuppressWildcards
interface ExploreApi {

    @GET("user/get-user-by-role/{role}")
    fun getTalents(
        @retrofit2.http.Path("role") role: String
    ): Single<UserListResponse>
}