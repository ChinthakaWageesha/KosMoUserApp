package sl.com.eightdigitz.client.apis

import io.reactivex.Single
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import sl.com.eightdigitz.client.apiSupports.responses.UserSearchResponse
import sl.com.eightdigitz.client.apiSupports.responses.UserListResponse
import sl.com.eightdigitz.client.apiSupports.requests.LogSearchRequest

@JvmSuppressWildcards
interface SearchHistoryApi {

    @GET("search-history/profile-views/{user-id}")
    fun getRecentSearchedTalents(
        @retrofit2.http.Path("user-id") userId: String
    ): Single<UserListResponse>

    @POST("search-history")
    fun logSearch(
        @retrofit2.http.Header("x-id-token") idToken: String,
        @retrofit2.http.Body logSearchRequest: LogSearchRequest
    ): Single<UserSearchResponse>

    @DELETE("search-history")
    fun removeRecentlyViewedProfile(
        @retrofit2.http.Header("x-id-token") idToken: String,
        @retrofit2.http.Query("owner-uuid") ownerId: String,
        @retrofit2.http.Query("user-uuid") userId: String,
        @retrofit2.http.Query("search-type") searchType: String
    ): Single<UserSearchResponse>
}