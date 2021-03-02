package sl.com.eightdigitz.client.apis

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import sl.com.eightdigitz.client.apiSupports.requests.AddUserPreferenceRequest
import sl.com.eightdigitz.client.apiSupports.responses.PreferenceListResponse
import sl.com.eightdigitz.client.apiSupports.responses.UserListResponse
import sl.com.eightdigitz.client.apiSupports.responses.UserPreferenceResponse

@JvmSuppressWildcards
interface PreferencesApi {

    @GET("preferences")
    fun getPreferences(
        @retrofit2.http.Query("language") language: String
    ): Single<PreferenceListResponse>

    @POST("user/preferences")
    fun addUserPreference(
        @retrofit2.http.Header("x-id-token") idToken: String,
        @retrofit2.http.Body addUserPreferenceRequest: AddUserPreferenceRequest
    ): Single<UserPreferenceResponse>


    @GET("user/get-profile-list-by-preference")
    fun getTalentsByPreference(
        @retrofit2.http.Header("x-id-token") idToken: String,
        @retrofit2.http.Query("preference-uuid") preferenceId: String
    ): Single<UserListResponse>
}