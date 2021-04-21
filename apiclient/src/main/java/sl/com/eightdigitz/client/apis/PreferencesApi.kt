package sl.com.eightdigitz.client.apis

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import sl.com.eightdigitz.client.apiSupports.requests.AddUserPreferenceRequest
import sl.com.eightdigitz.client.apiSupports.responses.PreferenceListResponse
import sl.com.eightdigitz.client.apiSupports.responses.UserListResponse
import sl.com.eightdigitz.client.apiSupports.responses.UserResponse

@JvmSuppressWildcards
interface PreferencesApi {

    @GET("preferences")
    fun getPreferences(
        @retrofit2.http.Query("language") language: String
    ): Single<PreferenceListResponse>

    @POST("user/preferences")
    @Headers(
        "Content-Type: application/x-www-form-urlencoded"
    )
    fun addUserPreference(
        @retrofit2.http.Header("x-id-token") idToken: String,
        @retrofit2.http.Body addUserPreferenceRequest: AddUserPreferenceRequest
    ): Single<UserResponse>


    @GET("talent/get-profile-list-by-category")
    fun getTalentsByPreference(
        @retrofit2.http.Header("x-id-token") idToken: String,
        @retrofit2.http.Query("preference-uuid") preferenceId: String,
        @retrofit2.http.Query("roles") role: String,
        @retrofit2.http.Query("search-keyword") searchKey: String?
    ): Single<UserListResponse>
}