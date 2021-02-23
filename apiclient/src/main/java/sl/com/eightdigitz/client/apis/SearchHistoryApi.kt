package sl.com.eightdigitz.client.apis

import io.reactivex.Single
import retrofit2.http.GET
import sl.com.eightdigitz.client.models.UserListResponse

@JvmSuppressWildcards
interface SearchHistoryApi {

    @GET("search-history/profile-views/{user-id}")
    fun getRecentSearchedTalents(
        @retrofit2.http.Path("user-id") userId: String
    ): Single<UserListResponse>
}