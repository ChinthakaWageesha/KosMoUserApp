package sl.com.eightdigitz.client.apis

import io.reactivex.Single
import retrofit2.http.GET
import sl.com.eightdigitz.client.apiSupports.responses.UserListResponse

@JvmSuppressWildcards
interface ExploreApi {

    @GET("talent/get-profile-list-2-explore")
    fun getTalents(
        @retrofit2.http.Query("preference-uuid") preferenceIds: Array<String>? = null
    ): Single<UserListResponse>
}